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
        if(completo.length >= 4)
        {
            String stringmensaje = "";
            this.ipFuente = completo[0];
            this.ipDestino = completo[1];
            this.accion = Integer.parseInt(completo[2]);

            for(int i = 3; i < completo.length; ++i)
            {
                stringmensaje = stringmensaje.concat(completo[i]);
                if(i < (completo.length - 1))
                {
                    stringmensaje = stringmensaje + "\n";
                }
            }

            this.ipMensaje = stringmensaje;
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