package com.coldbeer.coldbeer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class TelaAproveiteChopp extends AppCompatActivity {
    Button btnFim;

    final int MILISEGUNDOS = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aproveite_chopp);
//        btnFim = (Button) findViewById(R.id.btnFim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
//              finish();

                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        }, MILISEGUNDOS);

//        btnFim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }




}
