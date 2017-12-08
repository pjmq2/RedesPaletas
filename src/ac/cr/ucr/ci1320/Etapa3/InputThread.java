package ac.cr.ucr.ci1320.Etapa3;

import java.util.Random;

public class InputThread implements Runnable {
    public DataStructures dataStructures;
    private Random random;

    public InputThread(DataStructures inputStructures){
        this.dataStructures = inputStructures;
        random = new Random();
    }

    @Override
    public void run(){
        for (int i = 0; i < 30; i++) {
            try {
                Integer index = dataStructures.getEmptyPositions().poll();                  //Get new position
                if (index != null) {
                    BufferEntry bufferReference = dataStructures.getMainBuffers()[i];       //Get a reference to that position

                    bufferReference.getLock().lock();                                       //Lock that position
                    BufferEntry newBuffer = new BufferEntry(Thread.currentThread().getName() + " number: " + i, index);          //Create a new bufferEntry
                    dataStructures.getMainBuffers()[i] = newBuffer;                         //Store it on mainBuffers
                    System.out.println("Thread: " + Thread.currentThread().getName() +
                            " is modifying entry --> " + index);

                    this.sleepTimer(random.nextInt(10) * 100);
                    bufferReference.getLock().unlock();                                     //Unlock that mainBuffers position


                    dataStructures.getpQueue().put(newBuffer);                          //Add that new buffer entry to pQueue
                                                                                        //No need to sync cause its object type
                } else {
                    System.out.println("There's no empty positions".toUpperCase());
                    Thread.sleep(300);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }
        System.out.println("                            TERMINA --> " + Thread.currentThread().getName());
    }

/** La idea es
 *  Sacar un espacio vacío de emptyPositions
 *  Escribir algo en el lugar que retorna la queue
 *  Bloquear la pQueue, insertar esa referencia
 *  Desbloquear
 *
 *  //this.sleepTimer(random.nextInt(10)* 100);
   **/


    private void sleepTimer(int timer){
        try {
            Thread.sleep(timer);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    protected synchronized String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();

        while (salt.length() < random.nextInt(20) + 10) { // length of the random string.
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}


