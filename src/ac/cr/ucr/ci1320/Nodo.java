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
            paquete = analiza.empaquetar(mensaje);
            Solicitante solicitante = new Solicitante();
            solicitante.sendMesage(paquete.toString(),paquete.getIpDestinPaquete(),tablaD.get(paquete.getIpDestinPaquete()).getPuerto());
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
                    solicitante.sendMesage(paquete2.toString(),paquete2.getIpDestinPaquete(),tablaD.get(paquete2.getIpDestinPaquete()).getPuerto());
                }

                break;
            case 1:
                if(paquete.getMensaje().getIpMensaje().equals(miIp)){
                    Paquete paquete1 = analiza.responder3(paquete.getMensaje().getIpFuente());
                    solicitante.sendMesage(paquete1.toString(),paquete1.getIpDestinPaquete(),tablaD.get(paquete1.getIpDestinPaquete()).getPuerto());
                }
                break;
            case 2:
                Paquete paquete1 = analiza.responder4(paquete.getMensaje().getIpFuente());
                solicitante.sendMesage(paquete1.toString(),paquete1.getIpDestinPaquete(),tablaD.get(paquete1.getIpDestinPaquete()).getPuerto());
                break;
        }
    }

    private void imprimirMensaje(Mensaje mensaje){
        System.out.println(mensaje.getIpMensaje());
    }

}
