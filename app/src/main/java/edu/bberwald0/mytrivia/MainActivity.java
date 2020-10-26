package edu.bberwald0.mytrivia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mBackButton;
    private TextView mQuestionTextView;

    MainViewModel mViewModel;

//    private int mCurrentIndex = 0;
//
//
//    private Question[] mQuestionBank = new Question[] {
//            new Question(R.string.question_text, true),
//            new Question(R.string.question_text_two, true),
//            new Question(R.string.question_text_three, false),
//            new Question(R.string.question_text_four, false),
//            new Question(R.string.question_text_five, true)
//    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        Log.d(TAG, "Got a MainViewModel" + mViewModel);

        mQuestionTextView = findViewById(R.id.question_text_view);


        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //does nothing yet, but it will
                checkAnswer(true);
            }
        });


        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //does nothing yet, but it will
                checkAnswer(false);
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //does nothing yet, but it will
                mViewModel.moveToNext();
                updateQuestion();
            }
        });

        mBackButton = findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //does nothing yet, but it will
                mViewModel.moveToBack();
                updateQuestion();

            }
        });

        if (savedInstanceState != null) {
            mViewModel.mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            Log.i(TAG, "Resetting the savedInstanceState");
        }

        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mViewModel.mCurrentIndex);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private  void updateQuestion() {
        int question = mViewModel.currentQuestionText();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mViewModel.currentQuestionAnswer();

        int messageResId = 0;

        if(userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}