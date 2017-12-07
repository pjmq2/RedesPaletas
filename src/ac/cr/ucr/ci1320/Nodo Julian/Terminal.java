package ac.cr.ucr.ci1320;

import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Terminal {
    Scanner scanner;
    String dispatcherFIP;
    Nodo nodo;

    public Terminal(Nodo nodo, String dispatcherFIP){
        this.nodo = nodo;
        this.dispatcherFIP = dispatcherFIP;
    }

    public void terminal() {
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
                    String mensajeAEnviar = array[1];
                    Mensaje mensaje = new Mensaje(nodo.getmyFakeAddress(), array[0], 0, mensajeAEnviar);

                    solicitante = new Solicitante(nodo, mensaje); // ESE ARRAY[0] CÁMBIELO POR EL
                    solicitante.run();
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