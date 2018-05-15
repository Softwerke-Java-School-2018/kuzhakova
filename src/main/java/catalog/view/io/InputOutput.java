package catalog.view.io;

import java.io.*;
import java.math.BigDecimal;

public class InputOutput {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void printLine(String message) {
        try {
            bufferedWriter.write(message + System.getProperty("line.separator"));
            bufferedWriter.flush();
        } catch (IOException e) {
            printLine("Output error.");
        }
    }

    public static void printLine(Object o) {
        try {
            bufferedWriter.write(o.toString() + System.getProperty("line.separator"));
            bufferedWriter.flush();
        } catch (IOException e) {
            printLine("Output error.");
        }
    }

    public static String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            printLine("Input error.");
            return "";
        }
    }

    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(readLine());
            } catch (NumberFormatException e) {
                printLine("Input error. Enter the number: ");
            }
        }
    }

    public static BigDecimal readBigDecimal() {
        while (true) {
            try {
                return new BigDecimal(readLine());
            } catch (NumberFormatException e) {
                printLine("Input error. Enter the price: ");
            }
        }
    }
}
