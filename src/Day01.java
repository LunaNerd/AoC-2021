import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Set;

public class Day01 {

    public static void main(String[] args) throws FileNotFoundException {
        //Solve1();
        Solve2();
    }

    public static void Solve1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day01.txt"));
        int teller = 0;
        int toCompare = sc.nextInt();
        while (sc.hasNextInt()) {
            int newOne = sc.nextInt();
            if (newOne > toCompare) {
                teller++;
            }
            toCompare = newOne;
        }
        System.out.println(teller);
    }

    public static void Solve2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day01.txt"));

        Deque<Integer> deque = new ArrayDeque<>();

        int old = 0;

        for (int i = 0; i<3; i++) {
            int n = sc.nextInt();
            old += n;
            deque.addLast(n);
        }

        int teller = 0;
        while (sc.hasNextInt()) {
            deque.addLast(sc.nextInt());
            int newOne = old - deque.removeFirst() + deque.peekLast();
            if (newOne > old) {
                teller++;
            }
            old = newOne;
        }
        System.out.println(teller);
    }
}
