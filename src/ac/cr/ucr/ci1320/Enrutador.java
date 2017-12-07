package ac.cr.ucr.ci1320;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * Created by B50587 on 05/12/2017.
 */
public class Enrutador{
    Nodo nodo;
    int Itotal = 5;
    int bufferNumber = 0;
    List<Interfaz> myInters;
    String[] físicas = {"Interfaz1", "Interfaz2", "Interfaz3", "Interfaz4", "Interfaz5"};
    String[] IPs = {"12.1.0.1", "12.1.0.2", "12.1.0.3", "12.1.0.4", "12.1.0.5"};
    String[] Masks = {"12.2.0.1", "12.2.0.2", "12.2.0.3", "12.2.0.4", "12.2.0.5"};
    String[] Tickets = {"Primero", "Segundo", "Tercero", "Cuarto", "Quinto"};
    List<Integer> ports = new ArrayList<Integer>(Arrays.asList( 2222, 3333, 4444, 5555, 6666 ));
    private Semaphore available;
    Random rand = new Random();

    public Enrutador(Nodo nodo, int Itotal, int bufferNumber, int number) {
        this.Itotal = Itotal;
        available = new Semaphore(Itotal, true);
        this.nodo = nodo;
        myInters = new ArrayList<Interfaz>();
        this.bufferNumber = bufferNumber;
        if((Collections.max(ports) + number*100) < 10000) {
            for (int i = 0; i < ports.size(); ++i) {
                int oldValue = ports.get(i);
                int newValue = oldValue + (number * 100);
                ports.set(i, newValue);
            }
        }
    }

    public void start(){
        for(int i = 0; i < Itotal; ++i){
            Interfaz inter = new Interfaz();
            myInters.set(i, inter);
            inter.run();
        }
    }
}