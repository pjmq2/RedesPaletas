package ac.cr.ucr.ci1320.Etapa3;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Enrutador{

    int cantInterfaces;

    public Enrutador(int cantInterfaces, String[] ips, String[] fisicos) {
        String hola = ips[0];
        this.cantInterfaces = cantInterfaces;
        //Thread interfaz = new Thread(new Interfaz(int puerto));
    }
}