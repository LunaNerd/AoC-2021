import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day02.txt"));
        int horizontal = 0;
        int dept = 0;
        int aim = 0;

        while (sc.hasNextLine()) {
            String[] sp = sc.nextLine().split(" ");

            switch (sp[0]) {
                case "forward":
                    horizontal += Integer.parseInt(sp[1]);
                    dept += aim*Integer.parseInt(sp[1]);
                    break;
                case "down":
                    aim += Integer.parseInt(sp[1]);
                    break;
                case "up":
                    aim -= Integer.parseInt(sp[1]);
                    break;
            }
        }
        System.out.println(horizontal*dept);

        sc.close();
    }
}
