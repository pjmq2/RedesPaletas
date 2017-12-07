package ac.cr.ucr.ci1320;

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
    private String Alonso;
    private String wishedFaker;
    private Enrutador enrutadores[];
    private Terminal terminal;

    public Nodo(HashMap<String, TablaDirecciones> tablaD, String miIp, int miPuerto, HashMap<String,String>tablaIP, String fake1, String fake2, String fake3, String fake4) { // El fake 4 debe ser el IP del Dispatcher
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
        this.tablaIP = tablaIP;
        this.analizer = new Analizador(this);
        this.miIpFalsa = fake3;
        this.Alonso = fake4;
        this.wishedFaker = "";
        this.terminal = new Terminal(this, fake4);
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

    public  void setWished (String wish) { this.wishedFaker = wish; }

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
            if(!(fake.equals(Alonso)))
            {
                tabla.modifyBackPort(port);
            }
            return true;
        }
    }

    public void iniciar()
    {
        // Configurar número de Enrutadores y Ventanas

        String entrada[] = new String[3];
        String[] mensajes = {"¿Cuantos Enrutadores se deben usar?", "ERROR: El número de enrutadores debe ser un entero.", "¿Cuantas Interfaces se debe usar?", "ERROR: El número de interfaces debe ser un entero.", "¿Cuantos Buffer se deben usar?", "ERROR: El número de bufferes debe ser un entero."};
        int[] valores = new int[3]; // enrutadores, interfaces, bufferes

        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < mensajes.length; i+=2) {
            System.out.println(mensajes[i]);
            entrada[(i/2)] = scanner.nextLine();
            if(entrada[(i/2)] != null && entrada[(i/2)].matches("[-+]?\\d*\\.?\\d+")) {
                valores[(i/2)] = Integer.parseInt(entrada[(i/2)]);
            }
            else{
                System.out.println(mensajes[(i+1)]);
                i-=2;
            }
        }

        scanner = null;
        this.enrutadores = new Enrutador[valores[0]];

        for(int i = 0; i < valores[0]; ++i){
            enrutadores[i] = new Enrutador(this, valores[1], valores[2], i); // Nodo nodo, int Itotal, int bufferNumber, int number
        }

        // Leer línea de la terminal.

        this.terminal.terminal();
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
