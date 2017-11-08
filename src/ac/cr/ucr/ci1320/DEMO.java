package ac.cr.ucr.ci1320;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DEMO {
    public static void main(String args[])
    {
        runner();
    }

    public static void runner(){
        // Dirección Final, Dirección mía, Mensaje
        String mensaje1 = "25.0.0.0\n12.0.0.8\n1\n10\nHola Mundo";
        String mensaje2 = "12.0.0.7\n12.0.0.8\n1\n10\nHola Mundo";
        String mensaje3 = "12.0.20.2\n12.0.0.8\n1\n10\n1Hola Mundo";
        String mensaje4 = "12.0.0.8\n12.0.0.8\n1\n10\nHola Mundo";

        Solicitante cliente = new Solicitante();
        cliente.sendMessage(mensaje1, "192.168.0.121", 7777);
    }
}
