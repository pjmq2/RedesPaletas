package ac.cr.ucr.ci1320;

import java.util.Map;

public class Analizador {
    private Map<String,TablaDirecciones> tablaD ;
    private Map<String,String> tablaIP;
    private String miIp;

    public Analizador(Map<String, TablaDirecciones> tablaD, Map<String, String> tablaIP, String miIp) {
        this.tablaD = tablaD;
        this.tablaIP = tablaIP;
        this.miIp = miIp;
    }
    
    public Paquete empaquetar(Mensaje mensaje){
        TablaDirecciones tabla=tablaD.get(mensaje.getIpDestino());
        String ipFuturo = tablaIP.get(tabla.getaTraves());
        Paquete paquete = new Paquete(mensaje,miIp,ipFuturo); //La tercera es a ip de destino
        return paquete;
    }

    public Mensaje stringToMensaje(String mensajeString){
        String[] array = mensajeString.split("\n");
        Mensaje mensaje =new Mensaje(array[0],array[1],Integer.parseInt(array[2]),array[3]);
        return mensaje;
    }

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

    public Paquete responder4(String ipDestino){
        Mensaje mensaje = new Mensaje(miIp,ipDestino,4,Integer.toString(tablaD.get(ipDestino).getDistancia()));
        Paquete paquete = new Paquete(mensaje,miIp,ipDestino);
        return paquete;
    }

}
