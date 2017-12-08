package ac.cr.ucr.ci1320.Etapa3;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Enrutador{


    public Enrutador(int cantInterfaces, String[] ips, String[] fisicos, String ipDispatcher, Map<String,TablaDirecciones> tablaD) {
        if(cantInterfaces==1){
            //Crea un nodo de Julian
        }
        else{ //Crea los routers necesarios
            int[] puertos = new int[cantInterfaces];
            Map<String,TablaIp> tablaIP = new HashMap<>();
            for(int i=0; i<cantInterfaces; i++){
                TablaDirecciones tablaTemp = new TablaDirecciones("Directo",fisicos[i],0);
                TablaIp tabla1 = new TablaIp("localhost",5555+i);
                tablaIP.put(ips[i],tabla1);
                tablaD.put(ips[i],tablaTemp);
                puertos[i]=5555+i;
            }

            for (int i=0; i<cantInterfaces; i++){
                Thread interfaz = new Thread(new Interfaz(tablaD,ips[i],puertos[i],ipDispatcher,fisicos[i],tablaIP));
                interfaz.start();
            }
        }

    }
}