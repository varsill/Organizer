package org.mlt;

public enum SystemType {
    Windows, Linux, Mac, Other;

    public SystemType selectSystem(String systemInfo)
    {
        if(systemInfo.toLowerCase().contains("windows"))
            return Windows;
        else if(systemInfo.toLowerCase().contains("linux"))
            return Linux;
        else if (systemInfo.toLowerCase().contains("mac"))
            return Mac;
        return Other;
    }
}
