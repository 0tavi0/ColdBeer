package com.coldbeer.controladores;

import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import com.coldbeer.entidades.Pagamento;

import br.uol.pagseguro.client.plugpag.PlugPag;

/**
 * Created by Fernando on 19/01/2018.
 */

public class ConnectionPagSeguroAsyncTask extends AsyncTask<String,String,String> {

    private BluetoothSocket btSocket = null;
    private String btDevAddress = "B0:F1:EC:B5:22:F9";
    private String myUUID = "fa87c0d0-afac-11de-8a39-0800200c9a66";
    private TextView edtEnvio;
    private Button btProx,btCan, btCred, btDeb;
    private double valor;
    String valorTotal;

    public ConnectionPagSeguroAsyncTask(TextView edtEnvio, double valor, Button btProx, Button btnCancel, Button btnDeb, Button btnCred){
        this.edtEnvio = edtEnvio;
        this.btProx = btProx;
        this.valor = valor;
        this.valorTotal = Integer.toString((int)valor*100);
        this.btCan = btnCancel;
        this.btCred = btnCred;
        this.btDeb = btnDeb;
    }
    //10,00 -> 1000

    @Override
    protected String doInBackground(String... strings) {
        int metodo = 0;
        if(strings[0].equals("1")){
             metodo = PlugPag.CREDIT;

        }
        else if (strings[0].equals("2")){
            metodo = PlugPag.DEBIT;
        }

        int tipoParcelamento = PlugPag.A_VISTA;
        int numeroParcelas = 1;
        String codigoVenda = "COLDBEEREXP";

        PlugPag plugPag = new PlugPag();
        plugPag.InitBTConnection(btDevAddress);
        plugPag.SetVersionName("ColdBeerExpress","1.0");
        int ret = plugPag.SimplePaymentTransaction(
                metodo,
                tipoParcelamento,
                numeroParcelas,
                valorTotal,
                codigoVenda
        );
        final Pagamento p = new Pagamento(
                metodo,
                tipoParcelamento,
                numeroParcelas,
                valorTotal,
                codigoVenda,
                ret,
                plugPag.getTime(),
                plugPag.getDate()
        );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                MailControlador.enviarNovoPagamento(p);
            }
        });
        thread.start();



        if(ret ==0){
            return "OK";
        }

        return "Erro" + ret;
    }


    private String msg;



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s.trim().equals("OK")){

            btProx.setEnabled(true);
            btCan.setEnabled(false);
            btDeb.setEnabled(false);
            btCred.setEnabled(false);
            edtEnvio.setText("Pagamento efetuado com sucesso!");
        }else{

            edtEnvio.setText(s);
        }
    }
}
