package com.coldbeer.coldbeer;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.coldbeer.banco.BarrilDao;
import com.coldbeer.banco.CopoDao;
import com.coldbeer.controladores.ConnectionArduinoAsyncTask;
import com.coldbeer.controladores.MailControlador;
import com.coldbeer.entidades.Barril;
import com.coldbeer.entidades.Copo;

import java.util.Timer;

public class IniciarChopp extends AppCompatActivity {

    private int tipoCopo, atual;
    private int quantidadeCopo, capcopo;
    private String x, y, msg;
    private TextView txtMsg, txtSegundos;
    private Button btnEncher;
    private BarrilDao barrilDao;
    private Barril barril;
    private Copo c;
    private CopoDao dao;
    private AlertDialog mAlertDialog;

    static final int TIME_OUT = 5000;

    static final int MSG_DISMISS_DIALOG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_chopp);

        alerta();

//        //Pegando informações da Tela anterior.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        tipoCopo = extras.getInt("TipoCopo");
        quantidadeCopo = extras.getInt("Qtd");
        barrilDao = new BarrilDao(this);
        barrilDao.abrir();
        barril = barrilDao.pegarBarrilAtual();
        dao = new CopoDao(this);
        dao.abrir();
        txtMsg = (TextView) findViewById(R.id.txtCopo);
//
        switch (tipoCopo) {
            case 0:
                c = dao.pegarPelaDescricao("G");
                txtMsg.setText(c.getCapacidade() + "ML");
                break;
            case 1:
                c = dao.pegarPelaDescricao("P");
                txtMsg.setText(c.getCapacidade() + "ML");
                break;
            default:
                finish();
                break;

        }
//        dao.fechar();
        btnEncher = (Button) findViewById(R.id.btnIniciar);


        atual = quantidadeCopo;
       // ativarBluetooth();

        btnEncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (atual > 0) {


                    AlertDialog.Builder alerta = new AlertDialog.Builder(IniciarChopp.this);
                    alerta.setTitle("ATENÇÃO!!!");
                    alerta
                            .setCancelable(false)
                            .setMessage("Aguarde até completar o copo. Quando terminar clique em OK ")
                            .setPositiveButton("Ok", null);

                    AlertDialog alertDialog = alerta.create();
                    alertDialog.show();


                    //Destativando conexão com Arduino
//                    ConnectionArduinoAsyncTask conection = new ConnectionArduinoAsyncTask(btnEncher, mBluetoothAdapter);
//                    conection.execute(Integer.toString(tipoCopo));



                    atual--;
                    barril.dimCapacidade(c.getCapacidade());
                    barrilDao.atualizarCapacidade(barril.getCapacidade());

                    if (atual == 0) {
                        btnEncher.setBackgroundResource(R.drawable.btn_next);
                        //Destativando conexão com Arduino
                        //btnEncher.setEnabled(false);
                    }


                } else {
                    Intent intent = new Intent(IniciarChopp.this, TelaAproveiteChopp.class);
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
//                            MailControlador.enviarEncheuOCopo(c, quantidadeCopo, barril);
                        }
                    }
                    );
                    thread.start();

                    startActivity(intent);
                    finish();
                }

            }

        });


    }

    BluetoothAdapter mBluetoothAdapter;

    public void desativaBluetooth() {
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }
    }

    public void ativarBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        }
    }

    public void alerta() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(IniciarChopp.this);
        alerta.setTitle("Aviso!!!");
        alerta
                .setMessage("Coloque seu copo na bandeja. Assim que estiver pronto só clicar em iniciar!")
                .setCancelable(false)
                .setPositiveButton("Ok", null);

        AlertDialog alertDialog = alerta.create();
        alertDialog.show();
    }


    private void alertaComTempo() {
        int segundos = 10;
        try {
            for (int i = segundos; i > 0; i--) {
                System.out.println(i + " segundos");
                Thread.sleep(1000); // 1 segundo
            }

            AlertDialog.Builder alerta = new AlertDialog.Builder(IniciarChopp.this);
            alerta.setTitle("Aviso!!!");
            alerta
                    .setCancelable(false)
                    .setMessage("Coloque o próximo copo...")
                    .setPositiveButton("Ok", null);

            AlertDialog alertDialog = alerta.create();
            alertDialog.show();

            System.out.println("Terminei!");
        } catch (InterruptedException e) {
            System.out.println("Interromperam meu sono!");
        }


    }


}
