public class GameManager {
    private TurnPhase _turnPhase;
    private NumberGenerator _numberGenerator;
    private InputManager _inputManager;
    private JudgeManager _judgeManager;
    private String _generatedNumber;
    public GameManager(){
        _turnPhase = TurnPhase.generateNumber;
        _numberGenerator = new NumberGenerator();
        _inputManager = new InputManager();
        _judgeManager = new JudgeManager();
    }
    public String GenerateRandomNumber(){
        return _numberGenerator.GenerateNumber();
    }
}
