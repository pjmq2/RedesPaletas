package ac.cr.ucr.ci1320;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Interfaz {
    int portz;
    Nodo nodo;
    Analizador analizer;

    public Interfaz(Nodo node, int port, Analizador analizer) {
        this.portz = port;
        this.nodo = node;
        this.analizer = analizer;
    }

    public void iniciar() {
        Thread starter = new Thread(new Interfaz.Starter(nodo));
        starter.start();
        System.out.println("Servidor esperando...");
    }

    public class Starter implements Runnable
    {
        int puerto;
        public Starter(Nodo node) {
            puerto = node.getPort();
        }

        public void run(){
            try
            {
                ServerSocket servidor = new ServerSocket(puerto);
                while (true){
                    Socket cliente = servidor.accept();
                    String clientIP = cliente.getRemoteSocketAddress().toString().split(":")[0];
                    String clientIPRevealed = clientIP.split("/")[1];

                    // Checkear si hay espacio disponible
                    if(!(clientIPRevealed.equals(nodo.getIP()))) {
                        Recividor sender = new Recividor();
                        sender.set(cliente, nodo, clientIPRevealed);
                        Thread listener = new Thread(sender);
                        listener.start();
                        System.out.println("Conexión recibida, Servidor");
                    }
                }
            }
            catch (Exception ex)
            {
                System.out.println("ERROR!!! Socket no pudo ser creado");
            }
        }
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    // Imprime el mensaje en la terminal.
    private void imprimirMensaje(Mensaje mensaje){
        System.out.println(mensaje.getIpMensaje());
    }
}
