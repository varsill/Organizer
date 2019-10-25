package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mlt.Identifier;
import org.mlt.IdGenerator;

public class IdTest {
	private class MyGenerator implements IdGenerator
	{

		@Override
		public Integer getId() throws Exception {
			
			return 2;
			
		}

		@Override
		public void freeId(Integer id) {
			return;
			
		}
		
	}
	@Test
	public void test()
	{
		MyGenerator gen = new MyGenerator();
		Identifier x = new Identifier(gen);
		assertEquals(x.readAsInteger(), new Integer(2));
		Integer i = x.readAsInteger();
		i=new Integer(3);
		assertEquals(x.readAsInteger(), new Integer(2));
	}
}
