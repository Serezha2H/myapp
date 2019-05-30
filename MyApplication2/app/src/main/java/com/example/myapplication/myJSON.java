package com.example.myapplication;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class myJSON {
    public static URL generateURL (String _user, String _pass, String abon_id) {
        Uri _uri = Uri.parse("http://178.209.66.126/json/json.php").buildUpon() // сделать http-авторизацию
                .appendQueryParameter("user", _user)
                .appendQueryParameter("pass", _pass)
                .appendQueryParameter("action", "Search")
                .appendQueryParameter("id", abon_id)
                .build();
        URL url = null;
        try {
            url = new URL(_uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String SendRecv (URL _url) throws IOException {
        HttpURLConnection http = (HttpURLConnection)_url.openConnection();
        InputStream http_stream = http.getInputStream();
        Scanner scanner = new Scanner(http_stream).useDelimiter("\\A");
        //http.disconnect();
        String result = scanner.hasNext() ? scanner.next() : "recieve nothing";
        System.out.println(result);
        return result;
    }
}
