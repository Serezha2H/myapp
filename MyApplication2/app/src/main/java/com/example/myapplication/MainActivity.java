package com.example.myapplication;

import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import static com.example.myapplication.myJSON.SendRecv;
import static com.example.myapplication.myJSON.generateURL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class SendRecvAsync extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            String recv = null;
            try {
                recv = SendRecv(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return recv;
        }

        @Override
        protected void onPostExecute(String recv) {
            EditText text_res = (EditText) findViewById(R.id.text_result);
            text_res.setText(recv);
        }
    }


    public void ClickLogin(View v) {
        EditText user_name = (EditText) findViewById(R.id.user_name);
        EditText user_pass = (EditText) findViewById(R.id.user_pass);
        EditText text_res = (EditText) findViewById(R.id.text_result);

        String _user_name = user_name.getText().toString();
        String _user_pass = user_pass.getText().toString();
        String text = "Hello, " + _user_name + " you have enter pass: " + _user_pass;

        text_res.setText(text);
    }

    public void ClickSearch(View v) {
        EditText abon_id = (EditText) findViewById(R.id.abon_id);
        //TextView text_res = (TextView)findViewById(R.id.text_result);

        String _abon_id = abon_id.getText().toString();
        URL get = generateURL(_abon_id);
        new SendRecvAsync().execute(get);


//        String text = "SEND: " + get.toString();
//        text += "\nRECV: " + SendRecv;
//        text_res.setText(text);
    }
}