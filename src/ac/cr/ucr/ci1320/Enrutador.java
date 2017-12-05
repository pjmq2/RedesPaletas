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
    Interfaz myInters[] = new Interfaz[Itotal];
    private Semaphore available;

    public Enrutador(Nodo nodo, int bufferNumber) {
        available = new Semaphore(bufferNumber, true);
        this.nodo = nodo;
    }

    public int getaInter()
    {
        int freeOne = 0;
        boolean success = false;
        try {
            available.acquire();
            for (int j = 0; j < Itotal; j++) {
                if (myInters[j] == null) {
                    Random rand = new Random();
                    int  n1 = rand.nextInt(256);
                    int  n2 = rand.nextInt(256);
                    int  n3 = rand.nextInt(256);
                    int  n4 = rand.nextInt(256);
                    String IP = Integer.toString(n1) + "." + Integer.toString(n2) + "." + Integer.toString(n3) + "." + Integer.toString(n4);
                    //myInters[j] = new Interfaz(IP, );
                    success = true;
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

    public class Interfaz
    {
        String IP;
        String mask;
        String physicalDirection; // Banderas
        String interfaceTicket;
        int bufferNumber = 0;
        Buffer buffer[];

        public Interfaz(String IP, String mask, String physicalDirection, String interfaceTicket, int bufferNumber) {
            this.IP = IP;
            this.mask = mask;
            this.physicalDirection = physicalDirection;
            this.interfaceTicket = interfaceTicket;
            this.bufferNumber = bufferNumber;
            this.buffer = new Buffer[bufferNumber];
        }
    }

    public class Buffer
    {
        String mensaje;

        public Buffer() {

        }

        public void setContent(String content) {
            this.mensaje = content;
        }
    }
}