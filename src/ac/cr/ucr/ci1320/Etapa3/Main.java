package ac.cr.ucr.ci1320.Etapa3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner( System.in );

    private static Map<String,TablaDirecciones> Enrutador1()
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("168.5.0.0","RouterHobbit1",1);
        tablaD.put("192.118.1.0",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("168.5.0.0","RouterHobbit1",1);
        tablaD.put("06.0.28.0",tabla2);
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

    public static void main(String args[])
    {
        int cantInterfaces = 0; //La cantidad de interfaces
        int cantBuf = 0;
        int tipoEnrutador = 0;
        String ipDispatcher = "12.0.0.8"; //El ip del dispatcher
        Map<String,TablaDirecciones> tablaD = new HashMap<>();

        System.out.print( "Digite la cantidad de interfaces (de 1 a 6): \n" );
        cantInterfaces = Integer.parseInt(scanner.nextLine());

        System.out.print( "Digite la cantidad de buffers por interfaz (de 1 a 500): \n" );
        cantBuf = Integer.parseInt(scanner.nextLine());

        while(tipoEnrutador != 1 && tipoEnrutador != 2)
        {
            System.out.print( "Seleccione el tipo de interfaz (1 o 2): \n" );
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
        Enrutador enrutador = new Enrutador(cantInterfaces, ips, fisicos, ipDispatcher, tablaD, cantBuf);
    }
}
