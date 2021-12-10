import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day06 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day06.txt"));
        sc.useDelimiter(",");
        long[] frequency = new long[9];
        while (sc.hasNext()) {
            frequency[Integer.parseInt(sc.next().strip())]++;
        }
        for (int i = 0; i<256; i++) {
            long forSix = frequency[0];
            for (int j = 1; j<=8; j++) {
                frequency[j-1] = frequency[j];
            }
            frequency[6] += forSix;
            frequency[8] = forSix;
            /*for (int j = 0; j<frequency.length; j++) {
                System.out.print(frequency[j] + " ");
            }
            System.out.println();*/
        }

        long teller = 0l;
        for (int j = 0; j<frequency.length; j++) {
            teller += frequency[j];
        }

        System.out.println(teller);
    }
    /*public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day06.txt"));
        sc.useDelimiter(",");
        List<Integer> list = new ArrayList<>();
        while (sc.hasNext()) {
            list.add(Integer.parseInt(sc.next().strip()));
        }
        for (int i = 0; i<256; i++) {
            List<Integer> toAdd = new ArrayList<>();
            for (int j = 0; j<list.size(); j++) {
                list.set(j, list.get(j) - 1);
                if (list.get(j)<0) {
                    list.set(j, 6);
                    toAdd.add(8);
                }
            }
            list.addAll(toAdd);
            //System.out.println(list);
        }
        System.out.println(list.size());
    }*/
}
