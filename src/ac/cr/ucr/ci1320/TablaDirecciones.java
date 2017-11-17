package ac.cr.ucr.ci1320;

public class TablaDirecciones {
    private String direccionFisica;
    private String aTraves;
    private int distancia;
    private int puerto;


    public TablaDirecciones(String direccionFisica, String aTraves, int distancia, int puerto) {
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

    public int getPuerto() {
        return puerto;
    }

    public void modifyPort(int port){
        this.puerto = port;
    }
}
