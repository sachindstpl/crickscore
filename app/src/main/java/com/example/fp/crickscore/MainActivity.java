package com.example.fp.crickscore;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static MainActivity ins;
        TextView txtruns, txtovers, txtwickets, txtchase, txtteam;
        ConstantValues cv=new ConstantValues();

    MyBroadcastReceiver1 mMyBroadcastReceiver;
        BroadcastReceiver broad;
        IntentFilter i;
    public static String runs="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtruns=findViewById(R.id.txtRuns);
        txtovers=findViewById(R.id.txtOvers);
        txtwickets=findViewById(R.id.txtWickets);
        txtchase=findViewById(R.id.txtchase);
       //txtteam=findViewById(R.id.txtTeams);
            ins=this;
    mMyBroadcastReceiver=new MyBroadcastReceiver1();
    registerReceiver(mMyBroadcastReceiver, new IntentFilter("com.example.fp.crickscore"));

    NotificationManager notificationManager= (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.cancelAll();


/*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId=getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW));
        }*/

/*
broad=new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String   runs= intent.getStringExtra("runs");
            String overs= intent.getStringExtra("overs");
            Log.e("Remote msg", " :" +runs);
            Log.e("Remote msg", " :" +overs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    };*/

      /*  broad=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("In receive msg", " :" );
                Bundle bundle=new Bundle();
                String name = bundle.getString("Hi","yourdefaultvaluehere");
                String   runs= intent.getStringExtra("Hi");
                Log.e("Remote msg", " :" +name);
                txtruns.setText(runs);
                Log.e("Remote msg", " :" +runs);
            }
        };*/
      //  registerReceiver(broad,  new IntentFilter("com.example.fp.crickscore"));
}

    public static MainActivity  getInstace(){
        return ins;
    }
    public void updateTheTextView(final String runs1, final String overs1, final String wickets1, final String chase1) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView runs = (TextView) findViewById(R.id.txtRuns);
                TextView overs = (TextView) findViewById(R.id.txtOvers);
                TextView wickets = (TextView) findViewById(R.id.txtWickets);
                TextView chase = (TextView) findViewById(R.id.txtchase);
                runs.setText(runs1);
                overs.setText(overs1);
                wickets.setText(wickets1);
                chase.setText(chase1);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


      //  i=new IntentFilter("android.intent.action.SEND");


    }
    @Override
    protected void onPause() {
        super.onPause();
      //  unregisterReceiver(broad);
    }


    @Override
    protected void onStop() {
        super.onStop();
      //  unregisterReceiver(broad);
    }



}

class MyBroadcastReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("In receive msg1", " :" );
        // Bundle bundle=new Bundle();
        //    String name = bundle.getString("Hi","yourdefaultvaluehere");
      String runs= intent.getStringExtra("Run");
        String overs= intent.getStringExtra("Over");
        String wickets= intent.getStringExtra("Wicket");
        String chase= intent.getStringExtra("Chase");

        Log.e("Remote msg", " :" +runs);
        //  txtruns.setText(runs);
    //    Log.e("Remote msg1", " :" +runs);

        MainActivity.getInstace().updateTheTextView(runs, overs, wickets, chase);
    }


}
