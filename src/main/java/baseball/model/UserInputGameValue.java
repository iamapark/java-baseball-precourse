package baseball.model;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static baseball.constants.BaseballGameBaseConstant.VALID_USER_INPUT_DIGIT;
import static baseball.constants.BaseballGameBaseConstant.VALID_USER_INPUT_NUMBERS;

/**
 * @author jinyoung
 * @since 2022/04/14
 */
public class UserInputGameValue {

    private final String userInput;

    private UserInputGameValue(String userInput) {
        this.userInput = userInput;
    }

    public String getUserInput() {
        return userInput;
    }

    public static UserInputGameValue get() {
        System.out.print("숫자를 입력해주세요 : ");
        final String userInput = Console.readLine();
        final UserInputGameValue userInputGame = new UserInputGameValue(userInput);
        userInputGame.validate();
        return userInputGame;
    }

    public void validate() {
        checkNullOrEmpty();
        checkNumberOfDigits();
        checkValidValue();
    }

    private void checkValidValue() {
        final Set<Character> characterSet = new HashSet<>();
        for (int index = 0; index < userInput.length(); index++) {
            validateUserInputByIndex(userInput, index);
            validateUserInputIsDuplicated(characterSet, userInput.charAt(index));
        }
    }

    private void checkNumberOfDigits() {
        if (userInput.length() != VALID_USER_INPUT_DIGIT) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNullOrEmpty() {
        if (Objects.isNull(userInput) || "".equals(userInput)) {
            throw new IllegalArgumentException();
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
