package com.example.shruti.androidservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Shruti on 04-04-2017.
 */

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("FILES_DOWNLOADED")) {
            if (intent.getIntExtra("downloadedBytes", 0) > 0){
                Toast.makeText(context, "Download Completed", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(context, "Download Failed", Toast.LENGTH_LONG).show();
            }
        }
        else if (intent.getAction().equals("DOWNLOAD_PROGRESS")) {
            Log.d("PM","" + intent.getIntExtra("percentComplete", 0));
            Toast.makeText(context, intent.getIntExtra("percentComplete", 0)+ "% completed", Toast.LENGTH_LONG).show();

        }
    }
}
