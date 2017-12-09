/*

Aun no funciona

 */

package ac.cr.ucr.ci1320.Etapa3;

import ac.cr.ucr.ci1320.NodoJ.Recividor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dispatcher implements Runnable{
    private int port;
    private String myRealAddress;
    private String myFakeAddress;
    private Interfaz interfaz;

    public Dispatcher(Interfaz interfaz) {
        port = 5555;
        myRealAddress = "localhost";
        myFakeAddress = "165.8.6.25";
        this.interfaz = interfaz;
    }

    public void run(){
        try
        {
            ServerSocket servidor = new ServerSocket(port);
            while (true){
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
        catch (Exception ex)
        {
            System.out.println("ERROR!!! Socket no pudo ser creado");
        }
    }

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
                        if (success == true) {
                            System.out.println("Se ha guardado " + resultado[1] + " con " + resultado[0]);
                        } else {
                            System.out.println("ERROR! Dirección falsa otorgada no existe");
                        }
                    } else {
                        System.out.println("ERROR! El puerto debe ser un número");
                    }
                }
            }
        } else if ((envio.getIpMensaje()) != null && (envio.getIpMensaje()).matches("[-+]?\\d*\\.?\\d+")) {
            boolean success = this.modifyIPTableEntry(envio.getIpFuente(), lastClientRealIP, Integer.parseInt(envio.getIpMensaje()));
            if (success == true) {
                System.out.println("Se ha guardado " + envio.getIpFuente() + " con " + lastClientRealIP);
            } else {
                System.out.println("ERROR! Dirección falsa otorgada no existe");
            }
            String mensajeAEnviar = this.getTablaIPString();

            // Se lo manda a todos los que conoce
            Set<String> keys = this.interfaz.getTablaIP().keySet();
            String[] array = keys.toArray(new String[keys.size()]);
            for(int w = 0; w < array.length; ++w) {
                if(!(this.interfaz.getTablaIP().get(array[w]).getIpVerdadera().equals("0"))) {
                    TablaIp tabla = this.interfaz.getTablaIP().get(array[w]);
                    Mensaje mensaje = new Mensaje(this.myFakeAddress, array[w], 7, mensajeAEnviar);
                    SolicitanteLite sender = new SolicitanteLite(mensaje.toString(), tabla.getIpVerdadera(), tabla.getPuerto());
                    sender.start();
                }
            }
        } else {
            System.out.println("Este mensaje no debe ser manejado por el dispatcher");
        }
    }

    public String getTablaIPString() {
        String returnValue = new String();
        Set<String> keys = this.interfaz.getTablaIP().keySet();
        String[] array = keys.toArray(new String[keys.size()]);

        for(int i = 0; i < array.length; ++i) {
            if (!(this.interfaz.getTablaIP().get(array[i]).getIpVerdadera().equalsIgnoreCase("0"))) {
                if(!(returnValue.equals(""))) { returnValue = returnValue + "#"; }
                TablaIp tabla = this.interfaz.getTablaIP().get(array[i]);
                returnValue = returnValue + tabla.getIpVerdadera() + "," + array[i] + "," + tabla.getPuerto();
            }
        }
        return returnValue;
    }

    public boolean modifyIPTableEntry(String fake, String real, int port)
    {
        TablaIp faker = this.interfaz.getTablaIP().get(fake);
        if(faker == null)
        {
            return false;
        }
        else
        {
            faker.modifyipVerdadera(real);
            faker.modifypuerto(port);
            return true;
        }
    }
}
