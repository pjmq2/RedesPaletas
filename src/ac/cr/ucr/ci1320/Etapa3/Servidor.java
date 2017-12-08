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
 *
 * @author Selene
 */

public class Servidor
{
    int portz;
    Interfaz inter;
    public Servidor(Interfaz inter) {
        portz = inter.miPuerto;
        this.inter = inter;
    }

    public void iniciar() {
        Thread starter = new Thread(new Starter(inter));
        starter.start();
        System.out.println("\nServidor esperando...");
    }

    public class Starter implements Runnable{
        int puerto;
        public Starter(Interfaz inter) {
            puerto = inter.miPuerto;
        }

        public void run(){
            try
            {
                ServerSocket servidor = new ServerSocket(puerto);
                while (true){
                    Socket cliente = servidor.accept();
                    PrintWriter writer = new PrintWriter(cliente.getOutputStream());
                    Thread listener = new Thread(new Manejador(cliente, inter));
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
        Interfaz inter;

        public Manejador(Socket clientSocket, Interfaz inter)
        {
            this.inter = inter;
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
                String mensaje = outClient.readUTF(); //Lee los recibidos
                System.out.println(mensaje);
                //Thread buffing = new Thread(new InputThread(inter.dataStructures, mensaje));
            }
            catch (Exception ex)
            {
                System.out.println("Fallo envio");
            }
        }
    }
}