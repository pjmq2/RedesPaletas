package ac.cr.ucr.ci1320;

import ac.cr.ucr.ci1320.Dispatcher.Dispatcher;
import javafx.util.Pair;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;


public class Client extends Connection {

    public Client() {
    }

    public void startClient(String message){
        try {
            super.createSocket("client",2222,"192.168.0.136");
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            this.outServer.writeUTF(message);
            this.cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void testClient(){
        try {
            outServer = new DataOutputStream(cs.getOutputStream());
            for (int i = 0; i < 2; i++) {
                outServer.writeUTF("Este es el número"+(i+1)+"\nHOLA1\nHOLA2");
            }
            cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
