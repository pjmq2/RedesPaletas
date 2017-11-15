package ac.cr.ucr.ci1320;

import java.util.HashMap;
import java.util.Scanner;

public class Nodo {
    private HashMap<String,TablaDirecciones> tablaD ;
    private HashMap<String,String> tablaIP;
    private Dispatcher dispon;
    private String miIp;
    public int miPuerto;
    private Analizador analisis;

    public Nodo(HashMap<String, TablaDirecciones> tablaD, String miIp, int miPuerto, HashMap<String,String>tablaIP) {
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
        this.tablaIP = tablaIP;
        this.analisis = new Analizador(tablaD, tablaIP, miIp);
        this.dispon = new Dispatcher("12.0.0.7", this.tablaIP.get("12.0.0.7"), "12.0.20.2", this.tablaIP.get("12.0.20.2"), "12.0.0.8", this.tablaIP.get("12.0.0.8"), 9999, this);;
    }

    public void iniciar()
    {
        // Abrir Servidor

        Servidor server = new Servidor(this);
        server.iniciar();

        // Abrir el Dispatcher

        dispon.iniciar();

        // Leer línea de la terminal.

        System.out.println("Escriba -> IPDESTINO \\n MENSAJE");
        Scanner scanner = new Scanner(System.in);
        String entrada = "";
        while(!(entrada.equals("BYE")))
        {
            entrada = scanner.nextLine();
            if(!(entrada.equals("BYE")))
            {
                String[] array = entrada.split("\\\\n");
                // Enviar el mensaje
                Solicitante solicitante = new Solicitante();

                if(array.length == 2)
                {
                    String ipDestino = tablaD.get(array[0]).getaTraves();
                    String direccionReal = tablaIP.get(ipDestino); //Hacer un condicional
                    TablaDirecciones tabla = tablaD.get(array[0]); // FALTA EXCEPCION!!! [RED ALARM]
                    solicitante.sendMessage(array[1], direccionReal, tabla.getPuerto(), miIp, analisis); // Address Port Menssage
                }
            }
        }
    }

    // Procesa el mensaje
    public void recibirTransmicion(String entrada){
        String[] array = entrada.split("\n");
        Analizador analiza = new Analizador(tablaD,tablaIP,miIp);
        Paquete paquete;
        Mensaje mensaje;
        if(array.length>4){
            paquete = analiza.stringToPaquete(entrada); // Mete el mensaje en un paquete
            casosDeMensajes(paquete);
        }else {
            mensaje = analiza.stringToMensaje(entrada);
            if(mensaje.getIpDestino().equals(miIp)){
                imprimirMensaje(mensaje);
            }
            else
            {
                paquete = analiza.empaquetar(mensaje);
                String ipFalsa = analiza.getIpDestino(paquete.getIpDestinPaquete());
                String ipReal = tablaIP.get(ipFalsa);
                Solicitante solicitante = new Solicitante();
                solicitante.sendMessage(paquete.toString(),ipReal,tablaD.get(paquete.getIpDestinPaquete()).getPuerto(),miIp,analisis);
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
    private void casosDeMensajes(Paquete paquete){
        int accion = paquete.getMensaje().getAccion();
        Analizador analiza = new Analizador(tablaD,tablaIP,miIp); //
        Solicitante solicitante = new Solicitante(); // Cliente
        switch (accion){
            case 0:
                Paquete paquete2=analiza.empaquetar(paquete.getMensaje());
                if(paquete.getMensaje().getIpDestino().equals(miIp)) imprimirMensaje(paquete.getMensaje());
                else{
                    solicitante.sendMessage(paquete2.toString(),paquete2.getIpDestinPaquete(),tablaD.get(paquete2.getIpDestinPaquete()).getPuerto(),miIp,analisis);
                }

                break;
            case 1:
                if(paquete.getMensaje().getIpMensaje().equals(miIp)){
                    Paquete paquete1 = analiza.responder3(paquete.getMensaje().getIpFuente());
                    solicitante.sendMessage(paquete1.toString(),paquete1.getIpDestinPaquete(),tablaD.get(paquete1.getIpDestinPaquete()).getPuerto(),miIp,analisis);
                }
                break;
            case 2:
                Paquete paquete1 = analiza.responder4(paquete.getMensaje().getIpFuente());
                solicitante.sendMessage(paquete1.toString(),paquete1.getIpDestinPaquete(),tablaD.get(paquete1.getIpDestinPaquete()).getPuerto(),miIp,analisis);
                break;
        }
    }

    // Imprime el mensaje en la terminal.
    private void imprimirMensaje(Mensaje mensaje){
        System.out.println(mensaje.getIpMensaje());
    }
}
