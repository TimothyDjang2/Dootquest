package dootquest;

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
}