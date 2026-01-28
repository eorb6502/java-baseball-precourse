public class GameManager {
    private TurnPhase _turnPhase;
    private NumberGenerator _numberGenerator;
    private InputManager _inputManager;
    private JudgeManager _judgeManager;
    private String _generatedNumber;
    private String _userNumber;
    public GameManager(){
        _turnPhase = TurnPhase.idle;
        _numberGenerator = new NumberGenerator();
        _inputManager = new InputManager();
        _judgeManager = new JudgeManager();
    }
    private void idlePhasePlay() {
        if (_turnPhase != TurnPhase.idle) return;
        boolean idleFlag = _inputManager.IdleInput();
        if (idleFlag){
            _turnPhase = TurnPhase.generateNumber;
            _generatedNumber = "";
            return;
        }
        _turnPhase = TurnPhase.quit;
    }

    public void PlayGame(){
        while(true){
            if (_turnPhase == TurnPhase.quit) break;
            if (_turnPhase == TurnPhase.idle) {
                idlePhasePlay();
                continue;
            }
            if (_turnPhase == TurnPhase.generateNumber) {
                _generatedNumber = _numberGenerator.GenerateNumber();
                _turnPhase = TurnPhase.inputNumber;
                continue;
            }
            if (_turnPhase == TurnPhase.inputNumber) {
                _userNumber = _inputManager.PredictionInput();
                System.out.println(_userNumber);
                _turnPhase = TurnPhase.judgeResult;
                continue;
            }
            if (_turnPhase == TurnPhase.judgeResult){
                boolean result = _judgeManager.JudgeResult(_generatedNumber, _userNumber);
                if (result) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
                    _turnPhase = TurnPhase.idle;
                    continue;
                }
                _turnPhase = TurnPhase.inputNumber;
            }
        }
    }
}
