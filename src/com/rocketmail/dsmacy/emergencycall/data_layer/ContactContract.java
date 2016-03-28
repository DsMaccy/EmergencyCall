package com.rocketmail.dsmacy.emergencycall.data_layer;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContactContract
{
	public static final String AUTHORITY = "com.rocketmail.dsmacy.emergencycall";
	
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	
	public static final class Contacts implements BaseColumns 
	{

		public static final String PATH = "contacts";

		public static final Uri CONTENT_URI = Uri.withAppendedPath(
				ContactContract.CONTENT_URI, PATH);
		
		public static final String CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + AUTHORITY + PATH;
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + AUTHORITY + PATH;

		// Columns
		public static final String NAME = "name";
		public static final String NUMBER= "phone_number";
		public static final String IS_CONTACT = "is_contact";
		public static final String IS_CALLER = "is_caller";
		public static final String HAS_PERMISSION = "has_permission";
		
		public static final String[] PROJECTION_ALL = {_ID, _COUNT, NAME, NUMBER, IS_CONTACT, IS_CALLER, HAS_PERMISSION};
	      
		public static final String SORT_ORDER_DEFAULT = _ID + " ASC";
	}
}
