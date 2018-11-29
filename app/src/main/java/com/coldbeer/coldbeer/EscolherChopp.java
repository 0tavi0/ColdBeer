package com.coldbeer.coldbeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EscolherChopp extends AppCompatActivity {

    private Button btnAumentarCopoG, btnDiminuirCopoG, btnAumentarCopoP, btnDiminuirCopoP,btnProximo,btnCancelar;
    private TextView valorAcrescentadoG,valorAcrescentadoP, txtPreco;
    private int copoResultG = 0, copoResultP=0;
    private double  valor;
    private ImageView choppGrande, choppPequeno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_chopp);


        btnProximo = (Button) findViewById(R.id.btnProx);
        btnCancelar = (Button) findViewById(R.id.btnCan);
        //valorAcrescentadoG = (TextView) findViewById(R.id.txtQtdG);
        //valorAcrescentadoP = (TextView) findViewById(R.id.txtQtdP);
        choppGrande = (ImageView) findViewById(R.id.choppGrande);
        choppPequeno = (ImageView) findViewById(R.id.choppPequeno);

        choppGrande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int choppGrande = 0;
                Intent intent = new Intent(EscolherChopp.this,EscolhaQtdChopp.class);
                Bundle infos = new Bundle();
                infos.putInt("contador",choppGrande);
                intent.putExtras(infos);
                startActivity(intent);
                finish();

            }
        });

        choppPequeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int choppGrande = 1;
                Intent intent = new Intent(EscolherChopp.this,EscolhaQtdChopp.class);
                Bundle infos = new Bundle();
                infos.putInt("contador",choppGrande);
                intent.putExtras(infos);
                startActivity(intent);
                finish();

            }
        });

//
//        btnAumentarCopoG.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                copoResultG += 1;
//                valorAcrescentadoG.setText(String.format(Locale.getDefault(),"%d",copoResultG));
//
//                AtualizarChopp();
//
//
//
//            }
//        });
//
//        btnDiminuirCopoG.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(copoResultG>0)
//                copoResultG -= 1;
//                valorAcrescentadoG.setText(String.format(Locale.getDefault(),"%d",copoResultG));
//
//                AtualizarChopp();
//
//            }
//        });
//
//
//        btnAumentarCopoP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                copoResultP += 1;
//                valorAcrescentadoP.setText(String.format(Locale.getDefault(),"%d",copoResultP));
//                AtualizarChopp();
//
//            }
//        });
//
//        btnDiminuirCopoP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (copoResultP>0)
//                copoResultP -= 1;
//
//                valorAcrescentadoP.setText(String.format(Locale.getDefault(),"%d",copoResultP));
//
//                AtualizarChopp();
//            }
//        });
//        btnCancelar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//
//            }
//        });
//        btnProximo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(EscolherChopp.this,TelaDePagamento.class);
//                Bundle infos = new Bundle();
//                infos.putInt("QtdP",copoResultP);
//                infos.putInt("QtdG",copoResultG);
//                infos.putDouble("ValorVenda",valor);
//                intent.putExtras(infos);
//                startActivity(intent);
//            }
//        });


    }


//    private void AtualizarChopp(){
//
//
//        EscolherChopControlador e = new EscolherChopControlador();
//        valor = e.calcularValorTotalDoChop(copoResultP,copoResultG);
//        String valorFormatado = NumberFormat.getCurrencyInstance().format(valor);
//
//        txtPreco.setText(valorFormatado);}


}
