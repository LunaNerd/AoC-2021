import Classes16.Operator;
import Classes16.OperatorBits;
import Classes16.OperatorPack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day16 {
    static String hexaDecimal;
    static byte[] bitCode;

    static Map<Character, Long> hexToLong;

    public static void main(String[] args) throws FileNotFoundException {
        fillBitCode();

        Stack<Operator> stack = new Stack<>();
        byte b = 0;
        stack.add(new OperatorBits(b, bitCode.length - 1, bitCode.length - 1));
        int index = 0;

        long versionSum = 0;
        while (!stack.isEmpty()) {
            System.out.println(stack.peek());
            if (stack.peek().isFull(index)) {
                long value = stack.peek().calculateValueInside();
                stack.pop();
                if (!stack.isEmpty()) {
                    stack.peek().addValueToPackage(value);
                } else {
                    System.out.println("--->  " + value);
                }

            } else if (bitCode.length - index < 32 && bitCodeToLong(index, bitCode.length) == 0) {
                System.out.println("trailing zeros detected");
                index = bitCode.length - 1;
            } else {
                versionSum += bitCodeToLong(index, index + 3);
                index += 3;
                if (4L != bitCodeToLong(index, index + 3)) {
                    byte type = (byte) bitCodeToLong(index, index + 3);
                    index += 3;
                    if (bitCode[index] == 0) {
                        index++;
                        int length = (int) (bitCodeToLong(index, index + 15));
                        index += 15;
                        System.out.println(index);
                        stack.add(new OperatorBits(type, length, index + length));
                    } else {
                        index++;
                        stack.add(new OperatorPack(type, (int) bitCodeToLong(index, index + 11)));
                        index += 11;
                    }
                } else {
                    System.out.println("Literal Value");
                    index += 3;
                    long literalNumber = 0;
                    while (bitCode[index] == 1) {
                        index++;
                        literalNumber *= 16;
                        literalNumber += bitCodeToLong(index, index + 4);
                        index += 4;
                    }
                    index++;
                    literalNumber *= 16;
                    literalNumber += bitCodeToLong(index, index + 4);
                    index += 4;
                    stack.peek().addValueToPackage(literalNumber);
                    /*bitCodeToLong(0, index);
                    if (index % 4 != 0) {
                        index += 4 - (index%4);
                    }*/

                }
            }
            //bitCodeToLong(0, index);
        }
        //System.out.println(versionSum);
    }

    private static long bitCodeToLong(int start, int end) {
        long machtTwee = 1;
        long res = 0;
        for (int i = end - 1; i >= start; i--) {
            res += bitCode[i] * machtTwee;
            machtTwee *= 2;
        }
        /*for (int i = start; i < end; i++) {
            System.out.print(bitCode[i]);
        }
        System.out.println();*/
        return res;
    }

    private static void fillHexToLong() {
        hexToLong = new HashMap<>();
        for (long i = 0; i < 10; i++) {
            hexToLong.put((char) ('0' + i), i);
        }
        for (long i = 10; i < 16; i++) {
            hexToLong.put((char) ('A' + i - 10), i);
        }
    }

    private static void fillBitCode() throws FileNotFoundException {
        //Some very messy code to turn the hexadecimal number into a bitcode. Don't read it, it's embarrassing :p
        fillHexToLong();

        Scanner sc = new Scanner(new File("Day16.txt"));
        hexaDecimal = sc.next();
        bitCode = new byte[hexaDecimal.length() * 4];
        for (int i = 0; i < hexaDecimal.length(); i++) {
            int index = 4 * i;
            long getal = hexToLong.get(hexaDecimal.charAt(i));
            for (int j = 3; j >= 0; j--) {
                bitCode[index + j] = (byte) (getal % 2);
                getal = (getal - (getal % 2)) / 2;
            }
        }
        for (byte i : bitCode) {
            System.out.print(i);
        }
        System.out.println();
    }
}
