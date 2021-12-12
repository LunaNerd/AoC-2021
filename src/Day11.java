import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Day11 {
    static int zijde = 10;
    static int[][] kaart = new int[zijde+2][zijde+2];
    static int nFlashes = 0;

    public static void main(String[] args) throws FileNotFoundException {
        //Filling matrix kaart with the initial digits
        Scanner sc = new Scanner(new File("Day11.txt"));
        int currentLine = 0;
        for (int i = 0; i<zijde+2; i++) {
            kaart[0][i] = Integer.MIN_VALUE;
            kaart[zijde+1][i] = Integer.MIN_VALUE;
            kaart[i][0] = Integer.MIN_VALUE;
            kaart[i][zijde+1] = Integer.MIN_VALUE;
        }
        while (sc.hasNextLine()) {
            String[] split = sc.nextLine().split("");
            for (int x = 0; x<zijde; x++) {
                kaart[currentLine+1][x+1] = Integer.parseInt(split[x]);
            }
            currentLine++;
        }
        printKaart();
        wrongInterpretation();
        /*for (int i = 0; i<1000; i++) {
            //System.out.println("Step: " + i);
            boolean hasFlashed = false;
            for (int y = 1; y<zijde+1; y++) {
                for (int x = 1; x<zijde+1; x++) {
                    if (kaart[y][x] >= 0) {
                        kaart[y][x]++;
                        if (kaart[y][x] > 9) {
                            hasFlashed = true;
                        }
                    }
                }
            }
            while (hasFlashed) {
                hasFlashed = false;
                for (int y = 1; y<zijde+1; y++) {
                    for (int x = 1; x<zijde+1; x++) {
                        if (kaart[y][x] > 9) {
                            hasFlashed = true;
                            nFlashes++;
                            kaart[y][x] = -1;
                            List<Point> coords = getSurroundingCoords(new Point(x, y));
                            for (Point p : coords) {
                                if (kaart[p.y][p.x] >= 0) {
                                    kaart[p.y][p.x]++;
                                }
                            }
                        }
                    }
                }
                //printKaart();
            }
            boolean allAtOnce = true;
            for (int y = 1; y<zijde+1; y++) {
                for (int x = 1; x < zijde + 1; x++) {
                    if (kaart[y][x] == -1) {
                        kaart[y][x] = 0;
                    }
                    else {
                        allAtOnce = false;
                    }
                }
            }
            if (allAtOnce) {
                System.out.println(i);
            }
            System.out.println("====");
            printKaart();
            System.out.println("====");
        }*/

        System.out.println(nFlashes);

    }

    private static void printKaart() {
        for (int y = 0; y<zijde+2; y++) {
            for (int x = 0; x<zijde+2; x++) {
                if (kaart[y][x] >= 0 && kaart[y][x] < 10) {
                    System.out.print(kaart[y][x]);
                }
                else if (kaart[y][x] >= 10) {
                    System.out.print("*");
                }
                else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static List<Point> getSurroundingCoords(Point p) {
        List<Point> res = new ArrayList<>();
        for (int x = p.x-1; x<=p.x+1; x++) {
            for (int y = p.y-1; y<=p.y+1; y++) {
                if (x != p.x || y != p.y) {
                    res.add(new Point(x, y));
                }
            }
        }
        return res;
    }

    private static void wrongInterpretation() {
        for (int i = 0; i<100; i++) {
            //System.out.println(i);
            Stack<Point> toIncrement = new Stack<>();
            for (int y = 1; y<zijde+1; y++) {
                for (int x = 1; x<zijde+1; x++) {
                    toIncrement.add(new Point(x, y));
                }
            }
            //Stack<Point> newToIncrement = new Stack<>();
            while (!toIncrement.isEmpty() /*|| !newToIncrement.isEmpty()*/) {
                /*if (toIncrement.isEmpty()) {
                    toIncrement = newToIncrement;
                    newToIncrement = new Stack<>();
                    System.out.println(toIncrement.peek());
                    System.out.println("NewStack!");
                }*/

                Point p = toIncrement.pop();
                if (kaart[p.y][p.x] != -1) {
                    kaart[p.y][p.x]++;
                }
                if (kaart[p.y][p.x] > 9) {
                    kaart[p.y][p.x] = -1;
                    nFlashes++;
                    toIncrement.addAll(getSurroundingCoords(p));
                    //System.out.println("flash: " + p.y + ", " + p.x);
                    //printKaart();
                }
            }
            for (int y = 0; y<zijde; y++) {
                for (int x = 0; x<zijde; x++) {
                    if (kaart[y+1][x+1] == -1) {
                        kaart[y+1][x+1] = 0;
                    }
                }
            }
        }
    }
}
