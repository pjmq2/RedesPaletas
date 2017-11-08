package ac.cr.ucr.ci1320;

public class Mensaje {
    private String ipFuente;
    private String ipDestino;
    private int accion;
    private String ipMensaje;
    private int tamano;

    public Mensaje(String ipFuente, String ipDestino, int accion, String ipMensaje) {
        this.ipFuente = ipFuente;
        this.ipDestino = ipDestino;
        this.accion = accion;
        this.ipMensaje = ipMensaje;
        tamano = ipMensaje.length();
    }

    public String getIpFuente() {
        return ipFuente;
    }

    public String getIpDestino() {
        return ipDestino;
    }

    public int getAccion() {
        return accion;
    }

    public String getIpMensaje() {
        return ipMensaje;
    }

    public int getTamano() { return tamano; }

    public String toString(){
        String result = "";
        result.concat(ipFuente);
        result.concat("\n");
        result.concat(ipDestino);
        result.concat("\n");
        result.concat(Integer.toString(accion));
        result.concat("\n");
        result.concat(Integer.toString(tamano));
        result.concat("\n");
        result.concat(ipMensaje);
        return result;
    }
}
