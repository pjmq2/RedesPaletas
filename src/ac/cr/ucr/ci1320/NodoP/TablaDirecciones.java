package ac.cr.ucr.ci1320.NodoP;

public class TablaDirecciones {
    private String direccionFisica;
    private String aTraves;
    private int distancia;

    public TablaDirecciones(String direccionFisica, String aTraves, int distancia) {
        this.direccionFisica = direccionFisica;
        this.aTraves = aTraves;
        this.distancia = distancia;
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

}
