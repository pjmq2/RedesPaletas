/*

Aun no funciona

 */

package ac.cr.ucr.ci1320;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.io.DataOutputStream;

import static java.lang.Integer.signum;
import static java.lang.Integer.valueOf;

public class Dispatcher {
    private HashMap<String,String> tablaIP;
    String localIP;
    int port;
    Nodo nodo;

    public Dispatcher(String fake1, String real1, String fake2, String real2, String fake3, String miIP, String fake4, String real4, int backport, Nodo node) {
        this.tablaIP = new HashMap<>();
        this.tablaIP.put(fake1,real1); // P
        this.tablaIP.put(fake2,real2); // S
        this.tablaIP.put(fake3,miIP); // J
        this.tablaIP.put(fake4,real4); // A
        this.localIP = miIP;
        this.port = backport;
        this.nodo = node;
    }

    public HashMap<String,String> BuildIPTable(String fake1, String real1, String fake2, String real2, String fake3, String real3, String fake4, String real4) {
        HashMap<String,String> tablonIP = new HashMap<>();
        tablonIP.put(fake1,real1); // P
        tablonIP.put(fake2,real2); // S
        tablonIP.put(fake3,real3); // J
        tablonIP.put(fake4,real4); // A
        return tablonIP;
    }

    public HashMap<String,String> getTablaIP() {
        return tablaIP;
    }

    public void iniciar() {
        Thread starter = new Thread(new Dispatcher.Starter(port));
        starter.start();
        System.out.println("Dispatcher esperando...");
    }

    public class Starter implements Runnable
    {
        int puerto;

        public Starter(int backport) {
            puerto = backport;
        }

        public void run(){
            try
            {
                ServerSocket servidor = new ServerSocket(puerto);
                while (true){
                    Socket cliente = servidor.accept();
                    String clientIP = cliente.getRemoteSocketAddress().toString().split(":")[0];
                    String clientIPRevealed = clientIP.split("/")[1];
                    PrintWriter writer = new PrintWriter(cliente.getOutputStream());
                    Thread listener = new Thread(new Dispatcher.Manejador(cliente, clientIPRevealed));
                    listener.start();
                    System.out.println("Conexión recibida, Dispatcher");
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
        DataOutputStream writer;
        Socket sock;
        String lastClientFakeIP;
        String lastClientRealIP;

        public Manejador(Socket clientSocket, String clientRealIP)
        {
            try
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                writer = new DataOutputStream(sock.getOutputStream());
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

                // El mensaje debe ser el IP FALSO!!!

                String[] mensajeOriginalSeparado = mensaje.split("\n");
                if(mensajeOriginalSeparado.length == 4) {
                    String[] mensajeSeparado = mensajeOriginalSeparado[3].split("\n");
                    int numfin = mensajeSeparado.length;
                    if (numfin == 2 && isNumeric(mensajeSeparado[1]) == true) {
                        boolean success = nodo.modifyIPTableEntry(mensajeSeparado[0], lastClientRealIP, Integer.parseInt(mensajeSeparado[1]));
                        if (success == true) {
                            System.out.println("Se ha guardado " + mensajeSeparado[0] + " con " + lastClientRealIP);
                        } else {
                            System.out.println("ERROR! Dirección falsa otorgada no existe");
                        }
                    } else {
                        System.out.println("La dirección falsa asignada no sigue el formato adecuado");
                    }

                    // Enviar tabla completa

                    String finale = nodo.getTablaIPString();
                    Mensaje ultimate = new Mensaje(nodo.getFake(), mensajeSeparado[0], 7, finale);
                    String envio = ultimate.toString();
                    writer.writeUTF(envio);
                    writer.flush();
                }
                else
                {
                    System.out.println("Mensaje recibido por el DISPATCHER es inválido");
                }

                lastClientRealIP = null;
            }
            catch (Exception ex)
            {
                System.out.println("Fallo envio");
            }
        }
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
