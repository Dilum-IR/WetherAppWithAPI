package com.example.myapplication;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;

import com.example.myapplication.Models.Response;
import com.example.myapplication.Models.SearchArrayObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class CallAPI extends AsyncTask<String,Void,String> {

    String location = "London";
    public CallAPI(String location) {
        this.location = location;
    }

    // Before API call doing somethings
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progress dialog
    }

    // API call method
    @Override
    protected String doInBackground(String... strings) {
        String result="";
        // input location latitude and longitude
        String lat = "10.1",lon="125.6";


        // https://docs.tomorrow.io/reference/api-formats

        try {
            // Location {%20%22type%22:%20%22Point%22,%20%22coordinates%22:%20["+lon+",%20"+lat+"]%20}
            URL url = new URL("https://api.tomorrow.io/v4/weather/realtime?location="+location+"&apikey=DB6YxZcw7tOnmKduEyEkZcFDFkVV0uZm");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            String line = "";

            while ((line = bufferedReader.readLine()) !=null){
                stringBuilder.append(line);


            }
            result = stringBuilder.toString();
        }
        catch (Exception e){
            //System.out.println("Error");
            result = "Error";
            //  e.printStackTrace();
        }

        return result;
    }


    // Execution method
    @Override
    protected void onPostExecute(String result) {
        System.out.println("Result "+result+"\n" );
        try {
            JSONObject jsonObject = new JSONObject(result);
            String temp = jsonObject.getJSONObject("data")
                    .getJSONObject("values")
                    .getString("temperature");
            System.out.println("temperature :"+temp);

        } catch (JSONException e) {
            result = "Error";

//            throw new RuntimeException(e);
        }
        super.onPostExecute(result);

    }
}

//public class CallAPI {
//        /*Context context;
//
//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("https://movies-news1.p.rapidapi.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//
//    public CallAPI(Context context) {
//        this.context = context;
//    }
//
//    public interface getMovies{
//
//        @Headers(
//                {
//                "Accept:Applcation/json",
//                "X-RapidAPI-Host: movies-news1.p.rapidapi.com",
//                "X-RapidAPI-Key: e2e0b6fc3bmshe03cc36db878190p134d0djsn05c267021aac"
//                })
//        @GET("movies_news/recent");
//
//    }
//*/
//}
