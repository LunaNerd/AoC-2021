import Classes08.Coordinate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day09 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day08.txt"));
        sc.useDelimiter("\\Z");
        String input = sc.next();

        String[] perLine = input.split("\n");
        byte[][] map = new byte[perLine.length + 2][perLine[0].length() + 2];
        for (int i = 0; i < perLine[0].length() + 2; i++) {
            map[0][i] = Byte.MAX_VALUE;
            map[perLine.length + 1][i] = Byte.MAX_VALUE;
        }
        for (int i = 1; i < perLine.length + 1; i++) {
            map[i][0] = Byte.MAX_VALUE;
            for (int j = 0; j < perLine[i - 1].length(); j++) {
                //map[i][j+1] = Byte.parseByte(Character.toString(perLine[i].charAt(j)));
                map[i][j + 1] = (byte) (perLine[i - 1].charAt(j) - '0');
            }
            map[i][perLine[i - 1].length() + 1] = Byte.MAX_VALUE;
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == Byte.MAX_VALUE) {
                    System.out.print(".");
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }

        int som = 0;
        Map<Coordinate, Integer> minCoordinates = new HashMap<>();
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                if (map[i][j] < map[i + 1][j] && map[i][j] < map[i - 1][j] && map[i][j] < map[i][j + 1] && map[i][j] < map[i][j - 1]) {
                    som += map[i][j] + 1;
                    minCoordinates.put(new Coordinate(i, j), 0);
                }
            }
        }

        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                if (map[i][j] != 9) {
                    Coordinate c = new Coordinate(i, j);
                    while (!minCoordinates.containsKey(c)) {
                        Coordinate[] surroundingCoords = c.getSurroundingCoords();
                        byte min = Byte.MAX_VALUE;
                        Coordinate chosenOne = null;
                        for (Coordinate neighbour : surroundingCoords) {
                            if (map[neighbour.y][neighbour.x] <= min) {
                                min = map[neighbour.y][neighbour.x];
                                chosenOne = neighbour;
                            }
                        }
                        c = chosenOne;
                    }
                    if (minCoordinates.containsKey(c)) {
                        minCoordinates.put(c, minCoordinates.get(c) + 1);
                    }
                }
            }
        }
        long resPart2 = 1l;
        System.out.println();
        //System.out.println(minCoordinates.values());

        TreeSet<Integer> treeset = new TreeSet<Integer> (minCoordinates.values());
        resPart2 *= treeset.last();
        resPart2 *= treeset.lower(treeset.last());
        resPart2 *= treeset.lower(treeset.lower(treeset.last()));
        /*for (Integer i : new TreeSet<Integer> (minCoordinates.values())) {
            if (teller < 3) {
                resPart2 = resPart2 * i;
                teller++;
            }
        }*/
        System.out.println(resPart2);
    }
}
