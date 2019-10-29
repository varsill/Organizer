package org.mlt;

import java.util.List;

public class Link extends Member{
    private final String address;

    public Link(Identifier id, String name, String address)
    {
        super(id, name);
        this.address = address;
    }
    
    public String getAddress()
    {
    	return this.address;
    }
    
    @Override
    public List<String> serialize()
    {
    	
    }
}
