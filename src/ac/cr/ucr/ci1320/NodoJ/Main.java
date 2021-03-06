package ac.cr.ucr.ci1320.NodoJ;

import java.io.IOException;
import java.util.HashMap;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String args[])
    {
        Nodo servidor = julian();
        servidor.iniciar();
    }

    public static Nodo julian(){
        HashMap<String,TablaDirecciones> tablaD= new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("Julian","12.0.0.8",0,5555);
        TablaDirecciones tabla2 = new TablaDirecciones("Sebastian","12.0.20.2",-1,5555);
        TablaDirecciones tabla3 = new TablaDirecciones("Pablo","12.0.0.7",-1,5555);
        TablaDirecciones tabla4 = new TablaDirecciones("Alonso","12.0.0.3", 0,5555);
        TablaDirecciones tabla5 = new TablaDirecciones("Carrito","0",-1,0000);
        TablaDirecciones tabla6 = new TablaDirecciones("Paletas","0",-1,0000);
        TablaDirecciones tabla7 = new TablaDirecciones("Luces","0",-1,0000);
        TablaDirecciones tabla8 = new TablaDirecciones("Legos","0",-1,0000);
        TablaDirecciones tabla9 = new TablaDirecciones("Bolinchas","0",-1,0000);

        tablaD.put("12.0.0.8",tabla1);
        tablaD.put("12.0.20.2",tabla2);
        tablaD.put("12.0.0.7",tabla3);
        tablaD.put("12.0.0.3",tabla4);
        tablaD.put("165.8.6.25",tabla5); //2.0
        tablaD.put("200.5.0.0",tabla6);
        tablaD.put("25.0.0.0",tabla7);
        tablaD.put("201.6.0.0",tabla8);
        tablaD.put("140.90.0.0",tabla9);

        Nodo nodoJulian;

        try {
            InetAddress ipAddr;
            ipAddr = InetAddress.getLocalHost();
            String IP = ipAddr.getHostAddress();
            HashMap<String,String> tablaIP;
            String fake1 = "12.0.0.8";
            String fake2 = "12.0.20.2";
            String fake3 = "12.0.0.7";
            String fake4 = "12.0.0.3";
            tablaIP=getIPtable(fake1, IP, fake2, "0", fake3, "0", fake4, "10.1.130.151"); // La última es la real de Alonso
            nodoJulian = new Nodo(tablaD,IP,tabla1.getPuerto(), tablaIP, fake1, fake2, fake3, fake4);
            return nodoJulian;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getIPtable(String fake1, String real1, String fake2, String real2, String fake3, String real3, String fake4, String real4)
    {
        HashMap<String,String> tablonIP = new HashMap<>();
        tablonIP.put(fake1,real1); // P
        tablonIP.put(fake2,real2); // S
        tablonIP.put(fake3,real3); // J
        tablonIP.put(fake4,real4); // A
        return tablonIP;
    }
}
