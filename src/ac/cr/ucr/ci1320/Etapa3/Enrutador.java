package ac.cr.ucr.ci1320.Etapa3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Enrutador{

    boolean julian = false;

    public Enrutador(){

    }

    public void start(int cantInterfaces, String[] ips, String[] fisicos, String fipDispatcher, String ripDispatcher,
                     Map<String,TablaDirecciones> tablaD, int cantBuf)
    {

        if(cantInterfaces==1)
        {
            this.julian = true;
        }
        //Crea los routers necesarios
        int[] puertos = new int[cantInterfaces];
        Map<String,TablaIp> tablaIP = new HashMap<>();

        int i = 0;
        for(i=0; i<cantInterfaces; i++){
            TablaDirecciones tablaTemp = new TablaDirecciones("Directo",fisicos[i],0); //Siempre es directo porque son del mismo router, verdad?
            TablaIp tabla1 = new TablaIp("localhost",5555+i); //se crea la tabla ip siempre con un puerto mayor a 5555
            tablaIP.put(ips[i],tabla1); //Se junta la dirección falsa con [direccion real, puerto]
            tablaD.put(ips[i],tablaTemp); //Se junta la dirección falsa con [aTraves, dirFisica, distancia] (estas tres deberian ser siempre las mismas)
            puertos[i]=5555+i; //Se va guardando en la lista "puertos" los puertos asignados
        }
        TablaDirecciones tablaDisp = new TablaDirecciones("Directo",fipDispatcher,0);
        TablaIp tablaDisp1 = new TablaIp(ripDispatcher,4444);
        tablaIP.put(ips[i],tablaDisp1);
        tablaD.put(ips[i],tablaDisp);
        puertos[i]=4444;
        for (int y=0; y<cantInterfaces; y++){
            Thread interfaz = new Thread(new Interfaz(tablaD,ips[y],puertos[y],fipDispatcher,fisicos[y],tablaIP, cantBuf, julian));
            interfaz.start();
        }

    }
}