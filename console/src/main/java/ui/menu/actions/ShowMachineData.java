package ui.menu.actions;

import dto.CodeSnapShotDto;
import dto.MachineDataDto;

import java.util.List;

public class ShowMachineData {

    public static String showMachineData(MachineDataDto machineDataDto) {
        StringBuilder machineData = new StringBuilder();
        machineData.append("===== Machine specification =====\n");
        machineData.append("Number of rotors in system: ").append(machineDataDto.getRotorsInSystem()).append("\n");
        machineData.append("Number of reflectors in system: ").append(machineDataDto.getReflectorsInSystem()).append("\n");
        machineData.append("Total messages processed: ").append(machineDataDto.getProcessedMessageCount()).append("\n");
        if (machineDataDto.getCurrentCodeSnapShot() == null) {
            machineData.append("No code is currently set on the machine.\n");
        } else {
            machineData.append("Original code configuration:\n").append(showCodeData(machineDataDto.getOriginalCodeSnapShot()));
            machineData.append("Current code configuration:\n").append(showCodeData(machineDataDto.getCurrentCodeSnapShot()));
        }
        return machineData.toString();
    }

    public static String showCodeData(CodeSnapShotDto codeSnapShotDto) {
        StringBuilder codeData = new StringBuilder();
        List<Integer> rotorIds = codeSnapShotDto.getRotorIds();
        List<Character> rotorInitialPositions = codeSnapShotDto.getRotorPosition();
        List<Integer> notchDistanceFromWindow = codeSnapShotDto.getNotchDistanceFromWindow();
        codeData.append("<");
        for (int i=0; i<rotorIds.size(); i++) {
            codeData.append(rotorIds.get(i));
            if (i!=rotorIds.size()-1) codeData.append(",");
        }
        codeData.append("><");
        for (int i=0; i<rotorInitialPositions.size(); i++) {
            codeData.append(rotorInitialPositions.get(i)).append("(").append(notchDistanceFromWindow.get(i)).append(")");
            if (i!=rotorIds.size()-1) codeData.append(",");
        }
        codeData.append("><").append(codeSnapShotDto.getReflectorId());
        codeData.append("><");
        for (int i=0; i<codeSnapShotDto.getPlugboard().size(); i++) {
            codeData.append(codeSnapShotDto.getPlugboard().get(i)[0]).append("|").append(codeSnapShotDto.getPlugboard().get(i)[1]);
            if (i!=codeSnapShotDto.getPlugboard().size()-1) codeData.append(",");
        }
        codeData.append(">\n");
        return codeData.toString();
    }
}
