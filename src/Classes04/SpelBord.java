package Classes04;

import java.util.Scanner;

public class SpelBord {
    private int[][] cijfers;
    private boolean[][] isAangeduid;
    public boolean hasWon;

    public SpelBord(Scanner sc) {
        cijfers = new int[5][5];
        isAangeduid = new boolean[5][5];
        hasWon = false;

        //sc.nextLine();
        for (int i = 0; i<5; i++) {
            for (int j = 0; j<5; j++) {
                cijfers[i][j] = sc.nextInt();
            }
        }
    }

    public int duidAan(int teZoeken) {
        for (int i = 0; i<5; i++) {
            for (int j = 0; j<5; j++) {
                if (cijfers[i][j] == teZoeken) {
                    isAangeduid[i][j] = true;
                    if (testIfWin(i, j)) {
                        this.hasWon = true;
                        return calculateScore(cijfers[i][j]);
                    }
                    else {
                        return 0;
                    }
                }
            }
        }
        return 0;
    }

    private boolean testIfWin(int i, int j) {
        boolean horizontalWin = true;
        boolean verticalWin = true;
        for (int k = 0; k<5; k++) {
            horizontalWin = horizontalWin && isAangeduid[i][k];
            verticalWin = verticalWin && isAangeduid[k][j];
        }
        return horizontalWin || verticalWin;
    }

    private int calculateScore(int winningNumber) {
        int score = 0;
        for (int i = 0; i<5; i++) {
            for (int j = 0; j<5; j++) {
                if (!isAangeduid[i][j]) {
                    score += cijfers[i][j];
                }
            }
        }
        return score * winningNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.hasWon + "\n");
        for (int i = 0; i<5; i++) {
            for (int j = 0; j<5; j++) {
                if (isAangeduid[i][j]) {
                    sb.append("*  ");
                }
                else {
                    sb.append(cijfers[i][j] + "  ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
