package Controller;

import Model.JudgeManager;
import Model.NumberGenerator;
import View.InputManager;
import View.OutputManager;
import Constant.TurnPhase;
import Constant.ErrorEnum;
import Constant.JudgeResult;

import java.util.Map;
import java.util.HashMap;

public class GameManager {
    private TurnPhase _turnPhase;
    private final NumberGenerator _numberGenerator;
    private final InputManager _inputManager;
    private final JudgeManager _judgeManager;
    private final OutputManager _outputManager;
    private String _generatedNumber;
    private String _userNumber;
    private final Map<TurnPhase, Runnable> _phaseHandler;
    private static final int BASEBALL_SIZE = 3;
    public GameManager(TurnPhase turnPhase){
        _turnPhase = turnPhase;
        _numberGenerator = new NumberGenerator();
        _inputManager = new InputManager();
        _judgeManager = new JudgeManager();
        _outputManager = new OutputManager();

        _phaseHandler = new HashMap<>();
        _phaseHandler.put(TurnPhase.idle, this::handleIdlePhase);
        _phaseHandler.put(TurnPhase.generateNumber, this::handleGenerateNumberPhase);
        _phaseHandler.put(TurnPhase.inputNumber, this::handleInputNumberPhase);
        _phaseHandler.put(TurnPhase.judgeResult, this::handleJudgeResultPhase);
    }

    private void handleIdlePhase() {
        while (true) {
            String idleInput = _inputManager.GetInput();
            if (idleInput.equals("1")) {
                _turnPhase = TurnPhase.generateNumber;
                _generatedNumber = "";
                return;
            }
            if (idleInput.equals("2")) {
                _turnPhase = TurnPhase.quit;
                return;
            }
            _outputManager.PrintErrorMessage(ErrorEnum.idleInputError);
        }
    }

    private void handleGenerateNumberPhase() {
        _generatedNumber = _numberGenerator.GenerateNumber();
        _turnPhase = TurnPhase.inputNumber;
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

    private void handleInputNumberPhase() {
        while(true) {
            String userInput = _inputManager.GetInput();
            if (!IsProperInput((userInput))) {
                _outputManager.PrintErrorMessage(ErrorEnum.userInputError);
                continue;
            }
            _userNumber = userInput;
            break;
        }
        _turnPhase = TurnPhase.judgeResult;
    }
    
    private void handleJudgeResultPhase() {
        JudgeResult result = _judgeManager.JudgeResult(_generatedNumber, _userNumber);
        _outputManager.PrintJudgeResult(result);
        if (result.strike() != BASEBALL_SIZE) {
            _turnPhase = TurnPhase.inputNumber;
            return;
        }
        _turnPhase = TurnPhase.idle;
    }

    public void PlayGame(){
        while(_turnPhase != TurnPhase.quit){
            _outputManager.PrintPhaseStartMessage(_turnPhase);
            Runnable handler = _phaseHandler.get(_turnPhase);
            handler.run();
        }
    }
}
