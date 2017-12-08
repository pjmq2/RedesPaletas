import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class DataStructures {
    private BlockingQueue<BufferEntry> pQueue;
    private BufferEntry[] mainBuffers;
    private BlockingQueue<Integer> emptyPositions;
    private Integer bufferCount;

    public DataStructures(int bufferCount){
        pQueue = new PriorityBlockingQueue<>(bufferCount);
        this.mainBuffers = new BufferEntry[bufferCount];
        Arrays.fill(getMainBuffers(), new BufferEntry());
        emptyPositions = new LinkedBlockingQueue<>(bufferCount);
        this.bufferCount = bufferCount;

       this.fillEmptyPositions();

    }


    public BlockingQueue<BufferEntry> getpQueue() {
        return pQueue;
    }

    public void setpQueue(BlockingQueue<BufferEntry> pQueue) {
        this.pQueue = pQueue;
    }

    public BufferEntry[] getMainBuffers() {
        return mainBuffers;
    }

    public void setMainBuffers(BufferEntry[] mainBuffers) {
        this.mainBuffers = mainBuffers;
    }

    public BlockingQueue<Integer> getEmptyPositions() {
        return emptyPositions;
    }

    public void setEmptyPositions(BlockingQueue<Integer> emptyPositions) {
        this.emptyPositions = emptyPositions;
    }

    private void fillEmptyPositions(){
        for (int i = 0; i < this.bufferCount; i++) {
            emptyPositions.offer(i);
        }
    }


}
