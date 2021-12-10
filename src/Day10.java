import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {

    public static void main(String[] args) throws FileNotFoundException {
        part1();
    }

    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day10.txt"));

        Map<Character, Character> ClosingToOpeningChar = new HashMap<>();
        ClosingToOpeningChar.put('>', '<');
        ClosingToOpeningChar.put(')', '(');
        ClosingToOpeningChar.put(']', '[');
        ClosingToOpeningChar.put('}', '{');

        int scorePart1 = 0;
        List<Long> scoresPart2 = new ArrayList<>();

        Map<Character, Integer> toScore = new HashMap<>();
        toScore.put(')', 3);
        toScore.put(']', 57);
        toScore.put('}', 1197);
        toScore.put('>', 25137);

        Map<Character, Integer> toScoreP2 = new HashMap<>();
        toScoreP2.put('(', 1);
        toScoreP2.put('[', 2);
        toScoreP2.put('{', 3);
        toScoreP2.put('<', 4);

        while (sc.hasNextLine()) {
            Stack<Character> stack = new Stack<>();

            long lineScore = 0l;
            int multiplier = 5;

            Scanner line = new Scanner(sc.nextLine());
            line.useDelimiter("");
            Boolean hasMistake = false;

            while (line.hasNext() && !hasMistake) {
                char c = line.next().charAt(0);
                if (ClosingToOpeningChar.values().contains(c)) {
                    stack.add(c);
                }
                else if (ClosingToOpeningChar.get(c).equals(stack.peek())) {
                    stack.pop();
                }
                else {
                    //System.out.println(stack.peek());
                    scorePart1 += toScore.get(c);
                    hasMistake = true;
                }
            }

            if (!hasMistake) {
                while (!stack.empty()) {
                    //System.out.println(stack.peek());
                    lineScore *= multiplier;
                    lineScore += toScoreP2.get(stack.pop());
                }
                scoresPart2.add(lineScore);
            }
        }

        System.out.println(scorePart1);

        Collections.sort(scoresPart2);
        System.out.println(scoresPart2.get(scoresPart2.size()/2));
    }
}
