package ac.cr.ucr.ci1320.NodoJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Set;

public class Solicitante extends Thread {
    String targetfakeaddress;
    int port;
    int accion;
    Socket sock;
    BufferedReader reader;
    DataOutputStream writer;
    Nodo nodo;
    String miIP;
    Mensaje message;

    public Solicitante(Nodo node, Mensaje message){
        this.nodo = node;
        this.miIP = nodo.getmyRealIP();
        this.message = message;
        if(message != null) {
            this.accion = message.getAccion();
            this.targetfakeaddress = message.getIpDestino();
        }
        TablaDirecciones tabla = nodo.getDTable().get(this.targetfakeaddress);
        if(accion == 7) {
            this.port = tabla.getBackPuerto();
        }
        else
        {
            this.port =  tabla.getPuerto();
        }
    }

    public void run(){

        // Obtener el "ATravez"

        boolean success = true;

        TablaDirecciones tdir = nodo.getDTable().get(targetfakeaddress);
        if (tdir == null) {
            System.out.println("Dirección IP inválida");
        } else {
            // FALTA HACER QUE ELIJA LA DIRECCIÓN ADECUADA, Y QUE HAGA LO QUE OCUPE SI NO LA TIENE.
            // El solicitante del solicitante debería intentar a un enrutador, dirección que ya recibió vía broadcast.

            if(tdir.getDistancia() == -1)
            {
                this.nodo.wish(targetfakeaddress);
                Set<String> keys = nodo.getIPTable().keySet();
                String[] fakes = keys.toArray(new String[keys.size()]); // Arreglo de las falsas de J, P, A y S
                for(int i = 0; i < fakes.length; ++i) {
                    // Preguntar la distancia a cada uno.

                    Mensaje mensaje = new Mensaje(nodo.getmyFakeAddress(), fakes[i], 2, targetfakeaddress);
                    String routerTrueaddress = nodo.getIPTable().get(fakes[i]);

                    if(!(routerTrueaddress.equals("0"))) {
                        // Enviar la pregunta

                        Solicitante solicitante = new Solicitante(nodo, mensaje); // Address Port Menssage
                        solicitante.run();
                    }
                    else
                    {
                        // No puedo enviarle la pregunta porque no tengo el IP...
                        success = false;
                    }
                }

                // Espera hasta que el servidor reciba la respuesta y la guarde.
                String wish = nodo.getWish();
            }
        }

        if((success == true) && (-1 < tdir.getDistancia())) {
            try {
                // Empaqueta
                Paquete pack = new Paquete(message, nodo.getmyFakeAddress(), tdir.getaTraves());
                String address = nodo.getIPTable().get(tdir.getaTraves());
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new DataOutputStream(sock.getOutputStream());
                writer.writeUTF(pack.toString());
                writer.flush();

                try {
                    sock.close();
                    System.out.println("Socket Cliente Cerrado.");
                } catch (IOException e) {
                    System.out.println("ERROR");
                }
            } catch (Exception ex) {
                System.out.println("Mensaje no fue enviado.");
                try {
                    if (sock != null) {
                        sock.close();
                        System.out.println("Socket Cliente Cerrado.");
                    }
                } catch (IOException e) {
                    System.out.println("Socket Cliente Cerrado.");
                }
            }
        }
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}