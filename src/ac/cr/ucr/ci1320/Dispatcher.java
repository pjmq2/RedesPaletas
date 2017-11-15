/*

Aun no funciona

 */

package ac.cr.ucr.ci1320;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import static java.lang.Integer.signum;
import static java.lang.Integer.valueOf;

public class Dispatcher {
    private HashMap<String,String> tablaIP;
    String localIP;
    int port;
    Nodo nodo;

    public Dispatcher(String fake1, String real1, String fake2, String real2, String fake3, String miIP, int backport, Nodo node) {
        this.tablaIP = new HashMap<>();
        this.tablaIP.put(fake1,real1); // P
        this.tablaIP.put(fake2,real2); // S
        this.tablaIP.put(fake3,miIP); // J
        this.localIP = miIP;
        this.port = backport;
        this.nodo = node;
    }

    public HashMap<String,String> BuildIPTable(String fake1, String real1, String fake2, String real2, String fake3, String real3) {
        HashMap<String,String> tablonIP = new HashMap<>();
        tablonIP.put(fake1,real1); // P
        tablonIP.put(fake2,real2); // S
        tablonIP.put(fake3,real3); // J
        return tablonIP;
    }

    public String getTablaIPString() {
        String returnValue = new String();
        returnValue = returnValue + "12.0.0.7=" + tablaIP.get("12.0.0.7") + "\n";
        returnValue = returnValue + "12.0.20.2=" + tablaIP.get("12.0.0.7") + "\n";
        returnValue = returnValue + "12.0.0.8=" + tablaIP.get("12.0.0.7");
        return returnValue;
    }

    public HashMap<String,String> getTablaIP() {
        return tablaIP;
    }

    public void iniciar() {
        Thread starter = new Thread(new Dispatcher.Starter(port));
        starter.start();
        System.out.println("\nDispatcher esperando...");
    }

    public class Starter implements Runnable
    {
        int puerto;

        public Starter(int backport) {
            puerto = backport;
        }

        public void run(){
            try
            {
                ServerSocket servidor = new ServerSocket(puerto);
                while (true){
                    Socket cliente = servidor.accept();
                    String clientIP = cliente.getRemoteSocketAddress().toString().split(":")[0];
                    String clientIPRevealed = clientIP.split("/")[1];
                    PrintWriter writer = new PrintWriter(cliente.getOutputStream());
                    Thread listener = new Thread(new Dispatcher.Manejador(cliente, clientIPRevealed));
                    listener.start();
                    System.out.println("\nConexión recibida, Dispatcher");
                }
            }
            catch (Exception ex)
            {
                System.out.println("\nERROR!!! Socket no pudo ser creado");
            }
        }
    }

    public class Manejador implements Runnable
    {
        BufferedReader reader;
        PrintWriter writer;
        Socket sock;
        String lastClientRealIP;

        public Manejador(Socket clientSocket, String clientFakeIP)
        {
            try
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                writer = new PrintWriter(sock.getOutputStream());
                lastClientRealIP = clientFakeIP;
            }
            catch (Exception ex)
            {
                System.out.println("ERROR!!!");
            }

        }

        @Override
        public void run()
        {
            try
            {
                DataInputStream outClient;
                outClient = new DataInputStream(sock.getInputStream());
                String mensaje = outClient.readUTF();

                // El mensaje debe ser el IP FALSO!!!

                String[] mensajeSeparado = mensaje.split("\\n");
                Mensaje objmensaje = new Mensaje(mensajeSeparado[0],mensajeSeparado[1],valueOf(mensajeSeparado[2]),mensajeSeparado[4]);
                String mensajeDespejado = objmensaje.getIpMensaje();
                String[] mensajeCantidad = mensajeDespejado.split("\\.");
                int numfin = mensajeCantidad.length;
                if(numfin == 4) {
                    boolean success = nodo.modifyIPTableEntry(mensajeDespejado, lastClientRealIP);

                    if (success == true) {
                        System.out.println("Se ha guardado " + mensajeDespejado + " con " + lastClientRealIP);
                    }
                    else
                    {
                        System.out.println("ERROR! Dirección falsa otorgada no existe");
                    }
                }
                else
                {
                    System.out.println("La dirección falsa asignada no sigue el formato adecuado");
                }
                lastClientRealIP = null;
            }
            catch (Exception ex)
            {
                System.out.println("Fallo envio");
            }
        }
    }
}
