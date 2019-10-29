package org.mlt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Serializer {
    private static char[] escapeChars = {'/', '{', '}'};
    private static List<String> intoFile = new ArrayList<>();

    public static void addObjectToList(ISerializable object){
        intoFile.add(convertToString(object.serialize()));
    }

    private static String convertToString(List<String> data)
    {
        StringBuilder intoFile = new StringBuilder();
        for(String onePart: data)
        {
            intoFile.append('{');
            intoFile.append(escape(onePart));
            intoFile.append('}');
        }
        return intoFile.toString();
    }

    public static void recoverObjects(String fileName) throws FileNotFoundException {
        List<String> allObjects = readFromFile(fileName);

        for(String oneObject: allObjects)
        {
            List<String> dataForObject = null;
        }
    }

//    public static

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

    public static void save(String fileName) throws FileNotFoundException {
        PrintWriter outPutFile = new PrintWriter(fileName);
        outPutFile.print(convertToString(intoFile));
        outPutFile.close();
    }

    public static List<String> readFromFile() throws FileNotFoundException {
        return readFromFile("objects.txt");
    }

    private static String[] splitString(String fileInput)
    {
        return removeFrames(fileInput).split("}\\{");
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
