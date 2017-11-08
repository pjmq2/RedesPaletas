package ac.cr.ucr.ci1320;

import java.util.Map;

public class Nodo {
    private Map<String,TablaDirecciones> tablaD ;
    private Map<String,String> tablaIP;
    private String miIp;
    private int miPuerto;

    public Nodo(Map<String, TablaDirecciones> tablaD, String miIp, int miPuerto) {
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
    }

    private void recibirTransmicion(){
        Servidor server = new Servidor();
        String buffer = "";//server.getMesage();
        String[] array = buffer.split("\n");
        Analizador analiza = new Analizador(tablaD,tablaIP,miIp);
        Paquete paquete;
        Mensaje mensaje;
        if(array.length>4){
            paquete = analiza.stringToPaquete(buffer);
            casosDeMensajes(paquete);
        }else {
            mensaje = analiza.stringToMensaje(buffer);
            paquete = analiza.empaquetar(mensaje);
            Client solicitante = new Client();
            //solicitante.sendMesage(paquete.toString(),paquete.getIpDestinPaquete(),tablaD.get(paquete.getIpDestinPaquete()).getPuerto());
        }
    }

    public void casosDeMensajes(Paquete paquete){
        int accion = paquete.getMensaje().getAccion();
        Analizador analiza = new Analizador(tablaD,tablaIP,miIp);
        Client solicitante = new Client();
        switch (accion){
            case 0:
                Paquete paquete2=analiza.empaquetar(paquete.getMensaje());
                if(paquete.getMensaje().getIpDestino().equals(miIp)) imprimirMensaje(paquete.getMensaje());
                else{
                    //solicitante.sendMesage(paquete2.toString(),paquete2.getIpDestinPaquete(),tablaD.get(paquete2.getIpDestinPaquete()).getPuerto());
                }

                break;
            case 1:
                if(paquete.getMensaje().getIpMensaje().equals(miIp)){
                    Paquete paquete1 = analiza.responder3(paquete.getMensaje().getIpFuente());
                    //solicitante.sendMesage(paquete1.toString(),paquete1.getIpDestinPaquete(),tablaD.get(paquete1.getIpDestinPaquete()).getPuerto());
                }
                break;
            case 2:
                Paquete paquete1 = analiza.responder4(paquete.getMensaje().getIpFuente());
                //solicitante.sendMesage(paquete1.toString(),paquete1.getIpDestinPaquete(),tablaD.get(paquete1.getIpDestinPaquete()).getPuerto());
                break;
        }
    }

    public void imprimirMensaje(Mensaje mensaje){
        System.out.println(mensaje.getIpMensaje());
    }

}
