package ac.cr.ucr.ci1320.Etapa3;

import java.util.Map;

/**
 * Class that handles functions to correctly analyze and turn strings received by
 * interfaces and turn them into packages or messages when needed
 */
public class Analizador {
    private Map<String,TablaDirecciones> tablaD ;
    private Map<String,TablaIp> tablaIP;
    private String miIp;

    public Analizador(Map<String, TablaDirecciones> tablaD, Map<String, TablaIp> tablaIP, String miIp) {
        this.tablaD = tablaD;
        this.tablaIP = tablaIP;
        this.miIp = miIp;
    }

    // Crea el paquete físico usando el mensaje creado con los valores de red.
    public Paquete empaquetar(Mensaje mensaje){
        String ipDestino = this.getIpDestino(mensaje.getIpDestino());
        //TablaDirecciones tabla=tablaD.get(ipDestino);
        //String ipFuturo = tablaIP.get(ipDestino);
        Paquete paquete = new Paquete(mensaje,miIp,ipDestino); //La tercera es a ip de destino
        return paquete;
    }

    /**
     * Turns input string to a message object
     * @param mensajeString string received via socket
     * @return complete message object
     */
    public Mensaje stringToMensaje(String mensajeString){
        String[] array = mensajeString.split("\n");
        Mensaje mensaje =new Mensaje(array[0],array[1],Integer.parseInt(array[2]),array[3]);
        return mensaje;
    }

    /**
     * Turns input string to paquete object
     * @param paqueteString string received via socket
     * @return
     */
    public Paquete stringToPaquete(String paqueteString){
        String[] array = paqueteString.split("\n");
        Mensaje mensaje = new Mensaje(array[2],array[3],Integer.parseInt(array[4]),array[5]);
        Paquete paquete = new Paquete(mensaje,array[1],array[0]);
        return paquete;
    }

    public Paquete responder3(String ipDestino){
        Mensaje mensaje = new Mensaje(miIp,ipDestino,3,miIp);
        Paquete paquete = new Paquete(mensaje,miIp,ipDestino);
        return paquete;
    }

    public Paquete responder4(String ipDestino, String otroIp){
        Mensaje mensaje = new Mensaje(miIp,ipDestino,4,Integer.toString(tablaD.get(otroIp).getDistancia()));
        Paquete paquete = new Paquete(mensaje,miIp,ipDestino);
        return paquete;
    }

    public String getIpDestino(String ipInicial) //Recibe a quien se lo va a mandar, devuelve a traves de quien
    {
        //String ipDestino = tablaD.get(ipInicial).getEtiqueta(); //Aun creo que lo que deberia devolver es el aTraves...
        String ipDestino = "";
        if(tablaD.get(ipInicial) != null) {
            ipDestino = tablaD.get(ipInicial).getaTraves();
        }
        return ipDestino;
    }

    public int getPuertoDestino(String ipInicial) //Devuelve el puerto del destino
    {
        int puertoDestino = -1;
        if(tablaIP.get(ipInicial) != null) {
            puertoDestino = tablaIP.get(ipInicial).getPuerto();
        }
        return puertoDestino;
    }
}
