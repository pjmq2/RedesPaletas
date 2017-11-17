package ac.cr.ucr.ci1320;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.valueOf;

public class Nodo {
    private HashMap<String,TablaDirecciones> tablaD ;
    private HashMap<String,String> tablaIP;
    private String miIp;
    private String miIpFalsa;
    private int miPuerto;
    private Analizador analizer;
    private Servidor server;
    private String Alonso;

    public Nodo(HashMap<String, TablaDirecciones> tablaD, String miIp, int miPuerto, HashMap<String,String>tablaIP, String fake1, String fake2, String fake3, String fake4) {
        this.tablaD = tablaD;
        this.miIp = miIp;
        this.miPuerto = miPuerto;
        this.tablaIP = tablaIP;
        this.analizer = new Analizador(tablaD, tablaIP, miIp);
        this.server = new Servidor(this, this.analizer);
        this.miIpFalsa = fake3;
        this.Alonso = fake4;
    }

    public String getIP()
    {
        return this.miIp;
    }

    public int getPort()
    {
        return this.miPuerto;
    }

    public String getFake()
    {
        return this.miIpFalsa;
    }

    public Analizador getAnalizer() { return this.analizer; }

    public HashMap<String, String> getIPTable()
    {
        return this.tablaIP;
    }

    public HashMap<String,TablaDirecciones> getDTable() { return  this.tablaD; }

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
            return true;
        }
    }

    public void iniciar()
    {
        // Abrir Servidor

        server.iniciar();

        // Leer línea de la terminal.

        this.terminal();
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

    public void terminal() {
        System.out.println("Mensaje -> IPDESTINO \\n MENSAJE / Dispatcher -> DISPATCH"); // Hay que cambiar to.do esto para que en vez de ser IPDESTINO \n MENSAJE sea IPDESTINO \n PUERTO \n MENSAJE porque el dispatcher y el puerto correran en puertos distintos AÚN NO HE ACABADO, sigo a las 9 [Cuando llego a la casa]
        Scanner scanner = new Scanner(System.in);
        String entrada = "";
        while (!(entrada.equals("BYE"))) {
            entrada = scanner.nextLine();
            if (!(entrada.equals("BYE"))) {
                String[] array = entrada.split("\\\\n");
                // Enviar el mensaje
                Solicitante solicitante;

                if (array.length == 2) {
                    TablaDirecciones tdir = tablaD.get(array[0]);
                    if (tdir == null) {
                        System.out.println("Dirección IP inválida");
                    } else {
                        // FALTA HACER QUE ELIJA LA DIRECCIÓN ADECUADA, Y QUE HAGA LO QUE OCUPE SI NO LA TIENE.

                        if(tdir.getDistancia() == -1)
                        {
                            Set<String> keys = tablaIP.keySet();
                            String[] fakes = keys.toArray(new String[keys.size()]); // Arreglo de las falsas de J, P, A y S
                            for(int i = 0; i < fakes.length; ++i) {
                                Mensaje mensaje = new Mensaje(this.getFake(), fakes[i], 2, array[0]);
                                String envio = mensaje.toString();
                                String trueaddress = tablaIP.get(fakes[i]);
                                if(!(trueaddress.equals("0"))) {
                                    solicitante = new Solicitante(this, envio, fakes[i], 2); // Address Port Menssage
                                    solicitante.run();
                                }
                                else
                                {
                                    // No puedo enviarle la pregunta porque no tengo el IP...
                                }
                            }
                        }

                        while(tdir.getDistancia() == -1)
                        {
                            // Espera a que alguien responda, esta parte debería ser mejor hecha en un futuro
                        }
                        String mensajeAEnviar = array[1];
                        Mensaje mensaje = new Mensaje(this.getFake(), array[0], 0, mensajeAEnviar);
                        Paquete paquete = analizer.empaquetar(mensaje);
                        String envio = paquete.toString();
                        String imd = tdir.getaTraves();
                        solicitante = new Solicitante(this, envio, imd, 0); // ESE ARRAY[0] CÁMBIELO POR EL
                        solicitante.run();
                    }
                } else if (entrada.equalsIgnoreCase("DISPATCH")) {
                    Mensaje mensaje = new Mensaje(this.getFake(), Alonso, 7, Integer.toString(miPuerto));
                    String envio = mensaje.toString();
                    solicitante = new Solicitante(this, envio, Alonso, 7); // Address Port Menssage
                    solicitante.run();
                } else {
                    System.out.println("Mensaje Inválido");
                }
            }
        }
    }
}
