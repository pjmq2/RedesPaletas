package ac.cr.ucr.ci1320.Etapa3;

public class TablaDirecciones {
    private String aTraves;
    private String etiqueta;
    private int distancia;

    public TablaDirecciones(String aTraves, String etiqueta, int distancia) {
        this.aTraves = aTraves;
        this.etiqueta = etiqueta;
        this.distancia = distancia;
    }

    public String getaTraves() {
        return aTraves;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public int getDistancia() {
        return distancia;
    }

}
