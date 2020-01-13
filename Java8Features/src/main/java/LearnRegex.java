import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LearnRegex {

    private int getMatches(String pattern, String matcher) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(matcher);

        int matches = 0;
        while(m.find()) {
            matches++;
        }

        return matches;
    }

    public static void main(String[] args) {
        LearnRegex learnRegex = new LearnRegex();
//        System.out.println(learnRegex.getMatches("[abc]", "a"));
//        System.out.println(learnRegex.getMatches("[1-5[7-9]]", "6"));
//        System.out.println(learnRegex.getMatches("[1-5[7-9]]", "7"));
//        System.out.println(learnRegex.getMatches("[1-5][7-9]", "6"));
//        System.out.println(learnRegex.getMatches("[1-5][7-9]", "38"));

        System.out.println(learnRegex.getMatches("(\\d\\d)", "1212"));
        System.out.println(learnRegex.getMatches("(\\d\\d)\\1", "1212"));
        System.out.println(learnRegex.getMatches("(\\d\\d)\\1", "1213"));
    }
}
