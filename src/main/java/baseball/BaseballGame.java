package baseball;

import baseball.model.BaseballGameResult;
import baseball.model.RandomGameValue;
import baseball.model.UserInputGameValue;

/**
 * @author jinyoung
 * @since 2022/04/14
 */
public class BaseballGame {

    private RandomGameValue randomGeneratedGame;
    private boolean isLastGameUserWin = false;

    public void start() {
        boolean shouldGameKeepGoing = true;
        while(shouldGameKeepGoing) {
            // 사용자로부터 값을 입력받는다.
            final UserInputGameValue userInputGameValue = UserInputGameValue.get();

            // Generate random 3 digit numeric value
            final RandomGameValue randomGameValue = generateRandomGameValue();

            // userInput, randomValue 비교하여 randomGeneratedGame 결과를 계산 한다.
            final BaseballGameResult baseballGameResult = BaseballGameResult.decideGameResult(userInputGameValue, randomGameValue);
            System.out.println(baseballGameResult.getResultString());

            this.isLastGameUserWin = baseballGameResult.isUserWin();
            shouldGameKeepGoing = baseballGameResult.shouldKeepGoing();
        }
    }

    private RandomGameValue generateRandomGameValue() {
        if (shouldStartANewGame()) {
            this.randomGeneratedGame = RandomGameValue.get();
        }

        return this.randomGeneratedGame;
    }

    private boolean shouldStartANewGame() {
        if (this.randomGeneratedGame == null) {
            return true;
        }

        return isLastGameUserWin;
    }
}
