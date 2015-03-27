package com.example.mwoven;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity implements View.OnClickListener {

    TextView timeText;
    TextView statusText;
    int timer = 0;
    Timer t;
    boolean paused = false;
    boolean doorOpened = true;
    //door status
    //0 closed
    //1 open
    //2 cooking

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = (TextView) findViewById(R.id.text_time);
        statusText = (TextView) findViewById(R.id.text_status);
        timeText.setText("0:00:00");

        Button[] buttons = {
                (Button) findViewById(R.id.button_1s),
                (Button) findViewById(R.id.button_10s),
                (Button) findViewById(R.id.button_30s),
                (Button) findViewById(R.id.button_1m),
                (Button) findViewById(R.id.button_5m),
                (Button) findViewById(R.id.button_10m),
                (Button) findViewById(R.id.button_1h),
                (Button) findViewById(R.id.button_clear),
                (Button) findViewById(R.id.button_start),
                (Button) findViewById(R.id.button_stop),
                (Button) findViewById(R.id.button_open_close),
        };

        for (Button button : buttons){
            button.setOnClickListener(this);
        }
    }

    private void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timeText.setText(converTimeToString(timer));

            }

        });

        if (timer ==0){
            t.cancel();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.button_1s){
            timer+=1;
            updateUI();

        }
        if (id == R.id.button_10s){
            timer+=10;
            updateUI();

        }
        if (id == R.id.button_30s){
            timer+=30;
            updateUI();

        }
        if (id == R.id.button_1m){
            timer+=60;

        }
        if (id == R.id.button_5m){
            timer+=60*5;
            updateUI();

        }
        if (id == R.id.button_10m){
            timer+=60*10;
            updateUI();

        }
        if (id == R.id.button_1h){
            timer+=3600;
            updateUI();

        }
        if (id == R.id.button_clear){

        }
        if (id == R.id.button_start){
            if (timer <= 0){
                timer = 0;
                Toast.makeText(this, "Select Time", Toast.LENGTH_SHORT).show();
                return;
            }
            t = new Timer("time");
            paused = false;
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    timer--;
                    updateUI();
                }
            }, 0, 1000);

            findViewById(R.id.button_start).setEnabled(false);
            findViewById(R.id.button_stop).setEnabled(true);

        }
        if (id == R.id.button_stop){

            t.cancel();
            if(!paused){
                findViewById(R.id.button_start).setEnabled(true);
                findViewById(R.id.button_stop).setEnabled(true);
                paused = true;
            }else {
                findViewById(R.id.button_start).setEnabled(true);
                findViewById(R.id.button_stop).setEnabled(false);
                timer = 0;
                updateUI();
                paused =false;

            }
        }
        if (id == R.id.button_open_close){

            if(doorOpened){
                statusText.setText("Close");
                doorOpened = false;

            }else {
                statusText.setText("Open");
                doorOpened = true;
            }

        }
    }
    private String converTimeToString(int totalSecs){

        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
