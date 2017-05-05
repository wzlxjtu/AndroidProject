package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTestMessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    public void onSubmit(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        EditText pwText = (EditText) findViewById(R.id.password);
        String message = pwText.getText().toString();

        if (message.equals("0302")) {
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }

}
