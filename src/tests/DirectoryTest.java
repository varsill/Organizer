package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.mlt.Directory;
import org.mlt.File;
import org.mlt.Link;
import org.mlt.Member;

class DirectoryTest {
    private Directory testDirectory;
    @BeforeEach
    void setUp()
    {
        testDirectory = new Directory(1,"for tests");
    }

    @Test
    void addingToList()
    {
        testDirectory.addChild(new File(2, "plik 3", "123"));
        testDirectory.addChild(new Link(3, "link 3", "asdasd"));
        testDirectory.addChild(new Directory(1,"folder 1"));
        assertTrue(isOk());
        testDirectory.addChild(new Link(3, "ala", "asdasd"));
        testDirectory.addChild(new Directory(1,"ala"));
        testDirectory.addChild(new File(2, "ala", "123"));
        assertTrue(isOk());
        testDirectory.addChild(new Directory(1,"folder 3"));
        testDirectory.addChild(new File(2, "plik 1", "123"));
        testDirectory.addChild(new Link(3, "link 1", "asdasd"));
        assertTrue(isOk());
    }

    boolean isOk()
    {
        Member previous = testDirectory.children.getFirst();
        for(Member child: testDirectory.children)
        {
            if(child.equals(previous))
                continue;
            if(child.getClass() == previous.getClass() && child.getName().compareTo(previous.getName()) < 0)
            {
                return false;
            }
            else if(priority(previous) > priority(child))
            {
                return false;
            }
            previous = child;
        }
        return true;
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