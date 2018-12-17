package com.example.fp.crickscore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("In receive msg", " :" );
       // Bundle bundle=new Bundle();
    //    String name = bundle.getString("Hi","yourdefaultvaluehere");
        String   runs= intent.getStringExtra("Run");
        //Log.e("Remote msg", " :" +name);
      //  txtruns.setText(runs);
        Log.e("Remote msg", " :" +runs);

    }
}
