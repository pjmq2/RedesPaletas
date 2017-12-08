package ac.cr.ucr.ci1320.Etapa3;

import java.time.LocalTime;
import java.util.concurrent.locks.ReentrantLock;

public class BufferEntry implements Comparable<BufferEntry>{
    private LocalTime time;
    private String data;
    private Boolean valid;
    private ReentrantLock lock;
    private int arrayPosition;

    public BufferEntry() {
        this.time =  LocalTime.now();
        this.lock = new ReentrantLock();

    }

    public BufferEntry(String inputString, int inputPosition) {
        this.time =  LocalTime.now();
        this.setData(inputString);
        this.lock = new ReentrantLock();
        this.setArrayPosition(inputPosition);
    }

    @Override
    public int compareTo(BufferEntry T){
        return this.getTime().compareTo(T.getTime());
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }

    public int getArrayPosition() { return arrayPosition; }

    public void setArrayPosition(int arrayPosition) { this.arrayPosition = arrayPosition; }

    public String getData() {  return data;  }

    public void setData(String data) {  this.data = data;  }
}