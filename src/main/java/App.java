import constant.TurnPhase;
import controller.GameManager;

public class App {
	private GameManager _gameManager;

	public static void main(String[] args) {
		GameManager _gameManager = new GameManager(TurnPhase.GENERATE_NUMBER);
		_gameManager.playGame();
	}
}
