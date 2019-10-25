package org.mlt;

public class Link extends Member{
    public final String address;

    public Link(int id, String name, String address)
    {
        super(id, name);
        this.address = address;
    }
}
