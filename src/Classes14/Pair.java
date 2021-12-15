package Classes14;

public class Pair {
    private String left;
    private String right;
    private char middle;
    private long oldCount;
    private long newCount;
    private int lastStep;

    public Pair(String pair, String middle) {
        this.left = pair.charAt(0) + middle;
        this.right = middle + pair.charAt(1);
        this.middle = middle.charAt(0);
    }

    public void increasePairs(long toAdd, int currentStep) {
        updateCount(currentStep);
        newCount += toAdd;
    }
    public long getCount(int currentStep) {
        updateCount(currentStep);
        return oldCount;
    }

    private void updateCount(int currentStep) {
        if (currentStep != lastStep) {
            oldCount = newCount;
            newCount = 0;
            lastStep = currentStep;
        }
    }
    public String getLeft() {
        return left;
    }
    public String getRight() {
        return right;
    }

    public char getMiddle() {
        return middle;
    }

    @Override
    public String toString() {
        return "(" + Long.toString(oldCount) + " " + Long.toString(newCount) + ")";

    }
}
