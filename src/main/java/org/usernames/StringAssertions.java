package org.usernames;

import java.util.regex.Pattern;

public class StringAssertions {
    private static final Pattern spaces = Pattern.compile("^\\s*$");
    private static final Pattern wholeWord = Pattern.compile(".*\\s.*");
    private static final Pattern letters = Pattern.compile(".*[\\S\\D].*");

    public static void isVisible(String parameter, String name) {
        if (spaces.matcher(parameter).find()) {
            throw new IllegalArgumentException(name +
                    " must be visible on screen");
        }
    }

    public static void isWholeWord(String parameter, String name) {
        if (wholeWord.matcher(parameter).find()) {
            throw new IllegalArgumentException(name +
                    " must be whole word");
        }
    }

    public static void containsLetter(String parameter, String name) {
        if (!letters.matcher(parameter).find()) {
            throw new IllegalArgumentException(name +
                    " must contain at least one letter");
        }
    }
}
