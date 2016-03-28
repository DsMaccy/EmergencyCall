package com.rocketmail.dsmacy.emergencycall.presentation_layer;

import com.rocketmail.dsmacy.emergencycall.R;
import com.rocketmail.dsmacy.emergencycall.data_layer.EmergencyContact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewEmergencyContactActivity extends Activity 
{
	public static final String PARCEL_TAG = "contact";
	
	private EmergencyContact m_contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.i("Fill", "onCreate method in ViewEmergencyContact Activity");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_emergency_contact);
		
		Bundle extra = this.getIntent().getExtras();
		m_contact = (EmergencyContact)(extra.getParcelable(PARCEL_TAG));
		((TextView)findViewById(R.id.ViewEmergencyContactName)).setText(m_contact.getPhoneNumber());
		((TextView)findViewById(R.id.ViewEmergencyContactNumber)).setText(m_contact.getName().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_emergency_contact, menu);
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
	
	public void edit(View v)
	{
		Log.i("Fill", "Edit method in View Emergency Contact called");
		Intent editContact = new Intent(this, EditContactActivity.class);
		editContact.putExtra(EditContactActivity.CONTACT_TAG, m_contact);
		startActivity(editContact);
	}
	public void call(View v)
	{
		Log.i("Fill", "Call method in View Emergency Contact called");
	}
	
}
