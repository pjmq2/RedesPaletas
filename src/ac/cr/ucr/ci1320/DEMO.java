package ac.cr.ucr.ci1320;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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

        Map<String,TablaDirecciones> tablaD= new HashMap<>();

        TablaDirecciones tabla2 = new TablaDirecciones("CRR2","12.0.0.7",1,5555); // P
        TablaDirecciones tabla3 = new TablaDirecciones("CRR3","12.0.0.7",1,5555); // P
        TablaDirecciones tabla4 = new TablaDirecciones("TELO1","12.0.0.7",2,5555); // P
        TablaDirecciones tabla5 = new TablaDirecciones("LEOG2","12.0.20.2",3,5555); // S
        TablaDirecciones tabla6 = new TablaDirecciones("Bolinchas.Jorge","12.0.20.2",2,7777); // S
        TablaDirecciones tabla7 = new TablaDirecciones("P2","12.0.20.2",1,7777); // S

        tablaD.put("165.8.2.0",tabla2); // P
        tablaD.put("165.8.48.2",tabla3); // P
        tablaD.put("25.0.2.5",tabla4); // P
        tablaD.put("201.6.0.2",tabla5); // S
        tablaD.put("140.90.0.20",tabla6); // S
        tablaD.put("200.5.0.2",tabla7); // S

        Map<String,String> tablaIP = new HashMap<>();
        tablaIP.put("12.0.0.7","192.168.0.161"); // P
        tablaIP.put("12.0.20.2","10.1.130.211"); // S
        tablaIP.put("12.0.0.8","10.1.130.84"); // J

        InetAddress ipAddr;

        Nodo nodoJulian;

        try {
            ipAddr = InetAddress.getLocalHost();
            String IP = ipAddr.getHostAddress();
            Solicitante cliente = new Solicitante();
            Analizador analizador = new Analizador(tablaD,tablaIP,IP);
            cliente.sendMessage(mensaje2,"192.168.0.166",7777,IP,analizador);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
}