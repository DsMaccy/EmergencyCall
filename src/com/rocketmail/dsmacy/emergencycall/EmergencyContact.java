package com.rocketmail.dsmacy.emergencycall;

import android.util.Log;

public class EmergencyContact extends Person
{
	private boolean m_seekingPermission;
	private boolean m_approved;
	
	
	public EmergencyContact(String name, int number)
	{
		super(name, number);
		m_seekingPermission = true;
		m_approved = false;
	}
	
	public EmergencyContact(String name, int number, boolean seekP, boolean approved)
	{
		super(name, number);
		m_seekingPermission = seekP;
		m_approved = approved;
	}
	
	public void update()
	{
		// Fill w/ authentication: spam corresponding # for permission
		Log.i("Fill", "update method of Emergency Contact called");
	}
}
