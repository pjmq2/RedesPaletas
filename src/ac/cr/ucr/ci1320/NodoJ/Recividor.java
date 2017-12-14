package ac.cr.ucr.ci1320.NodoJ;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

public class Recividor implements Runnable {
    boolean set = false;

    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    Nodo nodo;
    String lastClientRealIP;

    public Recividor() {
    }

    public void set(Socket clientSocket, Nodo node, String clientRealIP) {
        nodo = node;
        try
        {
            sock = clientSocket;
            InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(isReader);
            writer = new PrintWriter(sock.getOutputStream());
            lastClientRealIP = clientRealIP;
            set = true;
        }
        catch (Exception ex)
        {
            System.out.println("ERROR!!!");
            set = false;
        }
    }

    @Override
    public void run()
    {
        if(set == true) {
            try {
                DataInputStream outClient;
                outClient = new DataInputStream(sock.getInputStream());
                String mensaje = outClient.readUTF();
                System.out.println(mensaje);
                if (mensaje.split("\\n").length == 4) {
                    Mensaje mensajer = new Mensaje(mensaje);
                    if (mensajer != null) {
                        String finale = mensajer.toString();
                        casosDePaquetes(finale, lastClientRealIP, true);
                    } else {
                        System.out.println("Mensaje no valido");
                    }
                } else if (mensaje.split("\\n").length >= 3) {
                    Paquete paquete = new Paquete(mensaje);
                    if (paquete != null) {
                        String finale = paquete.toString();
                        casosDePaquetes(finale, lastClientRealIP, false);
                    } else {
                        System.out.println("Paquete no valido");
                    }
                }
            } catch (Exception ex) {
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
            Solicitante solicitante; // Cliente
            switch (accion) {
                case 0:
                    String recivido = envio.getIpMensaje();
                    System.out.println(recivido);
                    break;
                // Distancia recivida;
                case 4:
                    if ((envio.getIpMensaje()) != null && (envio.getIpMensaje()).matches("[-+]?\\d*\\.?\\d+"))
                    {
                        int distancia = Integer.parseInt(envio.getIpMensaje());

                        // Determinar si la distancia es la mejor, y si la es, cambiar el atravez del wished

                        nodo.nominateWish(distancia, envio.getIpFuente());
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
                                if ((resultado[2]) != null && (resultado[2]).matches("[-+]?\\d*\\.?\\d+")) {
                                    int porte = Integer.parseInt(resultado[2]);
                                    boolean success = nodo.modifyIPTableEntry(resultado[1], resultado[0], porte);
                                    if (success == true) {
                                        System.out.println("Se ha actualizado " + envio.getIpFuente() + " con " + lastClientRealIP);
                                    } else {
                                        System.out.println("Se ha guardado " + resultado[1] + " con " + resultado[0]);
                                    }
                                } else {
                                    System.out.println("ERROR! El puerto debe ser un número");
                                }
                            }
                        }
                    } else if ((envio.getIpMensaje()) != null && (envio.getIpMensaje()).matches("[-+]?\\d*\\.?\\d+")) {
                        boolean success = nodo.modifyIPTableEntry(envio.getIpFuente(), lastClientRealIP, Integer.parseInt(envio.getIpMensaje()));
                        if (success == true) {
                            System.out.println("Se ha actualizado " + envio.getIpFuente() + " con " + lastClientRealIP);
                        } else {
                            System.out.println("Se ha guardado " + envio.getIpFuente() + " con " + lastClientRealIP);
                        }
                        String mensajeAEnviar = nodo.getTablaIPString();

                        // Se lo manda a todos los que conoce
                        Set<String> keys = nodo.getIPTable().keySet();
                        String[] array = keys.toArray(new String[keys.size()]);
                        for(int w = 0; w < array.length; ++w) {
                            if(-1 < nodo.getDTable().get(array[w]).getDistancia()) {
                                Mensaje mensaje = new Mensaje(nodo.getmyFakeAddress(), array[w], 7, mensajeAEnviar);
                                solicitante = new Solicitante(this.nodo, mensaje); // OJO QUE SI LLEGA AQUI CON UN FAKE QUE NO SEA J, A, S ó P VA A EXPLOTAR!!!
                                solicitante.run();
                            }
                        }
                    } else {
                        System.out.println("Este mensaje no debe ser manejado por el dispatcher");
                    }
                    break;
                case 8:
                    try {
                        String filename = envio.getIpMensaje();
                        String path =System.getProperty("user.dir") + ("/Music/" + filename + ".wav");
                        path = path.replace('\\', '/');
                        File soundFile = AudioUtil.getSoundFile(path);

                        System.out.println("servidor: " + soundFile);

                        try {
                            FileInputStream in = new FileInputStream(soundFile);
                            OutputStream out = sock.getOutputStream();
                            byte buffer[] = new byte[2048];
                            int count;
                            while ((count = in.read(buffer)) != -1) {
                                out.write(buffer, 0, count);
                            }
                        }
                        catch (Exception ex)
                        {
                            System.out.println("Error estableciendo conexion de audio");
                        }

                        System.out.println("El envio ha terminado");
                    }
                    catch(Exception ex) {
                        System.out.println("Error enviando audio");
                    }
                break;
            }
        }
        else
        {
            System.out.println("Error del servidor");
        }
    }
}
