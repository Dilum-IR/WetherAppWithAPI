package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    Context context;
    TextView SearchInput,Temp;
    private Button searchBtn;
    String TempValue = null;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void search(View view){
        SearchInput = findViewById(R.id.search);
        Temp = findViewById(R.id.temp);

        String location = SearchInput.getText().toString();

        if (location != null ){

            CallAPI callAPI = new CallAPI(location);
            callAPI.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }

        Response response = new Response();
        TempValue = response.getTemp();

        System.out.println(TempValue);
        /*if (TempValue !=null){
            Temp.setText(TempValue);

        }else {
            Temp.setText("404 Not Found");

        }
*/

    }

}