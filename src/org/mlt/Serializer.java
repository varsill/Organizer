package org.mlt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Serializer {
    private static char[] escapeChars = {'{', '}', '/'};
    private static List<String> intoFile = new ArrayList<>();

    public static void addObjectToList(ISerializable object){
        List<String> allInformationAboutObject = object.serialize();
        allInformationAboutObject.add(0, convertObjectClassToString(object));

        intoFile.add(convertToString(allInformationAboutObject));
    }

    public static void save(String fileName) throws FileNotFoundException {
        PrintWriter outPutFile = new PrintWriter(fileName);
        outPutFile.print(convertToString(intoFile));
        intoFile.clear();
        outPutFile.close();
    }

    public static List<Member> recoverMembers(String fileName) throws Exception {
        List<Member> objectsReference = new ArrayList<>();
        List<String> allObjects = convertToArrayOfString(readFromFile(fileName));
        for(String oneObject: allObjects)
        {
            List<String> dataForObject = convertToArrayOfString(oneObject);
            String objectClass = dataForObject.get(0);
            dataForObject.remove(0);
            switch (objectClass)
            {
                case "link" :
                    objectsReference.add((Member) Link.createFromStringList(dataForObject));
                    break;
                case "file" :
                    objectsReference.add((Member) UsersFile.createFromStringList(dataForObject));
                    break;
                case "dir" :
                    objectsReference.add((Member) LogicalDirectory.createFromStringList(dataForObject));
                    break;
            }
        }

        return objectsReference;
    }

    private static String convertObjectClassToString(ISerializable object)
    {
        if(object instanceof Link)
            return "link";
        if(object instanceof UsersFile)
            return "file";
        if(object instanceof LogicalDirectory)
            return "dir";
        return "";
    }

    static String convertToString(List<String> data)
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

    private static String readFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner in = new Scanner(file);
        return in.nextLine();
    }

    static List<String> convertToArrayOfString(String input){

        List<String> data = new ArrayList<>();

        for(String oneObjectData: splitString(input))
        {
            data.add(deEscape(oneObjectData));
        }
        return data;
    }

    private static String[] splitString(String fileInput)
    {
        return removeFrames(fileInput).split("}\\{", -1);
    }

    private static String removeFrames(String rawString)
    {
        if(rawString.length() < 2)
            return rawString;
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
