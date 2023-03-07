package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.NetworkErrorException;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.utils.NetworkUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity {

    Context context;
    TextView SearchInput,output,Erro,visiblity,humiditity;
    //String TempValue = null,visibility = null;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchInput = findViewById(R.id.search);
        output = findViewById(R.id.temp);
        Erro = findViewById(R.id.erro);
        visiblity = findViewById(R.id.visible);
        humiditity = findViewById(R.id.humiditer);


    }
    public void search(View view) throws IOException {

        String cityName = SearchInput.getText().toString();

        if (cityName.isEmpty()){
            Erro.setText("Please Enter Location");
        }
        else {

            getData(cityName);
        }

    }
    public void getData(String cityName) throws IOException  {
        Uri uri = Uri.parse("https://api.tomorrow.io/v4/weather/realtime?location="+cityName+"&apikey=DB6YxZcw7tOnmKduEyEkZcFDFkVV0uZm")
                .buildUpon().build();
        URL url = null;
        try {
            url = new URL(uri.toString());
            new CallApi(MainActivity.this).execute(url);

        } catch (MalformedURLException e) {
            System.out.println("error"+ e);
        }


    }

    @SuppressLint("StaticFieldLeak")
    class CallApi extends AsyncTask<URL,Void,String> {

        ProgressDialog progressDialog;
        Context context;

        public CallApi(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String data = null;
            try {
                data = NetworkUtils.makeHTTPRequest(url);
                return data;

            } catch (IOException e) {
                System.out.println("error"+ e);

                return "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            try {
                parseJson(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        @SuppressLint("SetTextI18n")
        public void parseJson(String data)throws IOException  {
            String temp,symbolWith,visible,humiditer,wind;

            JSONObject cityo = null,subJson=null;

            try {
                cityo = new JSONObject(data);
                subJson = new JSONObject(data);

                subJson = cityo.getJSONObject("data")
                        .getJSONObject("values");


                temp = subJson.getString("temperature");
                symbolWith = temp + " \u2103";

                visible = subJson.getString("visibility");
                humiditer =subJson.getString("humidity");

                wind=subJson.getString("windSpeed");

                output.setText(symbolWith);
                visiblity.setText(visible+" Km");
                humiditity.setText(humiditer+" %");
            }
            catch (JSONException e) {
               System.out.println("error"+ e);
             //  output.setText("Error");

            }
            if (data.equals("Error")){
                Erro.setText("Not Found");
            }
            progressDialog.dismiss();

        }
    }

    public void GotoWebsite(){


    }




}
