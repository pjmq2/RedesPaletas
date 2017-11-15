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

public class Dispatcher {
    private HashMap<String,String> tablaIP;
    String localIP;
    int port;

    public Dispatcher(String miIP, int backport) {
        this.tablaIP = new HashMap<>();
        this.tablaIP.put("12.0.0.7","000.000.0.000"); // P
        this.tablaIP.put("12.0.20.2","000.000.0.000"); // S
        this.tablaIP.put("12.0.0.8","000.000.0.000"); // J
        this.tablaIP.put("12.0.0.8",miIP); // CON IP FALSA DE JULIÁN
        this.localIP = miIP;
        this.port = backport;
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
                    PrintWriter writer = new PrintWriter(cliente.getOutputStream());
                    Thread listener = new Thread(new Dispatcher.Manejador(cliente));
                    listener.start();
                    System.out.println("\nConexión recibida");
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

        public Manejador(Socket clientSocket)
        {
            try
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                writer = new PrintWriter(sock.getOutputStream());
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
                System.out.println(getTablaIPString());
            }
            catch (Exception ex)
            {
                System.out.println("Fallo envio");
            }
        }
    }
}
