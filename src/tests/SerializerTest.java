package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mlt.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {
    private List<String> data;
    private List<Member> objects;
    private Identifier id;

    @BeforeEach
    void setUp() {
        id = new Identifier(new IdGenerator() {
            public List<String> serialize() {
                return null;
            }

            public void deserialize(List<String> args) {

            }

            public Integer getId() {
                return 1;
            }

            @Override
            public void freeId(Integer id) {

            }

            @Override
            public boolean isOccupied(Integer id) {
                return false;
            }
        });
        objects = new ArrayList<>();
        objects.add(new UsersFile(id, "jakis pliczek", "C:/cos/costam.png"));
        objects.add(new UsersFile(id, "jakis p//lic//zek", "C:/23cos/costam.txt"));
        objects.add(new UsersFile(id, "jakis pliczek", "C:/co11s/cos12tam.txt"));
        objects.add(new UsersFile(id, "{}{{ p///////lic}ek", "C:/gdzies/costam.txt"));

        objects.add(new LogicalDirectory(id, "moj katalog"));
        objects.add(new LogicalDirectory(id, "drugi katalog"));
        objects.add(new LogicalDirectory(id, "moj katalog123"));
        objects.add(new LogicalDirectory(id, "neiw iem"));
//        ((LogicalDirectory)objects.get(4)).addChild(objects.get(1));
        ((LogicalDirectory)objects.get(4)).addChild(objects.get(3).getId());

        objects.add(new Link(id, "link numer 1", "https://facebook.com"));
        objects.add(new Link(id, "", ""));
        objects.add(new Link(id, "link numer 3", "https://youtube.com"));
        objects.add(new Link(id, " ", "https://mlt.org/gdzies/tam.php?"));
    }

    @Test
    void serializerTests() throws Exception {
        for(Member object: objects)
        {
            Serializer.addObjectToList(object);
        }
        Serializer.save("tests.txt");
        assertEquals(objects, Serializer.recoverMembers("tests.txt"));
    }
}