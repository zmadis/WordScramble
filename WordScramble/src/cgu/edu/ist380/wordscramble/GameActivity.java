/**
 * 
 */
package cgu.edu.ist380.wordscramble;

import java.util.Random;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
	private Word word;
	
	private Button submit; 
	private Button exit; 
	private Button resume;
	private TextView answerView; 
	private int numberOfTrials =0;
	private TextView numberOfTrialsTextView;
	
	
	
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

		submit = (Button)findViewById(R.id.submit); 
    	exit = (Button)findViewById(R.id.exit1);
    	answerView = (TextView) findViewById(R.id.AnswerTextView1); 
    	numberOfTrialsTextView = (TextView) findViewById(R.id.numberOfTrials);
    	
    	
	
	
		setLetterViews();
				
		
		// get a random word
		word = getRandomWord();
		//set a random word
		setLetters(word,6);
		
		submit.setOnClickListener(new OnClickListener(){ 
			
    		
    		@Override
    	public void onClick(View v) { 
    			
    			numberOfTrials+=1;
    			
    			if (numberOfTrials<3){	
    		    answerView.setText(""); 
    			numberOfTrialsTextView.setText(numberOfTrials+" trial out of 3");
    			String wordAnswer = word.getWord();
    			checkAnswer(wordAnswer);
    			}
    			else {
    				numberOfTrials = 0;
    				numberOfTrialsTextView.setText("1 trial out of 3");
    				answerView.setText(""); 
    				word = getRandomWord();
    				setLetters(word,6);
    				
    				
    			}
    	}});
		
		
		
		exit = (Button) findViewById(R.id.exit1);
	    exit.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	          
	            System.exit(0);
	        }
	    });
	    
		
		
		
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
	
	
	/*
	* This method return true if the answer equals to correct
	answer
	* (Ignoring case)
	*/
	public boolean isCorrect(String word) {
		
		
		
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
		
		
		
		
		
		for (int i =0; i< letters.length; i++)
		{
			if(letters[i].getText().toString().trim().equalsIgnoreCase(word.substring(i, i+1))){
				
				
			}
			else 
				{
				
				return false;
				}
		}
		
			
		return true;
		
		
	}
	/* this method :
	* 1: Read the text ( answer) from the answerTextEdit 
	* 2: call the isCorrect method
	* 3: display the appropriate message.
	*/
	public void checkAnswer(String word) {
		
		if(isCorrect(word)){
		answerView.setText("You're right!"); 
		numberOfTrials = 0;
		numberOfTrialsTextView.setText("1 trial out of 3");
		Word word1 = getRandomWord();
		setLetters(word1,6);
	
		}
	else{
		
		answerView.setText("Sorry, your answer is not correct");
		
	}
	}
	
	
	/* this method :
	* get a new random word from the DB
	*/
	public Word getRandomWord() {
	
		WordDataSource ds = new WordDataSource(this);
		ds.open();
		Word word2 = ds.getRandomWord();
		ds.close();
		Log.w(MySQLiteHelper.class.getName(),
		        "Got a random word from the db :"+ word2.getWord()); 
		
		
		return word2;
		
	}
	
	
}
