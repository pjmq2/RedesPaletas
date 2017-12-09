package ac.cr.ucr.ci1320.Etapa3;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Scanner;

public class Terminal implements Runnable{
    Scanner scanner;
    String myFakeAddress;
    Interfaz interfaz;

    public Terminal(Interfaz interfaz){
        this.interfaz = interfaz;
        this.myFakeAddress = interfaz.getMyFakeAddress();
    }


    public void run() {
        System.out.println("Mensaje -> IPDESTINO \\n MENSAJE / Dispatcher -> DISPATCH"); // Hay que cambiar to.do esto para que en vez de ser IPDESTINO \n MENSAJE sea IPDESTINO \n PUERTO \n MENSAJE porque el dispatcher y el puerto correran en puertos distintos AÚN NO HE ACABADO, sigo a las 9 [Cuando llego a la casa]
        scanner = new Scanner(System.in);
        String entrada = "";
        while (!(entrada.equals("BYE"))) {

            entrada = scanner.nextLine();
            if (!(entrada.equals("BYE"))) {

                String[] array = entrada.split("\\\\n");

                // Enviar el mensaje

                if (array.length == 2) {
                    if((array[1].substring(0, 9).equals("AUDIOTST/")) && (8 < array[1].length())) {
                        String mensajeAEnviar = array[1].split("/")[1];
                        Mensaje mensaje = new Mensaje(myFakeAddress, array[0], 8, mensajeAEnviar);
                        TablaIp tabla = this.interfaz.getTablaIP().get(array[0]);
                        SolicitanteLite sender = new SolicitanteLite(mensaje.toString(), tabla.getIpVerdadera(), tabla.getPuerto());
                        sender.start();
                    } else {
                        String mensajeAEnviar = array[1];
                        Mensaje mensaje = new Mensaje(myFakeAddress, array[0], 0, mensajeAEnviar);
                        TablaIp tabla = this.interfaz.getTablaIP().get(array[0]);
                        SolicitanteLite sender = new SolicitanteLite(mensaje.toString(), tabla.getIpVerdadera(), tabla.getPuerto());
                        sender.start();
                    }
                } else {
                    System.out.println("Mensaje Inválido");
                }
            }
        }
    }
}