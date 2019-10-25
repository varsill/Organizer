package org.mlt;

public class Id extends Object {
	
	private Integer id_num;
	private final IdGenerator generator;
	//Constructors:
	/**
	 * disabled constructor
	 */
	private Id()
	{
		id_num=-1;
		generator=null;
	}
	/**
	 * The only available constructor.
	 * If  generator cannot generate numerical id then id_num is set to -1.
	 * @param generator - object to get numerical id generated from
	 * 
	 */
	public Id(IdGenerator generator)
	{
		this.generator=generator;
		Integer id;
		try
		{
			id = generator.getId();
		}
		catch(Exception e)
		{
			this.id_num=-1;
			return;//couldn't create id with given generator
		}
		
		this.id_num=id;
		
	}
	
	
	//Methods:
	/**
	 * If id_num is not set (for example due to generator failure) then function will return null value
	 * @return numerical representation of id. 
	 */
	public Integer readAsInteger()
	{
		if(this.id_num.equals(-1))//id_num hasn't been set
		{
			return null;
		}
		else return new Integer(this.id_num);//returning a copy because we don't want to have id_num unauthorizedly modified
	}
	/**
	 * releases id in generator linked with this id
	 */
	public void release()
	{
		generator.freeId(this.id_num);
		this.id_num=new Integer(-1); 
	}


	
}
