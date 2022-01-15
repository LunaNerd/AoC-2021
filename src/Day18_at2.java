import Classes18_at2.SnailFishNumber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day18_at2 {

    static List<SnailFishNumber> sFList = new ArrayList<>();


    public static void main(String[] args) throws FileNotFoundException {
        fillSFList("Input/Day18.txt");

        //Part1
        /*while (sFList.size() != 1) {
            addFirstTwoNumbers();
        }
        SnailFishNumber res = sFList.get(0);
        System.out.println();
        System.out.println(res);
        System.out.println(res.magnitude());
        System.out.println(res);*/

        //Part2
        int max = Integer.MIN_VALUE;
        for (int i = 0; i<sFList.size(); i++) {
            for (int j = i+1; j<sFList.size(); j++) {
                SnailFishNumber one = sFList.get(i);
                SnailFishNumber two = sFList.get(j);
                SnailFishNumber tree = new SnailFishNumber(one, two);
                SnailFishNumber four = new SnailFishNumber(two, one);

                int tree_mag = tree.magnitude();
                max = tree_mag > max ? tree_mag : max;

                int four_mag = four.magnitude();
                max = four_mag > max ? four_mag : max;
            }
        }
        System.out.println(max);
    }

    private static void fillSFList(String filename) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(filename))){
            while (sc.hasNextLine()) {
                sFList.add(new SnailFishNumber(sc.nextLine()));
            }
        }
        /*for (SnailFishNumber sfn : sFList) {
            System.out.println(sfn);
            sfn.reduce();
            System.out.println(sfn);
            System.out.println();
        }*/
    }

    private static void addFirstTwoNumbers() {
        System.out.println();
        System.out.println("\t" + sFList.get(0));
        System.out.println("\t" + sFList.get(1));
        sFList.get(0).add(sFList.get(1));
        sFList.remove(1);
    }
}
