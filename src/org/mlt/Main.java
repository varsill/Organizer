package org.mlt;

import java.util.LinkedList;

public class Main {
	
	public static void main(String args[])
	{
		
		
		
		
		
	}
	
	protected static class MainIdGenerator implements IdGenerator
	{
		 private static final int MAX_NO_OF_IDS = Integer.MAX_VALUE; 
		 private static int offset = 0;
		//Singleton implementation
		
		 private MainIdGenerator()
		 {
			
		 } 
	    
	    private static class SingletonHelper{
	        private static final MainIdGenerator INSTANCE = new MainIdGenerator();
	    }
	    
	    /**
	     * 
	     * @return instance of singleton object of class MainIdGenerator
	     */
	    public static MainIdGenerator getInstance(){
	        return SingletonHelper.INSTANCE;
	    }
	    
		
	    //MainIdGenerator Initialization
	   
		
		@SuppressWarnings("serial")
		private static LinkedList<Integer> ids = new LinkedList<Integer>();
		
		
		//Methods
		@Override
		public Integer getId() throws Exception
		{
			Integer result = ids.pollFirst();//returns null if the list is empty
			if(result==null) throw new Exception("Error. There are no free ids left. ");
			return result; 
			
		}
		
		
		@Override
		public void freeId(Integer id)
		{
			int index = ids.indexOf(id);
			if(index!=-1)return;//number is already on the ids list
			ids.addFirst(id);
		}
		
			
		
	}
}
