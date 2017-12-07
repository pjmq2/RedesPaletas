package ac.cr.ucr.ci1320.NodoJ;

public class TablaDirecciones {
    private String direccionFisica;
    private String aTraves;
    private int distancia;
    private int puerto;
    private int backpuerto;

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

    public int getBackPuerto() { return backpuerto; }

    public int getPuerto() {
        return puerto;
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

    public void modifyPort(int port){
        this.puerto = port;
    }

    public void modifyBackPort(int port){
        this.backpuerto = port;
    }

    public void modifyaTravez(String atravez){
        this.aTraves = atravez;
    }
}