import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day15 {
    //these are just the levels you get when reading the map
    public static int[][] levelMap;

    //contains the lowest sum of danger to get to a certain position
    public static int[][] minSum;
    public static int size;

    public static void main(String[] args) throws FileNotFoundException {
        readMapFromFile();
        convertToBigMap();      //Part2
        System.out.println(size);

        //filling the minSum map with high values so they are not put into consideration for a non-dangerous path
        minSum = new int[size][size];
        for (int y = 0; y<size; y++) {
            for (int x = 0; x<size; x++) {
                minSum[y][x] = Integer.MAX_VALUE;
            }
        }

        //Initializing the minSum map with the dangerlevels of the points besides the start position
        minSum[0][1] = levelMap[0][1];
        minSum[1][0] = levelMap[1][0];

        long oldGrandSum = 0;
        long grandSum = 2;
        //  ^These two sums are used to check weather anything is still changing after
        //  checking every tile for a less dangerous route
        while (oldGrandSum != grandSum) {
            oldGrandSum = grandSum;
            grandSum = 0;
            for (int y = 0; y<size; y++) {
                for (int x = 0; x<size; x++) {
                    int l = lowestSurroundingSum(y, x);
                    if (l != Integer.MAX_VALUE && minSum[y][x] > l + levelMap[y][x]) {
                        minSum[y][x] = l + levelMap[y][x];
                    }
                    grandSum += minSum[y][x];
                }
            }

            /*for (int y = 0; y<size; y++) {
                for (int x = 0; x<size; x++) {
                    if (minSum[y][x] == Integer.MAX_VALUE) {
                        System.out.print(".");
                    }
                    else {
                        System.out.print(minSum[y][x] + "\t");
                    }
                }
                System.out.println();
            }
            System.out.println();*/
        }
        System.out.println(minSum[size-1][size-1]);
    }

    public static int lowestSurroundingSum(int y, int x) {
        int min = Integer.MAX_VALUE;
        int vert = y-1;
        int hor = x;
        if (vert >= 0 && vert < size && hor >= 0 && hor < size) {
            if (minSum[vert][hor] < min) {
                min = minSum[vert][hor];
            }
        }
        vert = y+1;
        hor = x;
        if (vert >= 0 && vert < size && hor >= 0 && hor < size) {
            if (minSum[vert][hor] < min) {
                min = minSum[vert][hor];
            }
        }
        vert = y;
        hor = x-1;
        if (vert >= 0 && vert < size && hor >= 0 && hor < size) {
            if (minSum[vert][hor] < min) {
                min = minSum[vert][hor];
            }
        }
        vert = y;
        hor = x+1;
        if (vert >= 0 && vert < size && hor >= 0 && hor < size) {
            if (minSum[vert][hor] < min) {
                min = minSum[vert][hor];
            }
        }
        return min;
    }

    public static void convertToBigMap() {
        int[][] bigMap = new int[size*5][size*5];
        for (int y = 0; y<size; y++) {
            for (int x = 0; x<size; x++) {
                for (int ver = 0; ver < 5; ver++) {
                    for (int hor = 0; hor < 5; hor++) {
                        bigMap[ver*size + y][hor*size + x] = (levelMap[y][x] + ver + hor);
                        while (bigMap[ver*size + y][hor*size + x] > 9) {
                            bigMap[ver*size + y][hor*size + x] -= 9;
                        }
                    }
                }
            }
        }
        levelMap = bigMap;
        size *= 5;
        for (int y = 0; y<size; y++) {
            if (y%(size/5) == 0) {
                System.out.print("\n");
            }
            for (int x = 0; x<size; x++) {
                if (x%(size/5) == 0) {
                    System.out.print(" ");
                }
                System.out.print(levelMap[y][x]);
            }
            System.out.println();
        }
    }

    public static void readMapFromFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day15.txt"));

        String[] split = sc.nextLine().split("");
        size = split.length;
        levelMap = new int[size][size];
        int x = 0;
        int y = 0;
        for (String s : split) {
            levelMap[y][x] = Integer.parseInt(s);
            x++;
        }
        y++;
        x=0;
        while (sc.hasNextLine()) {
            split = sc.nextLine().split("");
            x = 0;
            for (String s : split) {
                levelMap[y][x] = Integer.parseInt(s);
                x++;
            }
            y++;
        }
        /*for (y = 0; y<size; y++) {
            for (x = 0; x<size; x++) {
                System.out.print(levelMap[y][x]);
            }
            System.out.println();
        }*/
    }
}
