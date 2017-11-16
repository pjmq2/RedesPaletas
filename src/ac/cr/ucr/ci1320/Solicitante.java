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

public class Solicitante extends Thread {

    String address;
    int port;
    Socket sock;
    BufferedReader reader;
    DataOutputStream writer;
    String response;
    Nodo nodo;
    String miIP;
    String message;
    Boolean isConnected = false;
    Analizador analizador;

    public Solicitante(Nodo node, String message, String addr, int por, String IP, Analizador analisis){
        this.nodo = node;
        this.port=por;
        this.address=addr;
        this.miIP = IP;
        this.message = message;
    }

    public void run(){
        try
        {
            sock = new Socket(address, port);
            InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamreader);
            writer = new DataOutputStream(sock.getOutputStream());
            String envio;

            if(message.equalsIgnoreCase("DISPATCH"))
            {
                envio = Integer.toString(nodo.getPort()) + "," + nodo.getFake();
            }
            else {
                Mensaje mensaje = new Mensaje(miIP, address, 0, message);
                envio = mensaje.toString();
            }

            writer.writeUTF(envio);
            writer.flush();
            if (envio.equalsIgnoreCase("DISPATCH")) {
                response = reader.readLine();
                String entradas[] = response.split("|");
                int longitud = entradas.length;
                for(int i = 0; i < longitud; ++i) {
                    String resultado[] = entradas[i].split(",");
                    if(isNumeric(resultado[2]) == true) {
                        int porte = Integer.parseInt(resultado[2]);
                        boolean success = nodo.modifyIPTableEntry(resultado[1], resultado[0], porte);
                        if (success == true) {
                            System.out.println("Se ha guardado " + resultado[1] + " con " + resultado[0]);
                        } else {
                            System.out.println("ERROR! Dirección falsa otorgada no existe");
                        }
                    }
                    else
                    {
                        System.out.println("ERROR! El puerto debe ser un número");
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Message was not sent.\n");
        }
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}