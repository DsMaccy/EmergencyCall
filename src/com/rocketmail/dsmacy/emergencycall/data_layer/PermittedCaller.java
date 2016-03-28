package com.rocketmail.dsmacy.emergencycall.data_layer;

import android.os.IBinder;
import android.os.Parcel;
import android.content.ContentValues;
import android.content.Intent;

public class PermittedCaller extends Person
{
	/**
	 * 
	 * @param name the name used to identify this person object
	 * @param number the phone number used to call this person object
	 */
	public PermittedCaller(String name, int number)
	{
		super(name, number);
	}
	
	/**
	 * Use this constructor when transferring data 
	 * Should be used with public "WriteParcel(Parcel)" method
	 * @param in parcel object containing the person data
	 */
	public PermittedCaller(Parcel in)
	{
		super(in);
	}

	@Override
	public int describeContents()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	//***** Database Methods *****
	
	@Override
	public void spawn()
	{
		ContentValues values = new ContentValues();
		addBaseContentValues(values);
		values.put(ContactContract.Contacts.IS_CALLER, 1);
		values.put(ContactContract.Contacts.IS_CONTACT, 0);
		values.put(ContactContract.Contacts.HAS_PERMISSION, 0);
		getContentResolver().insert(CONTACT_PROVIDER, values);
		m_spawned = true;
	}
	@Override
	public IBinder onBind(Intent intent)
	{
		// Do not allow users to bind to this service
		return null;	
	}

}
