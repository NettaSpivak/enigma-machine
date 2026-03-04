package api.schemas;

public class HistoryEntry {
    private String input;
    private String output;
    private int duration;

    public HistoryEntry(String input, String output, int duration) {
        this.input = input;
        this.output = output;
        this.duration = duration;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public long getDuration() {
        return duration;
    }
}
