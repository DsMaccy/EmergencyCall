package com.rocketmail.dsmacy.emergencycall.data_layer;

import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

// TODO: Implement (look at custom content provider example 
//	in study abroad -- android folder
public class ContactContentProvider extends ContentProvider
{
	private static final int CONTACT_LIST = 1;
	private static final int CONTACT_ID = 2;
	
	private static final UriMatcher uri_matcher;
	static
	{
		uri_matcher = new UriMatcher(UriMatcher.NO_MATCH);
		uri_matcher.addURI(ContactContract.AUTHORITY, ContactContract.Contacts.PATH, CONTACT_LIST);
		uri_matcher.addURI(ContactContract.AUTHORITY, ContactContract.Contacts.PATH + "/#", CONTACT_ID);
	}
	
	private static String LOG_TAG = "DATABASE";
	
	// database
	private SQLiteDatabase contact_db;
	
	private static final String DATABASE_NAME = "books";
	private static final String DATABASE_TABLE = "titles";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = 
			"create table " + DATABASE_TABLE + 
			" (" + ContactContract.Contacts._ID + 
			" integer primary key autoincrement, " +
			ContactContract.Contacts.NAME + " text not null, " +
			ContactContract.Contacts.NUMBER + " int not null," +
			ContactContract.Contacts.IS_CONTACT + "int not null," + 
			ContactContract.Contacts.IS_CALLER + "int not null," + 
			ContactContract.Contacts.HAS_PERMISSION + "int not null);";
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(DATABASE_CREATE);	
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			Log.w(LOG_TAG, "upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2)
	{
		// TODO Auto-generated method stub
		
		int count = 0;
		switch(uri_matcher.match(arg0))
		{
		case CONTACT_LIST:
			count = contact_db.delete(DATABASE_TABLE, arg1, arg2);
			break;
		
		case CONTACT_ID:
			String id = arg0.getPathSegments().get(1);
			count = contact_db.delete(DATABASE_TABLE, ContactContract.Contacts._ID + " = " + id + (!TextUtils.isEmpty(arg1) ? " AND (" + arg1 + ")" : ""), arg2);
			break;
			
		default:
			throw new IllegalArgumentException("unknown uri" + arg0);
		}
		getContext().getContentResolver().notifyChange(arg0, null);
		return count;
	}

	@Override
	public String getType(Uri arg0)
	{
		switch(uri_matcher.match(arg0))
		{
			case CONTACT_LIST:
				return ContactContract.Contacts.CONTENT_DIR_TYPE;
			
			case CONTACT_ID:
				return ContactContract.Contacts.CONTENT_ITEM_TYPE;
				
			default:
				throw new IllegalArgumentException("unsupported uri: " + arg0);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values)
	{
		// add a new book
		long row_id = contact_db.insert(DATABASE_TABLE, "", values);
		
		if(row_id > 0)
		{
			Uri _uri = ContentUris.withAppendedId(ContactContract.Contacts.CONTENT_URI, row_id);
			getContext().getContentResolver().notifyChange(_uri, null);
			return _uri;
		}
		else
		{
			throw new SQLException("failed to insert row into " + uri);
		}
	}

	@Override
	public boolean onCreate()
	{
		Context context = getContext();
		DatabaseHelper db_helper = new DatabaseHelper(context);
		contact_db = db_helper.getWritableDatabase();
		
		return (contact_db == null ? false : true);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
		String sortOrder)
	{
		SQLiteQueryBuilder sql_builder = new SQLiteQueryBuilder();
		sql_builder.setTables(DATABASE_TABLE);
		
		if(uri_matcher.match(uri) == CONTACT_ID)
		{
			sql_builder.appendWhere(ContactContract.Contacts._ID + " = " + uri.getPathSegments().get(1));
		}
		
		if(sortOrder == null || sortOrder == "")
		{
			sortOrder = ContactContract.Contacts.NAME;
		}
		
		Cursor cursor = sql_builder.query(contact_db, projection, selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
	{
		int count = 0;
		
		switch(uri_matcher.match(uri))
		{
		case CONTACT_LIST:
			count = contact_db.update(DATABASE_TABLE, values, selection, selectionArgs);
			break;
		
		case CONTACT_ID:
			count = contact_db.update(
					DATABASE_TABLE,
					values,
					ContactContract.Contacts._ID + " = " + uri.getPathSegments().get(1) + 
					(!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""),
					selectionArgs);
		
			default:
				throw new IllegalArgumentException("unknown uri: " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
