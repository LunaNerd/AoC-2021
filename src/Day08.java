import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day08 {
    public static void main(String[] args) throws FileNotFoundException {
        int som = 0;

        //Determining frequency-sum -> digit
        String[] digitStrings =  {"abcefg", "cf", "acdeg", "acdfg", "bcdf", "abdfg", "abdefg", "acf", "abcdefg", "abcdfg"};
        int[] frequencies = new int[7];
        for (String s : digitStrings) {
            for (int i = 0; i<s.length(); i++) {
                frequencies[s.charAt(i) - 'a']++;
            }
        }
        Map<Integer, Integer> sumToDigit = new HashMap<>();
        for (int j = 0; j<digitStrings.length; j++) {
            int sumForDigit = 0;
            for (int i = 0; i<digitStrings[j].length(); i++) {
                sumForDigit += frequencies[digitStrings[j].charAt(i) - 'a'];
            }
            sumToDigit.put(sumForDigit, j);
        }
        //System.out.println(sumToDigit);

        Scanner sc = new Scanner(new File("Day07.txt"));
        while (sc.hasNextLine()) {
            String[] split = sc.nextLine().split("\\|");

            int[] characterCount = new int['g' - 'a' + 1];
            split[0] = split[0].replaceAll(" ", "");

            //Counting all of the characters in the part before |
            for (int i = 0; i < split[0].length(); i++) {
                int index = split[0].charAt(i) - 'a';
                characterCount[index]++;
            }
            /*for (int i = 0; i < characterCount.length; i++) {
                System.out.print(characterCount[i] + " ");
            }
            System.out.println();*/

            //Splitting the part after | into individual strings that each represent a number
            String[] toTranslate = split[1].strip().split(" ");

            int toAdd = 0;
            int multiplier = 1000;
            for (String word : toTranslate) {
                int getal = 0;
                //Running over each digit-representing-string and adding up the frequencies of every character
                for (int i = 0; i < word.length(); i++) {
                    getal += characterCount[word.charAt(i) - 'a'];
                }
                //All of the digits form a number.
                toAdd += sumToDigit.get(getal) * multiplier;
                multiplier /= 10;
            }
            //The number that is represented by one line of input is added to the grand sum that will become the solution to the problem
            som += toAdd;
        }
        sc.close();
        System.out.println(som);
    }
}
