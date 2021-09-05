package org.usernames.reader;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

public interface UsernameReader extends Iterable<String> {
    void setFullString(@NotNull String fullString);
    void setFullString(@NotNull InputStream stream)
            throws IOException;
    void readUsernames(@NotNull Set<String> usernames)
            throws IOException;
    @NotNull
    @Override
    Iterator<String> iterator();
}
