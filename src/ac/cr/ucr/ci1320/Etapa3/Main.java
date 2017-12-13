package ac.cr.ucr.ci1320.Etapa3;

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

    private static Map<String,TablaIp> Enrutador1TablaIp(){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp("10.1.130.84",8181);
        tablaIp.put("11.70.4.5",tabla1);
        TablaIp tabla2 = new TablaIp("10.1.130.211",8182);
        tablaIp.put("193.34.11.22",tabla2);
        TablaIp tabla3 = new TablaIp("10.1.130.204",8183);
        tablaIp.put("163.178.20.20",tabla3);
        TablaIp tabla4 = new TablaIp("10.1.130.205",8188);
        tablaIp.put("163.178.30.30",tabla4);
        TablaIp tabla5 = new TablaIp("localhost",8889);
        tablaIp.put("11.50.70.12",tabla5);
        TablaIp tabla6 = new TablaIp("localhost",8888);
        tablaIp.put("163.178.15.15",tabla6);
        TablaIp tabla7 = new TablaIp("localhost",8887);
        tablaIp.put("193.34.11.0",tabla7);
        return tablaIp;
    }

    private static Map<String,TablaIp> Enrutador2TablaIp(){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp("10.1.130.39",8184);
        tablaIp.put("12.0.0.6",tabla1);
        TablaIp tabla2 = new TablaIp("10.1.130.151",8189);
        tablaIp.put("12.0.0.7",tabla2);
        TablaIp tabla3 = new TablaIp("10.1.130.31",8185);
        tablaIp.put("12.70.4.5",tabla3);
        TablaIp tabla4 = new TablaIp("10.1.130.205",8188);
        tablaIp.put("163.178.30.30",tabla4);
        TablaIp tabla5 = new TablaIp("10.1.130.204",8183);
        tablaIp.put("163.178.20.20",tabla5);
        TablaIp tabla6 = new TablaIp("localhost",9998);
        tablaIp.put("12.0.9.8",tabla6);
        TablaIp tabla7 = new TablaIp("localhost",9999);
        tablaIp.put("163.178.17.17",tabla7);
        return tablaIp;
    }

    private static Map<String,TablaIp> Red12TablaIp(){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp("10.1.130.39",8184);
        tablaIp.put("12.0.0.6",tabla1);
        TablaIp tabla2 = new TablaIp("10.1.130.151",8189);
        tablaIp.put("12.0.0.7",tabla2);
        TablaIp tabla3 = new TablaIp("10.1.130.31",8185);
        tablaIp.put("12.70.4.5",tabla3);
        TablaIp tabla4 = new TablaIp("10.1.130.83",9998);
        tablaIp.put("12.0.9.8",tabla4);
        return tablaIp;
    }

    private static Map<String,TablaIp> Red163TablaIp(){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp("10.1.130.205",8188);
        tablaIp.put("163.178.30.30",tabla1);
        TablaIp tabla2 = new TablaIp("10.1.130.204",8183);
        tablaIp.put("163.178.20.20",tabla2);
        TablaIp tabla6 = new TablaIp("10.1.130.107",8888);
        tablaIp.put("163.178.15.15",tabla6);
        TablaIp tabla7 = new TablaIp("10.1.130.83",9999);
        tablaIp.put("163.178.17.17",tabla7);
        return tablaIp;
    }

    private static Map<String,TablaIp> Red11TablaIp(){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp("10.1.130.84",8181);
        tablaIp.put("11.70.4.5",tabla1);
        TablaIp tabla2 = new TablaIp("10.1.130.107",88899);
        tablaIp.put("11.50.70.12",tabla2);
        return tablaIp;
    }

    private static Map<String,TablaIp> Red193TablaIp(){
        Map<String,TablaIp> tablaIp = new HashMap<>();
        TablaIp tabla1 = new TablaIp("10.1.130.211",8182);
        tablaIp.put("193.34.11.22",tabla1);
        TablaIp tabla2 = new TablaIp("10.1.130.107",8887);
        tablaIp.put("193.34.11.0",tabla2);
        return tablaIp;
    }

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
                    tablaIp = Red12TablaIp();
                    break;
                case "163":
                    tablaDTablasD.put(fisicos[0], Red163TablaD());
                    tablaIp = Red163TablaIp();
                    break;
                case "11":
                    tablaDTablasD.put(fisicos[0], Red11TablaD());
                    tablaIp = Red11TablaIp();
                    break;
                case "193":
                    tablaDTablasD.put(fisicos[0], Red193TablaD());
                    tablaIp = Red193TablaIp();
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
                        tablaIp = Enrutador1TablaIp();
                        break;
                    case "12":
                        tablaDTablasD.put(fisicos[i], Enrutador12TablaD());
                        tablaIp = Enrutador1TablaIp();
                        break;
                    case "13":
                        tablaDTablasD.put(fisicos[i], Enrutador13TablaD());
                        tablaIp = Enrutador1TablaIp();
                        break;
                    case "21":
                        tablaDTablasD.put(fisicos[i], Enrutador21TablaD());
                        tablaIp = Enrutador2TablaIp();
                        break;
                    case "22":
                        tablaDTablasD.put(fisicos[i], Enrutador22TablaD());
                        tablaIp = Enrutador2TablaIp();
                        break;
                }
            }
        }

        for(int j=0; j<cantInterfaces; j++)
        {
            System.out.println( "Interfaz numero" + (j+1) + " = " + ips[j] + " _ " + fisicos[j] + "\n" );
        }
        Enrutador enrutador = new Enrutador();
        enrutador.start(cantInterfaces, ips, fisicos, ipDispatcher, ripDispatcher, tablaDTablasD, tablaIp, cantBuf);
    }


    private static void prueba() //Solo para hacer pruebas rapidas
    {
        int cantInterfaces = 3; //La cantidad de interfaces
        int cantBuf = 10;
        String ipDispatcher = "11.101.6.8"; //No importaria para hacer pruebas sin dispatcher supongo
        //String ipDispatcher = "163.178.20.20";
        String ripDispatcher = "localhost"; //Igual
        //Map<String,TablaDirecciones> tablaD = new HashMap<>();
        Map<String, Map<String,TablaDirecciones>> tablaDTablasD = new HashMap<>();
        tablaDTablasD.put("1.1", Enrutador11TablaD());
        tablaDTablasD.put("1.2", Enrutador12TablaD());
        tablaDTablasD.put("1.3", Enrutador13TablaD());
        //tablaD = Terminal3();

        Map<String,TablaIp> tablaIp = new HashMap<>();
        tablaIp = Enrutador1TablaIp();

        String[] ips = new String[cantInterfaces]; //Los ips falsos de dichas interfaces
        String[] fisicos = new String[cantInterfaces]; //Las direcciones fisicas

        for(int i = 0; i<cantInterfaces; i++)
        {
            //ips[i] = "193.34.11.22";
            //fisicos[i] = "193_1";
        }
        Enrutador enrutador = new Enrutador();
        enrutador.start(cantInterfaces, ips, fisicos, ipDispatcher, ripDispatcher, tablaDTablasD, tablaIp, cantBuf);
    }


    public static void main(String args[]){
        //correr();
        prueba();
    }
}
