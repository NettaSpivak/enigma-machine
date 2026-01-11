package ui.menu.actions;

import dto.CodeHistoryDto;
import dto.MachineHistoryDto;
import dto.MessageHistoryDto;

import java.util.List;

public class ShowHistoryAndStatistics {

    public static String showHistoryAndStatistics(MachineHistoryDto machineHistoryDto) {
        StringBuilder machineHistory = new StringBuilder();
        List<CodeHistoryDto> codeHistoryDtoList = machineHistoryDto.getCodeHistoryDto();
        machineHistory.append("=====Machine History =====\n");
        if (codeHistoryDtoList == null) {
            machineHistory.append("No code was set on the machine.\n");
        } else {
            machineHistory.append("Code History:\n");
            for (int i=0; i<codeHistoryDtoList.size(); i++) {
                machineHistory.append("(").append(i+1).append(") ");
                machineHistory.append(ShowMachineData.showCodeData(codeHistoryDtoList.get(i).getCodeSnapShotDto()));
                List<MessageHistoryDto> messageHistoryDtoList = codeHistoryDtoList.get(i).getMessageHistoryDto();
                if (messageHistoryDtoList == null) {
                    machineHistory.append("No messages was processed at this code.\n");
                } else {
                    machineHistory.append("Message history of code:\n");
                    for (int j = 0; j < messageHistoryDtoList.size(); j++) {
                        machineHistory.append(j+1).append(". ");
                        machineHistory.append(showMessageHistoryData(messageHistoryDtoList.get(j)));
                    }
                }
            }
        }
        return machineHistory.toString();
    }

    private static String showMessageHistoryData(MessageHistoryDto messageHistoryDto) {
        StringBuilder MessageHistoryData = new StringBuilder();
        MessageHistoryData.append("<").append(messageHistoryDto.getInputMessage()).append("> --> ");
        MessageHistoryData.append("<").append(messageHistoryDto.getProcessedMessage()).append("> ");
        MessageHistoryData.append("(").append(messageHistoryDto.getProcessTimeNano()).append(" nano-seconds)\n");

        return MessageHistoryData.toString();
    }
}
