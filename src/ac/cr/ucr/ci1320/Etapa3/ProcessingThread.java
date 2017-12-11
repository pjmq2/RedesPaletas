package ac.cr.ucr.ci1320.Etapa3;

import java.util.Random;

public class ProcessingThread implements  Runnable {
    public Interfaz localInterface;
    private Random random;

    public ProcessingThread(Interfaz inputInterface){
        this.localInterface = inputInterface;
        random = new Random();
    }

    @Override
    public void run(){
        System.out.println("Processing thread starting");

        for(;;){
            try {
                BufferEntry bufferReference = localInterface.getDataStructures().getpQueue().take();  //Get next item from pQueue, wait if theres none
                System.out.println("Processing data" + bufferReference.getData());
                localInterface.recibirTransmicion(bufferReference.getData());
                localInterface.getDataStructures().getEmptyPositions().put(bufferReference.getArrayPosition());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Sacar la referencia del siguiente a sacar
     *
     * Procesarlo
     *
     * Agregar la posición del mae y agregarla a emptyPositions
     */
}
