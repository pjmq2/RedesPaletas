package ac.cr.ucr.ci1320.Socket_Radio;

import java.io.IOException;

public class Server {
    public static void main(String [] args){
        if(args.length<=0){
            System.out.println("Pass IP Address of server as argument");
            return;
        }

        try{
            new Client(args[0]).Start();
        }
        catch(Exception ex){
            System.out.println("Error in client!!!");
        }
    }
}
