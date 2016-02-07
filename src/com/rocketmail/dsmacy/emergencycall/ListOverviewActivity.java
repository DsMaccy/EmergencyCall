package com.rocketmail.dsmacy.emergencycall;


import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ListOverviewActivity extends Activity
{
	
	//TODO: change these to take their respective object types
	private EmergencyContactList emergencyContacts;
	private PermittedCallerList permittedCallers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_overview);
		//EmergencyContactList = new ArrayList<...>();
		//PermittedCallerList = new ArrayList<...>();
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//***** Button Click Methods *****
	public void addNewPermittedCaller(View v)
	{
		Log.i("Fill", "Add New Permitted Caller method");
		NewPermittedCallerActivity anpca = new NewPermittedCallerActivity();
		
	}
	public void addNewEmergencyContact(View v)
	{
		Log.i("Fill", "Add New Emergency Contact method");
		NewEmergencyContactActivity anpca = new NewEmergencyContactActivity();
	}
	public void viewPermittedCaller()
	{
		Log.i("Fill", "View Permitted Caller method");
		ViewPermittedCallerActivity anpca = new ViewPermittedCallerActivity();
	}
	public void viewEmergencyContact()
	{
		Log.i("Fill", "View Emergency Contact method");
		ViewEmergencyContactActivity anpca = new ViewEmergencyContactActivity();
	}
	public void helpView()
	{
		Log.i("Fill", "Help View method");
		/* Create popup to explain:
		 *   emergency contact
		 *   permitted caller
		 *   which list is which
		 */ 
	}
}
