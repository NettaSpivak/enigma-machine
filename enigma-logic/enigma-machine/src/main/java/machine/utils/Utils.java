package machine.utils;

public class Utils {

    public static boolean isInAlphabet(char c, String alphabet) {
        for (char a : alphabet.toCharArray()) {
            if (a == c) return true;
        }
        return false;
    }

    public static char normalizeLetter(String s) throws IllegalArgumentException {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }
        if (s.contains(" ") && s.trim().isEmpty())
            return ' ';
        if (s.trim().length() != 1) {
            throw new IllegalArgumentException("Input string must contain exactly one non-space character");
        }
        return Character.toUpperCase(s.trim().charAt(0));
    }

    public static int romanToInt(String s) throws IllegalArgumentException {
        String id = s.toUpperCase();
        switch (id) {
            case "I":   return 1;
            case "II":  return 2;
            case "III": return 3;
            case "IV":  return 4;
            case "V":   return 5;
            default:
                throw new IllegalArgumentException("Invalid roman number: " + s + " (allowed: I, II, III, IV, V)");
        }
    }

    public static String intToRoman(int num) throws IllegalArgumentException {
        switch (num) {
            case 1:   return "I";
            case 2:   return "II";
            case 3:   return "III";
            case 4:   return "IV";
            case 5:   return "V";
            default:
                throw new IllegalArgumentException("Invalid number: " + num + " (allowed: 1, 2, 3, 4, 5)");
        }
    }
}
