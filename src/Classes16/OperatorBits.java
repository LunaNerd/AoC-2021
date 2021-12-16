package Classes16;

public class OperatorBits extends Operator {
    int nBits;
    int toIndex;

    public OperatorBits(byte type, int nBits, int toIndex) {
        super(type);
        this.nBits = nBits;
        this.toIndex = toIndex;
    }

    @Override
    public boolean isFull(int currentIndex) {
        return currentIndex >= toIndex;
    }


    @Override
    public String toString() {
        return "Operatorbits(" + type + ") " + toIndex;
    }
}
