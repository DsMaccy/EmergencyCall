package com.rocketmail.dsmacy.emergencycall.presentation_layer;


import com.rocketmail.dsmacy.emergencycall.R;
import com.rocketmail.dsmacy.emergencycall.business_layer.EmergencyContactList;
import com.rocketmail.dsmacy.emergencycall.business_layer.PermittedCallerList;
import com.rocketmail.dsmacy.emergencycall.data_layer.EmergencyContact;
import com.rocketmail.dsmacy.emergencycall.data_layer.PermittedCaller;
import com.rocketmail.dsmacy.emergencycall.data_layer.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;

public class ListOverviewActivity extends Activity
{
	private EmergencyContactList m_emergencyContacts;
	private PermittedCallerList m_permittedCallers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_overview);
		
		//EmergencyContactList = new ArrayList<...>();
		//PermittedCallerList = new ArrayList<...>();
		
		ListView permittedCallerList = (ListView) this.findViewById(R.id.PermittedCallersList);
		ListView emergencyContactList = (ListView) this.findViewById(R.id.EmergencyCallerList);
		
		permittedCallerList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
							int position, long id)
			{
				viewPermittedCaller(position);
				// TODO Auto-generated method stub
				return false;
			}
				
		});
		
		emergencyContactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
							int position, long id)
			{
				viewEmergencyContact(position);
				// TODO Auto-generated method stub
				return false;
			}
				
		});
		
		TabHost tabs = (TabHost)(findViewById(android.R.id.tabhost));
		
		tabs.newTabSpec("Permitted Callers");
		tabs.newTabSpec("Emergency Contacts");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_overview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//***** Interface Methods to Respond to Other Classes *****
	
	/**
	 * Add an object to the PermittedCallerList object being kept track of by this activity -- 
	 * NOTE: this will add the permitted caller to the database
	 * @param pc the PermittedCaller object being added
	 */
	public void addNewPermittedCaller(PermittedCaller pc)
	{
		Log.i("Fill", "Add New Permitted Caller method");
		m_permittedCallers.insert(pc);
	}
	
	/**
	 * Add an object to the EmergencyContactList object being kept track of by this activity -- 
	 * NOTE: this will add the emergency contact to the database
	 * @param ec the EmergencyContact object being added
	 */
	public void addNewEmergencyContact(EmergencyContact ec)
	{
		Log.i("Fill", "Add New Emergency Contact method");
		m_emergencyContacts.insert(ec);
	}
	
	/**
	 * Change a person's information that is contained within the list
	 * @param oldPerson The information for the previous object 
	 * @param newPerson The information for the new object
	 * @param emergencyContact True if the person objects are emergencyContacts 
	 * and false if they are permittedCallers
	 * @return true if edit is successful
	 */
	public boolean editInfo(Person oldPerson, String newName, int newNumber, boolean emergencyContact) throws Exception
	{
		Log.i("Fill", "edit info method in ListOverviewActivity called");
		return false;
		/*
		Log.i("Fill", "Edit Info method called");
		if (emergencyContact)
		{
			boolean success = m_emergencyContacts.insert(newPerson);
			if (success)
			{
				m_emergencyContacts.remove(oldPerson);
				return true;
			}
			else return false;
		}
		else
		{
			boolean success = m_permittedCallers.insert(newPerson);
			if (success)
			{
				m_permittedCallers.remove(oldPerson);
				return true;
			}
			else return false;
		}*/
	}
	
	
	//***** Button Click Methods *****
	
	/**
	 * Start a new Activity to create a new PermittedCaller object
	 * @param v This parameter is used to make this method compatible for button clicks but is otherwise unused
	 */
	public void addNewPermittedCaller(View v)
	{
		Log.i("Fill", "Add New Permitted Caller method");
		Intent addNewPermittedCallerIntent = new Intent(this, NewPermittedCallerActivity.class);
		
		startActivity(addNewPermittedCallerIntent);
	}
	
	/**
	 * Start a new Activity to create a new EmergencyContact object
	 * @param v This parameter is used to make this method compatible for button clicks but is otherwise unused
	 */
	public void addNewEmergencyContact(View v)
	{
		Log.i("Fill", "Add New Emergency Contact method");
		Intent addNewEmergencyContact = new Intent(this, NewEmergencyContactActivity.class);
		startActivity(addNewEmergencyContact);
	}
	
	/**
	 * Open the ViewPermittedCaller Activity -- permitted caller bundled under the tag "contact"
	 * @param position either the location in the list, the id of the contact, or the contact's phone number
	 */
	public void viewPermittedCaller(int position)
	{
		Log.i("Fill", "View Permitted Caller method");
		Intent viewPermittedCaller = new Intent(this, ViewPermittedCallerActivity.class);
		
		Person pc = m_permittedCallers.find(position);
		viewPermittedCaller.putExtra(ViewPermittedCallerActivity.PARCEL_TAG, pc);
		
		startActivity(viewPermittedCaller);
	}
	
	/**
	 * Open the ViewEmergencyContact Activity -- permitted caller bundled under the tag "contact"
	 * @param position either the location in the list, the id of the contact, or the contact's phone number
	 */
	public void viewEmergencyContact(int position)
	{
		Log.i("Fill", "View Emergency Contact method");
		Intent viewEmergencyContact = new Intent(this, ViewEmergencyContactActivity.class);
		
		Person ec = m_emergencyContacts.find(position);
		viewEmergencyContact.putExtra(ViewEmergencyContactActivity.PARCEL_TAG, ec);
		
		startActivity(viewEmergencyContact);
	}
	
	public void helpView()
	{
		//TODO: Complete
		Log.i("Fill", "Help View method");
		/* Create popup to explain:
		 *   emergency contact
		 *   permitted caller
		 *   which list is which
		 */ 
	}
}
