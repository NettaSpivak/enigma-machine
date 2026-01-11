package ui.menu.actions;

import dto.CodeHistoryDto;
import dto.CodeSnapShotDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetCodeManual {
    public static CodeSnapShotDto setCodeMenual(String rotors_, String rotorsPositions_, String reflector, String plugboard) {
        try {
            // rotors string to ids list
            String rotors = rotors_.trim();
            String rotorsPositions = rotorsPositions_.trim();
            List<Integer> rotorsList = Arrays.stream(rotors.split(","))
                    .map(rotor -> Integer.parseInt(rotor.trim())).toList();
            // positions string to list
            List<Character> positionsList = new ArrayList<>();
            rotorsPositions = rotorsPositions.trim().toUpperCase();
            for (Character c : rotorsPositions.toCharArray()) {
                positionsList.add(c);
            }
            List<char[]> plugboardPairs = setPlugboardPairs(plugboard.trim().toUpperCase());
            return new CodeSnapShotDto(rotorsList, positionsList, reflector.trim(), plugboardPairs);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error building code: rotors Ids must be numbers: " + e.getMessage());
        }
    }

    private static List<char[]> setPlugboardPairs(String plugboard) {
        List<char[]> plugboardPairs = new ArrayList<>();
        if (plugboard == null || plugboard.isEmpty()) {
            return plugboardPairs;
        }
        if (plugboard.length() % 2 != 0) {
            throw new IllegalArgumentException("Plugboard input must contain an even number of characters");
        }
        for (int i = 0; i < plugboard.length(); i+=2) {
            char c1 = plugboard.charAt(i);
            char c2 = plugboard.charAt(i + 1);
            plugboardPairs.add(new char[]{c1, c2});
        }
        return plugboardPairs;
    }
}
