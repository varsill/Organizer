package org.mlt;

import java.util.LinkedList;

public class Directory extends Member{
    public LinkedList <Member> children;

    public Directory(int id, String name)
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

    private int findPositionForChild(Member newChild)
    {
        int newChildPriority = priority(newChild);

        for(Member child : this.children)
        {
            if(priority(child) == newChildPriority) {
                if (child.name.compareTo(newChild.name) >= 0)
                    return this.children.indexOf(child);
            }
            else if(priority(child) > newChildPriority)
                    return this.children.indexOf(child);

        }
        return this.children.size();
    }

    private int priority(Member object)
    {
        if(object instanceof Directory)
            return 0;
        if(object instanceof File)
            return 1;
        if(object instanceof Link)
            return 2;
        return 100;
    }
}
