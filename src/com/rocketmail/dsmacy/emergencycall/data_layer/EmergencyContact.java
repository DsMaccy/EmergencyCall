package com.rocketmail.dsmacy.emergencycall.data_layer;

import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;
import android.content.*;

public class EmergencyContact extends Person
{
	private boolean m_seekingPermission;
	private boolean m_approved;
	
	/**
	 * 
	 * @param name the name used to identify this person object
	 * @param number the phone number used to call this person object
	 */
	public EmergencyContact(String name, int number)
	{
		super(name, number);
		m_seekingPermission = true;
		m_approved = false;
	}
	
	/**
	 * This constructor should not be accessible to others
	 * @param name the name used to identify this person object
	 * @param number the phone number used to call this person object
	 * @param seekP whether the user is actively seeking permission to use this EmergencyContact
	 * @param approved whether the user has been given permission to use this EmergencyContact
	 */
	private EmergencyContact(String name, int number, boolean seekP, boolean approved)
	{
		super(name, number);
		m_seekingPermission = seekP;
		m_approved = approved;
	}
	
	/**
	 * Use this constructor when transferring data 
	 * Should be used with public "WriteParcel(Parcel)" method
	 * @param in parcel object containing the person data
	 */
	public EmergencyContact(Parcel in)
	{
		super(in);
		
		if (in.readInt() == 1)
		{
			m_seekingPermission = true;
		}
		else
		{
			m_seekingPermission = false;
		}
		if (in.readInt() == 1)
		{
			m_approved = true;
		}
		else
		{
			m_approved = false;
		}
	}
	
	public void update()
	{
		// TODO Fill w/ authentication: spam corresponding # for permission
		Log.i("Fill", "update method of Emergency Contact called");
	}

	@Override
	public int describeContents()
	{
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		super.writeToParcel(dest, flags);
		if (m_seekingPermission)
		{
			dest.writeInt(1);
		}
		else
		{
			dest.writeInt(0);
		}
		if (m_approved)
		{
			dest.writeInt(1);
		}
		else
		{
			dest.writeInt(0);
		}
	}

	//***** Database Methods *****

	@Override
	public void spawn()
	{
		ContentValues values = new ContentValues();
		addBaseContentValues(values);
		values.put(ContactContract.Contacts.IS_CALLER, 0);
		values.put(ContactContract.Contacts.IS_CONTACT, 1);
		values.put(ContactContract.Contacts.HAS_PERMISSION, 1);
		getContentResolver().insert(CONTACT_PROVIDER, values);
		m_spawned = true;
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		// Do not allow users to bind to this services
		return null;
	}
}
