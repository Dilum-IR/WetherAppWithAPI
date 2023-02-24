package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myapplication.Models.Response;
import com.example.myapplication.Models.SearchArrayObject;

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

        try {

            URL url = new URL("https://www.hpb.health.gov.lk/api/get-current-statistical");
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
            e.printStackTrace();
        }

        return result;
    }


    // Execution method
    @Override
    protected void onPostExecute(String result) {
        System.out.println("Result"+result );
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
