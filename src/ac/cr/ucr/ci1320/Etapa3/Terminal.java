package ac.cr.ucr.ci1320.Etapa3;

import java.util.Scanner;

public class Terminal implements Runnable{
    Scanner scanner;
    String myFakeAddress;
    Interfaz interfaz;

    String dispatcherFIP = "165.8.6.25";

    public Terminal(Interfaz interfaz, String dispatcherFIP){
        this.interfaz = interfaz;
        this.myFakeAddress = interfaz.getMyFakeAddress();
        this.dispatcherFIP = dispatcherFIP;
    }


    public void run() {
        System.out.println("Mensaje -> IPDESTINO \\n MENSAJE / Dispatcher -> DISPATCH");
        scanner = new Scanner(System.in);
        String entrada = "";
        while (!(entrada.equals("BYE"))) {

            entrada = scanner.nextLine();
            if (!(entrada.equals("BYE"))) {

                String[] array = entrada.split("\\\\n");

                // Enviar el mensaje

                if (array.length == 2) {
                    if((8 < array[1].length())&&(array[1].substring(0, 9).equals("AUDIOTST/")) ) {
                        String mensajeAEnviar = array[1].split("/")[1];
                        Mensaje mensaje = new Mensaje(myFakeAddress, array[0], 8, mensajeAEnviar);
                        TablaIp tabla = this.interfaz.getTablaIP().get(array[0]);
                        SolicitanteLite sender = new SolicitanteLite(mensaje.toString(), tabla.getIpVerdadera(), tabla.getPuerto());
                        sender.start();
                    } else { //El caso normal
                        String mensajeAEnviar = array[1];
                        Mensaje mensaje = new Mensaje(myFakeAddress, array[0], 0, mensajeAEnviar);
                        String ipIntermedio = this.interfaz.getATravez(array[0]);
                        TablaIp tabla = this.interfaz.getTablaIP().get(ipIntermedio);
                        SolicitanteLite sender = new SolicitanteLite(mensaje.toString(), tabla.getIpVerdadera(), tabla.getPuerto());
                        sender.start();
                    }
                } else if (entrada.equalsIgnoreCase("DISPATCH") && interfaz.getisTerminal()) {
                    Mensaje mensaje = new Mensaje(myFakeAddress, dispatcherFIP, 7, Integer.toString(interfaz.getMiPuerto()));
                    TablaIp tabla = this.interfaz.getTablaIP().get(this.dispatcherFIP);
                    SolicitanteLite sender = new SolicitanteLite(mensaje.toString(), tabla.getIpVerdadera(), tabla.getPuerto());
                    sender.start();
                } else {
                    System.out.println("Mensaje Inválido");
                }
            }
        }
    }
}