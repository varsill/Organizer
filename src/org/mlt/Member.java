package org.mlt;

public class Member {
    protected final int id;
    protected String description;
    protected String name;

    protected Member(int id, String name)
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
