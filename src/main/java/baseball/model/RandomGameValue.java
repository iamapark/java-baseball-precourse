package baseball.model;

import java.util.*;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

/**
 * @author jinyoung
 * @since 2022/04/14
 */
public class RandomGameValue {

    private final LinkedHashSet<Integer> randomValues;

    protected RandomGameValue(LinkedHashSet<Integer> randomValue) {
        this.randomValues = randomValue;
    }

    public List<Integer> getRandomValues() {
        return new ArrayList<>(randomValues);
    }

    public static RandomGameValue get() {
        final List<Integer> randomValues = generateRandomValues();
        return new RandomGameValue(new LinkedHashSet<>(randomValues));
    }

    private static List<Integer> generateRandomValues() {
        return Arrays.asList(
                pickNumberInRange(1, 3), pickNumberInRange(4, 6), pickNumberInRange(7, 9)
        );
    }
}
