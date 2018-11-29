package com.coldbeer.coldbeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.coldbeer.banco.UsuarioDao;

public class LoginCold extends AppCompatActivity {

    Button btnLogin;
    EditText edtLogin, edtSenha;
    UsuarioDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cold);
        dao = new UsuarioDao(this);
        dao.abrir();

        btnLogin = (Button) findViewById(R.id.btnIniciar);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dao.verificarUsuario(edtLogin.getText().toString(), edtSenha.getText().toString())){
                    Intent intent = new Intent(LoginCold.this,PrecosEBarril.class);
                    startActivity(intent);
                    dao.fechar();
                    finish();
                }
            }
        });


    }
}
