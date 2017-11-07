package ac.cr.ucr.ci1320.Dispatcher;

import ac.cr.ucr.ci1320.IpData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by josue on 06/11/17.
 */
public class Dispatcher {
    private Map<String,IpData> ipTable;

    public Dispatcher(){
        this.ipTable = new HashMap<>();
        this.ipTable.put("12.0.0.0",new IpData("192.168.100.16", "12.0.0.8", "12.0.0.7", 0,6666));//banderas
        this.ipTable.put("200.5.0.0",new IpData("192.168.100.16", "200.5.0.2", "12.0.0.7",1,0));//paletas
        this.ipTable.put("140.90.0.0",new IpData("192.168.100.16", "12.0.0.8", "12.0.0.7", 2,0));//bolinchas
        this.ipTable.put("201.6.0.0",new IpData("192.168.100.16", "12.0.0.8", "165.8.0.6", 1,0));//legos
        this.ipTable.put("25.0.0.0",new IpData("192.168.100.16", "12.0.0.8", "165.8.0.6", 0,5555));//luces
    }                                                    //hardcodear este ip de maquina

    public IpData getData(String key){
        return this.ipTable.get(key);
    }
}
