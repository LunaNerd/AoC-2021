package Classes16;

import java.util.ArrayList;
import java.util.List;

public abstract class Operator {
    List<Long> list = new ArrayList<>();
    byte type;

    public Operator(byte type) {
        this.type = type;
    }

    public abstract boolean isFull(int currentIndex);

    public void addValueToPackage(long value) {
        System.out.println("\tadding " + value);
        list.add(value);
    }

    public long calculateValueInside() {
        System.out.println(list);
        long res = 0;
        if (type == 0) {
            for (Long value : list) {
                res += value;
            }
        }
        else if (type == 1) {
            res = 1;
            for (Long value : list) {
                res *= value;
            }
        }
        else if (type == 2) {
            res = Long.MAX_VALUE;
            for (Long value : list) {
                res = value < res ? value : res;
            }
        }
        else if (type == 3) {
            res = Long.MIN_VALUE;
            for (Long value : list) {
                res = value > res ? value : res;
            }
        }
        else if (type == 5) {
            if (list.get(0) > list.get(1)) {
                res = 1;
            }
        }
        else if (type == 6) {
            if (list.get(0) < list.get(1)) {
                res = 1;
            }
        }
        else if (type == 7) {
            if (list.get(0) == list.get(1)) {
                res = 1;
            }
        }
        else {
            System.out.println("Something is wrong");
        }

        return res;
    }
}
