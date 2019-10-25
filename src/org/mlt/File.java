package org.mlt;

public class File extends Member {
    public final String source;

    public File(int id, String name, String source)
    {
        super(id, name);
        this.source = source;
    }
}
