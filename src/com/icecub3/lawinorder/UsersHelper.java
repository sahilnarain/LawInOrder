package com.icecub3.lawinorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "LAWINORDER";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_USERS = "USERS";

	private static final String KEY_ID = "ID";
	private static final String KEY_EMAIL = "EMAIL";
	private static final String KEY_PASSWORD = "PASSWORD";
	private static final String KEY_NAME = "NAME";
	private static final String KEY_COMPANY = "COMPANY";
	private static final String KEY_PHONE = "PHONE";

	public UsersHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_EMAIL
				+ " TEXT, " + KEY_PASSWORD + " TEXT, " + KEY_NAME + " TEXT, "
				+ KEY_COMPANY + " TEXT, " + KEY_PHONE + " TEXT" + ") ";
		db.execSQL(CREATE_USERS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String DROP_USERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_USERS;
		db.execSQL(DROP_USERS_TABLE);
		onCreate(db);
	}

	public void addUser(Users user) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EMAIL, user.getEmail());
		values.put(KEY_PASSWORD, user.getPassword());
		values.put(KEY_NAME, user.getName());
		values.put(KEY_COMPANY, user.getCompany());
		values.put(KEY_PHONE, user.getPhone());

		db.insert(TABLE_USERS, null, values);
		db.close();
	}

	public int getIdFromEmail(String email) {
		int id = -1;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID },
				KEY_EMAIL + "=?", new String[] { email }, null, null, null,
				null);

		if (cursor != null)
			cursor.moveToFirst();

		try {
			id = Integer.parseInt(cursor.getString(0));
		} catch (Exception e) {
			id = -2;
		}

		return id;
	}

	public int getIdFromEmailPass(String email, String password) {
		int id = -1;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID },
				KEY_EMAIL + "=? AND " + KEY_PASSWORD + "=?", new String[] {
						email, password }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		try {
			id = Integer.parseInt(cursor.getString(0));
		} catch (Exception e) {
			id = -2;
		}

		return id;
	}

}
