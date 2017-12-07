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
                    TablaDirecciones tdir = nodo.getDTable().get(array[0]);
                    if (tdir == null) {
                        System.out.println("Dirección IP inválida");
                    } else {
                        // FALTA HACER QUE ELIJA LA DIRECCIÓN ADECUADA, Y QUE HAGA LO QUE OCUPE SI NO LA TIENE.

                        if(tdir.getDistancia() == -1)
                        {
                            this.nodo.wish(array[0]);
                            Set<String> keys = nodo.getIPTable().keySet();
                            String[] fakes = keys.toArray(new String[keys.size()]); // Arreglo de las falsas de J, P, A y S
                            for(int i = 0; i < fakes.length; ++i) {
                                // Preguntar la distancia a cada uno.

                                Mensaje mensaje = new Mensaje(nodo.getmyFakeAddress(), fakes[i], 2, array[0]);
                                Paquete paquete = new Paquete(mensaje, nodo.getmyFakeAddress(), fakes[i]);
                                String envio = paquete.toString();
                                String trueaddress = nodo.getIPTable().get(fakes[i]);

                                if(!(trueaddress.equals("0"))) {
                                    solicitante = new Solicitante(nodo, envio, fakes[i], 2); // Address Port Menssage
                                    solicitante.run();
                                }
                                else
                                {
                                    // No puedo enviarle la pregunta porque no tengo el IP...
                                }
                            }

                            // Espera hasta que el servidor reciba la respuesta y la guarde.
                            String wish = nodo.getWish();
                        }

                        if (tdir.getDistancia() == -1)
                        {
                            System.out.println("No hay ningun router conectado a la ruta " + array[0]);
                        }
                        else {
                            String mensajeAEnviar = array[1];
                            String imd = tdir.getaTraves();
                            Mensaje mensaje = new Mensaje(nodo.getmyFakeAddress(), array[0], 0, mensajeAEnviar);
                            Paquete paquete = new Paquete(mensaje, nodo.getmyFakeAddress(), imd);
                            String envio = paquete.toString();
                            solicitante = new Solicitante(nodo, envio, imd, 0); // ESE ARRAY[0] CÁMBIELO POR EL
                            nodo.wish("");
                            solicitante.run();
                        }
                    }
                } else if (entrada.equalsIgnoreCase("DISPATCH")) {
                    Mensaje mensaje = new Mensaje(nodo.getmyFakeAddress(), dispatcherFIP, 7, Integer.toString(nodo.getmyPort()));
                    String envio = mensaje.toString();
                    solicitante = new Solicitante(nodo, envio, dispatcherFIP, 7); // Address Port Menssage
                    solicitante.run();
                } else {
                    System.out.println("Mensaje Inválido");
                }
            }
        }
    }
}