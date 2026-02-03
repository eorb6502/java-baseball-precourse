package view;

import java.util.HashMap;
import java.util.Map;

import constant.ErrorEnum;
import constant.JudgeResult;
import constant.TurnPhase;

public class OutputManager {
	// errorEnumStringMap의 string에는 뒤에 \n을 넣어 출력 메서드는 println이 아닌 print를 사용
	private final Map<ErrorEnum, String> _errorEnumStringMap;
	private final Map<TurnPhase, String> _phaseStartMessageMap;

	public OutputManager() {
		_errorEnumStringMap = new HashMap<>();
		_errorEnumStringMap.put(ErrorEnum.IDLE_INPUT_ERROR, "1 혹은 2를 입력해주세요.\n");
		_errorEnumStringMap.put(
				ErrorEnum.USER_INPUT_ERROR,
				"1 ~ 9 사이의 중복되지 않는 세 자리 숫자를 입력해주세요.\n숫자를 입력해주세요 : "
		);

		_phaseStartMessageMap = new HashMap<>();
		_phaseStartMessageMap.put(TurnPhase.IDLE, "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.\n");
		_phaseStartMessageMap.put(TurnPhase.INPUT_NUMBER, "숫자를 입력해주세요 : ");
	}

	public void printErrorMessage(ErrorEnum errorEnum) {
		if (_errorEnumStringMap.containsKey(errorEnum)) {
			System.out.print(_errorEnumStringMap.get(errorEnum));
		}
	}

	public void printPhaseStartMessage(TurnPhase turnPhase) {
		if (_phaseStartMessageMap.containsKey(turnPhase)) {
			System.out.print(_phaseStartMessageMap.get(turnPhase));
		}
	}

	public void printJudgeResult(JudgeResult result) {
		if (result.strike() == 3) {
			System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
			return;
		}
		String resultString = result.strike() + result.ball() == 0
				? "낫씽"
				: result.strike() + "스트라이크 " + result.ball() + "볼";
		System.out.println(resultString);
	}
}
