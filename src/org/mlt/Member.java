package org.mlt;

import java.util.LinkedList;
import java.util.List;

public class Member implements ISerializable {
    private Identifier id;
    private String description;
    private String name;

    public Member()
    {
    	
    }
    public Member(Identifier id, String name)
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

	@Override
	public List<String> serialize() {
		List<String> result = new LinkedList<String>();
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
