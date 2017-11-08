package ac.cr.ucr.ci1320;

import java.util.Map;

public class Nodo {
    private Map<String,TablaDirecciones> tablaD ;
    private Map<String,String> tablaIP;
    private String miIp;
    private String miCanal;

    public Nodo(Map<String, TablaDirecciones> tablaD, String miIp, String miCanal) {
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miCanal = miCanal;
    }

    public void brocast(String ip){

    }

    private void recibirMensaje(){
        Client cliente = new Client();
        String mensaje = cliente.getMesage();
    }
}
