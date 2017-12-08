package ac.cr.ucr.ci1320.Etapa3;

import java.util.Random;

public class ProcessingThread implements  Runnable {
    public DataStructures dataStructures;
    private Random random;

    public ProcessingThread(DataStructures inputStructures){
        this.dataStructures = inputStructures;
        random = new Random();
    }

    @Override
    public void run(){
        System.out.println("Processing thread starting");

        for(;;){
            try {
                BufferEntry bufferReference = dataStructures.getpQueue().take();  //Get next item from pQueue, wait if theres none
                System.out.println("Processing data from " + bufferReference.getIpPackage().getMessage());
                Thread.sleep(random.nextInt(10) * 100);   //Process it
                dataStructures.getEmptyPositions().put(bufferReference.getArrayPosition());
                System.out.println("Position: " + bufferReference.getArrayPosition() + " Logically erased");
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
