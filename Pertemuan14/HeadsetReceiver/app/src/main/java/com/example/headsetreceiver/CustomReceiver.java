package com.example.headsetreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       String intentAction = intent.getAction();

       if (intentAction != null) {
           switch (intentAction) {
               case Intent.ACTION_HEADSET_PLUG:
                   Toast.makeText(context, "Headset plugged or unplugged.", Toast.LENGTH_SHORT).show();
                   break;
           }
       }
    }
}