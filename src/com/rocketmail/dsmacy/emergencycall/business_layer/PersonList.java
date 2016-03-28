package com.rocketmail.dsmacy.emergencycall.business_layer;

import java.util.ArrayList;

import com.rocketmail.dsmacy.emergencycall.data_layer.Person;

import android.util.Log;

public abstract class PersonList
{
	private ArrayList<Person> m_contactsOrderedByName;
	private ArrayList<Person> m_contactsOrderedById;
	private ArrayList<Person> m_contactsOrderedByNumber;
	
	public PersonList()
	{
		m_contactsOrderedByName = new ArrayList<Person>();
		m_contactsOrderedById = new ArrayList<Person>();
		
		// TODO: fill list w/ sql database?
	}
	
	// ***** Public Methods ***** 

	/**
	 * Insert a new Person into the PersonList --
	 * NOTE: This calls spawn for Person p
	 * @param p the new Person object being added to the PersonList
	 * @return true if p was inserted successfully
	 */
	public boolean insert(Person p)
	{
		boolean success = addToLists(p);
		if (success)
		{
			p.spawn();
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Remove a Person from the PersonList
	 * @param p the Person being removed from the PersonList
	 * @return true if p was removed successfully
	 */
	public boolean remove(Person p)
	{
		p.destroy();
		this.m_contactsOrderedById.remove(p);
		this.m_contactsOrderedByName.remove(p);
		this.m_contactsOrderedByNumber.remove(p);
		return true;
	}

	public Person find(int identifier)
	{
		Log.i("Fill","find by id method in PersonList called");
		
		int start = 0;
		int end = size(); // all lists should have the same size
		int mid = start/2 + end/2;
		if (identifier >= 1000000000)	// Value is a phone number
		{
			while (mid >= start && mid < end)
			{
				if (m_contactsOrderedByNumber.get(mid).getPhoneNumber() == identifier)
				{ return m_contactsOrderedByNumber.get(mid); }
				else if  (m_contactsOrderedByNumber.get(mid).getPhoneNumber() > identifier)
				{ start = mid; }
				else
				{ end = mid; }
				
				mid = start/2 + end/2;
			}
		}
		else if (identifier >= 9000)	// Value is an id tag
		{
			while (mid >= start && mid < end)
			{
				if (m_contactsOrderedById.get(mid).getId() == identifier)
				{ return m_contactsOrderedById.get(mid); }
				else if  (m_contactsOrderedById.get(mid).getId() > identifier)
				{ start = mid; }
				else
				{ end = mid; }
				
				mid = start/2 + end/2;
			}
		}
		else // Value is a position
		{
			return m_contactsOrderedByName.get(identifier);
		}
		
		return null;
	}
	public Person find (String name)
	{
		int compare = 0;
		int start = 0;
		int end = size(); // all lists should have the same size
		int mid = start/2 + end/2;
		while (mid >= start && mid < end)
		{
			compare = name.compareTo(m_contactsOrderedByName.get(mid).getName());
			if (compare == 0)
			{ return m_contactsOrderedByName.get(mid); }
			else if  (compare > 0)
			{ start = mid; }
			else
			{ end = mid; }
			
			mid = start/2 + end/2;
		}
		return null;
	}
	
	public int size()
	{
		return this.m_contactsOrderedById.size();
	}
	
	//***** Protected Interface Methods for Sub-Classes *****
	
	/**
	 * Insert a person into each of the super class lists in sorted order
	 * @param p the person being added to each list
	 */
	protected boolean addToLists (Person p)
	{
		if (this.find(p.getId()) != null)
		{
			throw new IllegalStateException(); // return false;
		}
		if (this.find(p.getName()) != null || find(p.getPhoneNumber()) != null)
		{
			return false;
		}
		
		insertToNameList(p);
		insertToIdList(p);
		insertToNumberList(p);
		
		return true;
	}
	
	/**
	 * Remove a person from the every list
	 * @param p the object being removed from all of the lists
	 */
	protected void removeFromLists (Person p)
	{
		m_contactsOrderedByName.remove(p);
		m_contactsOrderedById.remove(p);
		m_contactsOrderedByNumber.remove(p);
	}
	
	//***** Private Helper Methods *****
	
	private void insertToNameList (Person p)
	{
		int insertIndex = 0;
		while (insertIndex < m_contactsOrderedByName.size() && 
			p.getName().compareTo(m_contactsOrderedByName.get(insertIndex).getName()) > 0)
		{
			insertIndex++;
		}
		m_contactsOrderedByName.add(insertIndex, p);
	}
	private void insertToIdList (Person p)
	{
		int insertIndex = 0;
		while (insertIndex < m_contactsOrderedById.size() && 
			p.getId() > m_contactsOrderedById.get(insertIndex).getId())
		{
			insertIndex++;
		}
		m_contactsOrderedById.add(insertIndex, p);
	}
	private void insertToNumberList (Person p)
	{
		int insertIndex = 0;
		while (insertIndex < m_contactsOrderedByNumber.size() && 
			p.getPhoneNumber() > m_contactsOrderedByNumber.get(insertIndex).getPhoneNumber())
		{
			insertIndex++;
		}
		m_contactsOrderedByNumber.add(insertIndex, p);
	}

}
