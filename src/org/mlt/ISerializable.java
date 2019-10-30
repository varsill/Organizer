package org.mlt;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ISerializable {
	
	 public static ISerializable createFromStringList(List<String> args)
	    {
	    	ISerializable result=null;
	    	try {
				 result = (ISerializable) MethodHandles.lookup().lookupClass().getConstructor().newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	result.deserialize(args);
			return result;
	    }

	
	
	List<String> serialize();
	void deserialize(List<String> args);
	
	
	
}
