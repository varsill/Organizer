package org.mlt;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UsersFile extends Member {
    private final String source;

    public UsersFile(Identifier id, String name, String source)
    {
        super(id, name);
        this.source = source;
    }

    public void open() throws IOException {
        Desktop desktop = java.awt.Desktop.getDesktop();
        desktop.open(new File(this.source));
    }

    public String getSource()
    {
        return this.source;
    }
}
