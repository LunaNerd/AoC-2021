package Classes04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bingo {

    private String[] teZoeken;
    private int index;
    private List<SpelBord> borden;
    private int nHasWon = 0;

    private int size = 5;

    public Bingo(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        teZoeken = sc.nextLine().split(",");
        index = 0;
        borden = new ArrayList<>();

        while (sc.hasNextInt()) {
            borden.add(new SpelBord(sc));
        }

        //System.out.println(this);
    }

    public int callNumber() {
        for (SpelBord bord : borden) {
            if (!bord.hasWon) {
                int potentialScore = bord.duidAan(Integer.parseInt(teZoeken[index]));
                if (potentialScore != 0) {
                    nHasWon++;
                    bord.hasWon = true;
                    /*if (nHasWon>=98) {
                        System.out.println(this);
                        System.out.println("\n\n\n\n");
                        System.out.println(teZoeken[index]);
                    }*/
                    return potentialScore;
                }
            }
            else {
                //System.out.println("Er word een bord overgeslagen");
            }
        }
        index++;
        return 0;
    }

    public boolean wasTheLastOne() {
        int teller = 0;
        for (SpelBord bord : borden) {
            if (bord.hasWon) {
                teller++;
            }
        }
        System.out.println(teller + "<-->" + borden.size());
        return teller == borden.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SpelBord bord : borden) {
            if (!bord.hasWon) {
                sb.append("[");
                sb.append(bord);
                sb.append("]\n");
            }
        }
        return sb.toString();
    }
}
