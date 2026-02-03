package controller;

import java.util.HashMap;
import java.util.Map;

import constant.ErrorEnum;
import constant.JudgeResult;
import constant.TurnPhase;
import model.JudgeManager;
import model.NumberGenerator;
import view.InputManager;
import view.OutputManager;

public class GameManager {
	private static final int BASEBALL_SIZE = 3;

	private TurnPhase _turnPhase;
	private final NumberGenerator _numberGenerator;
	private final InputManager _inputManager;
	private final JudgeManager _judgeManager;
	private final OutputManager _outputManager;
	private String _generatedNumber;
	private String _userNumber;
	private final Map<TurnPhase, Runnable> _phaseHandler;

	public GameManager(TurnPhase turnPhase) {
		_turnPhase = turnPhase;
		_numberGenerator = new NumberGenerator();
		_inputManager = new InputManager();
		_judgeManager = new JudgeManager();
		_outputManager = new OutputManager();

		_phaseHandler = new HashMap<>();
		_phaseHandler.put(TurnPhase.IDLE, this::handleIdlePhase);
		_phaseHandler.put(TurnPhase.GENERATE_NUMBER, this::handleGenerateNumberPhase);
		_phaseHandler.put(TurnPhase.INPUT_NUMBER, this::handleInputNumberPhase);
		_phaseHandler.put(TurnPhase.JUDGE_RESULT, this::handleJudgeResultPhase);
	}

	private void handleIdlePhase() {
		while (true) {
			String idleInput = _inputManager.getInput();
			if (idleInput.equals("1")) {
				_turnPhase = TurnPhase.GENERATE_NUMBER;
				_generatedNumber = "";
				return;
			}
			if (idleInput.equals("2")) {
				_turnPhase = TurnPhase.QUIT;
				return;
			}
			_outputManager.printErrorMessage(ErrorEnum.IDLE_INPUT_ERROR);
		}
	}

	private void handleGenerateNumberPhase() {
		_generatedNumber = _numberGenerator.generateNumber();
		_turnPhase = TurnPhase.INPUT_NUMBER;
	}

	private boolean isProperInput(String input) {
		if (input.length() != BASEBALL_SIZE) {
			return false;
		}
		Map<Character, String> map = new HashMap<>();
		for (int i = 0; i < BASEBALL_SIZE; i++) {
			char character = input.charAt(i);
			if (character < '1' || character > '9' || map.containsKey(character)) {
				return false;
			}
			map.put(character, "");
		}
		return true;
	}

	private void handleInputNumberPhase() {
		while (true) {
			String userInput = _inputManager.getInput();
			if (!isProperInput(userInput)) {
				_outputManager.printErrorMessage(ErrorEnum.USER_INPUT_ERROR);
				continue;
			}
			_userNumber = userInput;
			break;
		}
		_turnPhase = TurnPhase.JUDGE_RESULT;
	}

	private void handleJudgeResultPhase() {
		JudgeResult result = _judgeManager.judgeResult(_generatedNumber, _userNumber);
		_outputManager.printJudgeResult(result);
		if (result.strike() != BASEBALL_SIZE) {
			_turnPhase = TurnPhase.INPUT_NUMBER;
			return;
		}
		_turnPhase = TurnPhase.IDLE;
	}

	public void playGame() {
		while (_turnPhase != TurnPhase.QUIT) {
			_outputManager.printPhaseStartMessage(_turnPhase);
			Runnable handler = _phaseHandler.get(_turnPhase);
			handler.run();
		}
	}
}
