package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import org.mlt.*;

class LogicalDirectoryTest {
    private LogicalDirectory testDirectory;
    private Identifier id;

    @BeforeEach
    void setUp()
    {
        id = new Identifier(new IdGenerator() {
            @Override
            public Integer getId() throws Exception {
                return 1;
            }

            @Override
            public void freeId(Integer id) {

            }
        });
        testDirectory = new LogicalDirectory(id,"for tests");
    }

    @Test
    void addingToList()
    {
        testDirectory.addChild(new UsersFile(id, "plik 3", "123"));
        testDirectory.addChild(new Link(id, "link 3", "asdasd"));
        testDirectory.addChild(new LogicalDirectory(id,"folder 1"));
        assertTrue(isOk());
        testDirectory.addChild(new Link(id, "ala", "asdasd"));
        testDirectory.addChild(new LogicalDirectory(id,"ala"));
        testDirectory.addChild(new UsersFile(id, "ala", "123"));
        assertTrue(isOk());
        testDirectory.addChild(new LogicalDirectory(id,"folder 3"));
        testDirectory.addChild(new UsersFile(id, "plik 1", "123"));
        testDirectory.addChild(new Link(id, "link 1", "asdasd"));
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
        if(object instanceof LogicalDirectory)
            return 0;
        if(object instanceof UsersFile)
            return 1;
        if(object instanceof Link)
            return 2;
        return 100;
    }
}