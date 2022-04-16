package baseball.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

import static baseball.constants.BaseballGameBaseConstant.VALID_GAME_VALUE_DIGITS;
import static baseball.constants.BaseballGameBaseConstant.VALID_GAME_VALUE_NUMBERS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author jinyoung
 * @date 2022/04/14
 */
class RandomGameValueTest {

    /**
     * Random 값을 다루는 클래스이기 때문에 100회 정도 값을 생성하여 조건을 통과하는지 검증한다.
     */

    @Test @DisplayName("RandomGameValue.get() 결과가 3자리인지 테스트")
    void test_randomGameValue_is_3_digits() {
        doTestMultipleTimes(randomValues -> assertEquals(VALID_GAME_VALUE_DIGITS, randomValues.size()));
    }

    @Test @DisplayName("RandomGameValue.get() 결과가 1~9 사이 값인지 테스트")
    void test_randomGameValue_is_number() {
        doTestMultipleTimes(randomValues -> randomValues.forEach(randomValue ->
            assertTrue(VALID_GAME_VALUE_NUMBERS.contains(String.valueOf(randomValue)))
        ));
    }

    private void doTestMultipleTimes(Consumer<List<Integer>> consumer) {
        for (int count = 0; count < 100; count++) {
            consumer.accept(RandomGameValue.get().getRandomValues());
        }
    }
}
