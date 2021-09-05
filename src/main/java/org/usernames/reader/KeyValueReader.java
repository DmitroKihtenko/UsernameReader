package org.usernames.reader;

import org.jetbrains.annotations.NotNull;
import org.usernames.StringAssertions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyValueReader implements UsernameReader {
    private String fullString;
    private String usernameKeyword;
    private String delimiter;
    private String keyValueDelimiter;

    public KeyValueReader(@NotNull String fullString) {
        this.fullString = fullString;
        usernameKeyword = "user";
        delimiter = ",";
        keyValueDelimiter = ":";
    }

    public KeyValueReader(@NotNull InputStream stream)
            throws IOException {
        setFullString(stream);
        usernameKeyword = "user";
        delimiter = ",";
        keyValueDelimiter = ":";
    }

    @NotNull
    public String getFullString() {
        return fullString;
    }

    @Override
    public void setFullString(@NotNull String fullString) {
        this.fullString = fullString;
    }

    public void setFullString(@NotNull InputStream stream)
            throws IOException {
        int size = stream.available();
        byte[] bytes = new byte[size];
        for(int byteNum = 0; byteNum < size; byteNum++) {
            bytes[byteNum] = (byte) stream.read();
        }
        fullString = new String(bytes);
    }

    @NotNull
    public String getUsernameKeyword() {
        return usernameKeyword;
    }

    public void setUsernameKeyword(@NotNull String usernameKeyword) {
        StringAssertions.isVisible(usernameKeyword,
                "Username keyword");
        StringAssertions.isWholeWord(usernameKeyword,
                "Username keyword");

        this.usernameKeyword = usernameKeyword;
    }

    @NotNull
    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(@NotNull String delimiter) {
        StringAssertions.isVisible(delimiter,
                "Delimiter sequence");
        StringAssertions.isWholeWord(delimiter,
                "Delimiter sequence");
        this.delimiter = delimiter;
    }

    @NotNull
    public String getKeyValueDelimiter() {
        return keyValueDelimiter;
    }

    public void setKeyValueDelimiter(@NotNull String
                                             delimiter) {
        StringAssertions.isVisible(delimiter,
                "Key-value delimiter sequence");
        StringAssertions.isWholeWord(delimiter,
                "Key-value delimiter sequence");
        keyValueDelimiter = delimiter;
    }



    @Override
    public void readUsernames(@NotNull Set<String> usernames) {
        Pattern pattern = Pattern.compile(usernameKeyword +
                        keyValueDelimiter + "\\s*|" +
                delimiter + "\\s*");
        Matcher matcher = Pattern.compile(usernameKeyword +
                keyValueDelimiter + "\\s*[\\w\\s]*" +
                delimiter + "\\s*").matcher(fullString);
        String value;
        while(matcher.find()) {
            value = matcher.group();
            value = pattern.matcher(value).replaceAll("");
            usernames.add(value);
        }
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        final Pattern pattern = Pattern.compile(usernameKeyword +
                keyValueDelimiter + "\\s*|" +
                delimiter + "\\s*");
        final Matcher matcher = Pattern.compile(usernameKeyword +
                keyValueDelimiter + "\\s*[\\w\\s]*" +
                delimiter + "\\s*").matcher(fullString);
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return matcher.find();
            }

            @Override
            public String next() {
                String value = matcher.group();
                return pattern.matcher(value).replaceAll("");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
