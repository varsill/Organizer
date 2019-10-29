package org.mlt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Serializer {
    private static char[] escapeChars = {'/', '{', '}'};

    public static void writeIntoFile(List<String> data, String fileName) throws FileNotFoundException {
        PrintWriter outPutFile = new PrintWriter(fileName);
        StringBuilder intoFile = new StringBuilder();
        for(String oneObject: data)
        {
            intoFile.append('{');
            intoFile.append(escape(oneObject));
            intoFile.append('}');
        }
        outPutFile.print(intoFile.toString());
        outPutFile.close();
    }

    public static void writeIntoFile(List<String> data) throws FileNotFoundException {
        writeIntoFile(data, "objects.txt");
    }

    public static List<String> readFromFile() throws FileNotFoundException {
        return readFromFile("objects.txt");
    }

    public static List<String> readFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner in = new Scanner(file);
        List<String> data = new ArrayList<>();

        String fileInput = in.nextLine();

        for(String oneObjectData: splitString(fileInput))
        {
            data.add(deEscape(oneObjectData));
        }
        return data;
    }

    private static String[] splitString(String fileInput)
    {
        fileInput = removeFrames(fileInput);
        return fileInput.split("}\\{");
    }

    private static String removeFrames(String rawString)
    {
        return rawString.substring(1, rawString.length()-1);
    }

    private static String escape(String data)
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

    private static String deEscape(String data)
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
