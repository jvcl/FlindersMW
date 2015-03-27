package com.example.mwoven;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    TextView timeText, statusText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = (TextView) findViewById(R.id.text_time);
        timeText.setText("0:00:00");
        statusText = (TextView) findViewById(R.id.text_status);
        statusText.setText("Closed");

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

    }
}
