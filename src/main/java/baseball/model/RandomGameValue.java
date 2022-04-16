package baseball.model;

import java.util.*;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

/**
 * @author jinyoung
 * @date 2022/04/14
 */
public class RandomGameValue {

    private final Set<Integer> randomValues;

    public RandomGameValue(Set<Integer> randomValue) {
        this.randomValues = randomValue;
    }

    public Set<Integer> getRandomValues() {
        return randomValues;
    }

    public static RandomGameValue get() {
        final List<Integer> randomValues = Arrays.asList(
            pickNumberInRange(1, 3), pickNumberInRange(4, 6), pickNumberInRange(7, 9)
        );
        Collections.shuffle(randomValues);
        return new RandomGameValue(new HashSet<>(randomValues));
    }
}
