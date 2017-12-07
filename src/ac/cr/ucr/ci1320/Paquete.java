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
        if(completo.length >= 3) {
            String stringmensaje = "";
            this.ipFuentePaquete = completo[0];
            this.ipDestinPaquete = completo[1];

            for(int i = 2; i < completo.length; ++i)
            {
                stringmensaje = stringmensaje.concat(completo[i]);
                if(i < (completo.length - 1))
                {
                    stringmensaje = stringmensaje + "\n";
                }
            }

            this.mensaje = new Mensaje(stringmensaje);
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
        result = result.concat(ipDestinPaquete);
        result = result.concat("\n");
        result = result.concat(ipFuentePaquete);
        result = result.concat("\n");
        result = result.concat(mensaje.toString());
        return result;
    }
}
