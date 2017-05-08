package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity
    implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String TAG = "MyTestMessage";
    private GestureDetectorCompat myDetector;
    private TextView gestureText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = "我爱你";
        Log.i(TAG,message);

        gestureText = (TextView) findViewById(R.id.gestureText);
        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        myDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap listener.
        myDetector.setOnDoubleTapListener(this);

        Toolbar childToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(childToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.myDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //    Gestures handling
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gestureText.setText("我爱你");
        gestureText.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gestureText.setText("");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        gestureText.setText("超级爱你");
        gestureText.setTextSize(TypedValue.COMPLEX_UNIT_SP,60);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        gestureText.setText("");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        gestureText.setText("我爱你");
        gestureText.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        gestureText.setText("我爱你");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        gestureText.setText("么么哒");
        gestureText.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        gestureText.setText("我爱你");
        gestureText.setTextSize(TypedValue.COMPLEX_UNIT_SP,60);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        gestureText.setText("");
        return true;
    }
}

