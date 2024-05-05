import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MainDSTest {
    @Test
    public void FixedTests() {

    }
    @Test
    public void votingTest() {
        List<String> votes = Arrays.asList("John", "Jenny","John", "Jenny", "Joe", "Jenny");
        String results[] = MainDS.getResults(votes);
        String expectedResults[] = new String[]{ "Jenny","John",  "Joe"};
        Assertions.assertArrayEquals(expectedResults, results);
    }
    @Test
    public void votingTestCollision() {
        List<String> votes = Arrays.asList("John", "Jenny","Joe", "Jenny", "Joe", "John", "Jenny");
        String results[] = MainDS.getResults(votes);
        String expectedResults[] = new String[]{ "Jenny","Joe", "John"};
        Assertions.assertArrayEquals(expectedResults, results);
    }
}
