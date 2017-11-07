package ac.cr.ucr.ci1320;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Connection {
    private int PORT;
    private String HOST;
    protected String serverMessage;
    protected ServerSocket ss;
    protected Socket cs;
    protected DataOutputStream outServer;
    protected DataInputStream outClient;

    public Connection(){}

    public void createSocket(String tipo, int PORT, String HOST) throws IOException{
        this.PORT = PORT;
        this.HOST = HOST;
        if (tipo.equalsIgnoreCase("server")) {
            this.ss = new ServerSocket(this.PORT);
            this.cs = new Socket();
        }
        else {
            this.cs = new Socket(HOST, PORT);
        }
    }
}