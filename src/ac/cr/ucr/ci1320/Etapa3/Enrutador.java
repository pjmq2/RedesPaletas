package ac.cr.ucr.ci1320.Etapa3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Enrutador{

    boolean julian = false;

    public Enrutador(int cantInterfaces, String[] ips, String[] fisicos, String ipDispatcher,
                     Map<String,TablaDirecciones> tablaD, int cantBuf)
    {

        if(cantInterfaces==1)
        {
            this.julian = true;
        }
        else{ //Crea los routers necesarios
            int[] puertos = new int[cantInterfaces];
            Map<String,TablaIp> tablaIP = new HashMap<>();
            for(int i=0; i<cantInterfaces; i++){
                TablaDirecciones tablaTemp = new TablaDirecciones("Directo",fisicos[i],0); //Siempre es directo porque son del mismo router, verdad?
                TablaIp tabla1 = new TablaIp("localhost",5555+i); //se crea la tabla ip siempre con un puerto mayor a 5555
                tablaIP.put(ips[i],tabla1); //Se junta la dirección falsa con [direccion real, puerto]
                tablaD.put(ips[i],tablaTemp); //Se junta la dirección falsa con [aTraves, dirFisica, distancia] (estas tres deberian ser siempre las mismas)
                puertos[i]=5555+i; //Se va guardando en la lista "puertos" los puertos asignados
            }

            for (int i=0; i<cantInterfaces; i++){
                Thread interfaz = new Thread(new Interfaz(tablaD,ips[i],puertos[i],ipDispatcher,fisicos[i],tablaIP, cantBuf, julian));
                interfaz.start();
            }
        }

    }
}