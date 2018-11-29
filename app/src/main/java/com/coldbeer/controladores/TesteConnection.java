package com.coldbeer.controladores;

import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.widget.Button;

import static java.lang.Thread.sleep;

/**
 * Created by Fernando on 19/01/2018.
 */

public class TesteConnection extends AsyncTask<String,String,String> {

    private BluetoothSocket btSocket = null;
    private String btDevAddress = null;
    private String myUUID = "fa87c0d0-afac-11de-8a39-0800200c9a66";
    private Button btnEncher;


    public TesteConnection(String macBt, Button btn){
        this.btDevAddress = macBt;
        this.btnEncher = btn;

    }

    @Override
    protected String doInBackground(String... strings) {
        publishProgress("off");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return msg;
    }


    private String msg = "";


    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        if(values[0].equals("off")){
            btnEncher.setEnabled(false);
        }
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println("_______________________________FIM_________________________________________");
        super.onPostExecute(s);
        btnEncher.setEnabled(true);
    }
}
