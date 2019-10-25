package org.mlt;

import java.util.LinkedList;

public class Directory extends Member{
    private LinkedList <Member> children;

    public Directory(int id, String name)
    {
        super(id, name);
    }

    public void addChild(Member child)
    {
        this.children.add(findPositionForChild(child), child);
    }

    public void deleteChild(Member child)
    {
        this.children.remove(child);
    }

    private int findPositionForChild(Member child)
    {
        int childPriority = priority(child);

        for(int i = 0; i < this.children.size(); i++)
        {
            Member nextElement = this.children.get(i);
            if(priority(nextElement) == childPriority) {
                if (nextElement.name.compareTo(child.name) >= 0)
                    return i;
            }
            else if(priority(nextElement) < childPriority)
                    return i;

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
