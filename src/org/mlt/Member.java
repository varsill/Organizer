package org.mlt;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
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
        this.description = "";
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

    public Identifier getId()
    {
        return this.id;
    }

    public boolean equals(Object other)
    {
        if(other == null)
            return false;
        if(this == other) {
            return true;
        }
        if(!(other.getClass().equals(this.getClass()))) {
            return false;
        }
//        System.out.println();
        return this.getName().equals(((Member) other).getName()) && this.getId().readAsInteger().equals(((Member)other).getId().readAsInteger()) &&
                this.getDescription().equals(((Member) other).getDescription());
    }

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
			this.id = new Identifier(Main.MainIdGenerator.getInstance(), Integer.valueOf(args.get(0)));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.description=args.get(1);
		this.name=args.get(2);
	}
}
