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

    public void iniciar(String addr[], int por[], String message){
        port = por;
        address = addr;
        response = new String[addr.length];

        try
        {
            for(int i = 0; i < address.length; ++i)
            {
                Thread writer = new Thread(new Writer(message, i));
                writer.start();
            }
        }
        catch (Exception ex)
        {
            System.out.println("\nCannot Connect!");
        }
    }

    public void sendMessage(String message,String addr, int por){
        port = new int[1];
        port[0]=por;
        address = new String[1];
        address[0]=addr;
        try{
            Thread writer = new Thread(new Writer(message, 0));
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

        public Writer(String text, int number) {
            message = text;
            integer = number;
        }

        public void run(){
            try
            {
                sock = new Socket(address[integer], port[integer]);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new DataOutputStream(sock.getOutputStream());
                writer.writeUTF(message);
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
