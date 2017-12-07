package ac.cr.ucr.ci1320;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import static java.lang.Integer.valueOf;


public class Nodo {
    private HashMap<String, TablaDirecciones> tablaD;
    private HashMap<String, String> tablaIP;
    private String miIp;
    private String miIpFalsa;
    private Servidor server;
    private int miPuerto;
    private String Alonso;
    private String wishedFaker;
    private Terminal terminal;
    private String bestWish;
    private int bestWishValue;
    private int comparedWishes;
    private Semaphore canWish = new Semaphore(1, true);
    private Semaphore getWish = new Semaphore(0, true);
    private Semaphore nomineeTurn = new Semaphore(1, true);

    public Nodo(HashMap<String, TablaDirecciones> tablaD, String miIp, int miPuerto, HashMap<String, String> tablaIP, String fake1, String fake2, String fake3, String fake4) { // El fake 4 debe ser el IP del Dispatcher
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
        this.tablaIP = tablaIP;
        this.miIpFalsa = fake3;
        this.Alonso = fake4;
        this.wishedFaker = "";
        this.terminal = new Terminal(this, fake4);
        this.server = new Servidor(this);
    }

    public HashMap<String,TablaDirecciones> getDTable() { return  this.tablaD; }

    public String getmyFakeAddress() { return this.miIpFalsa; }

    public int getmyPort() { return this.miPuerto; }

    public HashMap<String, String> getIPTable() { return this.tablaIP; }

    public String getmyRealIP() { return this.miIp; }

    public  String getWishedFaker() { return this.wishedFaker; }

    public void wish (String wish) {
        try {
            canWish.acquire();
            this.wishedFaker = wish;
        }
        catch(Exception ex){
            System.out.println("Error al pedir un deseo.");
        }
    }

    public void giveWish () {
        try {
            // Dejar el "a travez del wished como la respuesta"
            TablaDirecciones tabla = tablaD.get(this.wishedFaker);
            if(tabla != null) {
                tabla.modifyaTravez(this.bestWish);
            }
            else {
                System.out.println("Error el deseo no se cumplió.");
            }
            //Dejar que la terminal siga
            canWish.release();
            getWish.release();
        }
        catch(Exception ex){
            System.out.println("Error al recivir un deseo.");
        }
    }

    public void nominateWish (int distance, String nominee) {
        int number = 0;
        try {
            nomineeTurn.acquire();
            ++this.comparedWishes;
            if(distance < bestWishValue){
                this.bestWish = nominee;
                this.bestWishValue = distance;
            }
            number = comparedWishes;
            nomineeTurn.release();
        }
        catch(Exception ex){
            System.out.println("Error al recivir un deseo.");
        }
        if(number == tablaIP.size()){
            this.giveWish();
        }
    }

    public String getWish () {
        try {
            getWish.acquire();
            return this.wishedFaker;
        }
        catch(Exception ex){
            System.out.println("Error al recivir un deseo.");
            return "";
        }
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
            tabla.modifyaTravez(fake);
            if(!(fake.equals(Alonso)))
            {
                tabla.modifyBackPort(port);
            }
            return true;
        }
    }

    public void iniciar() {
        // Abrir Servidor

        server.iniciar();

        // Leer línea de la terminal.

        this.terminal.terminal();
    }
}