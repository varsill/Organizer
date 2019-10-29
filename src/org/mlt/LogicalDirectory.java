package org.mlt;

import java.util.LinkedList;

public class LogicalDirectory extends Member
{
    public LinkedList <Member> children;

    public LogicalDirectory(Identifier id, String name)
    {
        super(id, name);
        this.children = new LinkedList<Member>();
    }

    public void addChild(Member child)
    {
        int pos = findPositionForChild(child);
        this.children.add(pos, child);
    }

    public void deleteChild(Member child)
    {
        this.children.remove(child);
    }

    public void open()
    {

    }

    private int findPositionForChild(Member newChild)
    {
        int newChildPriority = priority(newChild);

        for(Member child : this.children)
        {
            if(priority(child) == newChildPriority) {
                if (child.getName().compareTo(newChild.getName()) >= 0)
                    return this.children.indexOf(child);
            }
            else if(priority(child) > newChildPriority)
                    return this.children.indexOf(child);

        }
        return this.children.size();
    }

    private int priority(Member object)
    {
        if(object instanceof LogicalDirectory)
            return 0;
        if(object instanceof UsersFile)
            return 1;
        if(object instanceof Link)
            return 2;
        return 100;
    }
}
