/*

Aun no funciona

 */

package ac.cr.ucr.ci1320.Etapa3;

import ac.cr.ucr.ci1320.NodoJ.Recividor;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dispatcher implements Runnable{
    private int port;
    private String myRealAddress;
    private String myFakeAddress;
    private Map<String, TablaIp> tablaIp;


    /**
     * Dispatcher class for a particular network, works by listening other network components,
     * storing their data and sharing it with other net components
     * @param tablaIp data to be stored
     * @param fip Fake dispatcher ip
     */
    public Dispatcher(Map<String, TablaIp> tablaIp, String fip) {
        port = 4444;
        InetAddress ipAddr;
        try {
            ipAddr = InetAddress.getLocalHost();
            myRealAddress = ipAddr.getHostAddress();
            myFakeAddress = fip;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        this.tablaIp = tablaIp;
    }


    /**
     *  Method called when start() is called to the desired instance
     */

    public void run(){
        try{
            ServerSocket servidor = new ServerSocket(port);
            while (true){
                System.out.println("\nDispatcher esperando...");

                Socket cliente = servidor.accept();
                String clientIP = cliente.getRemoteSocketAddress().toString().split(":")[0];
                String clientIPRevealed = clientIP.split("/")[1];
                DataInputStream outClient;
                outClient = new DataInputStream(cliente.getInputStream());
                String mensaje = outClient.readUTF();
                if (mensaje.split("\\n").length == 4) {
                    Mensaje mensajer = new Mensaje(mensaje);
                    if (mensajer != null) {
                        dispatch(mensajer, clientIPRevealed);
                    } else {
                        System.out.println("Mensaje no valido");
                    }
                } else {
                    System.out.println("Mensaje no valido");
                }
                System.out.println("Conexión recibida, Servidor");
            }
        }
        catch (Exception ex){
            System.out.println("ERROR!!! Socket no pudo ser creado");
        }
    }

    /**
     * If the input is an ipTable, update mine, if not then store that input and update everyone's ip table by sending them a message
     * @param envio input message
     * @param lastClientRealIP controIP needed in some cases
     */
    public void dispatch(Mensaje envio, String lastClientRealIP) {
        if (envio.getIpMensaje().contains("#") == true) {
            String entradas[] = envio.getIpMensaje().split("#", -1);
            int longitud = entradas.length;
            for (int i = 0; i < longitud; ++i) {
                if(!(entradas[i].equals(""))) {
                    String resultado[] = entradas[i].split(",");
                    if ((resultado[2]) != null && (resultado[2]).matches("[-+]?\\d*\\.?\\d+")) {
                        int porte = Integer.parseInt(resultado[2]);
                        boolean success = this.modifyIPTableEntry(resultado[1], resultado[0], porte);
                        if (success) {
                            System.out.println("Se ha actualizado " + resultado[1] + " con " + resultado[0]);
                        } else {
                            System.out.println("Se ha guardado " + resultado[1] + " con " + resultado[0]);
                        }
                    } else {
                        System.out.println("ERROR! El puerto debe ser un número");
                    }
                }
            }
        } else if ((envio.getIpMensaje()) != null && (envio.getIpMensaje()).matches("[-+]?\\d*\\.?\\d+")) {
            boolean success = this.modifyIPTableEntry(envio.getIpFuente(), lastClientRealIP, Integer.parseInt(envio.getIpMensaje()));
            if (success == true) {
                System.out.println("Se ha actualizado: " + envio.getIpFuente() + " con " + lastClientRealIP);
            } else {
                System.out.println("Se ha guardado " + envio.getIpFuente() + " con " + lastClientRealIP);
            }
            String mensajeAEnviar = this.getTablaIPString(); //Aca se pone lo raro

            // Se lo manda a todos los que conoce
            Set<String> keys = this.tablaIp.keySet();
            String[] array = keys.toArray(new String[keys.size()]); //Devuelve un array que contiene todos los elementos del set
            for(int w = 0; w < array.length; ++w) { //Podria ponerle a array.length un -1 para que no se cuente a sí mismo
                if(!(this.tablaIp.get(array[w]).getIpVerdadera().equals("0"))) {
                    TablaIp tabla = this.tablaIp.get(array[w]);
                    Mensaje mensaje = new Mensaje(this.myFakeAddress, array[w], 7, mensajeAEnviar);
                    String rip = tabla.getIpVerdadera();
                    int port = tabla.getPuerto();
                    SolicitanteLite sender = new SolicitanteLite(mensaje.toString(), rip, port);
                    sender.start();
                }
            }
        } else {
            System.out.println("Este mensaje no debe ser manejado por el dispatcher");
        }
    }

    /**
     * Takes a fake ip and modifies its entry on my own iptable
     * @param fake fake input ip
     * @param real real input ip
     * @param portz real input port
     * @return
     */
    public boolean modifyIPTableEntry(String fake, String real, int portz){
        TablaIp faker = tablaIp.get(fake);
        if(faker == null){
            //return false;
            tablaIp.put(fake, new TablaIp(real, portz));
            return false;
        }
        else{
            faker.modifyipVerdadera(real);
            faker.modifypuerto(portz);
            return true;
        }
    }

    /**
     * Return
     * @return
     */
    public String getTablaIPString() {
        String returnValue = new String();
        Set<String> keys = tablaIp.keySet();
        String[] array = keys.toArray(new String[keys.size()]);

        for(int i = 0; i < array.length; ++i) {
            if (!(tablaIp.get(array[i]).getIpVerdadera().equalsIgnoreCase("0"))) {
                if(!(returnValue.equals(""))) { returnValue = returnValue + "#"; }
                returnValue = returnValue + tablaIp.get(array[i]) + "," + array[i] + "," + tablaIp.get(array[i]).getPuerto(); //Aca esta el error!
            }
        }
        return returnValue
                ;
    }
}
