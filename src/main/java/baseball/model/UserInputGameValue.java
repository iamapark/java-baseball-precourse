package baseball.model;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static baseball.constants.BaseballGameBaseConstant.VALID_GAME_VALUE_DIGITS;
import static baseball.constants.BaseballGameBaseConstant.VALID_GAME_VALUE_NUMBERS;

/**
 * @author jinyoung
 * @since 2022/04/14
 */
public class UserInputGameValue {

    private final String userInput;

    protected UserInputGameValue(String userInput) {
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
            checkIsNumberValue(userInput, index);
            checkValueIsDuplicated(characterSet, userInput.charAt(index));
        }
    }

    private void checkNumberOfDigits() {
        if (userInput.length() != VALID_GAME_VALUE_DIGITS) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNullOrEmpty() {
        if (Objects.isNull(userInput) || "".equals(userInput)) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkValueIsDuplicated(Set<Character> characterSet, char character) {
        if (characterSet.contains(character)) {
            throw new IllegalArgumentException();
        }
        characterSet.add(character);
    }

    private static void checkIsNumberValue(String userInput, int index) {
        final boolean isValidNumberCharacter = VALID_GAME_VALUE_NUMBERS.contains(
                String.valueOf(userInput.charAt(index)));
        if (!isValidNumberCharacter) {
            throw new IllegalArgumentException();
        }
    }
}
