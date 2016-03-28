package com.rocketmail.dsmacy.emergencycall.presentation_layer;

import com.rocketmail.dsmacy.emergencycall.R;
import com.rocketmail.dsmacy.emergencycall.data_layer.PermittedCaller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ViewPermittedCallerActivity extends Activity 
{
	public static final String PARCEL_TAG = "contact";
	
	private PermittedCaller m_contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.i("Fill", "onCreate method in ViewPermittedCaller Activity");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_permitted_caller);
		
		Bundle extra = this.getIntent().getExtras();
		m_contact = (PermittedCaller)(extra.getParcelable(PARCEL_TAG));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_permitted_caller, menu);
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
		Log.i("Fill", "Edit method in View Permitted Caller called");
		Intent editContact = new Intent(this, EditContactActivity.class);
		editContact.putExtra(EditContactActivity.CONTACT_TAG, m_contact);
		startActivity(editContact);
	}
}
