package Classes18_at2;

import java.util.LinkedList;
import java.util.List;

public class SnailFishNumber {
    private List<Integer> depths = new LinkedList<>();
    private List<Integer> values = new LinkedList<>();

    public SnailFishNumber(String input) {
        int currentDepth = 0; // [ -> depth=1, [[ ->depth=2, depth>4 -> explode
        String[] split = input.split("");
        for (String s : split) {
            if (s.equals("[")) {
                currentDepth++;
            } else if (s.equals("]")) {
                currentDepth--;
            } else if (s.equals(",")) {
                //do nothing
            } else {
                depths.add(currentDepth);
                values.add(Integer.parseInt(s));
            }
        }
        reduce();
    }

    private void increaseDepth(int increase) {
        for (int i = 0; i<depths.size(); i++) {
            depths.set(i, depths.get(i)+increase);
        }
    }

    public void add(SnailFishNumber sfn) {
        this.depths.addAll(sfn.depths);
        this.values.addAll(sfn.values);
        increaseDepth(1);
        reduce();
    }
    public SnailFishNumber(SnailFishNumber one, SnailFishNumber two) {
        for (int i = 0; i<one.depths.size(); i++) {
            depths.add(new Integer(one.depths.get(i)));
            values.add(new Integer(one.values.get(i)));
        }
        for (int i = 0; i<two.depths.size(); i++) {
            depths.add(new Integer(two.depths.get(i)));
            values.add(new Integer(two.values.get(i)));
        }
        this.increaseDepth(1);
        reduce();
    }

    public void reduce() {
        explodeAll();
        //System.out.println("\t" + this);
        while (splitFirst()) {
            //System.out.println("\t" + this);
            explodeAll();
            //System.out.println("\t" + this);
        }
    }

    public int magnitude() {
        for (int i = 5; i>0; i--) {
            for (int indx = 0; indx < values.size()-1; indx++) {
                if (depths.get(indx) == i && depths.get(indx+1) == i) {
                    values.set(indx, values.get(indx)*3 + values.get(indx+1)*2);
                    values.remove(indx+1);

                    depths.set(indx, i-1);
                    depths.remove(indx+1);
                }
            }
        }
       return values.get(0);
    }

    private boolean explodeAll() {
        boolean hasSomethingChanged = false;
        for (int i = 0; i<depths.size(); i++) {
            int depth = depths.get(i);
            if (depth > 4) {
                int left = values.get(i);
                int right = values.get(i+1);

                if (i-1 >= 0) {
                    values.set(i-1, values.get(i-1) + left);
                }
                if (i+2 < values.size()) {
                    values.set(i+2, values.get(i+2) + right);
                }
                depths.set(i, depth-1);
                values.set(i, 0);

                depths.remove(i+1);
                values.remove(i+1);
                //System.out.println();
                hasSomethingChanged = true;
            }
        }
        return hasSomethingChanged;
    }

    private boolean splitFirst() {
        for (int i = 0; i < values.size(); i++) {
            int currentValue = values.get(i);
            int currentDepth = depths.get(i);
            if (currentValue > 9) {
                values.add(i+1, currentValue/2 + currentValue%2);
                depths.add(i+1, currentDepth+1);

                values.set(i, currentValue/2);
                depths.set(i, currentDepth+1);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int currentDepth = 0;
        for (int i = 0; i<values.size(); i++) {
            if (currentDepth == depths.get(i)) {
                sb.append(",");
            }
            while (currentDepth < depths.get(i)) {
                sb.append("[");
                currentDepth++;
            }
            while (currentDepth > depths.get(i)) {
                sb.append("]");
                currentDepth--;
            }
            sb.append(values.get(i));
        }
        while (currentDepth != 0) {
            sb.append("]");
            currentDepth--;
        }

        return sb.toString();
    }
}
