package com.android.numberguessgame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		final Button exit = (Button)findViewById(R.id.btnExit);
		exit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity.this.finish();
				
			}
		});
		
		final Button start = (Button)findViewById(R.id.btnStart);
		start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//MainActivity.this.setContentView(R.layout.activity_main);	
				startActivity(new Intent(MainActivity.this,GameAction.class));
			}
		});
		
		final Button about = (Button)findViewById(R.id.btnAbout);
		about.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				setContentView(R.layout.about);
			}
		});
		
		final Button settings = (Button)findViewById(R.id.btnSet);
		settings.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(MainActivity.this,settingsPage.class),100);				
			}
		});
		
		final Button score = (Button)findViewById(R.id.btnScore);
		score.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, ScoreActivity.class));
				
			}
		});
			
	}

	
}
