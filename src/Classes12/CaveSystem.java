package Classes12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CaveSystem {

    private Map<String, Cave> allCaves;
    private Set<String> testDouble = new HashSet<>();

    public CaveSystem() {
        allCaves = new HashMap<>();
    }

    public void addLinkedCaves(String one, String two) {
        if (!allCaves.containsKey(one)) {
            allCaves.put(one, new Cave(one));
        }
        allCaves.get(one).addSurroundingCave(two);

        if (!allCaves.containsKey(two)) {
            allCaves.put(two, new Cave(two));
        }
        allCaves.get(two).addSurroundingCave(one);
    }

    public int countPaths(String start, String end, String toIgnore) {
        if (start.equals(end)) {
            System.out.println("..");
            return 1;
        }
        int res = 0;
        for (String cave : allCaves.get(start).getSurroundingCaves()) {
            System.out.print(cave);
            if (!toIgnore.contains(cave)) {
                if (allCaves.get(cave).getIsSmall()) {
                    res += countPaths(cave, end, toIgnore + cave + ",");
                }
                else {
                    res += countPaths(cave, end, toIgnore + ".");
                }
            }
        }
        System.out.println("--");
        return res;
    }

    public int countPathsDouble(String start, String end, String toIgnore, String toVisualize, boolean hasUsedDouble) {
        if (start.equals(end)) {
            if (!testDouble.contains(toVisualize)) {
                testDouble.add(toVisualize);
                return 1;
            }
            else return 0;
        }
        int res = 0;
        for (String cave : allCaves.get(start).getSurroundingCaves()) {
            if (!toIgnore.contains(cave)) {
                if (allCaves.get(cave).getIsSmall()) {
                    if (hasUsedDouble) {
                        res += countPathsDouble(cave, end, toIgnore + cave + ",", toVisualize + "-" + cave, true);
                    }
                    else {
                        res += countPathsDouble(cave, end, toIgnore + cave + ",", toVisualize + "-" + cave, false);
                        if (!cave.equals("start") && !cave.equals("end")) {
                            res += countPathsDouble(cave, end, toIgnore + ".", toVisualize + "-" + cave, true);
                        }
                    }
                }
                else {
                    res += countPathsDouble(cave, end, toIgnore + ".", toVisualize + "-" + cave, hasUsedDouble);
                }
            }
        }
        return res;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (String cave : allCaves.keySet()) {
            sb.append(cave + ": ");
            sb.append(allCaves.get(cave));
            sb.append("\n");
        }
        return sb.toString();
    }
}
