/**
 * 
 */
package cgu.edu.ist380.wordscramble;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import edu.cgu.ist380.wordscrambletracker.db.MySQLiteHelper;
import edu.cgu.ist380.wordscrambletracker.db.Word;
import edu.cgu.ist380.wordscrambletracker.db.WordDataSource;

/**
 * @author yousefabed
 *
 */
public class GameActivity extends Activity {
	
	
	
	Context context;
	EditText [] letters;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// make this activity full screen
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

				
		setContentView(R.layout.activity_game);
		context = this;

		setLetterViews();
				
	
		// get a random word
		WordDataSource ds = new WordDataSource(this);
		ds.open();
		Word word = ds.getRandomWord();
		ds.close();
		Log.w(MySQLiteHelper.class.getName(),
		        "Got a random word from the db :"+ word.getWord()); 
 
		setLetters(word,6);
		
	}
	private void setLetters(Word word, int x) {
		 //set the all letters in the their places
		for(int i=0;i<word.getWord().length();i++)
		{
			letters[i].setText(word.getWord().charAt(i)+" ");
			letters[i].setEnabled(false);
		}
		
		// hide as many letter as the value of x 
		
		while(x > 0)
		{
			Random random  = new Random ();
			int index = random.nextInt(word.getWord().length());
			if(!letters[index].isEnabled())
			{
				x --;
				letters[index].setEnabled(true);
				letters[index].setText("");
			}
			
		}
	}
	private void setLetterViews() {
		letters = new EditText[10]; 
		letters[0]= (EditText) findViewById(R.id.letter1);
		letters[1]= (EditText) findViewById(R.id.letter2);
		letters[2]= (EditText) findViewById(R.id.letter3);
		letters[3]= (EditText) findViewById(R.id.letter4);
		letters[4]= (EditText) findViewById(R.id.letter5);
		letters[5]= (EditText) findViewById(R.id.letter6);
		letters[6]= (EditText) findViewById(R.id.letter7);
		letters[7]= (EditText) findViewById(R.id.letter8);
		letters[8]= (EditText) findViewById(R.id.letter9);
		letters[9]= (EditText) findViewById(R.id.letter10);
		
	}
}
