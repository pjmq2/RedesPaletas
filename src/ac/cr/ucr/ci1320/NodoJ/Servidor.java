package ac.cr.ucr.ci1320.NodoJ;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor
{
    int portz;
    Nodo nodo;

    public Servidor(Nodo node) {
        this.portz = node.getmyPort();
        this.nodo = node;
    }

    public void iniciar() {
        Thread starter = new Thread(new Starter(nodo));
        starter.start();
        System.out.println("Servidor esperando...");
    }

    public class Starter implements Runnable
    {
        int puerto;
        public Starter(Nodo node) {
            puerto = node.getmyPort();
        }

        public void run(){
            try
            {
                ServerSocket servidor = new ServerSocket(puerto);
                while (true){
                    Socket cliente = servidor.accept();
                    String clientIP = cliente.getRemoteSocketAddress().toString().split(":")[0];
                    String clientIPRevealed = clientIP.split("/")[1];

                    Recividor sender = new Recividor();
                    sender.set(cliente, nodo, clientIPRevealed);
                    Thread listener = new Thread(sender);
                    listener.start();
                    System.out.println("Conexión recibida, Servidor");
                }
            }
            catch (Exception ex)
            {
                System.out.println("ERROR!!! Socket no pudo ser creado");
            }
        }
    }
}