package com.example.myapplication.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    public static String makeHTTPRequest(URL url)throws IOException {

        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        InputStream inputStream = connection.getInputStream();

        try{

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            } else return null;
        }
        finally {
            connection.disconnect();
        }

    }
}
