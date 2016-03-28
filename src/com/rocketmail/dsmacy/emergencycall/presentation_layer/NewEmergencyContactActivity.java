package com.rocketmail.dsmacy.emergencycall.presentation_layer;

import com.rocketmail.dsmacy.emergencycall.R;
import com.rocketmail.dsmacy.emergencycall.data_layer.EmergencyContact;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class NewEmergencyContactActivity extends Activity 
{	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_emergency_contact);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_emergency_contact, menu);
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
	
	// *** BUTTON METHODS ***
	public void submit (View v)
	{
		Log.i("Fill", "Submit method in New Emergency Contact Activity called");
		EditText nameField = (EditText)findViewById(R.id.NewPermittedCallerName);
		EditText phoneField = (EditText)findViewById(R.id.NewPermittedCallerNumber);
		EmergencyContact newContact = new EmergencyContact(
			nameField.getText().toString(), 
			Integer.parseInt(phoneField.getText().toString()));
		((ListOverviewActivity)this.getBaseContext()).addNewEmergencyContact(newContact);
		super.onBackPressed();
		
	}
	public void clear(View v)
	{
		Log.i("Fill", "Clear method in New Emergency Contact Activity called");
		EditText nameField = (EditText)findViewById(R.id.NewPermittedCallerName);
		EditText phoneField = (EditText)findViewById(R.id.NewPermittedCallerNumber);
		nameField.setText("");
		phoneField.setText("");
	}
	public void cancel(View v)
	{
		Log.i("Fill", "Cancel method in New Emergency Contact Activity called");
		super.onBackPressed();
	}
}
