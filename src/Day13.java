import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Day13 {

    public static void main(String[] args) throws FileNotFoundException {
        Set<Point> pointSet = new HashSet<>();
        Point borders = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Scanner sc = new Scanner(new File("Day13.txt"));
        String next = sc.next();
        while (!next.equals("fold")) {
            String[] split = next.split(",");
            pointSet.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            next = sc.next();
        }
        while (sc.hasNextLine()) {
            String[] foldInstruction = sc.nextLine().split("=");
            Point foldLine;
            if (foldInstruction[0].charAt(foldInstruction[0].length()-1) == 'y') {
                //System.out.println("Hiert!");
                foldLine = new Point(Integer.MAX_VALUE, Integer.parseInt(foldInstruction[1]));
                if (foldLine.y < borders.y) {
                    borders.y = foldLine.y;
                }
            }
            else {
                foldLine = new Point(Integer.parseInt(foldInstruction[1]), Integer.MAX_VALUE);
                if (foldLine.x < borders.x) {
                    borders.x = foldLine.x;
                }
            }

            List<Point> toAdd = new ArrayList<>();
            List<Point> toRemove = new ArrayList<>();
            for (Point p : pointSet) {
                if (p.x > foldLine.x) {
                    toAdd.add(new Point(foldLine.x - (p.x-foldLine.x), p.y));
                    toRemove.add(p);
                }
                else if (p.y > foldLine.y) {
                    toAdd.add(new Point(p.x, foldLine.y - (p.y-foldLine.y)));
                    toRemove.add(p);
                }
                else if (p.y == foldLine.y || p.x == foldLine.x) {
                    toRemove.add(p);
                }
            }
            pointSet.addAll(toAdd);
            pointSet.removeAll(toRemove);
            //System.out.println(pointSet.size());
        }
        System.out.println(borders);
        char[][] visual = new char[borders.y][borders.x];

        for (int y = 0; y<borders.y; y++) {
            for (int x = 0; x<borders.x; x++) {
                visual[y][x] = ' ';
            }
        }
        for (Point p : pointSet) {
            visual[p.y][p.x] = '*';
        }
        for (int y = 0; y<borders.y; y++) {
            for (int x = 0; x<borders.x; x++) {
                System.out.print(visual[y][x]);
            }
            System.out.println();
        }
    }

}
