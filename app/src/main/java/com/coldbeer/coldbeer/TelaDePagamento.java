package com.coldbeer.coldbeer;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coldbeer.banco.CopoDao;
import com.coldbeer.controladores.ConnectionPagSeguroAsyncTask;
import com.coldbeer.entidades.Copo;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Duration;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

import java.text.NumberFormat;
import java.util.Locale;

public class TelaDePagamento extends AppCompatActivity {
    private int tipoCopo, quantidadeCopo;
    private double valor;
    private TextView txtTipoCopo, txtQtd, txtValorVenda, txtAndamento;
    private Button btnCancelar, btnProx;
    private String mac = "E4:90:7E:62:EF:EA";
    private Button btnCredito, btnDebito;
    private ConnectionPagSeguroAsyncTask pagamento;
    private CopoDao dao;
    private Intent batteryStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_pagamento);
        dao = new CopoDao(this);
        dao.abrir();

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = this.registerReceiver(null, ifilter);

        //Pegando informações da Tela anterior.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        tipoCopo = extras.getInt("TipoCopo");
        quantidadeCopo = extras.getInt("Qtd");
        valor = extras.getDouble("ValorVenda");

        txtTipoCopo = (TextView) findViewById(R.id.txtTipo);
        txtQtd = (TextView) findViewById(R.id.txtQtdG);
        txtValorVenda = (TextView) findViewById(R.id.txtValorTotal);
        txtAndamento = (TextView) findViewById(R.id.txtAndamento);
        btnProx = (Button) findViewById(R.id.btnProx);
        btnDebito = (Button) findViewById(R.id.btnDebito);
        btnCredito = (Button) findViewById(R.id.btnCredito);

        Copo c = null;


        switch (tipoCopo) {
            case 0:
                c = dao.pegarPelaDescricao("G");
                txtTipoCopo.setText(c.getCapacidade() + "ml");
                dao.fechar();
                break;
            case 1:
                c = dao.pegarPelaDescricao("P");
                txtTipoCopo.setText(c.getCapacidade() + "ml");
                dao.fechar();
                break;
            default:
                break;

        }
       // ativarBluetooth();


        txtQtd.setText(String.format(Locale.getDefault(), "%d", quantidadeCopo));
        String valorFormatado = NumberFormat.getCurrencyInstance().format(valor);

        txtValorVenda.setText(valorFormatado);

        btnCancelar = (Button) findViewById(R.id.btnCan);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        btnCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (testaEnergia()) {

                    alerta();
//
//                    pagamento = new ConnectionPagSeguroAsyncTask(txtAndamento,valor,btnProx,btnCancelar,btnDebito,btnCredito);
//                    pagamento.execute("1");
                } else {
                    Toast.makeText(view.getContext(), "Máquina de Chopp sem energia!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        btnDebito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (testaEnergia()) {
                    alerta();
//                    pagamento = new ConnectionPagSeguroAsyncTask(txtAndamento,valor,btnProx,btnCancelar,btnDebito,btnCredito);
//                    pagamento.execute("2");

                } else {
                    Toast.makeText(view.getContext(), "Máquina de Chopp sem energia!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (testaEnergia()) {
                    Intent intent = new Intent(TelaDePagamento.this, IniciarChopp.class);
                    Bundle infos = new Bundle();
                    infos.putInt("Qtd", quantidadeCopo);
                    infos.putInt("TipoCopo", tipoCopo);
                    intent.putExtras(infos);
                    startActivity(intent);
                   // dao.fechar();
                  //  desativaBluetooth();
                    //finish();
                } else {
                    Toast.makeText(view.getContext(), "Máquina de Chopp sem energia!",
                            Toast.LENGTH_LONG).show();
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


    private void apenasTest() {
        btnProx.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnDebito.setEnabled(false);
        btnCredito.setEnabled(false);
        //txtAndamento.setText("Pagamento efetuado com sucesso!");
    }

    @Override
    protected void onResume() {
        dao.fechar();
        super.onResume();
    }

    private boolean testaEnergia() {
// How are we charging?
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        return usbCharge || acCharge;
    }

    public void alerta(){

        new MaterialStyledDialog.Builder(this)
                .setTitle("Aviso!")
                .setDescription("Coloque o cartão de crétido ou débito na máquina.\n\n")
                .setStyle(Style.HEADER_WITH_ICON)
                .withDialogAnimation(true)
                .setPositiveText("OK")

                .withDialogAnimation(true, Duration.FAST)
                .setHeaderColor(R.color.dialogHeader)
                .setIcon(R.drawable.coldlogo)
                .show();

//
//        AlertDialog.Builder alerta = new AlertDialog.Builder(TelaDePagamento.this);
//        alerta.setTitle("Aviso!!!");
//        alerta
//                .setIcon(R.drawable.ic_action_name)
//                .setMessage("Coloque o cartão de crétido ou débito na máquina.")
//                .setCancelable(false)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//
//        AlertDialog alertDialog = alerta.create();
//        alertDialog.show();
    }
}
