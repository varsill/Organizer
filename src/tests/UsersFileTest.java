package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mlt.IdGenerator;
import org.mlt.Identifier;
import org.mlt.LogicalDirectory;
import org.mlt.UsersFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UsersFileTest {
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
    }

    @Test
    void open() throws IOException {
        UsersFile fileToTests = new UsersFile(id, "plik", "tests.txt");
        fileToTests.open();
    }
}