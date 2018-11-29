package com.coldbeer.controladores;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;

import com.coldbeer.entidades.Barril;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import static java.lang.Thread.sleep;

/**
 * Created by Fernando on 19/01/2018.
 */

public class ConnectionArduinoAsyncTask extends AsyncTask<String,String,String> {

    private final BluetoothAdapter bluetoothAd;
    private BluetoothSocket btSocket = null;
    private String btDevAddress = "98:D3:32:70:8B:76";
    private String myUUID = "00001101-0000-1000-8000-00805F9B34FB";
    private Button btnEncher;
    private Barril barril;



    public ConnectionArduinoAsyncTask( Button btn, BluetoothAdapter mb){
        this.btnEncher = btn;
        this.bluetoothAd = mb;


    }

    @Override
    protected String doInBackground(String... strings) {
        System.out.println("_______________________________INICIO_________________________________________");



        BluetoothAdapter btAdapter = bluetoothAd;

        if(!btAdapter.isDiscovering()){
            btAdapter.startDiscovery();
        }
        System.out.println(btAdapter.isEnabled());

        System.out.println(btAdapter.isDiscovering());

        try {

                /*  Obtem uma representação do dispositivo Bluetooth com
                endereço btDevAddress.
                    Cria um socket Bluetooth.
                 */
            if(!btAdapter.isEnabled()) {
                btAdapter.enable();
            }
            BluetoothDevice btDevice = btAdapter.getRemoteDevice(btDevAddress);
            System.out.println("_______________________________________");
            System.out.println(btDevice.getAddress());
            System.out.println(btDevice.getName());
            System.out.println("_______________________________________");
            btSocket = btDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(myUUID));
            System.out.println("_______________________________________");
            System.out.println(btDevice.getAddress());
            System.out.println(btDevice.getName());
            System.out.println(btSocket.isConnected());
            System.out.println("_______________________________________");
                /*  Envia ao sistema um comando para cancelar qualquer processo
                de descoberta em execução.
                 */
            btAdapter.cancelDiscovery();

                /*  Solicita uma conexão ao dispositivo cujo endereço é
                btDevAddress.
                    Permanece em estado de espera até que a conexão seja
                estabelecida.
                 */

                Thread.sleep(1000);
            if (btSocket!=null) {
                btSocket.connect();
            }

        } catch (IOException e) {

                /*  Caso ocorra alguma exceção, exibe o stack trace para debug.
                    Envia um código para a Activity principal, informando que
                a conexão falhou.
                 */
            e.printStackTrace();
            System.out.println(e.getMessage());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(btSocket!=null)
        if(btSocket.isConnected()) {
            tratarCliente(btSocket, strings[0]);
            //publishProgress("OK");
        }

        return msg;
    }


    private String msg;

    private void tratarCliente(BluetoothSocket socket,String s) {
        BluetoothSocket cliente;
        InputStream entrada;
        OutputStream saida;
        if(s.equals("0")){
            s="G";
        }else if(s.equals("1")){
            s="P";
        }
        //s = s+"-klfsnklanlkan89426489$%#$#";
        try {
            cliente = socket;
            entrada = cliente.getInputStream();
            saida = cliente.getOutputStream();
            //while(true){
                publishProgress("off");
                System.out.println("Enviando Dados");
                saida.write(s.getBytes());
                saida.flush();
                InputStreamReader a = new InputStreamReader(entrada);
                BufferedReader bf = new BufferedReader(a);
                msg = bf.readLine();

                //if(msg.equals(s+"-OK")){
                  //  msg = "OK";
                //}
            System.out.println("Dados Recebidos: "+msg);

            sleep(1000);
                socket.close();
                //saida.write(("OK").getBytes());
                //saida.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

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
        if(btSocket!=null){
            try {
                btSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
