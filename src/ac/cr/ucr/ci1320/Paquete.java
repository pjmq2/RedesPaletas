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

    public Paquete(String TODO) {
        String completo[] = TODO.split("\n");
        if(completo.length == 3) {
            this.mensaje = new Mensaje(completo[0]);
            this.ipFuentePaquete = completo[1];
            this.ipDestinPaquete = completo[2];
        }
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
        String result = "";
        result.concat(ipDestinPaquete);
        result.concat("\n");
        result.concat(ipFuentePaquete);
        result.concat("\n");
        result.concat(mensaje.toString());
        return result;
    }
}
