package ac.cr.ucr.ci1320;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class DEMO {
    public static void main(String args[])
    {
        runner();
    }

    public static void runner(){
        try
        {
        Socket sock = new Socket("192.168.0.136", 8888);
        InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
        BufferedReader reader = new BufferedReader(streamreader);
        DataOutputStream writer = new DataOutputStream(sock.getOutputStream());
        String envio = "Hola";
        writer.writeUTF(envio);
        writer.flush();
        }
        catch (Exception ex) {
            System.out.println("Message was not sent.\n");
        }
    }

    public static HashMap<String, String> getIPtable(String fake1, String real1, String fake2, String real2, String fake3, String real3)
    {
        HashMap<String,String> tablonIP = new HashMap<>();
        tablonIP.put(fake1,real1); // P
        tablonIP.put(fake2,real2); // S
        tablonIP.put(fake3,real3); // J
        return tablonIP;
    }
}
