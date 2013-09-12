import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * main.java
 *
 * @author: hzhou
 */
public class main {

    // Speed test on in line compile.
    public static final int ITERATIONS = 10000;
    public static final String TEST_STRING = "I'm a pretty pony";
    public static void main(String[] args) {
        String restrictions[] = new String[ITERATIONS];
        Pattern compiledPatterns[] = new Pattern[ITERATIONS];

        for (int i = 0; i < ITERATIONS; ++i) {
            restrictions[i] = "restrictions"+i;
            String expression = "(^|\\s)" + restrictions[i] + "(\\s|$)";
            compiledPatterns[i] = Pattern.compile(expression);
        }

        Date test1_start = new Date();
        for (String word : restrictions) {
            String expression = "(^|\\s)" + word + "(\\s|$)";
            Matcher restriction = Pattern.compile(expression).matcher(TEST_STRING);
            if (restriction.find())
                break;
        }
        Date test1_end = new Date();

        System.out.println("Test 1 elapsed time: " + (test1_end.getTime() - test1_start.getTime()));

        Date test2_start = new Date();
        for (Pattern pattern : compiledPatterns) {
            Matcher restriction = pattern.matcher(TEST_STRING);
            if (restriction.find())
                break;
        }
        Date test2_end = new Date();

        System.out.println("Test 2 elapsed time: " + (test2_end.getTime() - test2_start.getTime()));
    }
}
