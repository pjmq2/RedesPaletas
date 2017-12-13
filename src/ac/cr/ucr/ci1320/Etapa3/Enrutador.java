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
                      Map<String, Map<String,TablaDirecciones>> tablaDTablas,  Map<String,TablaIp> tablaIp, int cantBuf)
    {

        if(cantInterfaces==1)
        {
            this.julian = true;
        }
        //Crea los routers necesarios
        int[] puertos = new int[cantInterfaces + 1];


        //Map<String,TablaDirecciones> tablaD= new HashMap<>();


        /*
        int i = 0;
        for(i=0; i<cantInterfaces; i++){
            TablaDirecciones tablaTemp = new TablaDirecciones("Directo",fisicos[i],0);
            tablaD.put(ips[i],tablaTemp);
            TablaIp tabla1 = new TablaIp("localhost",5555+i); //se crea la tabla ip siempre con un puerto mayor a 5555
            tablaIP.put(ips[i],tabla1); //Se junta la dirección falsa con [direccion real, puerto]

            puertos[i]=5555+i; //Se va guardando en la lista "puertos" los puertos asignados
        }
        */
        //

        //Sin dispatcher, como lo pruebo?
        //tablaIP.put("193.34.11.7", new TablaIp("1", 55551)); //Faltaria ipverdadero y puerto
        //tablaIP.put("11.70.4.5", new TablaIp("2", 55552));


        //

        /*
        TablaDirecciones tablaDisp = new TablaDirecciones("Directo",fipDispatcher,0);
        TablaIp tablaDisp1 = new TablaIp(ripDispatcher,4444);
        tablaIP.put(fipDispatcher,tablaDisp1);
        tablaD.put(fipDispatcher,tablaDisp);
        puertos[i]=4444;
        */


        if (cantInterfaces==3){
            Thread interfaz1 = new Thread(new Interfaz(tablaDTablas.get("1.1"),ips[0],puertos[0],fipDispatcher,fisicos[0],tablaIp, cantBuf, julian));
            interfaz1.start();
            Thread interfaz2 = new Thread(new Interfaz(tablaDTablas.get("1.2"),ips[1],puertos[1],fipDispatcher,fisicos[1],tablaIp, cantBuf, julian));
            interfaz2.start();
            Thread interfaz3 = new Thread(new Interfaz(tablaDTablas.get("1.3"),ips[2],puertos[2],fipDispatcher,fisicos[2],tablaIp, cantBuf, julian));
            interfaz3.start();
        }

        if (cantInterfaces==2){
            Thread interfaz1 = new Thread(new Interfaz(tablaDTablas.get("2.1"),ips[0],puertos[0],fipDispatcher,fisicos[0],tablaIp, cantBuf, julian));
            interfaz1.start();
            Thread interfaz2 = new Thread(new Interfaz(tablaDTablas.get("2.2"),ips[1],puertos[1],fipDispatcher,fisicos[1],tablaIp, cantBuf, julian));
            interfaz2.start();
        }


        //Thread interfaz = new Thread(new Interfaz(tablaD,ips[0],puertos[0],fipDispatcher,fisicos[0],tablaIp, cantBuf, julian));
        //interfaz.start();

        /*
        double value = 1;
        for (int y=0; y<cantInterfaces; y++){
            value += 0.1;
            tablaD = tablaDTablas.get(Double.toString(value));
            Thread interfaz = new Thread(new Interfaz(tablaD,ips[y],puertos[y],fipDispatcher,fisicos[y],tablaIp, cantBuf, julian));
            interfaz.start();
        }
        */
        if(cantInterfaces == 1)
        {
            Thread interfaz = new Thread(new Interfaz(tablaDTablas.get("1"),ips[0],puertos[0],fipDispatcher,fisicos[0],tablaIp, cantBuf, julian));
            interfaz.start();
        }

        //Thread starter2 = new Thread(new Dispatcher(tablaIP, fipDispatcher)); //COMENTAR PARA HACER PRUEBAS
        //starter2.start();
    }
}