package ac.cr.ucr.ci1320;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.valueOf;

public class Nodo {
    private Map<String,TablaDirecciones> tablaD ;
    private Map<String,String> tablaIP;
    private String miIp;
    public int miPuerto;
    private Analizador analisis;
    private String ipDispatcher;
    /*
    private String miIpFalsa;
    private int miPuerto;
    private int backlogport;
    private Analizador analisis;
    private Servidor server;
    private String Alonso;
    */

    public Nodo(Map<String,TablaDirecciones> tablaD, String miIp, int miPuerto, Map<String,String> tablaIP)
    {
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
        this.tablaIP = tablaIP;
        this.analisis = new Analizador(tablaD, tablaIP, miIp);
    }

    public void iniciar()
    {
        falseDispatcher();
    }

    //Dispatcher Falso
    public void falseDispatcher(){
        tablaIP.put("12.0.0.7","192.168.0.161"); // P
        tablaIP.put("12.0.20.2","192.168.0.166"); // S
        tablaIP.put("12.0.0.8","192.168.0.136"); // J
        tablaIP.put("12.0.0.3","192.168.0.136"); // A

        /*TablaIp tabla1 = new TablaIp("localhost",9999);
        TablaIp tabla2 = new TablaIp("localhost",9999);
        TablaIp tabla3 = new TablaIp("192.168.0.11",5555);

        tablaIP.put("12.0.0.8",tabla1);
        tablaIP.put("12.0.0.3",tabla2);
        tablaIP.put("165.8.0.0",tabla3);*/
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
                /*
                paquete = analiza.empaquetar(mensaje);
                String ipFalsa = analiza.getIpDestino(paquete.getIpDestinPaquete());
                String ipReal = tablaIP.get(ipFalsa);
                Solicitante solicitante = new Solicitante();
                solicitante.sendMessage(paquete.toString(),ipReal,tablaD.get(paquete.getIpDestinPaquete()).getPuerto(),miIp,analisis);
                */


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
                        String ipReal = tablaIP.get(ipTemp);
                        cliente.sendMessage(mensaje.toString(), ipReal, puerto);
                    }
                    break;
                case 1:
                    if (mensaje.getIpMensaje().equals(miIp)) {
                        Paquete paquete = analisis.responder3(mensaje.getIpFuente());
                        int puerto = analisis.getPuertoDestino(paquete.getIpDestinPaquete());
                        String ipTemp = analisis.getIpDestino(paquete.getIpDestinPaquete());
                        String ipReal = tablaIP.get(ipTemp);
                        cliente.sendMessage(paquete.toString(), ipReal, puerto);
                    }
                    break;
                case 2:
                    Paquete paquete = analisis.responder4(mensaje.getIpFuente());
                    int puerto = analisis.getPuertoDestino(paquete.getIpDestinPaquete());
                    String ipTemp = analisis.getIpDestino(paquete.getIpDestinPaquete());
                    String ipReal = tablaIP.get(ipTemp);
                    cliente.sendMessage(paquete.toString(), ipReal, puerto);
                    break;
                /*case 7:
                    Map<String, TablaIp> tablaTemp;
                    imprimirMensaje(mensaje);
                    tablaTemp = this.parseFromDispatcher(mensaje.getIpMensaje());
                    tablaIP = tablaTemp;
                    this.analisis = new Analizador(tablaD, tablaIP, miIp);
                    break;*/
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
                    //String ipReal = tablaIP.get(paquete2.getIpDestinPaquete()).getIpVerdadera();
                    String ipReal = tablaIP.get(paquete2.getIpDestinPaquete()); //Devuelve la direccion real asignada en la tablaIP
                    cliente.sendMessage(paquete2.toString(),ipReal,puerto);
                }
                break;
            case 1:
                if(paquete.getMensaje().getIpMensaje().equals(miIp)){
                    Paquete paquete1 = analiza.responder3(paquete.getMensaje().getIpFuente());
                    int puerto = analiza.getPuertoDestino(paquete1.getIpDestinPaquete());
                    String ipReal = tablaIP.get(paquete1.getIpDestinPaquete());
                    cliente.sendMessage(paquete1.toString(),ipReal,puerto);
                }
                break;
            case 2:
                Paquete paquete1 = analiza.responder4(paquete.getMensaje().getIpFuente());
                int puerto = analiza.getPuertoDestino(paquete1.getIpDestinPaquete());
                String ipReal = tablaIP.get(paquete1.getIpDestinPaquete());
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

    public Nodo(Map<String, TablaDirecciones> tablaD, String miIp, int miPuerto, HashMap<String,String>tablaIP, String fake1, String fake2, String fake3, String fake4, int backport) {
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
        this.tablaIP = tablaIP;
        this.analisis = new Analizador(tablaD, tablaIP, miIp);
        this.server = new Servidor(this);
        this.dispon = new Dispatcher(fake1, this.tablaIP.get(fake1), fake2, this.tablaIP.get(fake2), fake3, this.tablaIP.get(fake3), fake4, this.tablaIP.get(fake4), backport, this);
        this.backlogport = backport;
        this.miIpFalsa = fake3;
        this.Alonso = fake4;
    }



    public String getIP()
    {
        return this.miIp;
    }

    public int getPort()
    {
        return this.miPuerto;
    }

    public String getFake()
    {
        return this.miIpFalsa;
    }

    public Analizador getAnalizer() { return this.analisis; }

    public boolean modifyIPTableEntry(String fake, String real, int port)
    {
        String faker = tablaIP.get(fake);
        if(faker == null)
        {
            return false;
        }
        else
        {
            tablaIP.put(fake, real);
            TablaDirecciones tabla = tablaD.get(fake);
            tabla.modifyPort(port);
            return true;
        }
    }

    public void iniciar()
    {
        // Abrir Servidor

        server.iniciar();

        // Abrir el Dispatcher

        dispon.iniciar();

        // Leer línea de la terminal.

        this.terminal();
    }

    public String getTablaIPString() {
        String returnValue = new String();
        Set<String> keys = tablaIP.keySet();
        String[] array = keys.toArray(new String[keys.size()]);

        for(int i = 0; i < array.length; ++i) {
            if (!(tablaIP.get(array[i]).equalsIgnoreCase("0"))) {
                if(!(returnValue.equals(""))) { returnValue = returnValue + "|"; }
                returnValue = returnValue + array[i] + "," + tablaIP.get(array[i]) + "," + tablaD.get(array[i]).getPuerto();
            }
        }
        return returnValue;
    }

    public void terminal() {
        System.out.println("Mensaje -> IPDESTINO \\n MENSAJE / Dispatcher -> DISPATCH"); // Hay que cambiar to.do esto para que en vez de ser IPDESTINO \n MENSAJE sea IPDESTINO \n PUERTO \n MENSAJE porque el dispatcher y el puerto correran en puertos distintos AÚN NO HE ACABADO, sigo a las 9 [Cuando llego a la casa]
        Scanner scanner = new Scanner(System.in);
        String entrada = "";
        while (!(entrada.equals("BYE"))) {
            entrada = scanner.nextLine();
            if (!(entrada.equals("BYE"))) {
                String[] array = entrada.split("\\\\n");
                // Enviar el mensaje
                Solicitante solicitante;

                if (array.length == 2) {
                    TablaDirecciones tdir = tablaD.get(array[0]);
                    if (tdir == null) {
                        System.out.println("Dirección IP inválida");
                    } else {
                        // FALTA HACER QUE ELIJA LA DIRECCIÓN ADECUADA, Y QUE HAGA LO QUE OCUPE SI NO LA TIENE.

                        String ipDestino = tdir.getaTraves();
                        String direccionReal = tablaIP.get(ipDestino); //Hacer un condicional para que revize si ipDestino existe en la tabla, si no, abajo podría dar null pointer
                        TablaDirecciones tabla = tablaD.get(array[0]); // FALTA EXCEPCION!!! [RED ALARM]
                        int porte = 0000;
                        TablaDirecciones tabla2 = tablaD.get(ipDestino);
                        porte = tabla2.getPuerto();
                        String mensajeAEnviar = array[1];
                        solicitante = new Solicitante(this, mensajeAEnviar, direccionReal, porte, miIp, analisis, 7); // Address Port Menssage
                        solicitante.run();
                    }
                } else if (entrada.equalsIgnoreCase("DISPATCH")) {
                    String direccionReal = tablaIP.get(Alonso);
                    TablaDirecciones tabla = tablaD.get(Alonso);
                    int porte = tabla.getPuerto();
                    solicitante = new Solicitante(this, Integer.toString(miPuerto), direccionReal, porte, miIp, analisis, 7); // Address Port Menssage
                    solicitante.run();
                } else {
                    System.out.println("Mensaje Inválido");
                }
            }
        }
    }

    // Imprime el mensaje en la terminal.
    private void imprimirMensaje(Mensaje mensaje){
        System.out.println(mensaje.getIpMensaje());
    }

    */
}
