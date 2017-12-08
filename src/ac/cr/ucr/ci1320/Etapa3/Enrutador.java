package ac.cr.ucr.ci1320.Etapa3;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Enrutador{

    int cantInterfaces;

    public Enrutador(int numeroEnrutador, int cantInterfaces, String[] ips, String[] fisicos) {
        String hola = ips[0];
        for (int i=0; i<cantInterfaces; i++){
            Map<String,TablaDirecciones> tablaD = new HashMap<>();

        }
        //Thread interfaz = new Thread(new Interfaz(int puerto));
    }

    public Map<String,TablaDirecciones> enrutarRouters(int numeroEnrutador){
        Map<String,TablaDirecciones> tablaD = new HashMap<>();
        if(numeroEnrutador==0){
            TablaDirecciones tabla1 = new TablaDirecciones("Directo","RouterEnanos",0);
            tablaD.put("200.6.0.0",tabla1);
            TablaDirecciones tabla2 = new TablaDirecciones("Directo","RouterHombres",0);
            tablaD.put("178.8.60.0",tabla2);
            TablaDirecciones tabla3 = new TablaDirecciones("Directo","RouterElfos",0);
            tablaD.put("123.45.67.0",tabla3);
            TablaDirecciones tabla4 = new TablaDirecciones("Directo","RouterHobbit1",0);
            tablaD.put("168.5.0.0",tabla4);
            TablaDirecciones tabla5 = new TablaDirecciones("168.5.0.0","RouterHobbit1",1);
            tablaD.put("192.118.1.0",tabla5);
            TablaDirecciones tabla6 = new TablaDirecciones("168.5.0.0","RouterHobbit1",1);
            tablaD.put("06.0.28.0",tabla6);
            TablaDirecciones tabla7 = new TablaDirecciones("168.5.0.0","RouterHobbit1",1);
            tablaD.put("25.0.0.0",tabla7);
            TablaDirecciones tabla8 = new TablaDirecciones("168.5.0.0","RouterHobbit1",1);
            tablaD.put("10.8.0.0",tabla8);
        }else{
            TablaDirecciones tabla1 = new TablaDirecciones("168.5.0.0","RouterHobbit2",1);
            tablaD.put("200.6.0.0",tabla1);
            TablaDirecciones tabla2 = new TablaDirecciones("168.5.0.0","RouterHobbit2",1);
            tablaD.put("178.8.60.0",tabla2);
            TablaDirecciones tabla3 = new TablaDirecciones("168.5.0.0","RouterHobbit2",1);
            tablaD.put("123.45.67.0",tabla3);
            TablaDirecciones tabla4 = new TablaDirecciones("Directo","RouterHobbit2",0);
            tablaD.put("168.5.0.0",tabla4);
            TablaDirecciones tabla5 = new TablaDirecciones("Directo","RouterValar",0);
            tablaD.put("192.118.1.0",tabla5);
            TablaDirecciones tabla6 = new TablaDirecciones("Directo","RouterOrco",0);
            tablaD.put("06.0.28.0",tabla6);
            TablaDirecciones tabla7 = new TablaDirecciones("Directo","RouterMago",0);
            tablaD.put("25.0.0.0",tabla7);
            TablaDirecciones tabla8 = new TablaDirecciones("Directo","RouterDragon",0);
            tablaD.put("10.8.0.0",tabla8);
        }
        return tablaD;
    }
}