package edu.cgu.ist380.wordscrambletracker.db;

import edu.cgu.ist380.wordscrambletracker.db.MySQLiteHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	
	 /* database variables */
		private static final String DATABASE_NAME = "word.db";
		private static final int DATABASE_VERSION = 1;
		
		
		/* Word Table */
		 
		public static final String TABLE_WORD = "word";
		public static final String WORD_COLUMN_ID = "_id";
		public static final String WORD_COLUMN_NAME = "words";
		public static final String WORD_COLUMN_ENABLED = "is_enabled";

	
		// Database creation sql statement
		private static final String DATABASE_CREATE = "create table " + TABLE_WORD
				+ "(" 
				+ WORD_COLUMN_ID + " integer primary key autoincrement, "
				+ WORD_COLUMN_NAME + " text not null," 
				+ WORD_COLUMN_ENABLED + " text not null"   
				// no comma after last column
				+ ")";
	

		
		public MySQLiteHelper(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}	
				
		
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

		arg0.execSQL(DATABASE_CREATE);
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

}
