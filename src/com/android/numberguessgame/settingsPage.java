package com.android.numberguessgame;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class settingsPage extends PreferenceActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);	
		 addPreferencesFromResource(R.layout.settings);
	 
	}
}
