package com.coldbeer.coldbeer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.coldbeer.entidades.Copo;

import java.text.NumberFormat;
import java.text.ParseException;

public class TelaAdministrativa extends AppCompatActivity {
    Copo copo1, copo2;
    Button btnNovoBarril, btnOk;
    TextView txtCapCopo1,txtCapCopo2;
    EditText edtPrecoCopo1,edtPrecoCopo2,edtCapBarril;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.activity_tela_administrativa);
        CopoDao copoDao = new CopoDao(this);
        copoDao.abrir();
        List<Copo> copoList = copoDao.lerCopos();
        if(copoList.size()==2){
            copo1 = copoList.get(0);
            copo2 = copoList.get(1);
        }
        copoDao.fechar();

        BarrilDao barrilDao = new BarrilDao(this);
        barrilDao.abrir();
        Barril b = barrilDao.pegarBarrilAtual();
        barrilDao.fechar();

        btnNovoBarril = (Button) findViewById(R.id.btnNovoBarril);
        btnOk = (Button) findViewById(R.id.btnOk);

        txtCapCopo1 = (TextView) findViewById(R.id.txtCapCopo1);
        txtCapCopo2 = (TextView) findViewById(R.id.txtCapCopo2);

        edtPrecoCopo1 = (EditText) findViewById(R.id.edtPrecoCopo1);
        edtPrecoCopo2 = (EditText) findViewById(R.id.edtPrecoCopo2);
        edtCapBarril = (EditText) findViewById(R.id.edtCapBarril);

        txtCapCopo1.setText(copo1.getCapacidade()+"ml");
        txtCapCopo2.setText(copo2.getCapacidade()+"ml");

        edtPrecoCopo1.setText(formataValorMoeda(copo1.getPreco()));
        edtPrecoCopo2.setText(formataValorMoeda(copo2.getPreco()));
        edtCapBarril.setText(b.getCapacidade());
*/

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


}
