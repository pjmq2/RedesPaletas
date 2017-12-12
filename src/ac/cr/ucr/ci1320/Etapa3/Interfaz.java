package ac.cr.ucr.ci1320.Etapa3;

import java.io.PrintWriter;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Interfaz implements Runnable{
    //Se pasa toodo lo de Nodo a Interfaz
    private Map<String,TablaDirecciones> tablaD ;
    private Map<String,TablaIp> tablaIP;
    private String miIp; // Falsa
    private int miPuerto;
    private Analizador analisis;
    private String ipDispatcher;
    private String dirFisica;
    ///

    private DataStructures dataStructure;
    private ProcessingThread procThread;
    private Servidor server;
    private boolean isTerminal;

    public Interfaz(Map<String,TablaDirecciones> tablaD, String miIp, int miPuerto, String ipDispatcher,
                    String dirFisica, Map<String,TablaIp> tablaIP, int numBuf, boolean julian)
    {
        this.tablaD = tablaD;               //Tabla de direcciones
        this.miIp = miIp;                   //Direccion falsa                    PrintWriter writer = new PrintWriter(cliente.getOutputStream());

        dataStructure = new DataStructures(numBuf);

        this.miPuerto = miPuerto;           //Puerto
        this.ipDispatcher = ipDispatcher;   //Direccion real
        this.dirFisica = dirFisica;         //Direccion fisica
        this.tablaIP = tablaIP;     //Tabla con las direcciones verdaderas
        this.analisis = new Analizador(tablaD, tablaIP, miIp);
        this.isTerminal = julian;
        if(julian)
        {
            Thread starter1 = new Thread(new Terminal(this, this.ipDispatcher));
            starter1.start();
        }
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

    public boolean modifyIPTableEntry(String fake, String real, int portz)
    {
        TablaIp faker = tablaIP.get(fake);
        if(faker == null)
        {
            return false;
        }
        else
        {
            faker.modifyipVerdadera(real);
            faker.modifypuerto(portz);
            TablaDirecciones tabla = tablaD.get(fake);
            faker.modifypuerto(portz);
            tabla.modifyaTravez(fake);
            tabla.modifyDistance(0);
            return true;
        }
    }

    public String getTablaIPString() {
        String returnValue = new String();
        Set<String> keys = tablaIP.keySet();
        String[] array = keys.toArray(new String[keys.size()]);

        for(int i = 0; i < array.length; ++i) {
            if (!(tablaIP.get(array[i]).getIpVerdadera().equalsIgnoreCase("0"))) {
                if(!(returnValue.equals(""))) { returnValue = returnValue + "#"; }
                returnValue = returnValue + tablaIP.get(array[i]) + "," + array[i] + "," + tablaIP.get(array[i]).getPuerto();
            }
        }
        return returnValue;
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
                    if (mensaje.getIpDestino().equals(miIp))
                    {
                        imprimirMensaje(mensaje);
                        try(  PrintWriter out = new PrintWriter( "registro.txt" )  ) //Guardar los valores recibidos
                        {
                            out.println( mensaje.getIpFuente() + "\n");
                            out.println( mensaje.getIpMensaje() + "\n");
                        }
                        catch (java.io.FileNotFoundException c)
                        {
                            System.out.println("El documento al cual copiar los datos no existe");
                        }
                    }
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
                    if (mensaje.getIpMensaje().contains("#")) {
                        String entradas[] = mensaje.getIpMensaje().split("#", -1);
                        int longitud = entradas.length;
                        for (int i = 0; i < longitud; ++i) {
                            if(!(entradas[i].equals(""))) {
                                String resultado[] = entradas[i].split(",");
                                if ((resultado[2]) != null && (resultado[2]).matches("[-+]?\\d*\\.?\\d+")) {
                                    int porte = Integer.parseInt(resultado[2]);
                                    boolean success = this.modifyIPTableEntry(resultado[1], resultado[0], porte);
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
                    }
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

    public String getMyFakeAddress(){
        return this.miIp;
    }

    @Override
    public void run()    {
        server = new Servidor(this); //Se debe pasarle a Servidor un puntero al inicio de la cola
        server.iniciar();

        Thread bufferProcessor = new Thread(new ProcessingThread(this));
        bufferProcessor.start();
    }

    public DataStructures getDataStructures() {
        return dataStructure;
    }

    public Map<String,TablaIp> getTablaIP() { return tablaIP; }

    public void setDataStructures(DataStructures dataStructures) {
        this.dataStructure = dataStructures;
    }

    public int getMiPuerto() { return this.miPuerto; }

    public boolean getisTerminal() { return this.isTerminal; }
}

