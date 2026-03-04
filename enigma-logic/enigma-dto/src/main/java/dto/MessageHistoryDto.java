package dto;

public class MessageHistoryDto {
    private String input;
    private String output;
    private int duration;

    public MessageHistoryDto(String input, String output, int duration) {
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

    public int getDuration() {
        return duration;
    }
}
