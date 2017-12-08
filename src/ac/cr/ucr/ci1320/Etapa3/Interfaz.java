package ac.cr.ucr.ci1320.Etapa3;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Interfaz implements Runnable{
    //Se pasa toodo lo de Nodo a Interfaz
    private Map<String,TablaDirecciones> tablaD ;
    private Map<String,TablaIp> tablaIP;
    private String miIp;
    public int miPuerto;
    private Analizador analisis;
    private String ipDispatcher;
    private String dirFisica;

    ///

    DataStructures dataStructures;
    Servidor server;
    private String nombreFisico;
    //*PunteroAlBuffer ptrBuff;
    private final AtomicInteger permits = new AtomicInteger(0);
    private final Semaphore semaphore = new Semaphore(1, true);

    public Interfaz(Map<String,TablaDirecciones> tablaD, String miIp, int miPuerto, String ipDispatcher, String dirFisica)
    {
        this.tablaD = tablaD;               //Tabla de direcciones
        this.miIp = miIp;                   //Direccion falsa
        this.miPuerto = miPuerto;           //Puerto
        this.ipDispatcher = ipDispatcher;   //Direccion real
        this.dirFisica = dirFisica;         //Direccion fisica
        this.tablaIP = new HashMap<>();     //Tabla con las direcciones verdaderas

        this.iniciar();

        this.analisis = new Analizador(tablaD, tablaIP, miIp);
    }

    public void iniciar()
    {
        /*
        TablaDirecciones tabla1 = new TablaDirecciones("Julian","12.0.0.8",0);
        TablaDirecciones tabla2 = new TablaDirecciones("Sebastian","12.0.20.2",0);
        TablaDirecciones tabla3 = new TablaDirecciones("Carrito","165.8.0.0",0);
        TablaDirecciones tabla4 = new TablaDirecciones("Paletas","165.8.0.0",1);
        TablaDirecciones tabla5 = new TablaDirecciones("Luces","165.8.0.0",2);
        TablaDirecciones tabla6 = new TablaDirecciones("Legos","12.0.20.2",2);
        TablaDirecciones tabla7 = new TablaDirecciones("Bolinchas","12.0.20.2",1);
        TablaDirecciones tabla8 = new TablaDirecciones("Alonso","12.0.0.3",0);
        tablaD.put("12.0.0.8",tabla1);
        tablaD.put("12.0.20.2",tabla2);
        tablaD.put("165.8.0.0",tabla3);
        tablaD.put("200.5.0.0",tabla4);
        tablaD.put("25.0.0.0",tabla5);
        tablaD.put("201.6.0.0",tabla6);
        tablaD.put("140.90.0.0",tabla7);
        tablaD.put("12.0.0.3",tabla8);
        */
        //Mensaje pedirCosas = new Mensaje("12.0.0.7","12.0.0.0",7,"7777");
        //Cliente cliente = new Cliente();
        //cliente.sendMessage(pedirCosas.toString(),ipDispatcher,5000);
        falseDispatcher();
    }

    //Dispatcher Falso
    public void falseDispatcher(){
        TablaIp tabla1 = new TablaIp("localhost",9999);
        TablaIp tabla2 = new TablaIp("localhost",9999);
        TablaIp tabla3 = new TablaIp("192.168.0.11",5555);
        tablaIP.put("12.0.0.8",tabla1);
        tablaIP.put("12.0.0.3",tabla2);
        tablaIP.put("165.8.0.0",tabla3);
    }

    // Procesa el mensaje
    public void recibirTransmicion(String entrada){
        String[] array = entrada.split("\n");
        Paquete paquete;
        Mensaje mensaje;
        if(array.length>4){
            paquete = analisis.stringToPaquete(entrada); // Mete el mensaje en un paquete
            casosDePaquetes(paquete);
        }else {
            mensaje = analisis.stringToMensaje(entrada);
            casosDeMensajes(mensaje);
        }
    }

    private void casosDeMensajes(Mensaje mensaje){
        if(mensaje.getIpDestino().equals(miIp)){
            imprimirMensaje(mensaje);
        }
        else
        {
            Cliente cliente = new Cliente();
            int accion = mensaje.getAccion();
            switch (accion) {
                default:
                    if (mensaje.getIpDestino().equals(miIp)) imprimirMensaje(mensaje);
                    else {
                        Paquete paquete = analisis.empaquetar(mensaje);
                        int puerto = analisis.getPuertoDestino(paquete.getIpDestinPaquete());
                        String ipTemp = analisis.getIpDestino(paquete.getIpDestinPaquete());
                        String ipReal = tablaIP.get(ipTemp).getIpVerdadera();
                        cliente.sendMessage(mensaje.toString(), ipReal, puerto);
                    }
                    break;
                case 1:
                    if (mensaje.getIpMensaje().equals(miIp)) {
                        Paquete paquete = analisis.responder3(mensaje.getIpFuente());
                        int puerto = analisis.getPuertoDestino(paquete.getIpDestinPaquete());
                        String ipTemp = analisis.getIpDestino(paquete.getIpDestinPaquete());
                        String ipReal = tablaIP.get(ipTemp).getIpVerdadera();
                        cliente.sendMessage(paquete.toString(), ipReal, puerto);
                    }
                    break;
                case 2:
                    Paquete paquete = analisis.responder4(mensaje.getIpFuente(), mensaje.getIpMensaje());
                    int puerto = analisis.getPuertoDestino(paquete.getIpDestinPaquete());
                    String ipTemp = analisis.getIpDestino(paquete.getIpDestinPaquete());
                    String ipReal = tablaIP.get(ipTemp).getIpVerdadera();
                    cliente.sendMessage(paquete.toString(), ipReal, puerto);
                    break;
                case 7:
                    HashMap<String, TablaIp> tablaTemp;
                    imprimirMensaje(mensaje);
                    tablaTemp = this.parseFromDispatcher(mensaje.getIpMensaje());
                    tablaIP = tablaTemp;
                    this.analisis = new Analizador(tablaD, tablaIP, miIp);
                    break;
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
    private void casosDePaquetes(Paquete paquete){
        int accion = paquete.getMensaje().getAccion();
        Analizador analiza = new Analizador(tablaD,tablaIP,miIp); //
        Cliente cliente = new Cliente(); // Cliente
        switch (accion){
            default:
                Paquete paquete2=analiza.empaquetar(paquete.getMensaje());
                if(paquete.getMensaje().getIpDestino().equals(miIp)) imprimirMensaje(paquete.getMensaje());
                else{
                    int puerto = analiza.getPuertoDestino(paquete2.getIpDestinPaquete());
                    String ipReal = tablaIP.get(paquete2.getIpDestinPaquete()).getIpVerdadera();
                    cliente.sendMessage(paquete2.toString(),ipReal,puerto);
                }

                break;
            case 1:
                if(paquete.getMensaje().getIpMensaje().equals(miIp)){
                    Paquete paquete1 = analiza.responder3(paquete.getMensaje().getIpFuente());
                    int puerto = analiza.getPuertoDestino(paquete1.getIpDestinPaquete());
                    String ipReal = tablaIP.get(paquete1.getIpDestinPaquete()).getIpVerdadera();
                    cliente.sendMessage(paquete1.toString(),ipReal,puerto);
                }
                break;
            case 2:
                Paquete paquete1 = analiza.responder4(paquete.getMensaje().getIpFuente(),paquete.getMensaje().getIpMensaje());
                int puerto = analiza.getPuertoDestino(paquete1.getIpDestinPaquete());
                String ipReal = tablaIP.get(paquete1.getIpDestinPaquete()).getIpVerdadera();
                cliente.sendMessage(paquete1.toString(),ipReal,puerto);
                break;
        }
    }

    // Imprime el mensaje en la terminal.
    private void imprimirMensaje(Mensaje mensaje){
        System.out.println(mensaje.getIpMensaje());
    }


    public HashMap<String,TablaIp> parseFromDispatcher(String dispatcherInput){
        String[] computador = dispatcherInput.split("#");
        String[] aux;
        HashMap<String, TablaIp> tablita = new HashMap<>();
        for (String netComponent : computador) {
            aux = netComponent.split(",");
            TablaIp tablaAux = new TablaIp(aux[0],Integer.parseInt(aux[2].trim()));
            tablita.put(aux[0],tablaAux);
        }
        return tablita;
    }

    /*
    public void putToSleep() {
        semaphore.acquireUninterruptibly();
    }

    public void resume() {
        semaphore.release(permits.getAndSet(0));
    }
    */


    @Override
    public void run()
    {
        server = new Servidor(this); //Se debe pasarle a Servidor un puntero al inicio de la cola
        server.iniciar();

        while(true)
        {
            /*
            semaphore.acquireUninterruptibly(Integer.MAX_VALUE); //Que se detenga hasta que hayan datos disponibles
            semaphore.release(Integer.MAX_VALUE);
            */
            //El Buffer va a devolver el String por el que va
            String devuelto = "";
            recibirTransmicion(devuelto);
        }
    }

}

