package ac.cr.ucr.ci1320;

import ac.cr.ucr.ci1320.Dispatcher.Dispatcher;
import javafx.util.Pair;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;


public class Client extends Connection {

    private Dispatcher dispatcher;
    private Pair<String,String> pair;
    private Map<String, String> relation;

    public  Client(){
    }

    public String getMesage(){
        String mensaje="";
        return mensaje;
    }

    boolean isLocal(String ip){
        boolean isLocal;
        String[] host = ip.split(".");
        isLocal = host[1].equalsIgnoreCase("165");
        return isLocal;
    }

    public void startClient2() throws IOException {
        try {
            outServer = new DataOutputStream(cs.getOutputStream());
                outServer.writeUTF("hola");
            cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void startClient(String message){
        try {
            String[] arrayMessage = message.split("\n");
            int action = Integer.parseInt(arrayMessage[2]);
            String answerMessage = "";
            switch (action){
                case 1:
                    IpData ipData = dispatcher.getData(arrayMessage[1]);
                    if(ipData != null) { //no soy yo
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '5' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    } else { //soy yo
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '3' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    }
                    break;
                case 2:
                    IpData ipData2 = dispatcher.getData(arrayMessage[1]);
                    if(ipData2 != null) { //si se la ruta
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '4' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    }
                    else{ //ensaje de error, no conozco la ruta al mae
                        answerMessage = pair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '5' + "\n" + "" + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                    }
                    break;
                default: //caso 0
                    if(isLocal(arrayMessage[1])){
                        answerMessage = answerMessage + "\n" + this.pair.getValue() + "\n" + this.relation.get(arrayMessage[0]) + "\n";
                        //empaquetar fisico
                    }
                    break;
            }
            IpData ipData = this.dispatcher.getData(arrayMessage[1]);
            super.createSocket("client",ipData.getPort(),ipData.getRealIp());
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            this.outServer.writeUTF(answerMessage);
            this.cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void testClient(){
        try {
            outServer = new DataOutputStream(cs.getOutputStream());
            for (int i = 0; i < 2; i++) {
                outServer.writeUTF("Este es el número"+(i+1)+"\nHOLA1\nHOLA2");
            }
            cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}