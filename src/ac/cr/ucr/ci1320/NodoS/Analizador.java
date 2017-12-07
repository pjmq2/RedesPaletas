package ac.cr.ucr.ci1320.NodoS;

import java.util.HashMap;

public class Analizador {
    //private HashMap<String,TablaDirecciones> tablaD ;
    //private HashMap<String,String> tablaIP;
    //private String miIp;
    private Nodo nodo;

    public Analizador(Nodo nodo) {
        this.nodo = nodo;
    }

    // Crea el paquete físico usando el mensaje creado con los valores de red.
    public Paquete empaquetar(Mensaje mensaje){
        HashMap<String,TablaDirecciones> tablaD = nodo.getDTable();
        TablaDirecciones tabla = tablaD.get(mensaje.getIpDestino());
        String miFake = nodo.getFake();
        String ipFuturo = tabla.getaTraves();
        Paquete paquete = new Paquete(mensaje,miFake,ipFuturo); //La tercera es a ip de destino
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
        String miFake = nodo.getFake();
        Mensaje mensaje = new Mensaje(miFake,ipDestino,3,miFake);
        Paquete paquete = new Paquete(mensaje,miFake,ipDestino);
        return paquete;
    }

    public Paquete responder4(String ipDestino){
        HashMap<String,TablaDirecciones> tablaD = nodo.getDTable();
        String miFake = nodo.getFake();
        Mensaje mensaje = new Mensaje(miFake,ipDestino,4,Integer.toString(tablaD.get(ipDestino).getDistancia()));
        Paquete paquete = new Paquete(mensaje,miFake,ipDestino);
        return paquete;
    }

    public String getIpDestino(String ipInicial){
        HashMap<String,TablaDirecciones> tablaD = nodo.getDTable();
        String ipDestino="";
        String cadena[] = ipInicial.split("\\.");
        switch (Integer.parseInt(cadena[0])){
            default:
                ipDestino = tablaD.get("165.8.6.25").getaTraves();
                break;
            case 12:
                ipDestino = ipInicial;
                break;
            case 200:
                ipDestino = tablaD.get("200.5.0.0").getaTraves();
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
}