package org.mlt;


import java.util.List;

import java.awt.*;
import java.net.URI;


public class Link extends Member {
    private String address;

    private Link()
    {
        super();

    }

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
		List<String> result = super.serialize();
		result.add(address);
		return result;
    }

    @Override
    public void deserialize(List<String> args)
    {
    	super.deserialize(args);
    	this.address= args.get(3);
    }
    public void open()
    {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(this.address);
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


	static ISerializable createFromStringList(List<String> args) {
		
		Link result = new Link();
		result.deserialize(args);
		return result;
	}

	public boolean equals(Object other)
    {
        return super.equals(other) && this.address.equals(((Link) other).address);
    }
}
