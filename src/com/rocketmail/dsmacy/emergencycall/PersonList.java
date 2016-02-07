package com.rocketmail.dsmacy.emergencycall;

import java.util.ArrayList;
import android.util.Log;

public abstract class PersonList
{
	private ArrayList<Person> m_contactsOrderedByName;
	private ArrayList<Person> m_contactsOrderedById;
	
	public PersonList()
	{
		m_contactsOrderedByName = new ArrayList<Person>();
		m_contactsOrderedById = new ArrayList<Person>();
	}
	
	//Create insert 
	public boolean insert()
	{
		// Insert to both lists
		Log.i("Fill","insert method in PersonList called");
		return false;
	}
	public boolean remove()
	{
		// Remove from both lists
		Log.i("Fill","remove method in PersonList called");
		return false;
	}
	public Person find(int id)
	{
		// Implement binary search: Cannot use the java.util method
		Log.i("Fill","find by id method in PersonList called");
		return null;
	}
	public Person find (String name)
	{
		// Implement binary search: Cannot use the java.util method
		Log.i("Fill","find by name method in PersonList called");
		return null;
	}

}
