import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NumberGenerator {
    public NumberGenerator() {}
    public String GenerateNumber() {
        String result = "";
        Map<String, String> map = new HashMap<>();
        Random random = new Random();
        while(result.length() < 3) {
            String randomNumber =random.nextInt(1, 9) + "";
            if (map.get(randomNumber) != null) continue;
            map.put(randomNumber, "");
            result += randomNumber;
        }
        return result;
    }
}
