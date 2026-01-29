public class App {
    private GameManager _gameManager;
    public static void main (String[] args){
        GameManager _gameManager = new GameManager(TurnPhase.generateNumber);
        _gameManager.PlayGame();
    }
}