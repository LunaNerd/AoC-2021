package Classes12;

import java.util.*;

public class Cave {

    private Set<String> surroundingCaves;
    private String name;
    private boolean isSmall;

    public Cave(String name) {
        this.surroundingCaves = new HashSet<>();
        this.name = name;
        this.isSmall = Character.isLowerCase(name.charAt(0));
    }

    public void addSurroundingCave(String newName) {
        surroundingCaves.add(newName);
    }

    public Set<String> getSurroundingCaves() {
        return surroundingCaves;
    }

    public boolean getIsSmall() {
        return isSmall;
    }

    @Override
    public String toString() {
        return surroundingCaves.toString();
    }
}
