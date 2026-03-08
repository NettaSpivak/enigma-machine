package engine.engine;

import dto.*;
import engine.codeBuilder.CodeBuilder;
import engine.codeBuilder.CodeBuilderImpl;
import engine.history.MachineHistory;
import engine.history.MessageHistory;
import engine.machineRepository.MachineRepository;
import engine.machineRepository.MachineRepositoryImpl;
import machine.component.code.Code;
import machine.component.code.CodeSnapShot;
import machine.machine.Machine;
import machine.machine.MachineImpl;
import machine.utils.Utils;
import java.io.*;
import java.util.*;

public class EngineImpl implements Engine {
    private static final long serialVersionUID = 1L;
    private MachineRepository machineRepository;
    private Machine machine;
    private MachineHistory machineHistory;

    public EngineImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
        this.machine = new MachineImpl(machineRepository.getMachineName(), this.machineRepository.getAlphabet());
        this.machineHistory = new MachineHistory();
    }

    public EngineImpl(EngineImpl other) {
        this.machineRepository = new MachineRepositoryImpl((MachineRepositoryImpl) other.machineRepository);
        this.machine = new MachineImpl(other.machine.getName(), this.machineRepository.getAlphabet());
        this.machineHistory = new MachineHistory();
    }

    @Override
    public MachineStatusDto getMachineStatus() {
        int rotorsInSystem = machineRepository.getNumberOfDefinedRotors();
        int reflectorsInSystem = machineRepository.getNumberOfDefinedReflectors();
        int processedMessageCount = machineHistory.getTotalNumberOfProcessedMessages();
        CodeSnapShot originalCodeSnapShot = machineHistory.getCurrentCodeSnapShot();
        CodeSnapShot currentCodeSnapShot = machine.getCurrentCodeSnapShot();
        return new MachineStatusDto(rotorsInSystem, reflectorsInSystem, processedMessageCount, generateCodeSnapShotToDto(originalCodeSnapShot), generateCodeSnapShotToDto(currentCodeSnapShot));
    }

    private CodeSnapShotDto generateCodeSnapShotToDto(CodeSnapShot codeSnapShot) {
        if (codeSnapShot == null)
            return null;
        List<Integer> rotorIds = codeSnapShot.getRotorIds().reversed();
        List<Character> rotorsPositions = codeSnapShot.getRotorPosition().reversed();
        List<Integer> notchDistanceFromWindow = codeSnapShot.getNotchDistanceFromWindow().reversed();
        return new CodeSnapShotDto(rotorIds, rotorsPositions, notchDistanceFromWindow, codeSnapShot.getReflectorId(), codeSnapShot.getPlugboard());
    }

    @Override
    public void codeManual(CodeSnapShotDto codeSnapShotDto) throws IllegalArgumentException {
        try {
            CodeBuilder codeBuilder = new CodeBuilderImpl(machineRepository);
            Code newCode = codeBuilder.buildCode(codeSnapShotDto.getRotorIds(), codeSnapShotDto.getRotorPositions(), codeSnapShotDto.getReflectorId(), codeSnapShotDto.getPlugboard());
            machine.setCode(newCode);
            machineHistory.addNewCodeToHistory(newCode);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid rotor ID format. Rotor IDs must be integers separated by commas.");
        }
    }

    @Override
    public void codeAutomatic() throws RuntimeException {
        try {
            Random random = new Random();
            // generate random rotors ids
            int numOfRotors = machineRepository.getNumberOfDefinedRotors();
            List<Integer> rotorsIds = new ArrayList<>();
            for (int i = 1; i <= numOfRotors; i++) {
                rotorsIds.add(i);
            }
            Collections.shuffle(rotorsIds);
            // generate random rotors positions
            List <Character> randomRotorsPositions = new ArrayList<>();
            String alphabet = machineRepository.getAlphabet();
            for (int i = 0; i < machineRepository.getNumberOfRotorsInUse(); i++) {
                randomRotorsPositions.add(alphabet.charAt(random.nextInt(alphabet.length())));
            }
            // generate random reflector id
            int numOfReflectors = machineRepository.getNumberOfDefinedReflectors();
            String randomReflectorId = Utils.intToRoman(random.nextInt(numOfReflectors) + 1);
            // generate random Plugboard wires
            List<char[]> plugboardWires = new ArrayList<>();
            List<Character> alphabetList = new ArrayList<>();
            for (char c : alphabet.toCharArray()) {
                alphabetList.add(c);
            }
            Collections.shuffle(alphabetList);
            int numberOfWires = random.nextInt((alphabet.length() / 2) + 1);
            for (int i = 0; i < numberOfWires*2; i+=2) {
                char c1 = alphabetList.get(i);
                char c2 = alphabetList.get(i+1);
                plugboardWires.add(new char[]{c1, c2});
            }
            // build code and set it to machine
            CodeBuilder codeBuilder = new CodeBuilderImpl(machineRepository);
            Code newCode = codeBuilder.buildCode(rotorsIds.subList(0, machineRepository.getNumberOfRotorsInUse()), randomRotorsPositions, randomReflectorId, plugboardWires);
            machine.setCode(newCode);
            machineHistory.addNewCodeToHistory(newCode);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to generate automatic code: " + e.getMessage());
        }
    }

    @Override
    public ProcessMessageDto processMessage(String message) throws IllegalArgumentException, IllegalStateException {
        ensureCodeConfigured();
        try {
            String codeBeforeProcessingCompact = generateCodeSnapShotToDto(machine.getCurrentCodeSnapShot()).toCompactString();
            String upperMessage = message.toUpperCase();
            long startTime = System.nanoTime();

            for (char c : upperMessage.toCharArray()) {
                if(!Utils.isInAlphabet(c, machineRepository.getAlphabet())){
                    throw new IllegalArgumentException("Input character " + c + " is not in alphabet");
                }
            }
            StringBuilder result = new StringBuilder();
            for (char c : upperMessage.toCharArray()) {
                result.append(machine.process(c));
            }
            long endTime = System.nanoTime();
            int durationNano = (int) (endTime - startTime);
            this.machineHistory.SaveMessageToHistory(codeBeforeProcessingCompact, upperMessage, result.toString(), durationNano);
            CodeSnapShotDto currentCodeDto = generateCodeSnapShotToDto(machine.getCurrentCodeSnapShot());
            return new ProcessMessageDto(result.toString(), currentCodeDto.toPositionsWithNotchCompact(), codeBeforeProcessingCompact, durationNano);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to process message: " + e.getMessage());
        }
    }

    @Override
    public void resetCode() throws IllegalStateException {
        ensureCodeConfigured();
        machine.resetCode();
    }


    @Override
    public HistoryDto getHistory() {
        Map<String, List<MessageHistory>> messageHistory = machineHistory.getMessageHistoryByCodeDescription();
        Map<String, List<MessageHistoryDto>> messageHistoryByCodeDescriptionDto = new LinkedHashMap<>();
        for (Map.Entry<String, List<MessageHistory>> entry : messageHistory.entrySet()) {
            List<MessageHistoryDto> messageHistoryDto = new ArrayList<>();
            for (MessageHistory message : entry.getValue()) {
                messageHistoryDto.add(generateMessageHistoryToDto(message));
            }
            messageHistoryByCodeDescriptionDto.put(entry.getKey(), messageHistoryDto);
        }
        return new HistoryDto(messageHistoryByCodeDescriptionDto);
    }
    private MessageHistoryDto generateMessageHistoryToDto(MessageHistory messageHistory) {
        if (messageHistory == null) {
            return null;
        }
        return new MessageHistoryDto(messageHistory.getInput(), messageHistory.getOutput(), messageHistory.getDuration());
    }


    private void ensureCodeConfigured() throws IllegalStateException {
        if (machine.getCode() == null) {
            throw new IllegalStateException("Machine code not configured");
        }
    }
}
