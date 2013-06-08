package edu.cgu.ist380.wordscrambletracker.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class WordDataSource {
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { 
			  MySQLiteHelper.WORD_COLUMN_ID,
			  MySQLiteHelper.WORD_COLUMN_WORD
	      };
public WordDataSource(Context context) {
	  try{
  dbHelper = new MySQLiteHelper(context);
	  }
	  catch (Exception e)
	  {
		    Log.e(WordDataSource.class.getName(), "Error opening the db "+ e.getMessage());
	  }
}


public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }
  
  public List<Word> getAllWords() {
	    List<Word> wordsList = new ArrayList<Word>();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_WORD,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Word word = cursorToWord(cursor);
	      wordsList.add(word);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return wordsList;
	  }
  
  public Word getRandomWord()
  {
	  // get the list of all words
	  List<Word> list = getAllWords();
	  
	  //pick a random number from 0 to the size of the list;
	  Random random = new Random();
	  int random_number = random.nextInt(list.size());
	  
	  // return the word at the random index
	  return list.get(random_number);
	  
  }

	  private Word cursorToWord(Cursor cursor) {
	    Word word = new Word();
	    // get the values from the cursor 
	    long id =  cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.WORD_COLUMN_ID));
	    String word_str=cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.WORD_COLUMN_WORD));
	
	    word.setId((int) id);
	    word.setWord(word_str);
	    
	    return word;
	  }
  
  
}
