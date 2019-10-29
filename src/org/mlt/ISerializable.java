package org.mlt;

import java.util.List;

public interface ISerializable {
	
	List<String> serialize();
	
	void deserialize(List<String> args);
	
	
	
}
