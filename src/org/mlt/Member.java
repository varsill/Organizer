package org.mlt;

public class Member {
    private final Identifier id;
    private String description;
    private String name;

    Member(Identifier id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public void addDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void open()
    {

    }
}
