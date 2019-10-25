package org.mlt;

public class Id extends Object {
	
	private Integer id_num;
	private final IdGenerator generator;
	//Constructors:
	
	private Id()
	{
		id_num=-1;
		generator=null;
	}
	
	public Id(IdGenerator gen)
	{
		generator=gen;
		Integer id;
		try
		{
			id = gen.getId();
		}
		catch(Exception e)
		{
			this.id_num=-1;
			return;//couldn't create id with given generator
		}
		
		this.id_num=id;
		
	}
	
	
	//Methods:
	public Integer readAsInteger()
	{
		if(this.id_num.equals(-1))//id_num hasn't been set
		{
			return null;
		}
		else return new Integer(this.id_num);//returning a copy because we don't want to have id_num unauthorizedly modified
	}
	
	public void release()
	{
		generator.freeId(this.id_num);
		this.id_num=new Integer(-1); 
	}


	
}
