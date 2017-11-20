package ac.cr.ucr.ci1320;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.io.DataOutputStream;
import java.io.IOException;

public class Solicitante extends Thread {

    String address;
    String fakeaddress;
    int port;
    int accion;
    Socket sock;
    BufferedReader reader;
    DataOutputStream writer;
    String response;
    Nodo nodo;
    String miIP;
    String message;

    public Solicitante(Nodo node, String message, String fakeaddr, int accion){
        this.nodo = node;
        this.miIP = nodo.getIP();
        this.message = message;
        this.accion = accion;
        this.fakeaddress = fakeaddr;
        address = nodo.getIPTable().get(fakeaddr);
        //address = fakeaddr;
        TablaDirecciones tabla = nodo.getDTable().get(fakeaddr);
        if(accion == 7) {
            this.port = tabla.getBackPuerto();
            //System.out.println("BackPuerto");
        }
        else
        {
            this.port =  tabla.getPuerto();
            //this.port = nodo.getPort();
        }
    }

    public void run(){
        try
        {
            sock = new Socket(address, port);
            InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamreader);
            writer = new DataOutputStream(sock.getOutputStream());
            writer.writeUTF(message);
            writer.flush();

            try {
                sock.close();
                System.out.println("Socket Cliente Cerrado.");
            } catch (IOException e) { System.out.println("ERROR"); }
        }
        catch (Exception ex) {
            System.out.println("Mensaje no fue enviado.");
            try {
                if(sock != null) {
                    sock.close();
                    System.out.println("Socket Cliente Cerrado.");
                }
            }
            catch (IOException e)
            { System.out.println("Socket Cliente Cerrado."); }
        }
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}