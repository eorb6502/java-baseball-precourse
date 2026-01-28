import java.util.Map;
import java.util.HashMap;

public class GameManager {
    private TurnPhase _turnPhase;
    private NumberGenerator _numberGenerator;
    private InputManager _inputManager;
    private JudgeManager _judgeManager;
    private String _generatedNumber;
    private String _userNumber;
    private Map<TurnPhase, Runnable> _phaseHandler;
    public GameManager(){
        _turnPhase = TurnPhase.idle;
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
        System.out.println(_userNumber);
        _turnPhase = TurnPhase.judgeResult;
    }
    
    private void handleJudgeResultPhase() {
        boolean result = _judgeManager.JudgeResult(_generatedNumber, _userNumber);
        if (!result) {
            _turnPhase = TurnPhase.inputNumber;
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
