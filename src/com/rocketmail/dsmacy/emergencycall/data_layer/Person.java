package com.rocketmail.dsmacy.emergencycall.data_layer;

import java.lang.String;

import android.app.Service;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class Person extends Service implements Comparable<Person>, Parcelable
{
	
	// Start at 9000 to easily differentiate between phone numbers, positions, and id's
	protected static int id = 9000;	
	protected final Uri CONTACT_PROVIDER = ContactContract.Contacts.CONTENT_URI; 
	
	protected int m_id;
	protected int m_phoneNumber;
	protected String m_name;
	protected boolean m_spawned;
	
	/**
	 * 
	 * @param name the name used to identify this person object
	 * @param number the phone number used to call this person object
	 */
	public Person (String name, int number)
	{
		m_phoneNumber = number;
		m_name = name;
		m_id = id; 
		id++;
	}
	
	/**
	 * Use this constructor when transferring data 
	 * Should be used with public "WriteParcel(Parcel)" method
	 * @param in parcel object containing the person data
	 */
	public Person (Parcel in)
	{
		m_id = in.readInt();
		m_phoneNumber = in.readInt();
		m_name = in.readString();
	}
	
	/**
	 * Add object to the database
	 */
	public abstract void spawn();
	
	/**
	 * Remove object from the database
	 */
	public void destroy()
	{
		String[] selectionArgs = new String[0];
		getContentResolver().delete(CONTACT_PROVIDER, ContactContract.Contacts.NAME + "='"+ m_name +"' AND " + 
			ContactContract.Contacts.NUMBER + "= " + m_phoneNumber, selectionArgs);
		m_spawned = false;
	}
	
	protected void addBaseContentValues(ContentValues values)
	{
		values.put(ContactContract.Contacts._ID, m_id);
		values.put(ContactContract.Contacts.NAME, m_name);
		values.put(ContactContract.Contacts.NUMBER, m_phoneNumber);
	}
	
	/**
	 * 
	 * @return the person's id
	 */
	public int getId()
	{
		return m_id;
	}
	
	/**
	 * 
	 * @return the phone number used to call this person
	 */
	public int getPhoneNumber()
	{
		return m_phoneNumber;
	}
	
	/**
	 * 
	 * @return the name used to identify this person
	 */
	public String getName() 
	{
		return m_name;
	}
	
	/**
	 * Change values of a Person object and update the database
	 * @param newName The new updated value of the contact name
	 * @param newNumber The new updated value of the contact's phone number
	 */
	public void editInfo(String newName, int newNumber)
	{
		String[] selectionArgs = new String[0];
		String where = ContactContract.Contacts.NAME + " = " + m_name + " AND " + 
			ContactContract.Contacts.NUMBER + " = " + m_phoneNumber;
		ContentValues values = new ContentValues();
		values.put(ContactContract.Contacts.NAME, newName);
		values.put(ContactContract.Contacts.NUMBER, newNumber);
		getContentResolver().update(CONTACT_PROVIDER, values, where, selectionArgs);
		m_name = newName;
		m_phoneNumber = newNumber;
	}
	
	/**
	 * Use this method to fill a Parcel that can be used to reconstruct this object
	 * @param dest the parcel object that will be filled with the proper information
	 */
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(m_id);
		dest.writeInt(m_phoneNumber);
		dest.writeString(m_name);
	}
	
	@Override
	public int compareTo(Person another)
	{
		return m_id - another.m_id;
	}
}
