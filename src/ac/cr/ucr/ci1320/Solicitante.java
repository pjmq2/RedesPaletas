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
import java.io.IOException;

public class Solicitante extends Thread {

    String address;
    int port;
    int accion;
    Socket sock;
    BufferedReader reader;
    DataOutputStream writer;
    String response;
    Nodo nodo;
    String miIP;
    String message;
    Boolean isConnected = false;
    Analizador analizador;

    public Solicitante(Nodo node, String message, String addr, int por, String IP, Analizador analisis, int accion){
        this.nodo = node;
        this.port=por;
        this.address=addr;
        this.miIP = IP;
        this.message = message;
        this.accion = accion;
    }

    public void run(){
        try
        {
            sock = new Socket(address, port);
            InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamreader);
            writer = new DataOutputStream(sock.getOutputStream());
            String envio;
            Mensaje mensaje = new Mensaje(nodo.getFake(), address, accion, Integer.toString(nodo.getPort()));
            envio = mensaje.toString();
            writer.writeUTF(envio);
            writer.flush();

            try {
                sock.close();
                System.out.println("Client Socket Closed.");
            } catch (IOException e) { /* failed */ }
        }
        catch (Exception ex) {
            System.out.println("Message was not sent.\n");
            try {
                if(sock != null) {
                    sock.close();
                    System.out.println("Client Socket Closed.");
                }
            }
            catch (IOException e)
            { /* failed */ }
        }
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}