import Classes05.Diagram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day05 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Day05.txt");

        Diagram diagram = new Diagram(file);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            diagram.addLine(sc);
        }
        System.out.println(diagram);

        System.out.println("\n\n" + diagram.getnOfDangerZones());
    }
}
