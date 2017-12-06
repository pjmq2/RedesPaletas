package ac.cr.ucr.ci1320;

public class Interfaz {
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
