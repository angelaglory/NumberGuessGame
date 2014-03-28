package com.android.numberguessgame;

import android.net.Uri;
import android.provider.BaseColumns;

public class Score implements Comparable<Score> {

	public static final class scores implements BaseColumns {
		private scores() {}
		public static final String RANGE = "range";
		public static final String SCORE = "score";
		public static final String NAME = "name";
		public static final String TABLE = "scores";
		public static final Uri CONTENT_URI = Uri.parse("Content://" + NumberContentProvider.AUTHORITY + "/scores");
		
	}
	
	private int score;
	private int range;
	private String name;
	
	public int getScore() {
		return score;
	}
	
	public Score(int score,int range) {
		this(range,score,"");
	}
	
	public Score(int range, int score, String name) {
		this.range=range;
		this.score=score;
		this.name=name;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(Score another) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
	
	
	
}
