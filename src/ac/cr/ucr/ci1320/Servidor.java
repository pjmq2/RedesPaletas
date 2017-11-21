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

public class Servidor
{
    int portz;
    Nodo nodo;
    Analizador analizer;
    public Servidor(Nodo node, Analizador analizer) {
        this.portz = node.getPort();
        this.nodo = node;
        this.analizer = analizer;
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
                    Thread listener = new Thread(new Manejador(cliente, nodo, clientIPRevealed));
                    listener.start();
                    System.out.println("Conexión recibida, Servidor");
                }
            }
            catch (Exception ex)
            {
                System.out.println("ERROR!!! Socket no pudo ser creado");
            }
        }
    }

    public class Manejador implements Runnable
    {
        BufferedReader reader;
        PrintWriter writer;
        Socket sock;
        Nodo nodo;
        String lastClientRealIP;

        public Manejador(Socket clientSocket, Nodo node, String clientRealIP)
        {
            nodo = node;
            try
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                writer = new PrintWriter(sock.getOutputStream());
                lastClientRealIP = clientRealIP;
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
                if(mensaje.split("\\n").length == 4)
                {
                    Mensaje mensajer = new Mensaje(mensaje);
                    if(mensajer != null) {
                        String finale = mensajer.toString();
                        casosDePaquetes(finale, lastClientRealIP, true);
                    }
                    else
                    {
                        System.out.println("Mensaje no valido");
                    }
                }
                else if(mensaje.split("\\n").length >= 3)
                {
                    Paquete paquete = new Paquete(mensaje);
                    if(paquete != null) {
                        String finale = paquete.toString();
                        casosDePaquetes(finale, lastClientRealIP, false);
                    }
                    else
                    {
                        System.out.println("Paquete no valido");
                    }
                }
            }
            catch (Exception ex)
            {
                System.out.println("Fallo envio");
            }
        }
    }

    // Dependiendo de la acción:
    // 0 - Acción específica
    // 1 - Pregunta de ¿Conoce esta dirección IP?
    // 2 - Pregunta de ¿Conoce un camino hacia esta dirección IP?
    // OJO: Para las acciones 1 y 2 , el campo (D) debe contener la dirección IP a la que se refieren las preguntas.
    // 3 - Respuesta: Sí, conozco esa dirección IP, soy YO.
    // 4 - Respuesta: Sí, conozco un camino hacia esa dirección IP.
    // packmess : False = Paquete - True = Mensaje
    private void casosDePaquetes(String stringpaquete, String lastClientRealIP, boolean packmess){
        Paquete paquete;
        Mensaje envio = null;
        int accion = -1;
        if(stringpaquete.split("\\n").length >= 4 && packmess == true)
        {
            envio = new Mensaje(stringpaquete);
            accion = envio.getAccion();
        }
        else if(stringpaquete.split("\\n").length >= 3 && packmess == false)
        {
            paquete = new Paquete(stringpaquete);
            envio = paquete.getMensaje();
            accion = envio.getAccion();
        }
        if(envio != null) {
            Analizador analizer = nodo.getAnalizer(); //

            Solicitante solicitante; // Cliente
            switch (accion) {
                case 0:
                    String recivido = envio.getIpMensaje();
                    System.out.println(recivido);
                    break;
                // Distancia recivida;
                case 4:
                    if (isNumeric(envio.getIpMensaje()) == true)
                    {
                        int distancia = Integer.parseInt(envio.getIpMensaje());
                        String fakeR = nodo.getWished(); // Indeciso
                        if(!(fakeR.equals(""))) {
                            TablaDirecciones TD = nodo.getDTable().get(fakeR);
                            boolean success = TD.setNew(fakeR, distancia);
                            if (success == true) {
                                System.out.println("Mensajes ahora se enviarán por " + fakeR + ".");
                            } else {
                                System.out.println("Distancia por " + fakeR + " no era mejor que la ya establecida.");
                            }
                        }
                        else{
                            System.out.println("Respuesta accion 4 diatancia " + envio.getIpMensaje() + " ha sido ignarada.");
                        }
                    }
                    else
                    {
                        System.out.println("ERROR! El puerto debe ser un número");
                    }
                    break;
                case 7:
                    if (envio.getIpMensaje().contains("#") == true) {
                        String entradas[] = envio.getIpMensaje().split("#", -1);
                        int longitud = entradas.length;
                        for (int i = 0; i < longitud; ++i) {
                            if(!(entradas[i].equals(""))) {
                                String resultado[] = entradas[i].split(",");
                                if (isNumeric(resultado[2]) == true) {
                                    int porte = Integer.parseInt(resultado[2]);
                                    boolean success = nodo.modifyIPTableEntry(resultado[1], resultado[0], porte);
                                    if(success == true) {
                                        success = nodo.getDTable().get(resultado[1]).setNew(resultado[1], 0);
                                    }
                                    if (success == true) {
                                        System.out.println("Se ha guardado " + resultado[1] + " con " + resultado[0]);
                                    } else {
                                        System.out.println("ERROR! Dirección falsa otorgada no existe");
                                    }
                                } else {
                                    System.out.println("ERROR! El puerto debe ser un número");
                                }
                            }
                        }
                    } else if (isNumeric(envio.getIpMensaje()) == true) {
                        boolean success = nodo.modifyIPTableEntry(envio.getIpFuente(), lastClientRealIP, Integer.parseInt(envio.getIpMensaje()));
                        if (success == true) {
                            System.out.println("Se ha guardado " + envio.getIpFuente() + " con " + lastClientRealIP);
                        } else {
                            System.out.println("ERROR! Dirección falsa otorgada no existe");
                        }
                        String mensajeAEnviar = nodo.getTablaIPString();
                        Mensaje mensaje = new Mensaje(nodo.getFake(), envio.getIpFuente(), 7, mensajeAEnviar);
                        String envioFinal = mensaje.toString();
                        solicitante = new Solicitante(this.nodo, envioFinal, envio.getIpFuente(), 7); // OJO QUE SI LLEGA AQUI CON UN FAKE QUE NO SEA J, A, S ó P VA A EXPLOTAR!!!
                        solicitante.run();
                    } else {
                        System.out.println("Este mensaje no debe ser manejado por el dispatcher");
                    }
                    break;
            }
        }
        else
        {
            System.out.println("Error del servidor");
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
