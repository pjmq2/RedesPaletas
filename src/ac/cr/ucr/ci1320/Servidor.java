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
                Paquete paquete = analizer.stringToPaquete(mensaje);
                casosDePaquetes(paquete, lastClientRealIP);
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
    private void casosDePaquetes(Paquete paquete, String lastClientRealIP){
        int accion = paquete.getMensaje().getAccion();
        Analizador analizer = nodo.getAnalizer(); //
        Mensaje envio = paquete.getMensaje();
        Solicitante solicitante; // Cliente
        switch (accion){
            default:
                Paquete paquete2=analizer.empaquetar(paquete.getMensaje());
                if(paquete.getMensaje().getIpDestino().equals(nodo.getIP())) imprimirMensaje(paquete.getMensaje());
                else{
                    String puertoz = nodo.getIPTable().get(paquete2.getIpDestinPaquete());
                    int puerto = Integer.parseInt(puertoz);
                    String ipReal = nodo.getIPTable().get(paquete2.getIpDestinPaquete());
                    solicitante = new Solicitante(this.nodo, paquete2.toString(), lastClientRealIP, Integer.parseInt(envio.getIpMensaje()), nodo.getIP(), nodo.getAnalizer(), 0);
                    solicitante.run();
                }

                break;
            case 1:
                if(paquete.getMensaje().getIpMensaje().equals(nodo.getIP())){
                    Paquete paquete1 = analizer.responder3(paquete.getMensaje().getIpFuente());
                    String puertoz = nodo.getIPTable().get(paquete1.getIpDestinPaquete());
                    int puerto = Integer.parseInt(puertoz); // ...
                    String ipReal = nodo.getIPTable().get(paquete1.getIpDestinPaquete());
                    solicitante = new Solicitante(this.nodo, paquete1.toString(), ipReal, puerto, nodo.getIP(), nodo.getAnalizer(), 1);
                    solicitante.run();
                }
                break;
            case 2:
                Paquete paquete1 = analizer.responder4(paquete.getMensaje().getIpFuente());

                String puertoz = nodo.getIPTable().get(paquete1.getIpDestinPaquete());
                int puerto = Integer.parseInt(puertoz); // ...

                String ipReal = nodo.getIPTable().get(paquete1.getIpDestinPaquete());
                solicitante = new Solicitante(this.nodo, paquete1.toString(), ipReal, puerto, nodo.getIP(), nodo.getAnalizer(), 7);
                solicitante.run();
                break;
            case 7:
                if(envio.getIpMensaje().contains("|") == true) {
                    String entradas[] = envio.getIpMensaje().split("\\|", -1);
                    int longitud = entradas.length;
                    for (int i = 0; i < longitud; ++i) {
                        String resultado[] = entradas[i].split(",");
                        if (isNumeric(resultado[2]) == true) {
                            int porte = Integer.parseInt(resultado[2]);
                            boolean success = nodo.modifyIPTableEntry(resultado[0], resultado[1], porte);
                            if (success == true) {
                                System.out.println("Se ha guardado " + resultado[0] + " con " + resultado[1]);
                            } else {
                                System.out.println("ERROR! Dirección falsa otorgada no existe");
                            }
                        } else {
                            System.out.println("ERROR! El puerto debe ser un número");
                        }
                    }
                }
                else if(isNumeric(envio.getIpMensaje()) == true) {
                    boolean success = nodo.modifyIPTableEntry(envio.getIpFuente(), lastClientRealIP, Integer.parseInt(envio.getIpMensaje()));
                    if (success == true) {
                        System.out.println("Se ha guardado " + envio.getIpFuente() + " con " + lastClientRealIP);
                    } else {
                        System.out.println("ERROR! Dirección falsa otorgada no existe");
                    }
                    String mensajeAEnviar = nodo.getTablaIPString();
                    solicitante = new Solicitante(this.nodo, mensajeAEnviar, lastClientRealIP, Integer.parseInt(envio.getIpMensaje()), nodo.getIP(), nodo.getAnalizer(), 7); // Address Port Menssage
                    solicitante.run();
                }
                else{
                    System.out.println("Este mensaje no debe ser manejado por el dispatcher");
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
