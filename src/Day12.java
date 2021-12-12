import Classes12.CaveSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day12 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day12.txt"));
        CaveSystem caveSystem = new CaveSystem();

        while (sc.hasNextLine()) {
            String[] split = sc.nextLine().split("-");
            caveSystem.addLinkedCaves(split[0], split[1]);
        }
        System.out.println(caveSystem);

        //System.out.println(caveSystem.countPaths("start", "end", "start"));
        System.out.println(caveSystem.countPathsDouble("start", "end", "start", "",false));
    }
}
