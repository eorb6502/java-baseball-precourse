package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NumberGenerator {
    private static final int BASEBALL_SIZE = 3;
    public NumberGenerator() {}
    public String GenerateNumber() {
        String result = "";
        Map<String, String> map = new HashMap<>();
        Random random = new Random();
        while(result.length() < BASEBALL_SIZE) {
            String randomNumber =random.nextInt(1, 9) + "";
            if (map.get(randomNumber) != null) continue;
            map.put(randomNumber, "");
            result += randomNumber;
        }
        return result;
    }

    public String GenerateFixedNumber(String fixedNumber) {
        if (!IsProperNumber(fixedNumber)) {
            throw new IllegalArgumentException("Invalid fixed number");
        }
        return fixedNumber;
    }

    private boolean IsProperNumber(String number) {
        if (number == null || number.length() != BASEBALL_SIZE) return false;
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < BASEBALL_SIZE; i++) {
            char c = number.charAt(i);
            if (c < '1' || c > '9' || map.containsKey(c)) return false;
            map.put(c, "");
        }
        return true;
    }
}
