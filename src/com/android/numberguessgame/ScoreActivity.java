package com.android.numberguessgame;

import java.util.zip.Inflater;

import com.android.numberguessgame.Score.scores;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class ScoreActivity extends Activity {
	
	private SimpleCursorAdapter adapter;
	
	private ListView listView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score);
		
		listView = (ListView)findViewById(R.id.scoreList);
		Cursor cursor = getContentResolver().query(scores.CONTENT_URI, null, null, null, null);
	
		adapter = new SimpleCursorAdapter(this, R.layout.rows, cursor, 
				 new String[] {scores.NAME,scores.RANGE,scores.SCORE},
				 new int[] {R.id.userName,R.id.userRange,R.id.userScore});
		listView.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.settings, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.btnSet:startActivityForResult(new Intent(this,settingsPage.class),100);
		 break;
		}
		return true;
	}

}
