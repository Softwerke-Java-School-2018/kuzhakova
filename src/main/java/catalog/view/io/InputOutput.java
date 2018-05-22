package catalog.view.io;

import java.io.*;
import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class InputOutput {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    /*public InputOutput(InputStream in, OutputStream out) throws IllegalArgumentException {
        bufferedReader = new BufferedReader(new InputStreamReader(in));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));
    }*/

    public static void printLine(String message) {
        try {
            if (StringUtils.isNoneBlank(message)) {
                bufferedWriter.write(message);
            }
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.flush();
        } catch (IOException e) {
            printLine("Output error.");
        }
    }

    public static void printLine(Object o) {
        try {
            if (Objects.nonNull(o)) {
                bufferedWriter.write(o.toString());
            }
            bufferedWriter.write(System.lineSeparator());
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
