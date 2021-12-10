import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day03 {

    public static void main(String[] args) throws FileNotFoundException {
        //part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day03.txt"));
        List<Integer> list = new ArrayList<>();
        String bitcode = sc.nextLine();
        String[] split = bitcode.split("");
        for (String bit : split) {
            list.add(Integer.parseInt(bit));
        }
        int teller = 1;
        while (sc.hasNextLine()) {
            bitcode = sc.nextLine();
            split = bitcode.split("");
            for (int i = 0; i<split.length; i++) {
                list.set(i, list.get(i) + Integer.parseInt(split[i]));
            }
            teller++;
        }
        sc.close();

        System.out.println(list);
        int machtTwee = 1;
        int gamma = 0;
        int elipson = 0;

        for (int i = list.size()-1; i>=0; i--) {
            if (list.get(i) > teller/2) {
                gamma += machtTwee;
            }
            else {
                elipson += machtTwee;
            }
            machtTwee *= 2;
        }

        System.out.println("gamma: " + gamma);
        System.out.println("elipson: " + elipson);

        System.out.println(gamma * elipson);
    }

    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day03.txt"));
        List<String> oxi = new LinkedList<>();
        List<String> co2 = new LinkedList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            oxi.add(line);
            co2.add(line);
        }

        int position = 0;
        while (oxi.size() != 1) {
            int teller = 0;
            for (String s : oxi) {
                if (s.charAt(position) == '1') {
                    teller++;
                }
            }
            boolean isEqual = oxi.size() % 2 == 0 && teller == oxi.size()/2;
            boolean isOne = teller > oxi.size()/2;

            for (int i = oxi.size()-1; i>=0; i--) {
                if (isEqual) {
                    if (oxi.get(i).charAt(position) != '1') {
                        oxi.remove(i);
                    }
                }
                else if (isOne) {
                    if (oxi.get(i).charAt(position) != '1') {
                        oxi.remove(i);
                    }
                }
                else {
                    if (oxi.get(i).charAt(position) != '0') {
                        oxi.remove(i);
                    }
                }
            }
            position++;
        }
        int oxig = toDecimal(oxi.get(0));

        oxi = co2;
        position = 0;
        while (oxi.size() != 1) {
            int teller = 0;
            for (String s : oxi) {
                if (s.charAt(position) == '1') {
                    teller++;
                }
            }
            boolean isEqual = oxi.size() % 2 == 0 && teller == oxi.size()/2;
            boolean isOne = teller > oxi.size()/2;

            for (int i = oxi.size()-1; i>=0; i--) {
                if (isEqual) {
                    if (oxi.get(i).charAt(position) != '0') {
                        oxi.remove(i);
                    }
                }
                else if (isOne) {
                    if (oxi.get(i).charAt(position) == '1') {
                        oxi.remove(i);
                    }
                }
                else {
                    if (oxi.get(i).charAt(position) == '0') {
                        oxi.remove(i);
                    }
                }
            }
            position++;
        }
        int co22 = toDecimal(oxi.get(0));

        System.out.println(oxig*co22);
    }

    public static int toDecimal(String bitcode) {
        System.out.println(bitcode);
        int machtTwee = 1;
        int res = 0;
        for (int i = bitcode.length()-1; i>= 0; i--) {
            if (bitcode.charAt(i) == '1') {
                res += machtTwee;
            }
            machtTwee *= 2;
        }
        return res;
    }
}
