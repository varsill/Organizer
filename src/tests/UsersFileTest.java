package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mlt.IdGenerator;
import org.mlt.Identifier;
import org.mlt.LogicalDirectory;
import org.mlt.UsersFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersFileTest {
    private Identifier id;

    @BeforeEach
    void setUp()
    {
        id = new Identifier(new IdGenerator() {
            @Override
            public List<String> serialize() {
                return null;
            }

            @Override
            public void deserialize(List<String> args) {

            }

            @Override
            public Integer getId() throws Exception {
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
    }

    @Test
    void open() throws IOException {
        UsersFile fileToTests = new UsersFile(id, "plik", "tests.txt");
        fileToTests.open();
    }
}