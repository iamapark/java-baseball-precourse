package baseball;

import static baseball.model.BaseballGameResult.decideGameResult;

import baseball.model.BaseballGameResult;
import baseball.model.RandomGameValue;
import baseball.model.UserInputGameValue;
import camp.nextstep.edu.missionutils.Console;

/**
 * @author jinyoung
 * @since 2022/04/14
 */
public class BaseballGame {

    private RandomGameValue randomGeneratedGame;
    private boolean isLastGameUserWin = false;

    public void start() {
        boolean shouldGameKeepGoing = true;
        while (shouldGameKeepGoing) {
            final UserInputGameValue userInputGameValue = UserInputGameValue.get();
            final RandomGameValue randomGameValue = generateRandomGameValue();
            final BaseballGameResult baseballGameResult = decideGameResult(userInputGameValue, randomGameValue);

            shouldGameKeepGoing = shouldGameKeepGoing(baseballGameResult);
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

        return this.isLastGameUserWin;
    }

    private boolean shouldGameKeepGoing(BaseballGameResult baseballGameResult) {
        this.isLastGameUserWin = baseballGameResult.isUserWin();
        if (!this.isLastGameUserWin) {
            return true;
        }

        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하시려면 1, 종료하려면 2를 입력하세요.");
        final String continueGameOrNot = Console.readLine();
        return "1".equals(continueGameOrNot);
    }
}
