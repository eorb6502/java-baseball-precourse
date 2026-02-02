package Model;

import Constant.JudgeResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class JudgeManagerTest {
    private static Stream<Arguments> judgeCases() {
        return Stream.of(
                Arguments.of("456", 0, 0),
                Arguments.of("415", 0, 1),
                Arguments.of("314", 0, 2),
                Arguments.of("312", 0, 3),
                Arguments.of("145", 1, 0),
                Arguments.of("134", 1, 1),
                Arguments.of("132", 1, 2),
                Arguments.of("124", 2, 0),
                Arguments.of("123", 3, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("judgeCases")
    void judgeResultCoversValidStrikeBallCombinations(String userNumber, int strike, int ball) {
        JudgeManager judgeManager = new JudgeManager();

        JudgeResult result = judgeManager.JudgeResult("123", userNumber);

        assertThat(result.strike()).isEqualTo(strike);
        assertThat(result.ball()).isEqualTo(ball);
    }

    @Test
    void twoStrikeOneBallIsNotReachableWithUniqueDigits() {
        JudgeManager judgeManager = new JudgeManager();
        String[] permutations = {"123", "132", "213", "231", "312", "321"};

        for (String userNumber : permutations) {
            JudgeResult result = judgeManager.JudgeResult("123", userNumber);
            assertThat(result.strike() == 2 && result.ball() == 1)
                    .as("2S1B should be unreachable for %s", userNumber)
                    .isFalse();
        }
    }
}
