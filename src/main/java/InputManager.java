import java.util.Scanner;

public class InputManager {
    Scanner scanner;
    public InputManager() {
        scanner = new Scanner(System.in);
    }
    public boolean IdleInput(){
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
        int flag = -1;
        while(true){
            flag = scanner.nextInt();
            // 게임 플레이
            if (flag == 1){
                return true;
            }
            // 게임 종료
            if (flag == 2){
                return false;
            }
            System.out.println("1 혹은 2를 입력해주세요");
        }
    }
}
