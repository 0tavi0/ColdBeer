package com.coldbeer.coldbeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.coldbeer.banco.BarrilDao;
import com.coldbeer.entidades.Barril;

public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;
    private BarrilDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new BarrilDao(this);
        dao.abrir();
        btnEntrar = (Button) findViewById(R.id.buttonEntrar);


        Barril barril = dao.pegarBarrilAtual();
        dao.fechar();
//        if(barril.getCapacidade()<500){
//            btnEntrar.setVisibility(View.INVISIBLE);
//
//        }
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EscolherChopp.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onPause() {
        dao.fechar();
        super.onPause();
    }
}
