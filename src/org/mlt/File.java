package org.mlt;

public class File extends Member {
    private final String source;

    public File(Identifier id, String name, String source)
    {
        super(id, name);
        this.source = source;
    }
    
    public String getSource()
    {
        return this.source;
    }
}
