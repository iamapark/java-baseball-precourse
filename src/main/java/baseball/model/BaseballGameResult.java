package baseball.model;

import camp.nextstep.edu.missionutils.Console;

import static baseball.constants.BaseballGameBaseConstant.*;

/**
 * @author jinyoung
 * @date 2022/04/14
 */
public class BaseballGameResult {

    private final int strikeCount;
    private final int ballCount;

    private BaseballGameResult(UserInputGameValue userInputGame, RandomGameValue randomGeneratedGame) {
        this.strikeCount = decideStrikeCount(userInputGame, randomGeneratedGame);
        this.ballCount = decideBallCount(userInputGame, randomGeneratedGame);
    }

    public static BaseballGameResult decideGameResult(UserInputGameValue userInputGame, RandomGameValue randomGeneratedGame) {
        return new BaseballGameResult(userInputGame, randomGeneratedGame);
    }

    public boolean isUserWin() {
        return VALID_GAME_VALUE_DIGITS == this.strikeCount;
    }

    private int decideStrikeCount(UserInputGameValue userInputGame, RandomGameValue randomGeneratedGame) {
        int strikeCount = 0;
        for (int index = 0; index < VALID_GAME_VALUE_DIGITS; index++) {
            final String randomIndexValue = randomGeneratedGame.getRandomValues().get(index).toString();
            final String userInputIndexValue = String.valueOf(userInputGame.getUserInput().charAt(index));

            strikeCount += randomIndexValue.equals(userInputIndexValue) ? 1 : 0;
        }

        return strikeCount;
    }

    private int decideBallCount(UserInputGameValue userInputGame, RandomGameValue randomGeneratedGame) {
        int ballCount = 0;

        for (int index = 0; index < VALID_GAME_VALUE_DIGITS; index++) {
            final String randomIndexValue = randomGeneratedGame.getRandomValues().get(index).toString();
            final String userInputIndexValue = String.valueOf(userInputGame.getUserInput().charAt(index));

            if (randomIndexValue.equals(userInputIndexValue)) {
                continue;
            }

            // '볼'이란 무엇인가? = 같은 자리가 아니면서 동일한 숫자가 있을 때
            ballCount += randomGeneratedGame.getRandomValues().indexOf(Integer.valueOf(userInputIndexValue)) > -1 ? 1 : 0;
        }

        return ballCount;
    }

    public boolean shouldKeepGoing() {
        if (!isUserWin()) {
            return true;
        }

        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하시려면 1, 종료하려면 2를 입력하세요.");
        final String finalResult = Console.readLine();

        return "1".equals(finalResult);
    }

    public String getResultString() {
        if (this.strikeCount == 0 && this.ballCount == 0) {
            return NOTHING_NAME;
        }

        String resultString = "";
        resultString += this.ballCount != 0 ? this.ballCount + "" + BALL_NAME : "";
        resultString += this.strikeCount != 0 ? " " + this.strikeCount + "" + STRIKE_NAME : "";

        return resultString.replaceAll("^ ", "").replaceAll(" $", "");
    }
}
