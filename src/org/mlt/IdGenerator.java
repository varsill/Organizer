package org.mlt;

public interface IdGenerator {
	 Integer getId() throws Exception;
	 void freeId(Integer id);
}
