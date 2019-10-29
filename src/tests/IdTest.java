package tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

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

		@Override
		public List<String> serialize() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void deserialize(List<String> args) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isOccupied(Integer id) {
			// TODO Auto-generated method stub
			return false;
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
