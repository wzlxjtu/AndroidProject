package com.example.myfirstapp;

import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class DisplayMessageActivity extends AppCompatActivity
    implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String TAG = "MyTestMessage";
    private GestureDetectorCompat myDetector;
    private TextView gestureText;
    private TextView timeText, seconds, minutes, hours, days, months;
    private ImageView image;
    private ViewGroup a_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = "我爱你";
        Log.i(TAG,message);

        gestureText = (TextView) findViewById(R.id.gestureText);
        timeText = (TextView) findViewById(R.id.timeText);
        seconds = (TextView) findViewById(R.id.seconds);
        minutes = (TextView) findViewById(R.id.minutes);
        hours = (TextView) findViewById(R.id.hours);
        days = (TextView) findViewById(R.id.days);
        months = (TextView) findViewById(R.id.months);

        image = (ImageView) findViewById(R.id.image);
        a_layout = (ViewGroup) findViewById(R.id.a_layout);

        TransitionManager.beginDelayedTransition(a_layout);

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

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    SystemClock.sleep(10);
                    textHandler.sendEmptyMessage(0);
                }
            }
        };
        Thread timeThread = new Thread(r);
        timeThread.start();
    }

    Handler textHandler = new Handler() {
        String dateStart = "03/02/2017 22:00:00";
        Date d1 = null;
        Date d2 = null;
        @Override
        public void handleMessage(Message msg) {
            SimpleDateFormat  df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            try {
                d1 = df.parse(dateStart);
            } catch (ParseException e){}
            d2 = new Date();

//            timeText.setText(df.format(d2));

            // time elapsed
            long diff = d2.getTime() - d1.getTime();
            String timeCount = formatMillis(diff);
            String diffSeconds = Long.toString(diff / 1000);
            String diffMinutes = Long.toString(diff / (60 * 1000));
            String diffHours = Long.toString(diff / (60 * 60 * 1000));
            String diffDays = Long.toString(diff / (24 * 60 * 60 * 1000));
            String diffMonths = Long.toString(-diff / (24 * 60 * 60 * 1000 * 30));
            timeText.setText(df.format(d2));
            seconds.setText(diffSeconds);
            minutes.setText(diffMinutes);
            hours.setText(diffHours);
            days.setText(diffDays);
            months.setText(diffMonths);
        }
    };

    static public String formatMillis(long val) {
        StringBuilder                       buf=new StringBuilder(20);
        String                              sgn="";

        if(val<0) { sgn="-"; val=Math.abs(val); }

        append(buf,sgn,0,( val/3600000             ));
        append(buf,":",2,((val%3600000)/60000      ));
        append(buf,":",2,((val         %60000)/1000));
//        append(buf,".",3,( val                %1000));
        return buf.toString();
    }

    /** Append a right-aligned and zero-padded numeric value to a `StringBuilder`. */
    static private void append(StringBuilder tgt, String pfx, int dgt, long val) {
        tgt.append(pfx);
        if(dgt>1) {
            int pad=(dgt-1);
            for(long xa=val; xa>9 && pad>0; xa/=10) { pad--;           }
            for(int  xa=0;   xa<pad;        xa++  ) { tgt.append('0'); }
        }
        tgt.append(val);
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
//        image.setY(1000);
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
//        image.setY(330);
        return true;
    }
}

