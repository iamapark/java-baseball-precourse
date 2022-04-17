package baseball.model;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;

import static baseball.constants.BaseballGameBaseConstant.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jinyoung
 * @since 2022/04/16
 */
class BaseballGameResultTest {

    // Todo: 각 테스트 케이스 별로 ParameterizedTest로 변환. Source의 경우 csv 파일을 읽어서 처리 (낫싱.csv, 스트라이크.csv, 볼.csv...)

    // 낫싱
    @Test
    void test_gameResult_nothing() {
        assertGameResultAndOutput("123", asList(4, 6, 5), NOTHING_TITLE, false);
    }

    // 스트라이크 (1스트라이크, 2스트라이크)
    @Test
    void test_gameResult_strike() {
        assertGameResultAndOutput("398", asList(1, 9, 5), "1" + STRIKE_TITLE, false);
        assertGameResultAndOutput("752", asList(7, 3, 2), "2" + STRIKE_TITLE, false);
    }

    // 볼 (1볼, 2볼, 3볼)
    @Test
    void test_gameResult_ball() {
        assertGameResultAndOutput("123", asList(4, 5, 1), "1" + BALL_TITLE, false);
        assertGameResultAndOutput("431", asList(1, 4, 2), "2" + BALL_TITLE, false);
        assertGameResultAndOutput("981", asList(8, 1, 9), "3" + BALL_TITLE, false);
    }

    // 스트라이크 + 볼 (1스트라이크 1볼, 1스트라이크 2볼)
    @Test
    void test_gameResult_strike_and_ball() {
        assertGameResultAndOutput("123", asList(1, 5, 2), "1" + BALL_TITLE + " 1" + STRIKE_TITLE, false);
        assertGameResultAndOutput("476", asList(6, 7, 4), "2" + BALL_TITLE + " 1" + STRIKE_TITLE, false);
    }

    // 3 스트라이크
    @Test
    void test_gameResult_3strike() {
        assertGameResultAndOutput("498", asList(4, 9, 8), "3" + STRIKE_TITLE, true);
    }

    private void assertGameResultAndOutput(String userInput, List<Integer> randomValues,
                                           String expectedOutputString, boolean expectedUserWin) {
        final UserInputGameValue userInputGameValue = new UserInputGameValue(userInput);
        final RandomGameValue randomGameValue = new RandomGameValue(new LinkedHashSet<>(randomValues));

        final BaseballGameResult gameResult = BaseballGameResult.decideGameResult(userInputGameValue, randomGameValue);
        assertEquals(expectedUserWin, gameResult.isUserWin());
        assertEquals(expectedOutputString, gameResult.getResultString());
    }
}
