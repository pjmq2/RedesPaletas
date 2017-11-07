package ac.cr.ucr.ci1320;

import java.util.Map;

public class Analizador {
    private Map<String,TablaDirecciones> tablaD ;
    private Map<String,String> tablaIP;

    Analizador(Map<String,TablaDirecciones> tablaD2, Map<String,String> tablaIP2){
        tablaD=tablaD2;
        tablaIP=tablaIP2;
    }

    public String calcularCamino(){
        String resultado="";
        return resultado;
    }
}
