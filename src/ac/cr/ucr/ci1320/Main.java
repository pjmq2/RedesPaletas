package ac.cr.ucr.ci1320;

import java.util.HashMap;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class Main {
    public static void main(String args[])
    {
        Nodo nodo = sebastian();
        nodo.iniciar();
        /*
        Analizador anal = new Analizador(nodo.getDTable(), nodo.getIPTable(), nodo.getIP()); //REVISAR
        Servidor servidor = new Servidor(sebastian(), anal);
        servidor.iniciar();
        */
    }

    public static Nodo sebastian(){
        HashMap<String,TablaDirecciones> tablaD= new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("Julian","12.0.0.8",0,0000, 0000);
        TablaDirecciones tabla2 = new TablaDirecciones("Sebastian","12.0.20.2",0,8888, 0000);
        TablaDirecciones tabla3 = new TablaDirecciones("Pablo","12.0.0.7",0,0000, 0000);
        TablaDirecciones tabla4 = new TablaDirecciones("Alonso","12.0.0.3", 0,0000, 0000);
        TablaDirecciones tabla5 = new TablaDirecciones("Kevin","200.5.0.2", 0,10003, 10003);
        TablaDirecciones tabla6 = new TablaDirecciones("Carrito","0",-1,0000, 0000);
        TablaDirecciones tabla7 = new TablaDirecciones("Paletas","200.5.0.2",0,0000, 0000);
        TablaDirecciones tabla8 = new TablaDirecciones("Luces","0",-1,0000, 0000);
        TablaDirecciones tabla9 = new TablaDirecciones("Legos","0",-1,0000, 0000);
        TablaDirecciones tabla10 = new TablaDirecciones("Bolinchas","0",-1,0000, 0000);

        tablaD.put("12.0.0.8",tabla1);
        tablaD.put("12.0.20.2",tabla2);
        tablaD.put("12.0.0.7",tabla3);
        tablaD.put("12.0.0.3",tabla4);
        tablaD.put("200.5.0.2",tabla5);
        tablaD.put("165.8.6.25",tabla6); //2.0
        tablaD.put("200.5.0.1",tabla7);
        tablaD.put("25.0.0.0",tabla8);
        tablaD.put("201.6.0.0",tabla9);
        tablaD.put("140.90.0.0",tabla10);

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
            String fake5 = "200.5.0.2";
            tablaIP=getIPtable(fake1, "0", fake2, IP, fake3, "0", fake4, "0", fake5, "10.1.130.107"); // La ultima es la real de Alonso
            nodoSebastian = new Nodo(tablaD,IP,tabla2.getPuerto(), tablaIP, fake1, fake2, fake3, fake4, fake5);
            return nodoSebastian;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getIPtable(String fake1, String real1, String fake2, String real2, String fake3, String real3, String fake4, String real4, String fake5, String real5)
    {
        HashMap<String,String> tablonIP = new HashMap<>();
        tablonIP.put(fake1,real1); // P
        tablonIP.put(fake2,real2); // S
        tablonIP.put(fake3,real3); // J
        tablonIP.put(fake4,real4); // A
        tablonIP.put(fake5,real5); // A
        return tablonIP;
    }
}
