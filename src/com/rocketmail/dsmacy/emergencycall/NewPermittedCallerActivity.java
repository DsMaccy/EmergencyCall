package com.rocketmail.dsmacy.emergencycall;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class NewPermittedCallerActivity extends ActionBarActivity 
{

	private PermittedCaller m_contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_permitted_caller);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_permitted_caller, menu);
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
	
	
	public void submit (View v)
	{
		Log.i("Fill", "Submit method in New Emergency Contact Activity called");
	}
	public void clear(View v)
	{
		Log.i("Fill", "Clear method in New Emergency Contact Activity called");
	}
	public void cancel(View v)
	{
		Log.i("Fill", "Cancel method in New Emergency Contact Activity called");
	}
}
