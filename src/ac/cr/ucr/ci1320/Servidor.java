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
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Servidor
{
    int portz;
    Nodo nodo;
    Analizador analizer;
    Enrutador enrutador;
    public Servidor(Nodo node, Analizador analizer, Enrutador enrutador) {
        this.portz = node.getPort();
        this.nodo = node;
        this.analizer = analizer;
        this.enrutador = enrutador;
    }

    public void iniciar() {
        Thread starter = new Thread(new Starter(nodo));
        starter.start();
        System.out.println("Servidor esperando...");
    }

    public class Starter implements Runnable
    {
        int puerto;
        public Starter(Nodo node) {
            puerto = node.getPort();
        }

        public void run(){
            try
            {
                ServerSocket servidor = new ServerSocket(puerto);
                while (true){
                    Socket cliente = servidor.accept();
                    String clientIP = cliente.getRemoteSocketAddress().toString().split(":")[0];
                    String clientIPRevealed = clientIP.split("/")[1];
                    int i = enrutador.getaInter();
                    if (i < 0) {
                        Interfaz inter = enrutador.getInters(i);
                        inter.set(cliente, nodo, clientIPRevealed);
                        Thread listener = new Thread(inter);
                        listener.start();
                        System.out.println("Conexión recibida, Servidor");
                    }
                    else {
                        System.out.println("No hay interfaces disponibles");
                    }
                }
            }
            catch (Exception ex)
            {
                System.out.println("ERROR!!! Socket no pudo ser creado");
            }
        }
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    // Imprime el mensaje en la terminal.
    private void imprimirMensaje(Mensaje mensaje){
        System.out.println(mensaje.getIpMensaje());
    }
}
