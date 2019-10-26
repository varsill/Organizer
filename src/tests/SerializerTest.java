package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mlt.Serializer;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {
    private String stringForTests1;
    private String stringForTests2;
    private String stringForTests3;

    @BeforeEach
    void setUp() {
        stringForTests1 = "jaki;es zdanie // {} cos/s";
        stringForTests2 = "{{}}{/}/";
        stringForTests3 = "//{{sd     /}";
    }

    @Test
    void deAndEscapeTest()
    {
        oneTest(stringForTests1, "jaki;es zdanie //// /{/} cos//s");
        oneTest(stringForTests2, "/{/{/}/}/{///}//");
        oneTest(stringForTests3, "/////{/{sd     ///}");
    }

    void oneTest(String stringForTests, String expected)
    {
        String withEscapedChars = Serializer.escape(stringForTests);
        assertEquals(expected, withEscapedChars);
        String withoutEscapedChars = Serializer.deEscape(withEscapedChars);
        assertEquals(stringForTests, withoutEscapedChars);
    }
}