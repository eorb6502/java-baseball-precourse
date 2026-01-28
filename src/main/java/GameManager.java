public class GameManager {
    private TurnPhase _turnPhase;
    private NumberGenerator _numberGenerator;
    private InputManager _inputManager;
    private JudgeManager _judgeManager;
    private String _generatedNumber;
    public GameManager(){
        _turnPhase = TurnPhase.idle;
        _numberGenerator = new NumberGenerator();
        _inputManager = new InputManager();
        _judgeManager = new JudgeManager();
    }
    public String GenerateRandomNumber(){
        return _numberGenerator.GenerateNumber();
    }
    private void idlePhasePlay() {
        if (_turnPhase != TurnPhase.idle) return;
        boolean idleFlag = _inputManager.IdleInput();
        if (idleFlag){
            _turnPhase = TurnPhase.generateNumber;
            return;
        }
        _turnPhase = TurnPhase.quit;
    }
    public void PlayGame(){
        while(true){
            if (_turnPhase == TurnPhase.quit) break;
            if (_turnPhase == TurnPhase.idle) {
                idlePhasePlay();
            }
        }
    }
}
