package com.example.fp.crickscore;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    ConstantValues cv=new ConstantValues();
    LocalBroadcastManager broadcastReceiver;



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("Log D", "From: " + remoteMessage.getFrom());


        if (remoteMessage.getNotification().getBody() != null) {

            String runs="", overs="", wickets="", chase = "" ;
            Log.e("FIREBASE", "Message Notification Body: " + remoteMessage.getNotification().getBody());
//            String msg = remoteMessage.getData().get("messageText");
            String msg = remoteMessage.getNotification().getBody();
            try {
                JSONObject object=new JSONObject(msg);
                JSONObject finalObj=object.getJSONObject("score");
                runs=finalObj.getString("runs");
                overs=finalObj.getString("overs");
                wickets=finalObj.getString("wickets");
                chase=finalObj.getString("Australia");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            cv.setRuns(runs);
            cv.setOvers(overs);
            cv.setTeam(chase);
            cv.setWickets(wickets);

            Log.e("Remote msg", " :" +runs);
            Log.e("Remote msg", " :" + msg);
         /*   Intent intent=new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            Uri sounduri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            builder.setContentTitle("Title");
            builder.setContentText(msg);
            builder.setContentIntent(pendingIntent);
            builder.setSound(sounduri);

            Notification n=builder.build();

            nm.notify(1,n);*/
           //  sendNotification(msg);

           /* broadcastReceiver= LocalBroadcastManager.getInstance(getBaseContext());
            Intent intent = new Intent();
            intent.putExtra("runs", runs);
            intent.putExtra("overs", overs);
            broadcastReceiver.sendBroadcast(intent);*/

            Intent i=new Intent("com.example.fp.crickscore");
            i.putExtra("Run", runs);
            i.putExtra("Over", overs);
            i.putExtra("Wicket", wickets);
            i.putExtra("Chase", chase);
           /* Bundle extras=new Bundle();
            extras.putString("com.example.fp.crickscore", runs);
            i.putExtra("Hi", extras);*/
            sendBroadcast(i);
        }
    }

    private void sendNotification(String remoteMessage) {

        //RemoteMessage.Notification notification=remoteMessage.getNotification();

        Intent intent=new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification))
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Msg")
                .setContentText(remoteMessage)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }


}
