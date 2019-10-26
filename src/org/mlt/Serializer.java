package org.mlt;

public class Serializer {
    private static char[] escapeChars = {'/', '{', '}'};

    public static void writeIntoFile(String data)
    {

    }

    public static String escape(String data)
    {
        StringBuilder dataWithEscapedChar = new StringBuilder();
        for(int i =0; i < data.length(); i++)
        {
            if(inEscapeChars(data.charAt(i)))
            {
                dataWithEscapedChar.append('/');
            }
            dataWithEscapedChar.append(data.charAt(i));
        }
        return dataWithEscapedChar.toString();
    }

    public static String deEscape(String data)
    {
        StringBuilder dataWithoutEscapedChar = new StringBuilder();
        boolean skipThisOne = false;

        for(int i =0; i < data.length(); i++)
        {
            if(skipThisOne)
            {
                skipThisOne = false;
                dataWithoutEscapedChar.append(data.charAt(i));
            }
            else if(data.charAt(i) == '/')
                skipThisOne = true;
            else
            {
                dataWithoutEscapedChar.append(data.charAt(i));
            }
        }
        return dataWithoutEscapedChar.toString();
    }

    private static boolean inEscapeChars(char letter)
    {
        for(char escChar: escapeChars)
        {
            if(escChar == letter)
                return true;
        }
        return false;
    }
}
