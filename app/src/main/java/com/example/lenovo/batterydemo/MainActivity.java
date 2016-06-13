package com.example.lenovo.batterydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn;
    IntentFilter intentFilter;
    BatteryReceiver batteryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        textView=(TextView)findViewById(R.id.textView);
        btn=(Button)findViewById(R.id.button);
        intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        batteryReceiver=new BatteryReceiver();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver(batteryReceiver, intentFilter);
            }
        });

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(batteryReceiver);
    }

    class BatteryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
            int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int total=intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            Log.e("battery ",""+level+" "+total);
            textView.setText("battery: "+level/total+"%");
        }
    }
}
