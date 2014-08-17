package com.example.test;

import android.content.Intent;
import android.os.Build;
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
	private static final String KEY_INDEX = "index";
	private Button mCheatButton;
	private boolean mIsCheater;
	private static final String KEY_CHEAT = "cheat";
	private static final String KEY_CHEAT_ARRAY = "cheat_array";
	private TextView mApiLevelText;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
			new TrueFalse(R.string.question_suma, true),
			new TrueFalse(R.string.question_grup, true),
			new TrueFalse(R.string.question_zi, false),
			new TrueFalse(R.string.question_mileniu, true),
			new TrueFalse(R.string.question_culori, false),
	};
	private boolean[] mCheatBank = {false, false, false, false, false};
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
//		Log.d(TAG, "Updating question text for question #" + mCurrentIndex, new Exception());
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	private void checkAnswer(boolean userPressedTrue){
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		
		int messageResId = 0;
		mIsCheater = mCheatBank[mCurrentIndex];
		if(mIsCheater) {
			messageResId = R.string.judgement_toast;
		} else {
				
			if (userPressedTrue == answerIsTrue){
				messageResId = R.string.correct_toast;
			}
			else{
				messageResId = R.string.incorrect_toast;
			}
			
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
		
		mApiLevelText = (TextView)findViewById(R.id.api_level_text);
		mApiLevelText.setText("Api level is" + Build.VERSION.SDK_INT);
		
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
		mCheatButton = (Button)findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(TestActivity.this, CheatActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
				startActivityForResult(i, 0);
				
			}
		});
		mH3nry = (TextView)findViewById(R.id.h3nry);
		mH3nry.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				mIsCheater = false;
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
				mIsCheater = false;
				updateQuestion();
			}
		});
		mNextButton = (ImageButton)findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				mIsCheater = false;
				updateQuestion();
			}
		});
		if(savedInstanceState != null){
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
			mIsCheater = savedInstanceState.getBoolean(KEY_CHEAT, false);
			mCheatBank = savedInstanceState.getBooleanArray(KEY_CHEAT_ARRAY);
		}
		Log.d(TAG, "Current question index: " + mCurrentIndex);
		updateQuestion();
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
		savedInstanceState.putBoolean(KEY_CHEAT, mIsCheater);
		savedInstanceState.putBooleanArray(KEY_CHEAT_ARRAY, mCheatBank);
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
		if (mIsCheater) {
			mCheatBank[mCurrentIndex] = true;
		}
	}
}
