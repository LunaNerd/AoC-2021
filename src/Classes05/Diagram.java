package Classes05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Diagram {

    private int[][] diagram;
    private int nOfDangerZones;

    public Diagram(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        int size = 0;
        while (sc.hasNextLine()) {
            Scanner scc = new Scanner(sc.nextLine());
            scc.useDelimiter(",");
            while (scc.hasNext()) {
                int newSize = Integer.parseInt(scc.next());
                if (newSize > size) {
                    size = newSize;
                }
            }
            scc.close();
        }
        sc.close();
        System.out.println(size+1);
        diagram = new int[size+1][size+1];
        nOfDangerZones = 0;
    }

    public void addLine(Scanner sc) {
        String line = sc.nextLine();
        String[] split = line.split(",");
        int x1 = Integer.parseInt(split[0]);
        int y1 = Integer.parseInt(split[1]);
        int x2 = Integer.parseInt(split[2]);
        int y2 = Integer.parseInt(split[3]);

        markDanger(x1, y1, x2, y2);
    }

    private void markDanger(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            if (y1 > y2) {
                markDanger(x2, y2, x1, y1);
            }
            else {
                while (y1 <= y2) {
                   diagram[y1][x1]++;
                   if (diagram[y1][x1] == 2) {
                       nOfDangerZones++;
                   }
                   y1++;
                }
            }
        }
        else if (y1 == y2){
            if (x1 > x2) {
                markDanger(x2, y2, x1, y1);
            }
            else {
                while (x1 <= x2) {
                    diagram[y1][x1]++;
                    if (diagram[y1][x1] == 2) {
                        nOfDangerZones++;
                    }
                    x1++;
                }
            }
        }
        else if (Math.abs(y2-y1) == Math.abs(x2-x1)) {
            int y_step = (y2-y1)/Math.abs(y2-y1);
            int x_step = (x2-x1)/Math.abs(x2-x1);
            while (x1 != x2) {
                diagram[y1][x1]++;
                if (diagram[y1][x1] == 2) {
                    nOfDangerZones++;
                }
                x1 += x_step;
                y1 += y_step;
            }
            diagram[y1][x1]++;
            if (diagram[y1][x1] == 2) {
                nOfDangerZones++;
            }
            if (y1 != y2) {
                System.out.println("Fout bij diagonaal!");
            }
        }
    }

    public int getnOfDangerZones() {
        return nOfDangerZones;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < diagram.length; i++) {
            for (int j = 0; j < diagram[0].length; j++) {
                if (diagram[i][j] >= 10) {
                    sb.append("!");
                }
                else if (diagram[i][j] == 0) {
                    sb.append(".");
                }
                else {
                    sb.append(diagram[i][j]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
