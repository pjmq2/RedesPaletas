package ac.cr.ucr.ci1320;

import java.io.IOException;
import java.security.spec.ECField;

public class ReadThread implements Runnable{

    private Server server;
    public ReadThread(Server server){
        this.server = server;
    }

    @Override
    public void run(){
        try {
            this.server.startServer();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
