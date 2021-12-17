import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day14_s.txt"));
        Map<String, Integer> pairCount = new HashMap<>();
        Map<String, String> polymerTemplate = new HashMap<>();
        Map<Character, Integer> charCount = new HashMap<>();

        String startPolymer = sc.next();
        sc.nextLine();
        sc.nextLine();

        while (sc.hasNextLine()) {
            String lijn = sc.nextLine();
            //System.out.println(lijn);
            String[] info = lijn.split(" -> ");
            polymerTemplate.put(info[0], info[1]);
        }
        charCount.put(startPolymer.charAt(0), 1);
        for (int i = 0; i<startPolymer.length()-1; i++) {
            pairCount.put(startPolymer.substring(i, i+2), 1);
            int toAdd = 0;
            if (charCount.containsKey(startPolymer.charAt(i+1))) {
                toAdd = charCount.get(startPolymer.charAt(i+1));
            }
            charCount.put(startPolymer.charAt(i+1), toAdd + 1);

        }
        System.out.println(charCount);

        for (int i = 0; i<10; i++) {
            Map<String, Integer> newPairCount = new HashMap<>();
            for (var entry : pairCount.entrySet()) {
                String inBetween = polymerTemplate.get(entry.getKey());
                int toAdd = 0;
                if (charCount.containsKey(inBetween.charAt(0))) {
                    toAdd = charCount.get(inBetween.charAt(0));
                }
                charCount.put(inBetween.charAt(0), toAdd + entry.getValue());
                String firstPair = entry.getKey().charAt(0) + inBetween;
                String secondPair = inBetween + entry.getKey().charAt(0);
                int firstToAdd = 0;
                int secondToAdd = 0;
                if (newPairCount.containsKey(firstPair)) {
                    firstToAdd += newPairCount.get(firstPair);
                }
                if (newPairCount.containsKey(secondPair)) {
                    secondToAdd += newPairCount.get(secondPair);
                }
                newPairCount.put(entry.getKey().charAt(0) + inBetween, firstToAdd + entry.getValue());
                newPairCount.put(inBetween + entry.getKey().charAt(1), secondToAdd + entry.getValue());
                //System.out.println(entry.getKey() + "/" + entry.getValue());
            }
            System.out.println(pairCount);
            pairCount = newPairCount;
            System.out.println(charCount);
            System.out.println();
        }
        // x=leftValue y=rightValue
        /*Map<Character, Point> charCount = new HashMap<>();
        for (var entry : pairCount.entrySet()) {
            int toAddLeft = 0;
            int toAddRight = 0;
            if (charCount.containsKey(entry.getKey().charAt(0))) {
                toAddLeft = charCount.get(entry.getKey().charAt(0)).x;
            }
            if (charCount.containsKey(entry.getKey().charAt(1))) {
                toAddRight = charCount.get(entry.getKey().charAt(1)).y;
            }
            charCount.put(entry.getKey().charAt(0), new Point(toAddLeft + entry.getValue(), toAddRight + entry.getValue()));
            charCount.put(entry.getKey().charAt(1), new Point(toAddLeft + entry.getValue(), toAddRight + entry.getValue()));
        }
        for (Character c : charCount.keySet()) {
            System.out.println("" + c + charCount.get(c));
        }*/
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        /*for (Point p : charCount.values()) {
            if (i<min) {
                min = i;
            }
            if (i>max) {
                max = i;
            }
        }
        System.out.println(max-min);*/
    }
}
