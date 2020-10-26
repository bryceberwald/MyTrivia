package edu.bberwald0.mytrivia;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel{
    private static final String TAG = "MainView Model";

    public MainViewModel() {
        Log.d(TAG, "ViewModel instance created");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "ViewModel instance about to be destroyed");
    }
}
