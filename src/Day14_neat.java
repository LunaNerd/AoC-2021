import Classes14.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day14_neat {
    public static int turns = 40;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day14.txt"));
        String startPolymer = sc.next();
        sc.nextLine();
        sc.nextLine();

        Map<String, Pair> pairCount = new HashMap<>();
        Map<Character, Long> charCount = new HashMap<>();

        while (sc.hasNextLine()) {
            String[] split = sc.nextLine().split(" -> ");
            pairCount.put(split[0], new Pair(split[0], split[1]));
        }

        charCount.put(startPolymer.charAt(0), 1l);
        for (int i = 0; i<startPolymer.length()-1; i++) {
            pairCount.get(startPolymer.substring(i, i+2)).increasePairs(1, 0);
            char c = startPolymer.charAt(i+1);
            addToCharCount(charCount, c, 1L);
        }

        for (int i = 1; i < turns + 1; i++) {
            for (String key : pairCount.keySet()) {
                String left = pairCount.get(key).getLeft();
                String right = pairCount.get(key).getRight();
                char middle = pairCount.get(key).getMiddle();
                long count = pairCount.get(key).getCount(i);

                pairCount.get(left).increasePairs(count, i);
                pairCount.get(right).increasePairs(count, i);
                addToCharCount(charCount, middle, count);
            }
        }

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        for (Long count : charCount.values()) {
            max = count > max ? count : max;
            min = count < min ? count : min;
        }
        //System.out.println(max);
        //System.out.println(min);
        System.out.println(max-min);
    }

    public static void addToCharCount(Map<Character, Long> charCount, char c, long amount) {
        if (charCount.containsKey(c)) {
            charCount.compute(c, ((key, value) -> value == null ? amount : value+amount));
        }
        else {
            charCount.computeIfAbsent(c, key -> amount);
        }
    }
}
