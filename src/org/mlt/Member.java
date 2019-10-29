package org.mlt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Member implements ISerializable{
    private Identifier id;
    private String description;
    private String name;

    public Member()
    {

    }

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

    public abstract void open() throws IOException;

    @Override
    public List<String> serialize() {
        List<String> result = new ArrayList<String>();
        result.add(id.readAsInteger().toString());
        result.add(description);
        result.add(name);
        return result;
    }

    @Override
    public void deserialize(List<String> args) {
        try {
            this.id = new Identifier(Main.MainIdGenerator.getInstance(), Integer.parseInt(args.get(0)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
