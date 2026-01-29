import java.util.Map;
import java.util.HashMap;

public class GameManager {
    private TurnPhase _turnPhase;
    private final NumberGenerator _numberGenerator;
    private final InputManager _inputManager;
    private final JudgeManager _judgeManager;
    private String _generatedNumber;
    private String _userNumber;
    private final Map<TurnPhase, Runnable> _phaseHandler;
    public GameManager(TurnPhase turnPhase){
        _turnPhase = turnPhase;
        _numberGenerator = new NumberGenerator();
        _inputManager = new InputManager();
        _judgeManager = new JudgeManager();

        _phaseHandler = new HashMap<>();
        _phaseHandler.put(TurnPhase.idle, this::handleIdlePhase);
        _phaseHandler.put(TurnPhase.generateNumber, this::handleGenerateNumberPhase);
        _phaseHandler.put(TurnPhase.inputNumber, this::handleInputNumberPhase);
        _phaseHandler.put(TurnPhase.judgeResult, this::handleJudgeResultPhase);
    }
    private void handleIdlePhase() {
        boolean idleFlag = _inputManager.IdleInput();
        
        _turnPhase = idleFlag ? TurnPhase.generateNumber : TurnPhase.quit;
        _generatedNumber = idleFlag ? "" : _generatedNumber;
    }

    private void handleGenerateNumberPhase() {
        _generatedNumber = _numberGenerator.GenerateNumber();
        _turnPhase = TurnPhase.inputNumber;
    }

    private void handleInputNumberPhase() {
        _userNumber = _inputManager.PredictionInput();
        _turnPhase = TurnPhase.judgeResult;
    }
    
    private void handleJudgeResultPhase() {
        JudgeResult result = _judgeManager.JudgeResult(_generatedNumber, _userNumber);
        if (result.strike() != 3) {
            _turnPhase = TurnPhase.inputNumber;
            String resultString = result.strike() + result.ball() == 0 ? "낫씽" : result.strike() + "스트라이크 " + result.ball() + "볼";
            System.out.println(resultString);
            return;
        }
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
        _turnPhase = TurnPhase.idle;
    }

    public void PlayGame(){
        while(_turnPhase != TurnPhase.quit){
            Runnable handler = _phaseHandler.get(_turnPhase);
            handler.run();
        }
    }
}
