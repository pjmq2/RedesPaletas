package ac.cr.ucr.ci1320;

import java.util.HashMap;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String args[])
    {
        //Nodo nodo = julian();
        //nodo.iniciar();
        Servidor servidor = new Servidor(sebastian());
        servidor.iniciar();
    }

    public static Nodo sebastian(){
        HashMap<String,TablaDirecciones> tablaD= new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("Julian","12.0.0.8",0,8888, 9999);
        TablaDirecciones tabla2 = new TablaDirecciones("Carrito","12.0.0.7",1,5555, 9999);
        TablaDirecciones tabla3 = new TablaDirecciones("Luces","12.0.0.7",2,5555, 9999);
        TablaDirecciones tabla4 = new TablaDirecciones("Legos","200.5.0.1",2,2222, 9999);
        TablaDirecciones tabla5 = new TablaDirecciones("Bolinchas","200.5.0.1",1,2222, 9999);
        TablaDirecciones tabla6 = new TablaDirecciones("Pablo","12.0.0.7",0,2222, 9999);
        TablaDirecciones tabla7 = new TablaDirecciones("Paletas","200.5.0.1",0,2222, 9999);
        TablaDirecciones tabla8 = new TablaDirecciones("Alonso","12.0.0.3",0,2222, 9999);
        TablaDirecciones tabla9 = new TablaDirecciones("Sebastian","12.0.20.2",0,2222, 9999);
        tablaD.put("12.0.0.8",tabla1);
        tablaD.put("165.8.2.0",tabla2);
        tablaD.put("25.0.2.5",tabla3);
        tablaD.put("201.6.0.2",tabla4);
        tablaD.put("140.90.0.20",tabla5);
        tablaD.put("12.0.0.7",tabla6);
        tablaD.put("200.5.0.2",tabla7);
        tablaD.put("12.0.0.3",tabla8);
        tablaD.put("12.0.20.2",tabla9);

        Nodo nodoSebastian;

        try {
            InetAddress ipAddr;
            ipAddr = InetAddress.getLocalHost();
            String IP = ipAddr.getHostAddress();
            HashMap<String,String> tablaIP;
            String fake1 = "12.0.0.7";
            String fake2 = "12.0.20.2";
            String fake3 = "12.0.0.8";
            String fake4 = "12.0.0.3";
            tablaIP=getIPtable(fake1, "0", fake2, IP, fake3, "0", fake4, "0"); // La última es la real de Alonso
            nodoSebastian = new Nodo(tablaD,IP,tabla9.getPuerto(), tablaIP, fake1, fake2, fake3, fake4,tabla9.getBackPuerto());
            return nodoSebastian;
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
