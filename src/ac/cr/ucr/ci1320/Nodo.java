package ac.cr.ucr.ci1320;

import java.util.Map;

public class Nodo {
    private Map<String,TablaDirecciones> tablaD ;
    private Map<String,String> tablaIP;
    private String miIp;
    private int miPuerto;

    public Nodo(Map<String, TablaDirecciones> tablaD, String miIp, int miPuerto, Map<String,String>tablaIP) {
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
        this.tablaIP = tablaIP;
    }

    public void recibirTransmicion(String entrada){
        String[] array = entrada.split("\n");
        Analizador analiza = new Analizador(tablaD,tablaIP,miIp);
        Paquete paquete;
        Mensaje mensaje;
        if(array.length>4){
            paquete = analiza.stringToPaquete(entrada);
            casosDeMensajes(paquete);
        }else {
            mensaje = analiza.stringToMensaje(entrada);
            if(mensaje.getIpDestino().equalsIgnoreCase(miIp)){
                imprimirMensaje(mensaje);
            }else {
                paquete = analiza.empaquetar(mensaje);
                String ipReal = analiza.getIpDestino(paquete.getIpDestinPaquete());
                Solicitante solicitante = new Solicitante();
                solicitante.sendMessage(paquete.toString(),ipReal,tablaD.get(paquete.getIpDestinPaquete()).getPuerto());
            }
        }
    }

    private void casosDeMensajes(Paquete paquete){
        int accion = paquete.getMensaje().getAccion();
        Analizador analiza = new Analizador(tablaD,tablaIP,miIp);
        Solicitante solicitante = new Solicitante();
        switch (accion){
            case 0:
                Paquete paquete2=analiza.empaquetar(paquete.getMensaje());
                if(paquete.getMensaje().getIpDestino().equals(miIp)) imprimirMensaje(paquete.getMensaje());
                else{
                    String ipReal = analiza.getIpDestino(paquete.getIpDestinPaquete());
                    solicitante.sendMessage(paquete2.toString(),ipReal,tablaD.get(paquete2.getIpDestinPaquete()).getPuerto());
                }

                break;
            case 1:
                if(paquete.getMensaje().getIpMensaje().equals(miIp)){
                    Paquete paquete1 = analiza.responder3(paquete.getMensaje().getIpFuente());
                    String ipReal = analiza.getIpDestino(paquete.getIpDestinPaquete());
                    solicitante.sendMessage(paquete1.toString(),ipReal,tablaD.get(paquete1.getIpDestinPaquete()).getPuerto());
                }
                break;
            case 2:
                Paquete paquete1 = analiza.responder4(paquete.getMensaje().getIpFuente());
                String ipReal = analiza.getIpDestino(paquete.getIpDestinPaquete());
                solicitante.sendMessage(paquete1.toString(),ipReal,tablaD.get(paquete1.getIpDestinPaquete()).getPuerto());
                break;
        }
    }

    private void imprimirMensaje(Mensaje mensaje){
        System.out.println(mensaje.getIpMensaje());
    }

}
