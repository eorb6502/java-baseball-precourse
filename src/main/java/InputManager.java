import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class InputManager {
    Scanner scanner;
    public InputManager() {
        scanner = new Scanner(System.in);
    }
    public boolean IdleInput(){
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String flag = "";
        while(true) {
            flag = scanner.next();
            // 게임 플레이
            if (flag.equals("1")) {
                return true;
            }
            // 게임 종료
            if (flag.equals("2")) {
                return false;
            }
            System.out.println("1 혹은 2를 입력해주세요.");
        }
    }
    private boolean IsProperInput(String s){
        if (s.length() != 3) return false;
        Map<Character, String> map = new HashMap<>();
        for (int i=0; i<3; i++){
            if ((49 > s.charAt(i) || s.charAt(i) > 57) || map.get(s.charAt(i)) != null) return false;
            map.put(s.charAt(i), "");
        }
        return true;
    }
    public String PredictionInput(){
        System.out.print("숫자를 입력해주세요 : ");
        while(true) {
            String userInput = scanner.next();
            if (!IsProperInput((userInput))) {
                System.out.print("1 ~ 9 사이의 중복되지 않는 세 자리 숫자를 입력해주세요.\n숫자를 입력해주세요 : ");
                continue;
            }
            return userInput;
        }
    }
}
