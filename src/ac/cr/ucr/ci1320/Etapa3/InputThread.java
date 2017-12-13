package ac.cr.ucr.ci1320.Etapa3;

import java.util.Random;

public class InputThread implements Runnable {
    public DataStructures dataStructures;
    private Random random;
    private String data;

    public InputThread(DataStructures inputStructures, String inputData){
        this.dataStructures = inputStructures;
        random = new Random();
        this.data = inputData;
    }

    @Override
    public void run(){
        try {
            Integer index = dataStructures.getEmptyPositions().poll();
            if(index != null){
                BufferEntry bufferReference = dataStructures.getMainBuffers()[index];
                bufferReference.getLock().lock();
                bufferReference.setData(this.data);
                bufferReference.setArrayPosition(index);
                bufferReference.getLock().unlock();
                System.out.println("Acabo de recibir un:" + this.data);
                dataStructures.getpQueue().put(bufferReference);
            }
            else{
                System.out.println("Package lost, main buffers are all used");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
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


