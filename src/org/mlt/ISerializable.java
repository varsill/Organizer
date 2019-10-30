package org.mlt;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ISerializable {
	
	
	List<String> serialize();
	void deserialize(List<String> args);
	
	
	
}
