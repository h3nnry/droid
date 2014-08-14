package com.example.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.test.TrueFalse;

public class TestActivity extends ActionBarActivity {
	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private TextView mQuestionTextView;
	private TextView mH3nry;
	private ImageButton mPreviousButton;
	private static final String TAG = "TestActivity"; 
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
			new TrueFalse(R.string.question_suma, true),
			new TrueFalse(R.string.question_grup, true),
			new TrueFalse(R.string.question_zi, false),
			new TrueFalse(R.string.question_mileniu, true),
			new TrueFalse(R.string.question_culori, false),
	};
	private int mCurrentIndex = 0;
	
	@Override
	public void onStart(){
		super.onStart();
		Log.d(TAG, "onStart() called");
	}
	
	@Override
	public void onPause(){
		super.onPause();
		Log.d(TAG, "onPause() called");
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Log.d(TAG, "onResume() called");
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG, "onStop() called");
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG, "onDestroy() called");
	}
	
	private void updateQuestion(){
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	private void checkAnswer(boolean userPressedTrue){
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		
		int messageResId = 0;
		
		if (userPressedTrue == answerIsTrue){
			messageResId = R.string.correct_toast;
		}
		else{
			messageResId = R.string.incorrect_toast;
		}
		
		Toast.makeText(this,  messageResId, Toast.LENGTH_SHORT)
			.show();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate(Bundle) called");
		setContentView(R.layout.activity_test);
		
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
		
		mTrueButton = (Button)findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkAnswer(true);
				
			}
		});
		mFalseButton = (Button)findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkAnswer(false);
				
			}
		});
		mH3nry = (TextView)findViewById(R.id.h3nry);
		mH3nry.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
		mPreviousButton = (ImageButton)findViewById(R.id.previous_button);
		mPreviousButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mCurrentIndex>0){
					mCurrentIndex = (mCurrentIndex -1) % mQuestionBank.length;
				}
				updateQuestion();
			}
		});
		mNextButton = (ImageButton)findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
		updateQuestion();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
