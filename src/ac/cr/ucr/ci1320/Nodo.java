package ac.cr.ucr.ci1320;

import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class Nodo {
    private HashMap<String,TablaDirecciones> tablaD ;
    private HashMap<String,String> tablaIP;
    private Dispatcher dispon;
    private String miIp;
    public int miPuerto;
    public int backlogport;
    private Analizador analisis;

    public Nodo(HashMap<String, TablaDirecciones> tablaD, String miIp, int miPuerto, HashMap<String,String>tablaIP, String fake1, String fake2, String fake3, int backport) {
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
        this.tablaIP = tablaIP;
        this.analisis = new Analizador(tablaD, tablaIP, miIp);
        this.dispon = new Dispatcher(fake1, this.tablaIP.get(fake1), fake2, this.tablaIP.get(fake2), fake3, this.tablaIP.get(fake3), backport, this);
        this.backlogport = backport;
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public boolean modifyIPTableEntry(String fake, String real)
    {
        String faker = tablaIP.get(fake);
        if(faker == null)
        {
            return false;
        }
        else
        {
            tablaIP.put(fake, real);
            return true;
        }
    }

    public void iniciar()
    {
        // Abrir Servidor

        Servidor server = new Servidor(this);
        server.iniciar();

        // Abrir el Dispatcher

        dispon.iniciar();

        // Leer línea de la terminal.

        System.out.println("Escriba -> IPDESTINO \\n PORT \\n MENSAJE"); // Hay que cambiar to.do esto para que en vez de ser IPDESTINO \n MENSAJE sea IPDESTINO \n PUERTO \n MENSAJE porque el dispatcher y el puerto correran en puertos distintos AÚN NO HE ACABADO, sigo a las 9 [Cuando llego a la casa]
        Scanner scanner = new Scanner(System.in);
        String entrada = "";
        while(!(entrada.equals("BYE")))
        {
            entrada = scanner.nextLine();
            if(!(entrada.equals("BYE")))
            {
                String[] array = entrada.split("\\\\n");
                // Enviar el mensaje
                Solicitante solicitante = new Solicitante(this);

                if(array.length == 3 && isNumeric(array[1]))
                {
                    int porte = valueOf(array[1]);
                    TablaDirecciones tdir = tablaD.get(array[0]);
                    if(tdir == null)
                    {
                        System.out.println("Dirección IP inválida");
                    }
                    else
                    {
                        String ipDestino = tdir.getaTraves();
                        String direccionReal = tablaIP.get(ipDestino); //Hacer un condicional para que revize si ipDestino existe en la tabla, si no, abajo podría dar null pointer
                        TablaDirecciones tabla = tablaD.get(array[0]); // FALTA EXCEPCION!!! [RED ALARM]
                        solicitante.sendMessage(array[2], direccionReal, porte, miIp, analisis); // Address Port Menssage
                    }
                }
                else
                {
                    System.out.println("Mensaje Inválido");
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
                Solicitante solicitante = new Solicitante(this);
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
        Solicitante solicitante = new Solicitante(this); // Cliente
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
