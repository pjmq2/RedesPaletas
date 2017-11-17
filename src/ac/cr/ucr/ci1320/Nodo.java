package ac.cr.ucr.ci1320;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.valueOf;

public class Nodo {
    private Map<String,TablaDirecciones> tablaD ;
    private TablaIp tablaIP;
    private Dispatcher dispon;
    private String miIp;
    private String miIpFalsa;
    private int miPuerto;
    private int backlogport;
    private Analizador analisis;
    private Servidor server;
    private String Alonso;

    public Nodo(Map<String, TablaDirecciones> tablaD, String miIp, int miPuerto, HashMap<String,String>tablaIP, String fake1, String fake2, String fake3, String fake4, int backport) {
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
        this.tablaIP = tablaIP;
        this.analisis = new Analizador(tablaD, tablaIP, miIp);
        this.server = new Servidor(this);
        this.dispon = new Dispatcher(fake1, this.tablaIP.get(fake1), fake2, this.tablaIP.get(fake2), fake3, this.tablaIP.get(fake3), fake4, this.tablaIP.get(fake4), backport, this);
        this.backlogport = backport;
        this.miIpFalsa = fake3;
        this.Alonso = fake4;
    }

    public String getIP()
    {
        return this.miIp;
    }

    public int getPort()
    {
        return this.miPuerto;
    }

    public String getFake()
    {
        return this.miIpFalsa;
    }

    public Analizador getAnalizer() { return this.analisis; }

    public boolean modifyIPTableEntry(String fake, String real, int port)
    {
        String faker = tablaIP.get(fake);
        if(faker == null)
        {
            return false;
        }
        else
        {
            tablaIP.put(fake, real);
            TablaDirecciones tabla = tablaD.get(fake);
            tabla.modifyPort(port);
            return true;
        }
    }

    public void iniciar()
    {
        // Abrir Servidor

        server.iniciar();

        // Abrir el Dispatcher

        dispon.iniciar();

        // Leer línea de la terminal.

        this.terminal();
    }

    public String getTablaIPString() {
        String returnValue = new String();
        Set<String> keys = tablaIP.keySet();
        String[] array = keys.toArray(new String[keys.size()]);

        for(int i = 0; i < array.length; ++i) {
            if (!(tablaIP.get(array[i]).equalsIgnoreCase("0"))) {
                if(!(returnValue.equals(""))) { returnValue = returnValue + "|"; }
                returnValue = returnValue + array[i] + "," + tablaIP.get(array[i]) + "," + tablaD.get(array[i]).getPuerto();
            }
        }
        return returnValue;
    }

    public void terminal() {
        System.out.println("Mensaje -> IPDESTINO \\n MENSAJE / Dispatcher -> DISPATCH"); // Hay que cambiar to.do esto para que en vez de ser IPDESTINO \n MENSAJE sea IPDESTINO \n PUERTO \n MENSAJE porque el dispatcher y el puerto correran en puertos distintos AÚN NO HE ACABADO, sigo a las 9 [Cuando llego a la casa]
        Scanner scanner = new Scanner(System.in);
        String entrada = "";
        while (!(entrada.equals("BYE"))) {
            entrada = scanner.nextLine();
            if (!(entrada.equals("BYE"))) {
                String[] array = entrada.split("\\\\n");
                // Enviar el mensaje
                Solicitante solicitante;

                if (array.length == 2) {
                    TablaDirecciones tdir = tablaD.get(array[0]);
                    if (tdir == null) {
                        System.out.println("Dirección IP inválida");
                    } else {
                        // FALTA HACER QUE ELIJA LA DIRECCIÓN ADECUADA, Y QUE HAGA LO QUE OCUPE SI NO LA TIENE.

                        String ipDestino = tdir.getaTraves();
                        String direccionReal = tablaIP.get(ipDestino); //Hacer un condicional para que revize si ipDestino existe en la tabla, si no, abajo podría dar null pointer
                        TablaDirecciones tabla = tablaD.get(array[0]); // FALTA EXCEPCION!!! [RED ALARM]
                        int porte = 0000;
                        TablaDirecciones tabla2 = tablaD.get(ipDestino);
                        porte = tabla2.getPuerto();
                        String mensajeAEnviar = array[1];
                        solicitante = new Solicitante(this, mensajeAEnviar, direccionReal, porte, miIp, analisis, 7); // Address Port Menssage
                        solicitante.run();
                    }
                } else if (entrada.equalsIgnoreCase("DISPATCH")) {
                    String direccionReal = tablaIP.get(Alonso);
                    TablaDirecciones tabla = tablaD.get(Alonso);
                    int porte = tabla.getPuerto();
                    solicitante = new Solicitante(this, Integer.toString(miPuerto), direccionReal, porte, miIp, analisis, 7); // Address Port Menssage
                    solicitante.run();
                } else {
                    System.out.println("Mensaje Inválido");
                }
            }
        }
    }

    // Imprime el mensaje en la terminal.
    private void imprimirMensaje(Mensaje mensaje){
        System.out.println(mensaje.getIpMensaje());
    }
}
