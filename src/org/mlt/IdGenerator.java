package org.mlt;

public interface IdGenerator {
	
	 /** 
	  * 
	
	  * @return generated Id as Integer
	  * @throws Exception - when Id cannot be generated
	  */
	 Integer getId() throws Exception;
	 /**
	  * 
	  * @param id - Integer value of Id to be realised
	  */
	 void freeId(Integer id);
}
