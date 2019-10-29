package org.mlt;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	public static void main(String args[])
	{
		
		
		
		
		
	}
	
	public static class MainIdGenerator implements IdGenerator
	{
		 private static final int MAX_NO_OF_IDS = 30; 
		 private static int offset = 0;
		 private static LinkedList<Integer> ids;
		//Singleton implementation
		
		 private MainIdGenerator()
		 {
			offset = 0;
		    ids = new LinkedList<Integer>(); 
		 } 
	    
	    private static class SingletonHelper
	    {
	        private static final MainIdGenerator INSTANCE = new MainIdGenerator();
	    }
	    
	    /**
	     * 
	     * @return instance of singleton object of class MainIdGenerator
	     */
	    public static MainIdGenerator getInstance(){
	        return SingletonHelper.INSTANCE;
	    }
	    
		

		//Methods
		@Override
		public Integer getId() throws Exception
		{
			Integer result = ids.pollFirst();//returns null if the list is empty
			if(result==null)
			{
				if(offset == MAX_NO_OF_IDS)
					throw new Exception("Error. There are no free ids left. ");
			
				result=offset;
				offset=offset+1;
			}
			return result; 
			
		}
		
		
		@Override
		public void freeId(Integer id)
		{
			if(id>=offset)return;
			int index = ids.indexOf(id);
			if(index!=-1)return;//number is already on the ids list

			ids.addFirst(id);
			
		}
		
		private void optimize()
		{
			Collections.sort(ids);
			int x = ids.getLast();
			while(offset==x)
			{
				offset=offset-1;
				x=ids.poll();
			}
		}



		@Override
		public List<String> serialize() {
			List<String> result = new LinkedList<String>();
			result.add(new Integer(offset).toString());
			for(Integer i: ids)
			{
				result.add(i.toString());
			}
			return result;
		}



		@Override
		public void deserialize(List<String> args) {
			offset = Integer.valueOf(args.get(0));
			for(int i=1; i<args.size(); i++)
			{
				ids.add(Integer.valueOf(args.get(i)));
			}
			
		}



		@Override
		public boolean isOccupied(Integer id) {
			if(id>=offset) return false;
			int index = ids.indexOf(id);
			if(index==-1)return false;//number is already on the ids list
			return true;
		}
		
			
		
	}
}
