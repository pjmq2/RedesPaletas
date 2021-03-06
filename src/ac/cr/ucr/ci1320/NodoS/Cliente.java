package ac.cr.ucr.ci1320.NodoS;

import java.net.Socket;
import java.io.DataOutputStream;

/**
 *
 * @author Selene
 */
public class Cliente {

    String ipDestino;
    int puerto;
    Socket sock;
    DataOutputStream writer;

    public void sendMessage(String message, String ip, int port){
        puerto = port;
        ipDestino=ip;
        try{
            Thread writer = new Thread(new Writer(message,puerto,ipDestino));
            writer.start();
        }
        catch (Exception ex)
        {
            System.out.println("\nCannot Connect!");
        }
    }

    public class Writer extends Thread {
        String messages;
        int puertos;
        String ipDestinos;

        public Writer(String text, int number, String ip) {
            messages = text;
            puertos = number;
            ipDestinos = ip;
        }

        public void run(){
            try
            {
                sock = new Socket(ipDestinos, puertos);
                writer = new DataOutputStream(sock.getOutputStream());
                writer.writeUTF(messages);
                sock.close();
            }
            catch (Exception ex) {
                System.out.println("Message was not sent.\n");
            }
        }
    }
}