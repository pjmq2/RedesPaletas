package ac.cr.ucr.ci1320;

public class TablaDirecciones {
    private String direccionFisica;
    private String aTraves;
    private int distancia;
    private String puerto;

    public TablaDirecciones(String direccionFisica, String aTraves, int distancia, String puerto) {
        this.direccionFisica = direccionFisica;
        this.aTraves = aTraves;
        this.distancia = distancia;
        this.puerto = puerto;
    }

    public String getDireccionFisica() {
        return direccionFisica;
    }

    public String getaTraves() {
        return aTraves;
    }

    public int getDistancia() {
        return distancia;
    }

    public String getPuerto() {
        return puerto;
    }
}
