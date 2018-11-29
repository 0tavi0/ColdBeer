package com.coldbeer.coldbeer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coldbeer.ControladorDePrecos;
import com.coldbeer.banco.BarrilDao;
import com.coldbeer.banco.CopoDao;
import com.coldbeer.controladores.MailControlador;
import com.coldbeer.entidades.Barril;
import com.coldbeer.entidades.Copo;

import java.text.NumberFormat;

public class EscolhaQtdChopp extends AppCompatActivity {

    private Button btn1x, btn2x, btn3x,btnProx,btnCan;
    private TextView precoTotal,preco1x,preco2x,preco3x,valorQTD, desc3copo, desc2copo;
    private double precoTotalVenda;
    private int tamanhoDoCopo, quantidadeCopos;

    private CopoDao dao;
    private BarrilDao BDao;
    private Copo c;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_qtd_chopp);

        btn1x = (Button) findViewById(R.id.btn1x);
        btn2x = (Button) findViewById(R.id.btn2x);
        btn3x = (Button) findViewById(R.id.btn3x);
        btnCan = (Button) findViewById(R.id.btnCan);
        btnProx = (Button) findViewById(R.id.btnProx);
        precoTotal = (TextView) findViewById(R.id.preco_Total);
        preco1x = (TextView) findViewById(R.id.preco1x);
        preco2x = (TextView) findViewById(R.id.preco2x);
        preco3x = (TextView) findViewById(R.id.preco3x);
        valorQTD = (TextView) findViewById(R.id.qtd);
        desc2copo = (TextView) findViewById(R.id.txtDesconto2Copo);
        desc3copo = (TextView) findViewById(R.id.txtDesconto3Copo);

        dao = new CopoDao(this);
        dao.abrir();
        quantidadeCopos=0;







        //Pegando informações da Tela anterior.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        tamanhoDoCopo = extras.getInt("contador");
        switch(tamanhoDoCopo) {
            case 0:
                c = dao.pegarPelaDescricao("G");
                desc2copo.setText("(5% Desconto)");
                desc3copo.setText("(10% Desconto)");
                break;
            case 1:
                c = dao.pegarPelaDescricao("P");
                break;
            default:
                break;

        }



        valorQTD.setText(c.getCapacidade()+"ml");
        preco1x.setText( ControladorDePrecos.formataPreco(1,c.getPreco(), tamanhoDoCopo));
        preco2x.setText(ControladorDePrecos.formataPreco(2,c.getPreco(), tamanhoDoCopo));
        preco3x.setText(ControladorDePrecos.formataPreco(3,c.getPreco(), tamanhoDoCopo));

        btn1x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                precoTotalVenda += ControladorDePrecos.calculaPreco(1,c.getPreco(), tamanhoDoCopo);
                quantidadeCopos += 1;
                precoTotal.setText(formataValorMoeda(precoTotalVenda));

            }
        });

        btn2x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                precoTotalVenda += ControladorDePrecos.calculaPreco(2,c.getPreco(), tamanhoDoCopo);
                quantidadeCopos += 2;
                precoTotal.setText(formataValorMoeda(precoTotalVenda));
            }
        });

        btn3x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                precoTotalVenda += ControladorDePrecos.calculaPreco(3,c.getPreco(), tamanhoDoCopo);
                quantidadeCopos += 3;
                precoTotal.setText(formataValorMoeda(precoTotalVenda));
            }
        });

        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        BDao = new BarrilDao(this);
        BDao.abrir();
        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantidadeCopos>0){
                    Barril barril = BDao.pegarBarrilAtual();

                    System.out.println("Quantidade de Copos: "+quantidadeCopos);
                    System.out.println("Tamanho dos Copos: "+tamanhoDoCopo);

                    if(barril.getCapacidade()<quantidadeCopos*c.getCapacidade()){
                        Toast.makeText(view.getContext(), "Capacidade de chopp excedida!",
                                Toast.LENGTH_LONG).show();
                    }else {

                        if(isOnline()){
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    //MailControlador.enviarNovoPedido(quantidadeCopos, c);
                                }
                            }
                            );
                            thread.start();

                        }
                        Intent intent = new Intent(EscolhaQtdChopp.this, TelaDePagamento.class);
                        Bundle infos = new Bundle();
                        infos.putInt("Qtd", quantidadeCopos);
                        infos.putInt("TipoCopo", tamanhoDoCopo);
                        infos.putDouble("ValorVenda", precoTotalVenda);
                        intent.putExtras(infos);
                        startActivity(intent);
                        dao.fechar();
                        finish();
                    }
                }
            }
        });

    }




    private String formataValorMoeda(Object valor){
        return NumberFormat.getCurrencyInstance().format(valor);

    }

    @Override
    protected void onResume() {
        dao.fechar();
        super.onResume();
    }

    public boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }
        catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Erro ao verificar se estava online! (" + ex.getMessage() + ")", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
