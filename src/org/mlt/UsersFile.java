package org.mlt;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UsersFile extends Member {
    private String source;

    
    private UsersFile()
    {
    	super();
    }
    public UsersFile(Identifier id, String name, String source)
    {
        super(id, name);
        this.source = source;
    }

    public void open() throws IOException {
        Desktop desktop = java.awt.Desktop.getDesktop();
        desktop.open(new File(this.source));
    }

    @Override
    public java.util.List<String> serialize()
    {
        java.util.List<String> result = super.serialize();
        result.add(source);
        return result;
    }

    @Override
    public void deserialize(List<String> args)
    {
        super.deserialize(args);
        this.source= args.get(3);
    }

    public String getSource()
    {
        return this.source;
    }
    
    static ISerializable createFromStringList(List<String> args) {
		UsersFile result = new UsersFile();
		result.deserialize(args);
		return result;
	}

    public boolean equals(Object other)
    {
        return super.equals(other) && this.getSource().equals(((UsersFile) other).getSource());
    }
}
