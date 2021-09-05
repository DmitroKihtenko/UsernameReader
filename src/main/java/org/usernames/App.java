package org.usernames;

import org.usernames.reader.KeyValueReader;
import org.usernames.reader.UsernameReader;

import java.io.FileInputStream;
import java.util.TreeSet;

public class App
{
    private static String usernameFile = "file1.txt";
    private static String repeatUsernameFile =
            "file2.txt";

    public static void main( String[] args )
    {
        setParams(args);
        try {
            FileInputStream fis = new FileInputStream(usernameFile);
            UsernameReader reader = new KeyValueReader(fis);
            TreeSet<String> usernames = new TreeSet<>();
            reader.readUsernames(usernames);
            fis = new FileInputStream(repeatUsernameFile);
            reader.setFullString(fis);
            for (String username : reader) {
                usernames.remove(username);
            }
            for(String username : usernames) {
                System.out.println(username);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setParams(String[] args) {
        if(args.length > 1 && !args[0].equals("")) {
            usernameFile = args[0];
        }
        if(args.length > 2 && !args[1].equals("")) {
            repeatUsernameFile = args[1];
        }
    }
}
