package dootquest;

//Class for understanding grammar stuff, like what a vowel is.
public class Vocabulary {

    public static boolean isVowel(Character c) {
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            return true;
        } else {
            return false;
        }
    }

}