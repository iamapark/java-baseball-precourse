package baseball.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static baseball.validator.UserInputValidator.validateUserInput;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author jinyoung
 * @date 2022/04/14
 */
class UserInputValidatorTest {

    @ParameterizedTest(name = "정해진 숫자가 아닌 입력 값 검증 테스트 {index}: {arguments}")
    @ValueSource(strings = {"a", "1a", "23b", "c39", "abc", "@&!", "012"})
    void test_non_numeric_userInput(String wrongUserInput) {
        assertThatIllegalArgumentExceptionThrown(wrongUserInput);
    }

    @ParameterizedTest(name = "중복 숫자 입력 값 검증 테스트 {index}: {arguments}")
    @ValueSource(strings = {"112", "122", "333", "454"})
    void test_duplicated_numeric_userInput(String wrongUserInput) {
        assertThatIllegalArgumentExceptionThrown(wrongUserInput);
    }

    @ParameterizedTest(name = "3자리 미만 또는 초과 입력 값 테스트 {index}: {arguments}")
    @ValueSource(strings = {"1", "12", "1234"})
    void test_moreThan_or_lessThan_3digit_numeric_userInput(String wrongUserInput) {
        assertThatIllegalArgumentExceptionThrown(wrongUserInput);
    }

    @ParameterizedTest(name = "비정상적인 입력 값 테스트 {index}: {arguments}")
    @NullAndEmptySource
    void test_null_and_empty_userInput(String nullOrEmptyUserInput) {
        assertThatIllegalArgumentExceptionThrown(nullOrEmptyUserInput);
    }

    @ParameterizedTest(name = "정상적인 입력 값 검증 테스트 {index}: {arguments}")
    @ValueSource(strings = {"123", "456", "987", "491"})
    void test_normal_userInput(String validUserInput) {
        assertThatNoException().isThrownBy(() -> validateUserInput(validUserInput));
    }

    private void assertThatIllegalArgumentExceptionThrown(String invalidUserInput) {
        assertThatThrownBy(() ->
            validateUserInput(invalidUserInput)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
