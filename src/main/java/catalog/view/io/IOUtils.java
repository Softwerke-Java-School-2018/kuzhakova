package catalog.view.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOUtils {

    /**
     * The method of checking the entered date for the format dd/mm/yyyy
     * @param dateString The entered date
     * @return true, if entered date in the specified format
     */
    public static boolean checkEnterDateWithRegExp(String dateString) {
        Pattern p = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\\d\\d");
        Matcher m = p.matcher(dateString);
        return m.matches();
    }

    public static boolean isCorrectParameter(int parameter, int max) {
        if (parameter < 0 || parameter > max) {
            throw new IllegalArgumentException("Invalid input parameter.");
        } else if (parameter == 0) {
            return false;
        }
        return true;
    }

    /**
     * The method that requests string and checks the presence of the entered value in Enum
     * If the user enters "0", then print the values of Enum
     * @param en Enum
     * @return Value of enum
     */
    public static <E extends Enum<E>> E lookupEnum(Class<E> en) {
        while (true) {
            String fromEnum = InputOutput.readLine();
            if ("0".equals(fromEnum)) {
                printElementsOfEnum(en);
            }
            try {
                E result = Enum.valueOf(en, fromEnum.toUpperCase());
                return result;
            } catch (IllegalArgumentException e) {
                InputOutput.printLine("Enter the correct value (enter 0 for print elements):");
            }
        }
    }

    public static <E extends Enum<E>> void printElementsOfEnum(Class<E> en) {
        for (E elementOfEnum : en.getEnumConstants()) {
            InputOutput.printLine(elementOfEnum.name());
        }
    }
}
