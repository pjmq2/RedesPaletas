package ac.cr.ucr.ci1320.Etapa3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner( System.in );

    private static Map<String,TablaDirecciones> Enrutador1() //Aca solo se ponen los que no son directos
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>(); //Cambiar etiquetas despues
        TablaDirecciones tabla1 = new TablaDirecciones("163.178.15.15","RouterHobbit1",1); //Se usan los nodos?
        tablaD.put("12.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("163.178.15.15","RouterHobbit1",1);
        tablaD.put("12.0.0.6",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("163.178.15.15","RouterHobbit1",1);
        tablaD.put("12.0.0.7",tabla3);
        return tablaD;
    }

    private static Map<String,TablaDirecciones> Enrutador2()
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("163.178.17.17","RouterHobbit2",1);
        tablaD.put("11.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("163.178.17.17","RouterHobbit2",1);
        tablaD.put("193.34.11.22",tabla2);
        return tablaD;
    }

    /*
    private static Map<String,TablaDirecciones> Enrutador1()
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("168.5.0.0","RouterHobbit1",1);
        tablaD.put("192.118.1.0",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("168.5.0.0","RouterHobbit1",1);
        tablaD.put("16.0.28.0",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("168.5.0.0","RouterHobbit1",1);
        tablaD.put("25.0.0.0",tabla3);
        TablaDirecciones tabla4 = new TablaDirecciones("168.5.0.0","RouterHobbit1",1);
        tablaD.put("10.8.0.0",tabla4);
        return tablaD;
    }

    private static Map<String,TablaDirecciones> Enrutador2()
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("168.5.0.0","RouterHobbit2",1);
        tablaD.put("200.6.0.0",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("168.5.0.0","RouterHobbit2",1);
        tablaD.put("178.8.60.0",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("168.5.0.0","RouterHobbit2",1);
        tablaD.put("123.45.67.0",tabla3);
        return tablaD;
    }
    */

    private static void correr()
    {
        int cantInterfaces; //La cantidad de interfaces
        int cantBuf;
        int tipoEnrutador = 0;
        String ipDispatcher = "165.8.6.25"; //El ip del dispatcher
        String ripDispatcher = "";
        Map<String,TablaDirecciones> tablaD = new HashMap<>();

        System.out.print( "Digite la cantidad de interfaces (de 1 a 6): \n" );
        cantInterfaces = Integer.parseInt(scanner.nextLine());

        System.out.print( "Digite la cantidad de buffers por interfaz (de 1 a 500): \n" );
        cantBuf = Integer.parseInt(scanner.nextLine());

        while(tipoEnrutador != 1 && tipoEnrutador != 2)
        {
            System.out.print( "Seleccione el tipo de enrutador (1 o 2): \n" );
            tipoEnrutador = Integer.parseInt(scanner.nextLine());
            if(tipoEnrutador == 1)
            {
                tablaD = Enrutador1();
            }
            else if (tipoEnrutador == 2)
            {
                tablaD = Enrutador2();
            }
            else
            {
                System.out.print( "Numero elegido incorrecto \n" );
            }
        }

        if(cantInterfaces == 1) {
            System.out.print("Digite la direccion real del Dispatcher: \n");
            ripDispatcher = scanner.nextLine();
        }

        String[] ips = new String[cantInterfaces]; //Los ips falsos de dichas interfaces
        String[] fisicos = new String[cantInterfaces]; //Las direcciones fisicas

        for(int i=0; i<cantInterfaces; i++)
        {
            System.out.print( "Para la interfaz numero " + (i+1) + ": \n" );
            System.out.print( "Digite el ip falso: \n" );
            ips[i] = scanner.nextLine();
            System.out.print( "Digite la direccion fisica: \n" );
            fisicos[i] = scanner.nextLine();
        }
        for(int j=0; j<cantInterfaces; j++)
        {
            System.out.println( "Interfaz numero" + (j+1) + " = " + ips[j] + " _ " + fisicos[j] + "\n" );
        }
        Enrutador enrutador = new Enrutador();
        enrutador.start(cantInterfaces, ips, fisicos, ipDispatcher, ripDispatcher, tablaD, cantBuf);
    }


    private static void prueba() //Solo para hacer pruebas rapidas
    {
        int cantInterfaces = 1; //La cantidad de interfaces
        int cantBuf = 10;
        String ipDispatcher = "11.101.6.8"; //El ip del dispatcher
        String ripDispatcher = "10.100.2.4";
        Map<String,TablaDirecciones> tablaD = new HashMap<>();
        tablaD = Enrutador1();

        String[] ips = new String[cantInterfaces]; //Los ips falsos de dichas interfaces
        String[] fisicos = new String[cantInterfaces]; //Las direcciones fisicas

        for(int i = 0; i<cantInterfaces; i++)
        {
            ips[i] = "12.0.0."+i;
            fisicos[i] = "Kareok"+i;
        }
        Enrutador enrutador = new Enrutador();
        enrutador.start(cantInterfaces, ips, fisicos, ipDispatcher, ripDispatcher, tablaD, cantBuf);
    }


    public static void main(String args[]){
        //correr();
        prueba();
    }
}
