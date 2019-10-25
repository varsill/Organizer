package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.mlt.Main;

class MainTest extends Main{
	
	
		@Test
		void testIdGenerator() throws Exception
		{
			 int x=Main.IdGenerator.getId(); //0
			 assertEquals(x, 0); 
			 
			 Integer y = Main.IdGenerator.getId();
			 assertEquals(y, new Integer(1));//1
			 
			 Main.IdGenerator.getId(); //2
			 Main.IdGenerator.getId();//3
			 Main.IdGenerator.freeId(x);
			 Main.IdGenerator.freeId(y);
			 Main.IdGenerator.freeId(2);
			
			 y = Main.IdGenerator.getId(); //2
			 assertEquals(y, new Integer(2));
			 
			 Main.IdGenerator.freeId(0);
			 Main.IdGenerator.freeId(x);
			 
			 y = Main.IdGenerator.getId(); //1
			 assertEquals(y, new Integer(1));
			 
			 assertThrows(Exception.class, () ->
	         { 
	        	 for(int i=0; i<=30; i++) Main.IdGenerator.getId();
	         });
				
		
	}
	
	

}
