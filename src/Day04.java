import Classes04.Bingo;

import java.io.File;
import java.io.FileNotFoundException;

public class Day04 {

    public static void main(String[] args) throws FileNotFoundException {
        //part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        Bingo bingo = new Bingo(new File("Day04.txt"));

        int score = bingo.callNumber();
        while (score == 0) {
            score = bingo.callNumber();
        }

        System.out.println(score);
    }

    public static void part2() throws FileNotFoundException {
        Bingo bingo = new Bingo(new File("Day04.txt"));

        int score = 0;
        while (!bingo.wasTheLastOne()) {
            score = 0;
            while (score == 0) {
                score = bingo.callNumber();
            }
        }

        System.out.println(bingo);
        System.out.println(score);
    }
}
