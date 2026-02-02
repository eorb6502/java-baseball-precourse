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
}
