package api.schemas;

public class PlugConnection {

    private String plug1;
    private String plug2;

    public PlugConnection(){}

    public PlugConnection(String plug1, String plug2) {
        this.plug1 = plug1;
        this.plug2 = plug2;
    }

    public String getPlug1() {
        return plug1;
    }

    public void setPlug1(String plug1) {
        this.plug1 = plug1;
    }

    public String getPlug2() {
        return plug2;
    }

    public void setPlug2(String plug2) {
        this.plug2 = plug2;
    }
}
