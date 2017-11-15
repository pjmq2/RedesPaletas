package ac.cr.ucr.ci1320;

public class TablaDirecciones {
    private String direccionFisica;
    private String aTraves;
    private int distancia;
    private int puerto;
    private  int backpuerto;


    public TablaDirecciones(String direccionFisica, String aTraves, int distancia, int puerto, int backpuerto) {
        this.direccionFisica = direccionFisica;
        this.aTraves = aTraves;
        this.distancia = distancia;
        this.puerto = puerto;
        this.backpuerto = backpuerto;
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

    public int getBackPuerto() {
        return backpuerto;
    }
}
