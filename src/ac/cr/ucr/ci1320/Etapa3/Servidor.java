/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucr.ci1320.Etapa3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Represent a server that contains a socket that listents for incoming connections
 */
public class Servidor
{
    int portz;
    Interfaz inter;
    public Servidor(Interfaz inter) {
        this.portz = inter.getMiPuerto();
        this.inter = inter;
    }

    public void iniciar() {
        //Thread starter = new Thread(new Starter(inter));
        //starter.start();
        try {
            ServerSocket servidor = new ServerSocket(this.inter.getMiPuerto());
            while (true){
                System.out.println("\nServidor de " +  inter.getMyFakeAddress() + " esperando...");

                Socket cliente = servidor.accept();
                String clientIP = cliente.getRemoteSocketAddress().toString().split(":")[0];
                String clientIPRevealed = clientIP.split("/")[1];

                try {
                    DataInputStream outClient;
                    outClient = new DataInputStream(cliente.getInputStream());
                    String mensaje = outClient.readUTF(); //Lee los recibidos
                    System.out.println(mensaje);

                    Thread buffing = new Thread(new InputThread(inter.getDataStructures(), mensaje));
                    buffing.start();

                    System.out.println("\nConexión recibida");
                }
                catch (Exception ex)
                {
                    System.out.println("Fallo envio");
                }
            }
        }
        catch (Exception ex){
            System.out.println("\nERROR!!! Socket no pudo ser creado");
        }
    }
}