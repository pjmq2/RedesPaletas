package ac.cr.ucr.ci1320;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.util.Random;

/**
 * Created by B50587 on 05/12/2017.
 */
public class Enrutador{
    Nodo nodo;
    int Itotal = 5;
    Interfaz[] myInters;
    String[] físicas = {"Interfaz1", "Interfaz2", "Interfaz3", "Interfaz4", "Interfaz5"};
    private Semaphore available;

    public Enrutador(Nodo nodo, int Itotal, int bufferNumber) {
        this.Itotal = Itotal;
        available = new Semaphore(bufferNumber, true);
        this.nodo = nodo;
        myInters = new Interfaz[Itotal];
    }

    public int getaInter()
    {
        int freeOne = 0;
        boolean success = false;
        try {
            available.acquire();

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
}