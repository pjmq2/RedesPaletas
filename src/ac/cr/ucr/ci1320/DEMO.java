package ac.cr.ucr.ci1320;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class DEMO {
    public static void main(String args[])
    {
        runner();
    }

    public static void runner(){
        // Dirección Final, Dirección mía, Mensaje
        String mensaje1 = "25.0.0.0\n12.0.0.8\n1\n10\nHola Mundo";
        String mensaje2 = "12.0.0.7\n12.0.0.8\n1\n10\nHola Mundo";
        String mensaje3 = "12.0.20.2\n12.0.0.8\n1\n10\n1Hola Mundo";
        String mensaje4 = "12.0.0.8\n12.0.0.8\n1\n10\nHola Mundo";

        HashMap<String,TablaDirecciones> tablaD= new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("Julian","12.0.0.8",0,8888, 9999);
        TablaDirecciones tabla2 = new TablaDirecciones("Sebastian","12.0.20.2",0,7777, 9999);
        TablaDirecciones tabla3 = new TablaDirecciones("Pablo","12.0.0.7",0,7777, 7777);
        TablaDirecciones tabla4 = new TablaDirecciones("Alonso","12.0.0.3", 0,3333, 5000);
        TablaDirecciones tabla5 = new TablaDirecciones("Carrito","165.8.2.0",0,5555, 9999);
        TablaDirecciones tabla6 = new TablaDirecciones("Paletas","200.5.0.0",1,5555, 9999);
        TablaDirecciones tabla7 = new TablaDirecciones("Luces","25.0.0.0",2,5555, 9999);
        TablaDirecciones tabla8 = new TablaDirecciones("Legos","201.6.0.0",2,2222, 9999);
        TablaDirecciones tabla9 = new TablaDirecciones("Bolinchas","140.90.0.0",1,2222, 9999);

        tablaD.put("12.0.0.8",tabla1);
        tablaD.put("12.0.20.2",tabla2);
        tablaD.put("12.0.0.7",tabla3);
        tablaD.put("12.0.0.3",tabla4);
        tablaD.put("165.8.2.0",tabla5);
        tablaD.put("200.5.0.0",tabla6);
        tablaD.put("25.0.0.0",tabla7);
        tablaD.put("201.6.0.0",tabla8);
        tablaD.put("140.90.0.0",tabla9);

        InetAddress ipAddr;
        Nodo nodoJulian;

        try {
            ipAddr = InetAddress.getLocalHost();
            String IP = ipAddr.getHostAddress();
            HashMap<String,String> tablaIP;
            String fake1 = "12.0.0.7";
            String fake2 = "12.0.20.2";
            String fake3 = "12.0.0.8";
            String fake4 = "12.0.0.3";
            tablaIP=getIPtable(fake1, "000.000.0.000", fake2, IP, fake3, "000.000.0.000");
            nodoJulian = new Nodo(tablaD,IP,tabla1.getPuerto(), tablaIP, fake1, fake2, fake3, fake4, tabla1.getBackPuerto());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
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
