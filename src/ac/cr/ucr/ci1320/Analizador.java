package ac.cr.ucr.ci1320;

import java.util.Map;

public class Analizador {
    private Map<String,TablaDirecciones> tablaD ;
    private Map<String,String> tablaIP;
    private Mensaje mensaje;
    private String miIp;

    public Analizador(Map<String, TablaDirecciones> tablaD, Map<String, String> tablaIP, Mensaje mensaje, String miIp) {
        this.tablaD = tablaD;
        this.tablaIP = tablaIP;
        this.mensaje = mensaje;
        this.miIp = miIp;
    }

    public Paquete empaquetar(){
        Paquete paquete = new Paquete(mensaje,miIp,);
        return paquete;
    }


}
