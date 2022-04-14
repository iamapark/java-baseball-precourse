package baseball.validator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static baseball.constants.BaseballGameBaseConstant.VALID_USER_INPUT_DIGIT;
import static baseball.constants.BaseballGameBaseConstant.VALID_USER_INPUT_NUMBERS;

/**
 * @author jinyoung
 * @date 2022/04/14
 */
public class UserInputValidator {

    private UserInputValidator() {}

    public static void validateUserInput(String userInput) {
        if (Objects.isNull(userInput) || "".equals(userInput)) {
            throw new IllegalArgumentException();
        }

        if (userInput.length() != VALID_USER_INPUT_DIGIT) {
            throw new IllegalArgumentException();
        }

        for (int index = 0; index < userInput.length(); index++) {
            validateUserInputByIndex(userInput, index);
        }

        // 중복 체크
        final Set<Character> characterSet = new HashSet<>();
        for (int index = 0; index < userInput.length(); index++) {
            validateUserInputIsDuplicated(characterSet, userInput.charAt(index));
        }
    }

    private static void validateUserInputIsDuplicated(Set<Character> characterSet, char character) {
        if (characterSet.contains(character)) {
            throw new IllegalArgumentException();
        } else {
            characterSet.add(character);
        }
    }

    private static void validateUserInputByIndex(String userInput, int index) {
        final boolean isValidNumberCharacter = VALID_USER_INPUT_NUMBERS.contains(String.valueOf(userInput.charAt(index)));
        if (!isValidNumberCharacter) {
            throw new IllegalArgumentException();
        }
    }
}
