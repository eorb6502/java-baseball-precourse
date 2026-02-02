package Model;

import Constant.JudgeResult;
import java.util.Map;
import java.util.HashMap;
public class JudgeManager {
    public JudgeManager() {}
    private static final int BASEBALL_SIZE = 3;
    private static final int PLAYER_CNT = 2;
    public JudgeResult JudgeResult(String number, String user){
        Map<Character, String> map = new HashMap<>();
        for (int i=0; i<BASEBALL_SIZE; i++){
            map.put(number.charAt(i), "");
            map.put(user.charAt(i), "");
        }
        int ball = BASEBALL_SIZE * PLAYER_CNT - map.size();
        int strike = 0;
        for (int i=0; i<BASEBALL_SIZE; i++){
            if (number.charAt(i) == user.charAt(i)) strike ++;
        }
        ball -= strike;
        return new JudgeResult(strike, ball);
    }
}
