package org.mlt;

import java.awt.*;
import java.net.URI;

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
}
