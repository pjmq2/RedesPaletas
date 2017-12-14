package ac.cr.ucr.ci1320.Etapa3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner( System.in );

    private static Map<String,TablaDirecciones> Enrutador11TablaD() //Aca solo se ponen los que no son directos
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>(); //Cambiar etiquetas despues
        TablaDirecciones tabla1 = new TablaDirecciones("163.178.17.17","Terminal 1",1); //Se usan los nodos?
        tablaD.put("12.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("163.178.17.17","Terminal 4",1);
        tablaD.put("12.0.0.6",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("163.178.17.17","Terminal 6",1);
        tablaD.put("12.0.0.7",tabla3);
        TablaDirecciones tabla4 = new TablaDirecciones("11.50.70.12","Terminal 2",0);
        tablaD.put("11.70.4.5",tabla4);
        TablaDirecciones tabla5 = new TablaDirecciones("193.34.11.7","Terminal 3",0);
        tablaD.put("193.34.11.22",tabla5);
        TablaDirecciones tabla6 = new TablaDirecciones("163.178.20.20","Terminal 5",0);
        tablaD.put("163.178.20.20",tabla6);
        TablaDirecciones tabla7 = new TablaDirecciones("163.178.30.30","Terminal 7",0);
        tablaD.put("163.178.30.30",tabla7);
        TablaDirecciones tabla8 = new TablaDirecciones("163.178.15.15","Router 1.1",0);
        tablaD.put("163.178.15.15",tabla8);
        TablaDirecciones tabla9 = new TablaDirecciones("11.50.70.12","Router 1.2",0);
        tablaD.put("11.50.70.12",tabla9);
        TablaDirecciones tabla10 = new TablaDirecciones("193.34.11.7","Router 1.3",0);
        tablaD.put("193.34.11.7",tabla10);
        return tablaD;
    }

    private static Map<String,TablaDirecciones> Enrutador12TablaD() //Aca solo se ponen los que no son directos
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>(); //Cambiar etiquetas despues
        TablaDirecciones tabla1 = new TablaDirecciones("163.178.15.15","Terminal 1",1); //Se usan los nodos?
        tablaD.put("12.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("163.178.15.15","Terminal 4",1);
        tablaD.put("12.0.0.6",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("163.178.15.15","Terminal 6",1);
        tablaD.put("12.0.0.7",tabla3);
        TablaDirecciones tabla4 = new TablaDirecciones("11.70.4.5","Terminal 2",0);
        tablaD.put("11.70.4.5",tabla4);
        TablaDirecciones tabla5 = new TablaDirecciones("193.34.11.7","Terminal 3",0);
        tablaD.put("193.34.11.22",tabla5);
        TablaDirecciones tabla6 = new TablaDirecciones("163.178.15.15","Terminal 5",0);
        tablaD.put("163.178.20.20",tabla6);
        TablaDirecciones tabla7 = new TablaDirecciones("163.178.15.15","Terminal 7",0);
        tablaD.put("163.178.30.30",tabla7);
        TablaDirecciones tabla8 = new TablaDirecciones("163.178.15.15","Router 1.1",0);
        tablaD.put("163.178.15.15",tabla8);
        TablaDirecciones tabla9 = new TablaDirecciones("11.50.70.12","Router 1.2",0);
        tablaD.put("11.50.70.12",tabla9);
        TablaDirecciones tabla10 = new TablaDirecciones("193.34.11.7","Router 1.3",0);
        tablaD.put("193.34.11.7",tabla10);
        return tablaD;
    }

    private static Map<String,TablaDirecciones> Enrutador13TablaD() //Aca solo se ponen los que no son directos
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>(); //Cambiar etiquetas despues
        TablaDirecciones tabla1 = new TablaDirecciones("163.178.15.15","Terminal 1",1); //Se usan los nodos?
        tablaD.put("12.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("163.178.15.15","Terminal 4",1);
        tablaD.put("12.0.0.6",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("163.178.15.15","Terminal 6",1);
        tablaD.put("12.0.0.7",tabla3);
        TablaDirecciones tabla4 = new TablaDirecciones("11.50.70.12","Terminal 2",0);
        tablaD.put("11.70.4.5",tabla4);
        TablaDirecciones tabla5 = new TablaDirecciones("193.34.11.22","Terminal 3",0);
        tablaD.put("193.34.11.22",tabla5);
        TablaDirecciones tabla6 = new TablaDirecciones("163.178.15.15","Terminal 5",0);
        tablaD.put("163.178.20.20",tabla6);
        TablaDirecciones tabla7 = new TablaDirecciones("163.178.15.15","Terminal 7",0);
        tablaD.put("163.178.30.30",tabla7);
        TablaDirecciones tabla8 = new TablaDirecciones("163.178.15.15","Router 1.1",0);
        tablaD.put("163.178.15.15",tabla8);
        TablaDirecciones tabla9 = new TablaDirecciones("11.50.70.12","Router 1.2",0);
        tablaD.put("11.50.70.12",tabla9);
        TablaDirecciones tabla10 = new TablaDirecciones("193.34.11.7","Router 1.3",0);
        tablaD.put("193.34.11.7",tabla10);
        return tablaD;
    }

    private static Map<String,TablaDirecciones> Enrutador21TablaD() //Aca solo se ponen los que no son directos
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>(); //Cambiar etiquetas despues
        TablaDirecciones tabla1 = new TablaDirecciones("12.0.9.8","Terminal 1",1); //Se usan los nodos?
        tablaD.put("12.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("12.0.9.8","Terminal 4",1);
        tablaD.put("12.0.0.6",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("12.0.9.8","Terminal 6",1);
        tablaD.put("12.0.0.7",tabla3);
        TablaDirecciones tabla4 = new TablaDirecciones("163.178.15.15","Terminal 2",0);
        tablaD.put("11.70.4.5",tabla4);
        TablaDirecciones tabla5 = new TablaDirecciones("163.178.15.15","Terminal 3",0);
        tablaD.put("193.34.11.22",tabla5);
        TablaDirecciones tabla6 = new TablaDirecciones("163.178.20.20","Terminal 5",0);
        tablaD.put("163.178.20.20",tabla6);
        TablaDirecciones tabla7 = new TablaDirecciones("163.178.30.30","Terminal 7",0);
        tablaD.put("163.178.30.30",tabla7);
        return tablaD;
    }




    private static Map<String,TablaDirecciones> Enrutador22TablaD() //Aca solo se ponen los que no son directos
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>(); //Cambiar etiquetas despues
        TablaDirecciones tabla1 = new TablaDirecciones("12.70.4.5","Terminal 1",1); //Se usan los nodos?
        tablaD.put("12.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("12.0.0.6","Terminal 4",1);
        tablaD.put("12.0.0.6",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("12.0.0.7","Terminal 6",1);
        tablaD.put("12.0.0.7",tabla3);
        TablaDirecciones tabla4 = new TablaDirecciones("163.178.17.17","Terminal 2",0);
        tablaD.put("11.70.4.5",tabla4);
        TablaDirecciones tabla5 = new TablaDirecciones("163.178.17.17","Terminal 3",0);
        tablaD.put("193.34.11.22",tabla5);
        TablaDirecciones tabla6 = new TablaDirecciones("163.178.17.17","Terminal 5",0);
        tablaD.put("163.178.20.20",tabla6);
        TablaDirecciones tabla7 = new TablaDirecciones("163.178.17.17","Terminal 7",0);
        tablaD.put("163.178.30.30",tabla7);
        return tablaD;
    }

    private static Map<String,TablaDirecciones> Red12TablaD() //Aca solo se ponen los que no son directos
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>(); //Cambiar etiquetas despues
        TablaDirecciones tabla1 = new TablaDirecciones("12.70.4.5","Terminal 1",1); //Se usan los nodos?
        tablaD.put("12.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("12.0.0.6","Terminal 4",1);
        tablaD.put("12.0.0.6",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("12.0.0.7","Terminal 6",1);
        tablaD.put("12.0.0.7",tabla3);
        TablaDirecciones tabla4 = new TablaDirecciones("12.0.9.8","Terminal 2",0);
        tablaD.put("11.70.4.5",tabla4);
        TablaDirecciones tabla5 = new TablaDirecciones("12.0.9.8","Terminal 3",0);
        tablaD.put("193.34.11.22",tabla5);
        TablaDirecciones tabla6 = new TablaDirecciones("12.0.9.8","Terminal 5",0);
        tablaD.put("163.178.20.20",tabla6);
        TablaDirecciones tabla7 = new TablaDirecciones("12.0.9.8","Terminal 7",0);
        tablaD.put("163.178.30.30",tabla7);
        return tablaD;
    }

    private static Map<String,TablaDirecciones> Red163TablaD() //Aca solo se ponen los que no son directos
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>(); //Cambiar etiquetas despues
        TablaDirecciones tabla1 = new TablaDirecciones("163.178.17.17","Terminal 1",1); //Se usan los nodos?
        tablaD.put("12.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("163.178.17.17","Terminal 4",1);
        tablaD.put("12.0.0.6",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("163.178.17.17","Terminal 6",1);
        tablaD.put("12.0.0.7",tabla3);
        TablaDirecciones tabla4 = new TablaDirecciones("163.178.15.15","Terminal 2",0);
        tablaD.put("11.70.4.5",tabla4);
        TablaDirecciones tabla5 = new TablaDirecciones("163.178.15.15","Terminal 3",0);
        tablaD.put("193.34.11.22",tabla5);
        TablaDirecciones tabla6 = new TablaDirecciones("163.178.20.20","Terminal 5",0);
        tablaD.put("163.178.20.20",tabla6);
        TablaDirecciones tabla7 = new TablaDirecciones("163.178.30.30","Terminal 7",0);
        tablaD.put("163.178.30.30",tabla7);
        return tablaD;
    }

    private static Map<String,TablaDirecciones> Red11TablaD() //Aca solo se ponen los que no son directos
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>(); //Cambiar etiquetas despues
        TablaDirecciones tabla1 = new TablaDirecciones("11.50.70.12","Terminal 1",1); //Se usan los nodos?
        tablaD.put("12.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("11.50.70.12","Terminal 4",1);
        tablaD.put("12.0.0.6",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("11.50.70.12","Terminal 6",1);
        tablaD.put("12.0.0.7",tabla3);
        TablaDirecciones tabla4 = new TablaDirecciones("11.70.4.5","Terminal 2",0);
        tablaD.put("11.70.4.5",tabla4);
        TablaDirecciones tabla5 = new TablaDirecciones("11.50.70.12","Terminal 3",0);
        tablaD.put("193.34.11.22",tabla5);
        TablaDirecciones tabla6 = new TablaDirecciones("11.50.70.12","Terminal 5",0);
        tablaD.put("163.178.20.20",tabla6);
        TablaDirecciones tabla7 = new TablaDirecciones("11.50.70.12","Terminal 7",0);
        tablaD.put("163.178.30.30",tabla7);
        return tablaD;
    }

    private static Map<String,TablaDirecciones> Red193TablaD() //Aca solo se ponen los que no son directos
    {
        Map<String,TablaDirecciones> tablaD = new HashMap<>(); //Cambiar etiquetas despues
        TablaDirecciones tabla1 = new TablaDirecciones("193.34.11.7","Terminal 1",1); //Se usan los nodos?
        tablaD.put("12.70.4.5",tabla1);
        TablaDirecciones tabla2 = new TablaDirecciones("193.34.11.7","Terminal 4",1);
        tablaD.put("12.0.0.6",tabla2);
        TablaDirecciones tabla3 = new TablaDirecciones("193.34.11.7","Terminal 6",1);
        tablaD.put("12.0.0.7",tabla3);
        TablaDirecciones tabla4 = new TablaDirecciones("193.34.11.7","Terminal 2",0);
        tablaD.put("11.70.4.5",tabla4);
        TablaDirecciones tabla5 = new TablaDirecciones("193.34.11.22","Terminal 3",0);
        tablaD.put("193.34.11.22",tabla5);
        TablaDirecciones tabla6 = new TablaDirecciones("193.34.11.7","Terminal 5",0);
        tablaD.put("163.178.20.20",tabla6);
        TablaDirecciones tabla7 = new TablaDirecciones("193.34.11.7","Terminal 7",0);
        tablaD.put("163.178.30.30",tabla7);
        return tablaD;
    }

    private static Map<String,TablaIp> Enrutador1TablaIp(String [] dirReales ){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp(dirReales[2],8181); //Terminal 2
        tablaIp.put("11.70.4.5",tabla1);
        TablaIp tabla2 = new TablaIp(dirReales[3],8182); //Terminal 3
        tablaIp.put("193.34.11.22",tabla2);
        TablaIp tabla3 = new TablaIp(dirReales[5],8183); //Terminal 5
        tablaIp.put("163.178.20.20",tabla3);
        TablaIp tabla4 = new TablaIp(dirReales[7],8188); //Terminal 7
        tablaIp.put("163.178.30.30",tabla4);
        TablaIp tabla5 = new TablaIp(dirReales[0],8889); //Router 1
        tablaIp.put("11.50.70.12",tabla5);
        TablaIp tabla6 = new TablaIp(dirReales[0],8888); //Router 1
        tablaIp.put("163.178.15.15",tabla6);
        TablaIp tabla7 = new TablaIp(dirReales[0],8887); //Router 1
        tablaIp.put("193.34.11.7",tabla7);
        return tablaIp;
    }

    private static Map<String,TablaIp> Enrutador2TablaIp(String [] dirReales ){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp(dirReales[4],8184); //Terminal 4
        tablaIp.put("12.0.0.6",tabla1);
        TablaIp tabla2 = new TablaIp(dirReales[6],8189); //Terminal 6
        tablaIp.put("12.0.0.7",tabla2);
        TablaIp tabla3 = new TablaIp(dirReales[2],8185); //Terminal 2
        tablaIp.put("12.70.4.5",tabla3);
        TablaIp tabla4 = new TablaIp(dirReales[7],8188); //Terminal 7
        tablaIp.put("163.178.30.30",tabla4);
        TablaIp tabla5 = new TablaIp(dirReales[5],8183); //Terminal 5
        tablaIp.put("163.178.20.20",tabla5);
        TablaIp tabla6 = new TablaIp(dirReales[0],9998); //Router 2
        tablaIp.put("12.0.9.8",tabla6);
        TablaIp tabla7 = new TablaIp(dirReales[0],9999); //Router 2
        tablaIp.put("163.178.17.17",tabla7);
        return tablaIp;
    }

    private static Map<String,TablaIp> Red12TablaIp(String [] dirReales ){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp(dirReales[4],8184); //Terminal 4
        tablaIp.put("12.0.0.6",tabla1);
        TablaIp tabla2 = new TablaIp(dirReales[6],8189); //Terminal 6
        tablaIp.put("12.0.0.7",tabla2);
        TablaIp tabla3 = new TablaIp(dirReales[1],8185); //Terminal 1
        tablaIp.put("12.70.4.5",tabla3);
        TablaIp tabla4 = new TablaIp(dirReales[9],9998); //Router 2
        tablaIp.put("12.0.9.8",tabla4);
        return tablaIp;
    }

    private static Map<String,TablaIp> Red163TablaIp(String [] dirReales ){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp(dirReales[7],8188); //Terminal 7
        tablaIp.put("163.178.30.30",tabla1);
        TablaIp tabla2 = new TablaIp(dirReales[5],8183); //Terminal 5
        tablaIp.put("163.178.20.20",tabla2);
        TablaIp tabla6 = new TablaIp(dirReales[8],8888); //Router 1
        tablaIp.put("163.178.15.15",tabla6);
        TablaIp tabla7 = new TablaIp(dirReales[9],9999); //Router 2
        tablaIp.put("163.178.17.17",tabla7);
        return tablaIp;
    }

    private static Map<String,TablaIp> Red11TablaIp(String [] dirReales ){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp(dirReales[1],8181); //Terminal 1
        tablaIp.put("11.70.4.5",tabla1);
        TablaIp tabla2 = new TablaIp(dirReales[8],8889); //Router 1
        tablaIp.put("11.50.70.12",tabla2);
        return tablaIp;
    }

    private static Map<String,TablaIp> Red193TablaIp(String [] dirReales ){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp(dirReales[3],8182); //Terminal 3
        tablaIp.put("193.34.11.22",tabla1);
        TablaIp tabla2 = new TablaIp(dirReales[8],8887); //Router 1
        tablaIp.put("193.34.11.7",tabla2);
        return tablaIp;
    }

    private static void correr()
    {
        int cantInterfaces; //La cantidad de interfaces
        int cantBuf;
        int tipoEnrutador = 0;
        String [] dirReales =   {"localhost", "10.1.130.146", "10.1.130.210", "10.1.130.244", "10.1.131.82", "10.1.232.156", "10.1.131.37", "10.1.130.111",
                "10.1.130.103", "10.1.130.55"};
        String ipDispatcher = "165.8.6.25"; //El ip del dispatcher
        String ripDispatcher = dirReales[3];
        Map<String,TablaDirecciones> tablaD = new HashMap<>();

        System.out.print( "Digite la cantidad de interfaces (de 1 a 6): \n" );
        cantInterfaces = Integer.parseInt(scanner.nextLine());

        System.out.print( "Digite la cantidad de buffers por interfaz (de 1 a 500): \n" );
        cantBuf = Integer.parseInt(scanner.nextLine());

        while(tipoEnrutador != 1 && tipoEnrutador != 2)
        {
            System.out.print( "Seleccione el tipo de enrutador (1 o 2): \n" );
            tipoEnrutador = Integer.parseInt(scanner.nextLine());
            if(tipoEnrutador == 1){
                //tablaD = Enrutador1();
            }
            else if (tipoEnrutador == 2)
            {
                //tablaD = Enrutador2();
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
        Map<String, Map<String,TablaDirecciones>> tablaDTablasD = new HashMap<>();
        Map<String,TablaIp> tablaIp = new HashMap<>();
        int num = 0;
        if(cantInterfaces == 1)
        {
            System.out.print( "Para la interfaz numero " + (1) + ": \n" );
            System.out.print( "Digite el ip falso: \n" );
            ips[0] = scanner.nextLine();
            System.out.print( "Digite la direccion fisica: \n" );
            fisicos[0] = scanner.nextLine();
            System.out.print( "Digite la red (11, 12, 163 0 193): \n" );
            switch(scanner.nextLine()) {
                case "12":
                    tablaDTablasD.put(fisicos[0], Red12TablaD());
                    tablaIp = Red12TablaIp(dirReales);
                    break;
                case "163":
                    tablaDTablasD.put(fisicos[0], Red163TablaD());
                    tablaIp = Red163TablaIp(dirReales);
                    break;
                case "11":
                    tablaDTablasD.put(fisicos[0], Red11TablaD());
                    tablaIp = Red11TablaIp(dirReales);
                    break;
                case "193":
                    tablaDTablasD.put(fisicos[0], Red193TablaD());
                    tablaIp = Red193TablaIp(dirReales);
                    break;
            }
            System.out.print( "Digite el tipo de tablaIP: \n" );
        }
        else
        {
            for(int i=0; i<cantInterfaces; i++)
            {
                System.out.print( "Para la interfaz numero " + (i+1) + ": \n" );
                System.out.print( "Digite el ip falso: \n" );
                ips[i] = scanner.nextLine();
                System.out.print( "Digite la direccion fisica: \n" );
                fisicos[i] = scanner.nextLine();
                System.out.print( "Digite el tipo de interfaz (11, 12, 13, 21, o 22): \n" );
                switch(scanner.nextLine()) {
                    case "11":
                        tablaDTablasD.put(fisicos[i], Enrutador11TablaD());
                        tablaIp = Enrutador1TablaIp(dirReales);
                        break;
                    case "12":
                        tablaDTablasD.put(fisicos[i], Enrutador12TablaD());
                        tablaIp = Enrutador1TablaIp(dirReales);
                        break;
                    case "13":
                        tablaDTablasD.put(fisicos[i], Enrutador13TablaD());
                        tablaIp = Enrutador1TablaIp(dirReales);
                        break;
                    case "21":
                        tablaDTablasD.put(fisicos[i], Enrutador21TablaD());
                        tablaIp = Enrutador2TablaIp(dirReales);
                        break;
                    case "22":
                        tablaDTablasD.put(fisicos[i], Enrutador22TablaD());
                        tablaIp = Enrutador2TablaIp(dirReales);
                        break;
                }
            }
        }

        for(int j=0; j<cantInterfaces; j++)
        {
            System.out.println( "Interfaz numero" + (j+1) + " = " + ips[j] + " _ " + fisicos[j] + "\n" );
        }
        //Enrutador enrutador = new Enrutador();
        //enrutador.start(cantInterfaces, ips, fisicos, ipDispatcher, ripDispatcher, tablaDTablasD, tablaIp, cantBuf);
    }


    private static void prueba() //Solo para hacer pruebas rapidas
    {
        int cantInterfaces = 1; //La cantidad de interfaces
        int cantBuf = 10;
        String [] dirReales =   {"localhost", "10.1.130.146", "10.1.130.210", "10.1.130.244", "10.1.131.82", "10.1.232.156", "10.1.131.37", "10.1.130.111",
                                "10.1.130.103", "10.1.130.55"};
        String ipDispatcher = "11.101.6.8"; //No importaria para hacer pruebas sin dispatcher supongo
        //String ipDispatcher = "163.178.20.20";
        String ripDispatcher = dirReales[3]; //Igual
        int portDispatcher = 4444;
        //Map<String,TablaDirecciones> tablaD = new HashMap<>();
        //tablaD = Red163TablaD();



        /*
        Map<String, Map<String,TablaDirecciones>> tablaDTablasD = new HashMap<>();
        tablaDTablasD.put("1", Red12TablaD());
        */

        Map<String, Map<String,TablaDirecciones>> tablaDTablasD = new HashMap<>();
        tablaDTablasD.put("4", Red163TablaD());

        //tablaD = Terminal3();

        Map<String,TablaIp> tablaIp = Enrutador1TablaIp(dirReales);
        tablaIp.put(ipDispatcher, new TablaIp(ripDispatcher, portDispatcher)); // Incluir Dispatcher
        Map<String,TablaDirecciones> tablaDispatcher = new HashMap<>();
        tablaDispatcher.put(ipDispatcher, new TablaDirecciones(ipDispatcher, "0.0.0.0", 0));
        tablaDTablasD.put("D", tablaDispatcher);

        String[] ips = new String[cantInterfaces]; //Los ips falsos de dichas interfaces
        String[] fisicos = new String[cantInterfaces]; //Las direcciones fisicas

        /*
        for(int i = 0; i<cantInterfaces; i++)
        {
            ips[i] = "12.70.4.5";
            fisicos[i] = "Terminal1";
        }*/
        String[] Pips = {"163.178.20.20", "11.50.70.12", "193.34.11.7"};
        String[] Pdf = {"Terminal.5", "Enrutador1.2", "Enrutador1.3"};
        for(int i = 0; i < ips.length; ++i){
            ips[i] = Pips[i];
        }
        for(int i = 0; i < fisicos.length; ++i){
            fisicos[i] = Pdf[i];
        }
        int[] puertos={8888,8889,8887};
        Enrutador enrutador = new Enrutador();
        enrutador.start(cantInterfaces, ips, fisicos, ipDispatcher, ripDispatcher, tablaDTablasD, tablaIp, cantBuf, puertos);
    }


    public static void main(String args[]){
        //correr();
        prueba();
    }
}
