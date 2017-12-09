package ac.cr.ucr.ci1320.Etapa3;

public class IpPackage {
    private String realIP;
    private String fakeIP;
    private Integer port;
    private String message;

    public IpPackage(String inputMessage){
        this.message = inputMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRealIP() {
        return realIP;
    }

    public void setRealIP(String realIP) {
        this.realIP = realIP;
    }

    public String getFakeIP() {
        return fakeIP;
    }

    public void setFakeIP(String fakeIP) {
        this.fakeIP = fakeIP;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
