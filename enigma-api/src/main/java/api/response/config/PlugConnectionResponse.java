package api.response.config;

public class PlugConnectionResponse {

    private String plug1;
    private String plug2;

    public PlugConnectionResponse(String plug1, String plug2) {
        this.plug1 = plug1;
        this.plug2 = plug2;
    }

    public String getPlug1() {
        return plug1;
    }

    public String getPlug2() {
        return plug2;
    }
}
