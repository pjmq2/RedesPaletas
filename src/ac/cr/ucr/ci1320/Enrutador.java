package ac.cr.ucr.ci1320;

import java.util.concurrent.Semaphore;
import java.util.Random;

/**
 * Created by B50587 on 05/12/2017.
 */
public class Enrutador{
    Nodo nodo;
    int Itotal = 5;
    int bufferNumber = 0;
    Enviador[] myInters;
    String[] físicas = {"Interfaz1", "Interfaz2", "Interfaz3", "Interfaz4", "Interfaz5"};
    String[] IPs = {"12.1.0.1", "12.1.0.2", "12.1.0.3", "12.1.0.4", "12.1.0.5"};
    String[] Masks = {"12.2.0.1", "12.2.0.2", "12.2.0.3", "12.2.0.4", "12.2.0.5"};
    String[] Tickets = {"Primero", "Segundo", "Tercero", "Cuarto", "Quinto"};
    private Semaphore available;
    Random rand = new Random();

    public Enrutador(Nodo nodo, int Itotal, int bufferNumber) {
        this.Itotal = Itotal;
        available = new Semaphore(Itotal, true);
        this.nodo = nodo;
        myInters = new Enviador[Itotal];
        this.bufferNumber = bufferNumber;
    }

    public int getaInter()
    {
        int freeOne = 0;
        boolean success = false;
        try {
            available.acquire();
            for(int i = 3; i < Itotal; ++i) {
                if(myInters[i] == null)
                {
                    success = true;
                    freeOne = i;

                    // freeOne sería la interfaz libre
                }
            }
            available.release();
        }
        catch (Exception ex)
        {
            System.out.println("ERROR!!!");
        }
        if(success == true)
        {
            return freeOne;
        }
        else
        {
            return -1;
        }
    }

    public Enviador getInters(int i) {
        if(i < 0 || 4 < i){
            return null;
        }
        else {
            return myInters[i];
        }
    }
}