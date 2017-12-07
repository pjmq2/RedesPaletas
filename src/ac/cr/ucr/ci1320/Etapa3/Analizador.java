package ac.cr.ucr.ci1320.Etapa3;

import java.util.Map;

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

    public Paquete responder4(String ipDestino, String otroIp){
        Mensaje mensaje = new Mensaje(miIp,ipDestino,4,Integer.toString(tablaD.get(otroIp).getDistancia()));
        Paquete paquete = new Paquete(mensaje,miIp,ipDestino);
        return paquete;
    }

    public String getIpDestino(String ipInicial){
        String ipDestino="";
        String cadena[] = ipInicial.split("\\.");
        switch (Integer.parseInt(cadena[0])){
            default:
                ipDestino = tablaD.get("165.8.0.0").getaTraves();
                break;
            case 12:
                ipDestino = ipInicial;
                break;
            case 200:
                ipDestino = tablaD.get("200.5.0.2").getaTraves();
                break;
            case 25:
                ipDestino = tablaD.get("25.0.0.0").getaTraves();
                break;
            case 201:
                ipDestino = tablaD.get("201.6.0.0").getaTraves();
                break;
            case 140:
                ipDestino = tablaD.get("140.90.0.0").getaTraves();
                break;
        }
        return ipDestino;
    }

    public int getPuertoDestino(String ipInicial){
        int puertoDestino=0;
        String cadena[] = ipInicial.split("\\.");
        switch (Integer.parseInt(cadena[0])){
            default:
                puertoDestino = tablaIP.get("165.8.0.0").getPuerto();
                break;
            case 12:
                puertoDestino = tablaIP.get(ipInicial).getPuerto();
                break;
            case 200:
                puertoDestino = tablaIP.get("200.5.0.2").getPuerto();
                break;
            case 25:
                puertoDestino = tablaIP.get("25.0.0.0").getPuerto();
                break;
            case 201:
                puertoDestino = tablaIP.get("201.6.0.0").getPuerto();
                break;
            case 140:
                puertoDestino = tablaIP.get("140.90.0.0").getPuerto();
                break;
        }
        return puertoDestino;
    }
}