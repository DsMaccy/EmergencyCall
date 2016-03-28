package com.rocketmail.dsmacy.emergencycall.presentation_layer;

import com.rocketmail.dsmacy.emergencycall.R;
import com.rocketmail.dsmacy.emergencycall.R.*;
import com.rocketmail.dsmacy.emergencycall.data_layer.Person;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditContactActivity extends Activity 
{
	public static final String CONTACT_TAG = "contact";

	private Person m_contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		m_contact = getIntent().getExtras().getParcelable(CONTACT_TAG);
		((EditText)findViewById(R.id.EditContactName)).setText(m_contact.getName());
		((EditText)findViewById(R.id.EditContactNumber)).setText(m_contact.getPhoneNumber());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_contact, menu);
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
	
	public void save(View v)
	{
		Log.i("Fill", "Save method in Edit Contact Activity called");
		String newName = ((EditText)findViewById(R.id.EditContactName)).getText().toString();
		int newNumber = Integer.parseInt(((EditText)findViewById(R.id.EditContactNumber)).toString());
		m_contact.editInfo(newName, newNumber);
		super.onBackPressed();
	}
	public void cancel(View v)
	{
		Log.i("Fill", "Cancel method in Edit Contact Activity called");
		super.onBackPressed();
	}
	public void delete(View v)
	{
		Log.i("Fill", "Delete method in Edit Contact Activity called");
		// TODO: move this to the View classes
	}
}
