/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucr.ci1320;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Selene
 */

public class Servidor
{

    Nodo nodo;
    int portz;
    public Servidor(Nodo nodo) {
        this.nodo = nodo;
        portz = nodo.miPuerto;
    }




    public void iniciar() {
        Thread starter = new Thread(new Starter());
        starter.start();
        System.out.println("\nServidor esperando...");
    }

    public class Starter implements Runnable
    {
        public void run(){
            try
            {
                ServerSocket servidor = new ServerSocket(portz);
                while (true){
                    Socket cliente = servidor.accept();
                    PrintWriter writer = new PrintWriter(cliente.getOutputStream());
                    Thread listener = new Thread(new Manejador(cliente,nodo));
                    listener.start();
                    System.out.println("\nConexión recibida");
                }
            }
            catch (Exception ex)
            {
                System.out.println("\nERROR!!! Socket no pudo ser creado");
            }
        }
    }

    public class Manejador implements Runnable
    {
        BufferedReader reader;
        PrintWriter writer;
        Socket sock;

        Nodo nodo;

        public Manejador(Socket clientSocket, Nodo nod)
        {
            nodo = nod;
            try
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                writer = new PrintWriter(sock.getOutputStream());
            }
            catch (Exception ex)
            {
                System.out.println("ERROR!!!");
            }

        }

        @Override
        public void run()
        {
            try
            {
                DataInputStream outClient;
                outClient = new DataInputStream(sock.getInputStream());
                String mensaje = outClient.readUTF();
                System.out.println(mensaje);
                nodo.recibirTransmicion(mensaje);
            }
            catch (Exception ex)
            {
                System.out.println("Fallo envio");
            }
        }
    }

}