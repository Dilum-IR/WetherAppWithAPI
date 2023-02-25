package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    private TextView MovieName,MovieDate,MovieDescrition;
    TextView SearchInput;
    private Button searchBtn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }
    public void CallAPI(View view){
        SearchInput = findViewById(R.id.search);
        String location = SearchInput.getText().toString();

        if (location != null ){
            CallAPI callAPI = new CallAPI(location);
            callAPI.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }

    }





}