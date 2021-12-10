package Classes08;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Coordinate {
    public int y;
    public int x;

    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Coordinate[] getSurroundingCoords() {
        Coordinate[] res = new Coordinate[4];
        int[] random = new int[4];
        Set<Integer> set = new HashSet<>();
        Random r = new Random();

        for (int i = 0; i<4; i++) {
            int getal = r.nextInt(4);
            while (set.contains(getal)) {
                getal = r.nextInt(4);
            }
            random[i] = getal;
            set.add(getal);
        }
        res[random[0]] = new Coordinate(y+1, x);
        res[random[1]] = new Coordinate(y-1, x);
        res[random[2]] = new Coordinate(y, x+1);
        res[random[3]] = new Coordinate(y, x-1);
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return y == that.y && x == that.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override public String toString() {
        return "(" + x +", " + y + ")";
    }
}
