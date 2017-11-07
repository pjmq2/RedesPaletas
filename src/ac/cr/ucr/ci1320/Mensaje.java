package ac.cr.ucr.ci1320;

public class Mensaje {
    private String ipFuente;
    private String ipDestino;
    private char accion;
    private String ipMensaje;

    public Mensaje(String ipFuente, String ipDestino, char accion, String ipMensaje) {
        this.ipFuente = ipFuente;
        this.ipDestino = ipDestino;
        this.accion = accion;
        this.ipMensaje = ipMensaje;
    }

    public String getIpFuente() {
        return ipFuente;
    }

    public String getIpDestino() {
        return ipDestino;
    }

    public char getAccion() {
        return accion;
    }

    public String getIpMensaje() {
        return ipMensaje;
    }
}
