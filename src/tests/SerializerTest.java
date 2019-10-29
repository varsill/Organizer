package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mlt.Serializer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {
    private List<String> data;

    @BeforeEach
    void setUp() {
        data = new ArrayList<>();
        String stringForTests1 = "i;es1213/sda}{{} zdanie // {} cos/s";
        data.add("jaki;es zd}{}{}{//{}anie // {} cos/s");
        data.add("{{}}{/}/");
        data.add("//{{sd     /}");
        data.add(stringForTests1);
        data.add("}{}{}{{}{}{}{}{}{");
    }

    @Test
    void serializerTests() throws FileNotFoundException {
        Serializer.writeIntoFile(data, "tests.txt");
        assertEquals(data, Serializer.readFromFile("tests.txt"));
    }
}