package com.rocketmail.dsmacy.emergencycall.business_layer;

import com.rocketmail.dsmacy.emergencycall.data_layer.EmergencyContact;

import android.util.Log;

public class EmergencyContactList extends PersonList
{
	public EmergencyContactList()
	{
		super();
	}
	
	public void update()
	{
		Log.i("Fill", "update method called in Emergency Contact List");
		for (int index = 0; index < size(); index++)
			((EmergencyContact)find(index)).update();
	}
}
