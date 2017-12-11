package ac.cr.ucr.ci1320.Etapa3;

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
        setpQueue(new PriorityBlockingQueue<>(bufferCount));
        this.setMainBuffers(new BufferEntry[bufferCount]);
        Arrays.fill(getMainBuffers(), new BufferEntry());
        setEmptyPositions(new LinkedBlockingQueue<>(bufferCount));
        this.setBufferCount(bufferCount);

       this.fillEmptyPositions();
    }




    private void fillEmptyPositions(){
        for (int i = 0; i < this.getBufferCount(); i++) {
            getEmptyPositions().offer(i);
        }
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

    public Integer getBufferCount() {
        return bufferCount;
    }

    public void setBufferCount(Integer bufferCount) {
        this.bufferCount = bufferCount;
    }
}
