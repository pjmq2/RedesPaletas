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
import java.util.Map;

public class Dispatcher {
    private Map<String,String> tablaIP;

    public Dispatcher() {
        this.tablaIP = new HashMap<>();
        tablaIP.put("12.0.0.8","192.168.0.136");
        tablaIP.put("12.0.20.2","192.168.0.166");
        tablaIP.put("165.8.6.25","192.168.0.136");
        tablaIP.toString();
    }

    public Map<String, String> getTablaIP() {
        return tablaIP;
    }

    public void iniciar() {
        Thread starter = new Thread(new Dispatcher.Starter());
        starter.start();
        System.out.println("\nDispatcher esperando...");
    }

    public class Starter implements Runnable
    {
        public void run(){
            try
            {
                ServerSocket servidor = new ServerSocket(7777);
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
                System.out.println(mensaje);
            }
            catch (Exception ex)
            {
                System.out.println("Fallo envio");
            }
        }
    }
}
