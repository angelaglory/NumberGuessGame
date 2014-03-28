package com.android.numberguessgame;

import java.util.HashMap;

import com.android.numberguessgame.Score.scores;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class NumberContentProvider extends ContentProvider {

	public static final String AUTHORITY = "com.android.numberguessgame.numbercontentprovider";
	private static final UriMatcher URI_MATCHER;
	private static final int SCORES = 1;
	private static HashMap<String, String> scoresMap;
	
	private DatabaseHelper dbHelper;
	
	static{
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(AUTHORITY, scores.TABLE,SCORES);
		scoresMap = new HashMap<String, String>();
		scoresMap.put(scores._ID, scores._ID);
		scoresMap.put(scores.NAME, scores.NAME);
		scoresMap.put(scores.RANGE, scores.RANGE);
		scoresMap.put(scores.SCORE, scores.SCORE);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		private static final int DATABASE_VERSION = 1;
		private static final String DATABASE_NAME = "scores.db";
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			createScoreTable(db);
		}
		
		private void createScoreTable(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE" + scores.TABLE + "(" +
					  scores._ID + "INTEGER PRIMARTY KEY autoincrement,"+
					  scores.NAME + "TEXT, " + 
					  scores.RANGE + "INTEGER," +
					  scores.SCORE + "INTEGER" +
					  ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXIST " + scores.TABLE);
			onCreate(db);
			
		}
		
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		switch (URI_MATCHER.match(uri)) {
		case SCORES:
			count = db.delete(scores.TABLE, selection, selectionArgs);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI" + uri);
		}
		getContext().getContentResolver().notifyChange(uri,null,true);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues iniValues) {
		if(URI_MATCHER.match(uri) == URI_MATCHER.NO_MATCH) {
			throw new IllegalArgumentException("Unknown URI "+uri);
		}
		
		ContentValues values;
		if(iniValues != null) {
			values = new ContentValues(iniValues);
		} else {
			values = new ContentValues();
		}
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		switch (URI_MATCHER.match(uri)) {
		case SCORES:
			db.insert(scores.TABLE, null, values);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI "+uri);
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch (URI_MATCHER.match(uri)) {
		case SCORES:
			qb.setTables(scores.TABLE);
			qb.setProjectionMap(scoresMap);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI " + uri );
		}
		Cursor c = qb.query(dbHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
		return c;
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;								
		switch (URI_MATCHER.match(uri)) {
		case SCORES:
			count = db.update(scores.TABLE, values, selection, selectionArgs);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
		
	}

	
}
