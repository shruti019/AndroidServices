package com.example.shruti.androidservices;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Shruti on 04-04-2017.
 */

public class Background extends IntentService {
    private Intent intentBroadcast= new Intent();
    public Background(){
        super("Download");
    }

    protected void onHandleIntent(Intent intent){
        String urls[]= intent.getStringArrayExtra("urls");
        int size= DownloadFiles(urls);
        Log.d("PM", "Downloaded" + size + "bytes");

        intentBroadcast.setAction("FILES_DOWNLOADED");
        intentBroadcast.putExtra("downloadedBytes", size);
        getBaseContext().sendBroadcast(intentBroadcast);
    }


    private int DownloadFiles(String[] urls)
    {
        int size=0;
        try{
            URL url;
            File file;
            int byteMaxLength= 1024*1024;

            for (int i=0; i<urls.length; i++){
                url= new URL(urls[i]);
                file= new File(getApplicationContext().getFilesDir(), urls[i].substring(urls[i].lastIndexOf("/")+1));
                HttpsURLConnection connection= (HttpsURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream= connection.getInputStream();
                FileOutputStream outputStream= new FileOutputStream(file);
                size += connection.getContentLength();

                byte[] buffer= new byte[byteMaxLength];
                int bufLength= 0;
                while ((bufLength= inputStream.read(buffer)) > 0 ){
                    outputStream.write(buffer, 0, bufLength);
                }

                inputStream.close();
                outputStream.close();

                intentBroadcast.setAction("DOWNLOAD_PROGRESS");
                intentBroadcast.putExtra("percentComplete", ((i+1)*100)/ urls.length);
                getBaseContext().sendBroadcast(intentBroadcast);
            }
        }

        catch (Exception e){
            Log.d("PM", e.getMessage());
            return 0;
        }

        return size;
    }
}
