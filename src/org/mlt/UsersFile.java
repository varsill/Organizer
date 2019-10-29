package org.mlt;

public class UsersFile extends Member {
    private final String source;

    public UsersFile(Identifier id, String name, String source)
    {
        super(id, name);
        this.source = source;
    }

    public String getSource()
    {
        return this.source;
    }
}
