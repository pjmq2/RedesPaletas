package ac.cr.ucr.ci1320.Dispatcher;

import ac.cr.ucr.ci1320.IpData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by josue on 06/11/17.
 */
public class Dispatcher {
    private String tabla;

    public Dispatcher(){
        tabla="12.0.0.8\n0.0.0.0";//Julian
        tabla.concat("\n");
        tabla.concat("12.0.0.7\n0.0.0.0");//Pablo
        tabla.concat("\n");
        tabla.concat("12.0.20.2\n0.0.0.0");//Sebastian
    }
}
