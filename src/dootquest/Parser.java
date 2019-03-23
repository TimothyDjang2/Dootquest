package dootquest;

import java.lang.StringBuilder;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Parser {

    Scanner scanner;
    
    public Parser() {
        scanner = new Scanner(System.in);
    }

    public String[] getInput() {
        String input = scanner.nextLine();
        return parseString(input);
    }

    /**
     * Converts a string to a String[] of all the words.
     */
    public String[] parseString(String input) {
        String storage;
        String[] output;
        List<String> cmdList;
        int i = 0;
        int oldStart = 0;

        cmdList = new LinkedList<>();
        
        for (i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                cmdList.add(input.substring(oldStart, i).toLowerCase());
                oldStart = (i + 1);
            }
        }
        cmdList.add(input.substring(oldStart, i).toLowerCase());

        output = new String[cmdList.size()];
        cmdList.toArray(output);
        return output;
    }

    public String combine(int startIndex, int endIndex, String[] array) {

        StringBuilder returnString = new StringBuilder();

        for (int i = startIndex; (i <= endIndex && i < array.length); i++) {
            returnString.append(array[i] + " ");
        }

        String returnStr = returnString.toString();
        return returnStr.substring(0, returnStr.length() - 1);
    }

}