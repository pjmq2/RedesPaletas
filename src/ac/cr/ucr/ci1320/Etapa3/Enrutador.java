package ac.cr.ucr.ci1320.Etapa3;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Enrutador{
    Nodo nodo;
    int Itotal = 1;
    int bufferNumber = 1;
    //List<Interfaz> misInterfaces;
    String[] físicas = {"Interfaz1", "Interfaz2", "Interfaz3", "Interfaz4", "Interfaz5"};
    String[] IPs = {"12.1.0.1", "12.1.0.2", "12.1.0.3", "12.1.0.4", "12.1.0.5"}; //Acá deben tener direcciones de redes diferentes
    //String[] Masks = {"12.2.0.1", "12.2.0.2", "12.2.0.3", "12.2.0.4", "12.2.0.5"}; //Descartando las mascaras por ahora
    String[] Tickets = {"Primero", "Segundo", "Tercero", "Cuarto", "Quinto"}; //Para qué se usan?
    List<Integer> ports = new ArrayList<Integer>(Arrays.asList( 2222, 3333, 4444, 5555, 6666 )); //Por qué así?
    private Semaphore available;
    Random rand = new Random();

    //Recibe el nodo original, el total de interfaces, el total de buffers por interfaz y
    public Enrutador(Nodo nodo, int Itotal, int bufferNumber, int number)
    {
        this.Itotal = Itotal;
        available = new Semaphore(Itotal, true);
        this.nodo = nodo;
        //misInterfaces = new ArrayList<Interfaz>();
        this.bufferNumber = bufferNumber;
        if((Collections.max(ports) + number*100) < 10000) {
            for (int i = 0; i < ports.size(); ++i) {
                int oldValue = ports.get(i);
                int newValue = oldValue + (number * 100);
                ports.set(i, newValue);
            }
        }
    }

    /*
    public void start(){
        for(int i = 0; i < Itotal; ++i){
            Interfaz inter = new Interfaz();
            misInterfaces.set(i, inter);
            inter.run();
        }
    }
    */
}