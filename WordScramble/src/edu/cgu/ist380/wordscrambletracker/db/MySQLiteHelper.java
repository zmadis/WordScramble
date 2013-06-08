package edu.cgu.ist380.wordscrambletracker.db;

import edu.cgu.ist380.wordscrambletracker.db.MySQLiteHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	
	 /* database variables */
		private static final String DATABASE_NAME = "word.db";
		private static final int DATABASE_VERSION = 2;
		
		
		/* Word Table */
		 
		public static final String TABLE_WORD = "word";
		public static final String WORD_COLUMN_ID = "_id";
		public static final String WORD_COLUMN_WORD = "word";

	
		// Database creation sql statement
		private static final String DATABASE_CREATE = "create table " + TABLE_WORD
				+ "(" 
				+ WORD_COLUMN_ID + " integer primary key autoincrement, "
				+ WORD_COLUMN_WORD + " text not null" 
				// no comma after last column
				+ ")";
	

		
		public MySQLiteHelper(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}	
				
		
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

		arg0.execSQL(DATABASE_CREATE);
		Log.w(MySQLiteHelper.class.getName(),
		        "Database Created"); 
		// add all th words once the db was created
		 addWords(arg0);
		 Log.w(MySQLiteHelper.class.getName(),
			        "Words list Created"); 
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

		 arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
		 onCreate(arg0);
		Log.w(MySQLiteHelper.class.getName(),
				        "Upgrading database from version " + arg1 + " to "
				            + arg2 + ", which will destroy all old data");   	

		
	}
	
	private void addWords(SQLiteDatabase database)
	{
	String []words = {"accredited",
			"graduation",
			"university",
			"mentorship",
			"leadership",
			"internship",
			"achievable",
			"innovation",
			"technology",
			"accomplish",
			"accurately",
			"accumulate",
			"automation",
			"automobile",
			"backgammon",
			"bandwidths",
			"basketball",
			"beachfront",
			"beginnings",
			"courageous",
			"creativity",
			"credential",
			"consultant",
			"copyrights",
			"connection",
			"contextual",
			"confiscate",
			"configures",
			"conforming",
			"confidence",
			"conference",
			"compulsory",
			"checklists",
			"cellphones",
			"complexity",
			"compliment",
			"compelling",
			"commitment",
			"compatible",
			"celebrates",
			"circumvent",
			"classified",
			"checkbooks",
			"checkmarks",
			"collective",
			"assortment",
			"ambassador",
			"assumption",
			"assistance",
			"believable",
			"benefactor",
			"beneficial",
			"decoration",
			"deposition",
			"distracted",
			"endangered",
			"everything",
			"fatherhood",
			"glossaries",
			"hopelessly",
			"inaugurate",
			"irrelevant",
			"journalism",
			"melancholy",
			"microscope",
			"nutritious",
			"ostensible",
			"population",
			"randomness",
			"reasonable",
			"reclaiming",
			"regenerate",
			"regretting",
			"republican",
			"settlement",
			"successful",
			"techniques",
			"touchdowns",
			"watermelon"};
	
	for(int i= 0 ; i< words.length ; i++)
	{
    ContentValues values = new ContentValues();  
    values.put(   MySQLiteHelper.WORD_COLUMN_WORD,words[i]);
    long insertedId = database.insert(MySQLiteHelper.TABLE_WORD,null, values);
	}
	}
  

}
