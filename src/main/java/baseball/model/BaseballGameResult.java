package baseball.model;

import static baseball.constants.BaseballGameBaseConstant.*;

import java.util.List;

/**
 * @author jinyoung
 * @since 2022/04/14
 */
public class BaseballGameResult {

    private final int strikeCount;
    private final int ballCount;

    private BaseballGameResult(UserInputGameValue userInputGame, RandomGameValue randomGeneratedGame) {
        this.strikeCount = decideStrikeCount(userInputGame, randomGeneratedGame);
        this.ballCount = decideBallCount(userInputGame, randomGeneratedGame);
        System.out.println(getResultString());
    }

    public static BaseballGameResult decideGameResult(UserInputGameValue userInputGame,
                                                      RandomGameValue randomGeneratedGame) {
        return new BaseballGameResult(userInputGame, randomGeneratedGame);
    }

    public boolean isUserWin() {
        return VALID_NUMBER_OF_GAME_VALUE_DIGIT == this.strikeCount;
    }

    public String getResultString() {
        if (this.strikeCount == 0 && this.ballCount == 0) {
            return NOTHING_TITLE;
        }

        String resultString = "";
        resultString += this.ballCount != 0 ? this.ballCount + "" + BALL_TITLE : "";
        resultString += this.strikeCount != 0 ? " " + this.strikeCount + "" + STRIKE_TITLE : "";

        return resultString.replaceAll("^ ", "").replaceAll(" $", "");
    }

    private int decideStrikeCount(UserInputGameValue userInputGame, RandomGameValue randomGeneratedGame) {
        int strikeCount = 0;
        for (int index = 0; index < VALID_NUMBER_OF_GAME_VALUE_DIGIT; index++) {
            final String randomIndexValue = randomGeneratedGame.getRandomValues().get(index).toString();
            final String userInputIndexValue = String.valueOf(userInputGame.getUserInput().charAt(index));
            strikeCount += randomIndexValue.equals(userInputIndexValue) ? 1 : 0;
        }
        return strikeCount;
    }

    private int decideBallCount(UserInputGameValue userInputGame, RandomGameValue randomGeneratedGame) {
        int ballCount = 0;
        final List<Integer> randomValues = randomGeneratedGame.getRandomValues();
        for (int index = 0; index < VALID_NUMBER_OF_GAME_VALUE_DIGIT; index++) {
            final String userInputIndexValue = String.valueOf(userInputGame.getUserInput().charAt(index));
            final int sameCharacterIndex = randomValues.indexOf(Integer.valueOf(userInputIndexValue));
            // Check if same character exists and not same index
            ballCount += sameCharacterIndex > -1 && sameCharacterIndex != index ? 1 : 0;
        }
        return ballCount;
    }
}
