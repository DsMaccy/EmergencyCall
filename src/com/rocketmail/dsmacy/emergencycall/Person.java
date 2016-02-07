package com.rocketmail.dsmacy.emergencycall;

import java.lang.String;

public abstract class Person implements Comparable<Person>
{
	
	protected static int id = 0;
	
	protected int m_id;
	protected int m_phoneNumber;
	protected String m_name;
	
	public Person (String name, int number)
	{
		m_phoneNumber = number;
		m_name = name;
		m_id = id; 
		id++;
	}
	
	public int getPhoneNumber()
	{
		return m_phoneNumber;
	}
	
	public String getName() 
	{
		return m_name;
	}
	
	@Override
	public int compareTo(Person another)
	{
		return m_id - another.m_id;
	}
	
	
}
