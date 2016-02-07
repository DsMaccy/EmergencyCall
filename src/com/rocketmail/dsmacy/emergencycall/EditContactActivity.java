package com.rocketmail.dsmacy.emergencycall;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class EditContactActivity extends Activity 
{

	private Person m_contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
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
	}
	public void cancel(View v)
	{
		Log.i("Fill", "Cancel method in Edit Contact Activity called");
	}
	public void delete(View v)
	{
		Log.i("Fill", "Delete method in Edit Contact Activity called");
	}
}
