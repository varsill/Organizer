package org.mlt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.mlt.ISerializable.*;

public class Serializer {
    private static char[] escapeChars = {'/', '{', '}'};
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

    public static List<Member> recoverObjects(String fileName) throws FileNotFoundException {
        List<Member> objectsReference = new ArrayList<>();
        Member oneMember = null;
        List<String> allObjects = convertToArrayOfString(readFromFile(fileName));

        for(String oneObject: allObjects)
        {
            List<String> dataForObject = convertToArrayOfString(oneObject);
            String objectClass = dataForObject.get(0);
            dataForObject.remove(0);
            switch (objectClass)
            {
                case "link" :
                    //oneMember = new Link();
                    oneMember = (Member) Link.createFromStringList(dataForObject);
                   
            }
            objectsReference.add(oneMember);
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
            return "dic";
        return "";
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

    private static String readFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner in = new Scanner(file);
        return in.nextLine();
    }

    private static List<String> convertToArrayOfString(String input){

        List<String> data = new ArrayList<>();

        for(String oneObjectData: splitString(input))
        {
            data.add(deEscape(oneObjectData));
        }
        return data;
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
