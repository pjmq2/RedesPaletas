package ac.cr.ucr.ci1320;

public class Paquete {
    private Mensaje mensaje;
    private String ipFuentePaquete;
    private String ipDestinPaquete;

    public Paquete(Mensaje mensaje, String ipFuentePaquete, String ipDestinPaquete) {
        this.mensaje = mensaje;
        this.ipFuentePaquete = ipFuentePaquete;
        this.ipDestinPaquete = ipDestinPaquete;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public String getMensajeString(){
        return  mensaje.toString();
    }

    public String getIpFuentePaquete() {
        return ipFuentePaquete;
    }

    public String getIpDestinPaquete() {
        return ipDestinPaquete;
    }

    public String toString() {
        String result =ipDestinPaquete+"\n"+ipFuentePaquete+"\n"+mensaje.toString();
        return result;
    }
}
