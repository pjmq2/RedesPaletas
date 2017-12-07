package ac.cr.ucr.ci1320.NodoS;

public class TablaIp {
    private String ipVerdadera;
    private int puerto;

    public TablaIp(String ipVerdadera, int puerto) {
        this.ipVerdadera = ipVerdadera;
        this.puerto = puerto;
    }

    public String getIpVerdadera() {
        return ipVerdadera;
    }

    public int getPuerto() {
        return puerto;
    }
}