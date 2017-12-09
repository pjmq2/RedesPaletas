package ac.cr.ucr.ci1320.NodoJ;

import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Terminal implements Runnable{
    Scanner scanner;
    String dispatcherFIP;
    Nodo nodo;

    public Terminal(Nodo nodo){
        this.nodo = nodo;
        this.dispatcherFIP = nodo.getDispatchPath();
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
                Solicitante solicitante;

                if (array.length == 2) {
                    if((array[1].substring(0, 9).equals("AUDIOTST/")) && (8 < array[1].length())) {
                        String mensajeAEnviar = array[1].split("/")[1];
                        Mensaje mensaje = new Mensaje(nodo.getmyFakeAddress(), array[0], 8, mensajeAEnviar);

                        solicitante = new Solicitante(nodo, mensaje); // ESE ARRAY[0] CÁMBIELO POR EL
                        solicitante.run();
                    } else {
                        String mensajeAEnviar = array[1];
                        Mensaje mensaje = new Mensaje(nodo.getmyFakeAddress(), array[0], 0, mensajeAEnviar);

                        solicitante = new Solicitante(nodo, mensaje); // ESE ARRAY[0] CÁMBIELO POR EL
                        solicitante.run();
                    }
                } else if (entrada.equalsIgnoreCase("DISPATCH")) {
                    Mensaje mensaje = new Mensaje(nodo.getmyFakeAddress(), dispatcherFIP, 7, Integer.toString(nodo.getmyPort()));
                    solicitante = new Solicitante(nodo, mensaje); // Address Port Menssage
                    solicitante.run();
                } else {
                    System.out.println("Mensaje Inválido");
                }
            }
        }
    }
}