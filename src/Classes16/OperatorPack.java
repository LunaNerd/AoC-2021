package Classes16;

public class OperatorPack extends Operator {

    int nPackages;
    int targetPackages;

    public OperatorPack(byte type, int targetPackages) {
        super(type);
        this.targetPackages = targetPackages;
    }

    @Override
    public boolean isFull(int currentIndex) {
        nPackages++;
        return nPackages-1 == targetPackages;
    }

    @Override
    public String toString() {
        return "OperatorPack(" + type + ") " + nPackages + "/" + targetPackages;
    }
}
