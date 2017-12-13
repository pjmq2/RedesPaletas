package ac.cr.ucr.ci1320.Etapa3;


/**
 * Stores distance to another node to its label and the correct path to that destinations
 */
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

    public boolean setNew(String fake, int distance) {
        if(distance < distancia || distancia == -1)
        {
            this.aTraves = fake;
            this.distancia = distance;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void modifyaTravez(String atravez){
        this.aTraves = atravez;
    }

    public void modifyDistance(int distance){
        this.distancia = distance;
    }
}
