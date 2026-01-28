import java.util.Map;
import java.util.HashMap;
public class JudgeManager {
    public JudgeManager() {}
    public JudgeResult JudgeResult(String number, String user){
        Map<Character, String> map = new HashMap<>();
        for (int i=0; i<3; i++){
            map.put(number.charAt(i), "");
            map.put(user.charAt(i), "");
        }
        int ball = 6 - map.size();
        int strike = 0;
        for (int i=0; i<3; i++){
            if (number.charAt(i) == user.charAt(i)) strike ++;
        }
        ball -= strike;
        return new JudgeResult(strike, ball);
    }
}
