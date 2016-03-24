package com.example.vutruong.groupproject2;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by VuTruong on 23/03/2016.
 */
public class HttpGetDemo extends AsyncTask<String, Void, String> {

    OnDataSendToActivity mData;

    public interface OnDataSendToActivity {
          void sendData(String str);
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mData.sendData(s);
    }
    public void setData(OnDataSendToActivity inter){
        mData = inter;
    }

}
