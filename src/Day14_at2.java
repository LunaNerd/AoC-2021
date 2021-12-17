import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Day14_at2 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day14.txt"));
        Map<String, Integer> pairCount = new HashMap<>();
        Map<String, String> polymerTemplate = new HashMap<>();
        Map<Character, Integer> charCount = new HashMap<>();

        String[] startPolymer = sc.next().split("");
        sc.nextLine();
        sc.nextLine();

        while (sc.hasNextLine()) {
            String lijn = sc.nextLine();
            //System.out.println(lijn);
            String[] info = lijn.split(" -> ");
            polymerTemplate.put(info[0], info[1]);
        }

        List<Character> polymer = new LinkedList<>();

        for (String s : startPolymer) {
            polymer.add(s.charAt(0));
        }
        System.out.println(polymer);

        for (int j = 0; j<40; j++) {
            System.out.println(j);
            Stack<Character> charStack = new Stack<>();
            Stack<Integer> posStack = new Stack<>();

            for (int i = 0; i<polymer.size()-1; i++) {
                charStack.add(polymerTemplate.get("" + polymer.get(i) + polymer.get(i+1)).charAt(0));
                posStack.add(i+1);
            }
            for (int i = 1; i<polymer.size()-2; i++)  {

                polymer.add(i*2, charStack.pop());
            }
            //System.out.println(polymer);
        }

        Map<Character, Long> toIndex = new HashMap<>();
        /*toIndex.put('N', 0);
        toIndex.put('C', 1);
        toIndex.put('B', 2);
        toIndex.put('H', 3);
        int[] count = new int[4];*/

        for (Character c : polymer) {
            if (!toIndex.containsKey(c)) {
                toIndex.put(c, 0l);
            }
            toIndex.put(c, toIndex.get(c) + 1);
        }

        long min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;
        for (Long value : toIndex.values()) {
            min = value < min ? value : min;
            max = value > max ? value : max;
        }
        System.out.println(max-min);


        //System.out.println(count);
    }
}
