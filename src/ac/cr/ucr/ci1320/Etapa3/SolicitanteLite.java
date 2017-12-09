package ac.cr.ucr.ci1320.Etapa3;

import java.io.DataOutputStream;
import java.net.Socket;

public class SolicitanteLite extends Thread {
    String message;
    String address;
    int port;

    public SolicitanteLite(String m, String a, int p){
        this.message = m;
        this.address = a;
        this.port = p;
    }

    @Override
    public void run() {
        try {
            Socket sock = new Socket(address, port);
            DataOutputStream writer = new DataOutputStream(sock.getOutputStream());
            writer.writeUTF(message);
            writer.flush();
            sock.close();
        } catch (Exception ex) {
            System.out.println("Mensaje no fue enviado.");
        }
    }
}
