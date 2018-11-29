package com.coldbeer.coldbeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.coldbeer.banco.BarrilDao;
import com.coldbeer.banco.CopoDao;
import com.coldbeer.entidades.Barril;
import com.coldbeer.entidades.Copo;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class PrecosEBarril extends AppCompatActivity {
    Copo copo1, copo2;
    Button btnNovoBarril, btnMesmoBarril, btnOk;
    TextView txtCapCopo1,txtCapCopo2;
    EditText edtPrecoCopo1,edtPrecoCopo2,edtCapBarril;
    Barril b;
    CopoDao copoDao;
    BarrilDao barrilDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precos_ebarril);
        copoDao = new CopoDao(this);
        copoDao.abrir();
        List<Copo> copoList = copoDao.lerCopos();
        if(copoList.size()==2){
            copo1 = copoList.get(0);
            copo2 = copoList.get(1);
        }


        barrilDao = new BarrilDao(this);
        barrilDao.abrir();
        b = barrilDao.pegarBarrilAtual();
//        System.out.println("----------------"+b.getCapacidade()+"----------------------");


        btnNovoBarril = (Button) findViewById(R.id.btnNovoBarril);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnMesmoBarril = (Button) findViewById(R.id.btnMesmoBarril);

        txtCapCopo1 = (TextView) findViewById(R.id.txtCapCopo1);
        txtCapCopo2 = (TextView) findViewById(R.id.txtCapCopo2);

        edtPrecoCopo1 = (EditText) findViewById(R.id.edtPrecoCopo1);
        edtPrecoCopo2 = (EditText) findViewById(R.id.edtPrecoCopo2);
        edtCapBarril = (EditText) findViewById(R.id.edtBarril);

        txtCapCopo1.setText(copo1.getCapacidade()+"ml");
        txtCapCopo2.setText(copo2.getCapacidade()+"ml");

        edtPrecoCopo1.setText(Double.toString(copo1.getPreco()));
        edtPrecoCopo2.setText(Double.toString(copo2.getPreco()));
        if(b!=null){
            edtCapBarril.setText(Integer.toString(b.getCapacidade()));
        }else{
            edtCapBarril.setText("0");
        }


        btnNovoBarril.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b!=null){
                    barrilDao.fecharBarrils();
                }
                String dataFormatada = DateFormat.getDateInstance().format(new Date());
                int cap = Integer.parseInt(edtCapBarril.getText().toString());
                Barril novo = new Barril(dataFormatada,cap);
                b = barrilDao.criarBarril(novo);
                btnOk.setEnabled(true);
                btnNovoBarril.setEnabled(false);

            }
        });

        btnMesmoBarril.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int novo = Integer.parseInt(edtCapBarril.getText().toString());
                if(novo!=b.getCapacidade()){
                    barrilDao.atualizarCapacidade(novo);
                }
                btnOk.setEnabled(true);

            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarPreco(copo1,edtPrecoCopo1);
                atualizarPreco(copo2,edtPrecoCopo2);
                barrilDao.fechar();
                copoDao.fechar();
                finish();
                Intent intent = new Intent(PrecosEBarril.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private String formataValorMoeda(Object valor){
        return NumberFormat.getCurrencyInstance().format(valor);

    }
    private double formataTextoMoeda(String valor){
        try {
            return NumberFormat.getCurrencyInstance().parse(valor).doubleValue();
        } catch (ParseException e) {
            return 0;
        }

    }
    private void atualizarPreco(Copo copo, EditText edtiText){
        double novoPrecoCopo01 = Double.parseDouble(edtiText.getText().toString());
        if(novoPrecoCopo01!=copo.getPreco()){
            copoDao.atualizarPreco(copo.getDescricao(),novoPrecoCopo01);
        }
    }
}
