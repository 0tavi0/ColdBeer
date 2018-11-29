package com.coldbeer.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coldbeer.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 28/01/2018.
 */


public class UsuarioDao {
    private SQLiteDatabase db;
    private BancoOpenHelper banco;

    private String dbname = "usuarios";

    public UsuarioDao(Context context){
        banco = new BancoOpenHelper(context);
    }

    public void abrir(){
        db = banco.getWritableDatabase();
    }

    public void fechar(){
        db.close();
    }
    public Usuario criarUsuario(Usuario usuario){
        ContentValues content = new ContentValues();
        content.put("login",usuario.getUsuario());
        content.put("senha",usuario.getSenha());
        long id = db.insert(dbname,null,content);
        usuario.setId(id);
        return usuario;
    }

    public boolean verificarUsuario(String login, String senha){
        boolean retorno = false;
        String[] s = {login, senha};
        Cursor cursor = db.query(dbname,null,"login = ? and senha = ?", s,null,null,null);
        while (cursor.moveToNext()){

            retorno = true;
        }
        cursor.close();
        return retorno;
    }

    public void removerBarril(Usuario usuario){
        long id = usuario.getId();
        db.delete(dbname,"_id ="+id,null);
    }

    public List<Usuario> todosUsuarios(){
        List<Usuario> lista = new ArrayList<Usuario>();
        Cursor cursor = db.query(dbname,null,null,null,null,null,null);
        while (cursor.moveToNext()){

            Usuario usuario;
            usuario = new Usuario(cursor.getLong(0),cursor.getString(1),cursor.getString(2));
            lista.add(usuario);
        }
        cursor.close();
        return lista;
    }

}
