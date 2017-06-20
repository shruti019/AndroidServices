package com.example.shruti.androidservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Download extends AppCompatActivity {

    Button download;
    private int[] id= {R.id.pdf1, R.id.pdf2, R.id.pdf3, R.id.pdf4, R.id.pdf5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        download= (Button) findViewById(R.id.button3);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getBaseContext(), Background.class);
                EditText editView;
                String[] urls= new String[5];

                for (int i=0; i<5; i++){
                    editView= (EditText) findViewById(id[i]);
                    urls[i]= editView.getText().toString().trim();
                }
                intent.putExtra("urls", urls);
                startService(intent);
            }
        });

    }
}
