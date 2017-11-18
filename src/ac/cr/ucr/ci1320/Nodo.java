package ac.cr.ucr.ci1320;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.valueOf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static java.lang.Integer.valueOf;


public class Nodo {
    private HashMap<String,TablaDirecciones> tablaD ;
    private HashMap<String,String> tablaIP;
    private String miIp;
    private String miIpFalsa;
    private int miPuerto;
    private Analizador analizer;
    private Servidor server;
    private String Alonso;
    private String wishedFaker;

    public Nodo(HashMap<String, TablaDirecciones> tablaD, String miIp, int miPuerto, HashMap<String,String>tablaIP, String fake1, String fake2, String fake3, String fake4) {
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
        this.tablaIP = tablaIP;
        this.analizer = new Analizador(tablaD, tablaIP, miIp);
        this.server = new Servidor(this, this.analizer);
        this.miIpFalsa = fake3;
        this.Alonso = fake4;
        this.wishedFaker = "";
    }

    public String getIP()
    {
        return this.miIp;
    }

    public int getPort()
    {
        return this.miPuerto;
    }

    public String getFake()
    {
        return this.miIpFalsa;
    }

    public Analizador getAnalizer() { return this.analizer; }

    public HashMap<String, String> getIPTable()
    {
        return this.tablaIP;
    }

    public  String getWished () { return  this.wishedFaker; }

    public HashMap<String,TablaDirecciones> getDTable() { return  this.tablaD; }

    public boolean modifyIPTableEntry(String fake, String real, int port)
    {
        String faker = tablaIP.get(fake);
        if(faker == null)
        {
            return false;
        }
        else
        {
            tablaIP.put(fake, real);
            TablaDirecciones tabla = tablaD.get(fake);
            tabla.modifyPort(port);
            return true;
        }
    }

    public void iniciar()
    {
        // Abrir Servidor

        server.iniciar();

        // Leer línea de la terminal.

    }

    public String getTablaIPString() {
        String returnValue = new String();
        Set<String> keys = tablaIP.keySet();
        String[] array = keys.toArray(new String[keys.size()]);

        for(int i = 0; i < array.length; ++i) {
            if (!(tablaIP.get(array[i]).equalsIgnoreCase("0"))) {
                if(!(returnValue.equals(""))) { returnValue = returnValue + "#"; }
                returnValue = returnValue + tablaIP.get(array[i]) + "," + array[i] + "," + tablaD.get(array[i]).getPuerto();
            }
        }
        return returnValue;
    }


}
