package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.mlt.IdGenerator;
import org.mlt.Main;

class MainTest extends Main{
	
	
		@Test
		public void testIdGenerator() throws Exception
		{
			 IdGenerator generator = Main.MainIdGenerator.getInstance();
			 int x=generator.getId(); //0
			 assertEquals(x, 0); 
			 
			 Integer y = generator.getId();
			 assertEquals(y, new Integer(1));//1
			 
			 generator.getId(); //2
			 generator.getId();//3
			 generator.freeId(x);
			 generator.freeId(y);
			 generator.freeId(2);
			
			 y = generator.getId(); //2
			 assertEquals(y, new Integer(2));
			 
			 generator.freeId(0);
			 generator.freeId(x);
			 
			 y = generator.getId(); //1
			 assertEquals(y, new Integer(1));
			 
			 assertThrows(Exception.class, () ->
	         { 
	        	 for(int i=0; i<32; i++) generator.getId();
	         });
			
		
	}
	
	

}
