/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucr.ci1320;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.io.DataOutputStream;

/**
 *
 * @author Selene
 */
public class Solicitante {

    String[] address;
    int port[];
    Boolean isConnected = false;
    Socket sock;
    BufferedReader reader;
    DataOutputStream writer;
    String response[];
    Analizador analizador;
    String miIP;

    public void iniciar(String addr[], int por[], String message, String IP, Analizador analisis){
        port = por;
        address = addr;
        response = new String[addr.length];
        analizador = analisis;
        miIP = IP;
        try
        {
            for(int i = 0; i < address.length; ++i)
            {
                Thread writer = new Thread(new Writer(message, i,analizador));
                writer.start();
            }
        }
        catch (Exception ex)
        {
            System.out.println("\nCannot Connect!");
        }
    }

    public void sendMessage(String message,String addr, int por, String IP, Analizador analisis){
        port = new int[1];
        port[0]=por;
        address = new String[1];
        address[0]=addr;
        miIP = IP;
        try{
            Thread writer = new Thread(new Writer(message, 0, analisis));
            writer.start();
        }
        catch (Exception ex)
        {
            System.out.println("\nCannot Connect!");
        }
    }

    public class Writer extends Thread {
        String message;
        int integer;
        Analizador analizador;

        public Writer(String text, int number, Analizador analisis) {
            message = text;
            integer = number;
            analizador = analisis;
        }

        public void run(){
            try
            {
                sock = new Socket(address[integer], port[integer]);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new DataOutputStream(sock.getOutputStream());
                Mensaje mensaje = new Mensaje(miIP, address[integer], 0, message);
                String envio = mensaje.toString();
                writer.writeUTF(envio);
                writer.flush();
                if (message.equalsIgnoreCase("Dispatch")){
                    response[integer] = reader.readLine();
                }
            }
            catch (Exception ex) {
                System.out.println("Message was not sent.\n");
            }
        }
    }
}