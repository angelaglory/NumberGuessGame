package com.android.numberguessgame;


import com.android.numberguessgame.Score.scores;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameAction extends Activity {
	private SetNumbers numbers;
	private Button btnGuess;
	private EditText textNumber;
	private TextView state;
	private TextView txtGuessSize;
	private int guessSize =0;
	double number = 0;
	private boolean finished = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	

		MediaPlayer sound = MediaPlayer.create(GameAction.this,R.raw.gamesound);	
		sound.start();
		btnGuess = (Button)findViewById(R.id.btnGuess);
		textNumber = (EditText)findViewById(R.id.txtGuess);
		state = (TextView)findViewById(R.id.txtDurum);
		txtGuessSize = (TextView)findViewById(R.id.txtGuessSize);
		
		number = Math.ceil(Math.random()*100);
		
		btnGuess.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				double doGuess = Double.parseDouble(textNumber.getText().toString());
				
				if(doGuess > number) {
					guessSize++;
					state.setText(textNumber.getText().toString()+" is too high ");
				
				}else if(doGuess < number) {
					guessSize++;
					state.setText(textNumber.getText().toString()+" is too low ");
				
				}else if(doGuess == number) {
					guessSize++;
					state.setText("Correct the answer was "+textNumber.getText().toString());
					finished = true ;
				}
				txtGuessSize.setText("Guess : "+ guessSize);
			
				
			}
		}); 
		if(finished) {
			SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
			ContentValues values = new ContentValues();
			values.put(scores.NAME, pref.getString(getResources().getString(R.string.name), "angela"));
			values.put(scores.RANGE, numbers.getRange());
			values.put(scores.SCORE, guessSize);
			getContentResolver().insert(scores.CONTENT_URI, values);
			
			}
			
		
	}


}
