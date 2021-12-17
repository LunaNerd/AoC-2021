import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day17 {

    static int x_min = 137;
    static int x_max = 171;
    static int y_min = -98;
    static int y_max = -73;

    /*static int x_min = 20;
    static int x_max = 30;
    static int y_min = -10;
    static int y_max = -5;*/

    static Map<Integer, Set<Integer>> potential_x;
    static Map<Integer, Integer> xConstantSinceStep = new HashMap<>();
    static Map<Integer, Set<Integer>> potential_y;

    public static void main(String[] args) throws FileNotFoundException {

        int x_v_max = x_max;
        int x_v_min = 0;
        int y_v_max = (-y_min) - 1;
        int y_v_min = y_min;

        potential_x = fillPotentialMap(x_v_max, x_v_min, x_max, x_min, false);
        potential_y = fillPotentialMap(y_v_max, y_v_min, y_max, y_min, true);

        System.out.println(potential_x);
        System.out.println();
        System.out.println(potential_y);

        List<String> mine = new ArrayList<>();

        int count = 0;
        for (var potentialSteps_y : potential_y.entrySet()) {
            for (var potentialSteps_x : potential_x.entrySet()) {
                boolean isStandStill = xConstantSinceStep.containsKey(potentialSteps_x.getKey());
                if (isStandStill) {
                    for (Integer step : potentialSteps_y.getValue()) {
                        if (step >= xConstantSinceStep.get(potentialSteps_x.getKey())) {
                            mine.add(potentialSteps_x.getKey() + "," + potentialSteps_y.getKey());
                            count++;
                            break;
                        }
                    }
                } else {
                    for (Integer step : potentialSteps_x.getValue()) {
                        if (potentialSteps_y.getValue().contains(step)) {
                            mine.add(potentialSteps_x.getKey() + "," + potentialSteps_y.getKey());
                            count++;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(count);

        //Debug-shizzle
        /*System.out.println(xConstantSinceStep);

        Collections.sort(mine);

        List<String> correct = new ArrayList<>();
        Scanner sc = new Scanner(new File("Day17_debug.txt"));
        while (sc.hasNext()) {
            correct.add(sc.next());
        }
        Collections.sort(correct);

        for (int i = 0; i<mine.size(); i++) {
            if (mine.get(i).length() <= 3) {
                System.out.print(mine.get(i) + "\t\t" + correct.get(i));
            }
            else {
                System.out.print(mine.get(i) + "\t" + correct.get(i));
            }
            if (!mine.get(i).equals(correct.get(i))) {
                System.out.print("    <----");
            }
            System.out.println();
        }*/
    }

    public static Map<Integer, Set<Integer>> fillPotentialMap(int v_max, int v_min, int max, int min, boolean canBeNegative) {
        Map<Integer, Set<Integer>> potentialMap = new HashMap<>();

        for (int v = v_min; v <= v_max; v++) {
            //potentialMap.put(v, new HashSet<>());
            int pos = 0;
            int v_current = v;
            int step = 0;
            while (pos > max) {
                pos += v_current;
                v_current--;
                step++;
                if (pos >= min && pos <= max) {
                    if (!potentialMap.containsKey(v)) {
                        potentialMap.put(v, new HashSet<>());
                    }
                    potentialMap.get(v).add(step);
                }
            }
            while (pos <= max && (canBeNegative || v_current != 0)) {
                pos += v_current;
                v_current--;
                step++;
                if (pos >= min && pos <= max) {
                    if (!potentialMap.containsKey(v)) {
                        potentialMap.put(v, new HashSet<>());
                    }
                    potentialMap.get(v).add(step);
                }
            }
            if (pos >= min && pos <= max && v_current == 0) {
                int minStep = Integer.MAX_VALUE;
                for (Integer i : potentialMap.get(v)) {
                    minStep = i < minStep ? i : minStep;
                }
                xConstantSinceStep.put(v, minStep);
            }
        }

        return potentialMap;
    }
}
