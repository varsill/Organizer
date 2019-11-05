package org.mlt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LogicalDirectory extends Member
{
    private List<Identifier> children;
    private IdGenerator generator;
    
    private LogicalDirectory()
    {
    	super();
        this.children = new ArrayList<>();
    }
    
    public LogicalDirectory(Identifier id, String name)
    {
        super(id, name);
        this.children = new ArrayList<>();
    }

    public void addChild(Identifier child)
    {
        this.children.add(child);
    }

    public void deleteChild(Identifier child)
    {
        this.children.remove(child);
    }

    public void open()
    {

    }

    static ISerializable createFromStringList(List<String> args) throws Exception {
		
		LogicalDirectory result = new LogicalDirectory();
		result.deserialize(args);
		return result;
	}

    @Override
    public List<String> serialize()
    {
        List<String> result = super.serialize();
        List<String> children = new ArrayList<>();
        for(Identifier child: this.children)
        {
            children.add(child.readAsInteger().toString());
        }
        result.add(Serializer.convertToString(children));
        result.add(Serializer.convertToString(this.generator.serialize()));
        return result;
    }

    @Override
    public void deserialize(List<String> args) throws Exception {
        super.deserialize(args);
        for(String child: Serializer.convertToArrayOfString(args.get(3)))
        {
            this.children.add(new Identifier(this.generator, Integer.parseInt(child)));
        }
        this.generator = Main.MainIdGenerator.createFromStringList(Serializer.convertToArrayOfString(args.get(4)));

    }

    public boolean equals(Object other)
    {
        return super.equals(other); //&& this.address.equals(((Link) other).address);
    }
    
}
