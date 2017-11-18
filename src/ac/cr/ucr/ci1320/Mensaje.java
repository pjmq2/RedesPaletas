package ac.cr.ucr.ci1320;

public class Mensaje {
    private String ipFuente;
    private String ipDestino;
    private int accion;
    private String ipMensaje;

    public Mensaje(String ipFuente, String ipDestino, int accion, String ipMensaje) {
        this.ipFuente = ipFuente;
        this.ipDestino = ipDestino;
        this.accion = accion;
        this.ipMensaje = ipMensaje;
    }


    public Mensaje(String TODO) {
        String completo[] = TODO.split("\n");
        if(completo.length == 4)
        {
            this.ipFuente = completo[0];
            this.ipDestino = completo[1];
            this.accion = Integer.parseInt(completo[2]);
            this.ipMensaje = completo[3];
        }
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

    public String toString(){
        String result = "";
        result = result.concat(ipFuente);
        result = result.concat("\n");
        result = result.concat(ipDestino);
        result = result.concat("\n");
        result = result.concat(Integer.toString(accion));
        result = result.concat("\n");
        result = result.concat(ipMensaje);
        return result;
    }
}
