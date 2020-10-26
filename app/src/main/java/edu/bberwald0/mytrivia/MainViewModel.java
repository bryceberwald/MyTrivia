package edu.bberwald0.mytrivia;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel{
    private static final String TAG = "MainViewModel";

    int mCurrentIndex = 0;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_text, true),
            new Question(R.string.question_text_two, true),
            new Question(R.string.question_text_three, false),
            new Question(R.string.question_text_four, false),
            new Question(R.string.question_text_five, true)
    };


    Boolean currentQuestionAnswer() {
        return mQuestionBank[mCurrentIndex].isAnswerTrue();
    }


    int currentQuestionText() {
        return mQuestionBank[mCurrentIndex].getTextResId();
    }


    void moveToNext() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
    }

    void moveToBack() {
            if (mCurrentIndex > 0) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
            } else {
                mCurrentIndex = 0;
            }
    }


    public MainViewModel() {
        Log.d(TAG, "ViewModel instance created");
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "ViewModel instance about to be destroyed");
    }
}
