package com.coldbeer.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coldbeer.entidades.Copo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 17/01/2018.
 */

public class CopoDao {
    private SQLiteDatabase db;
    private BancoOpenHelper banco;

    private String dbname = "copos";

    public CopoDao(Context context){
        banco = new BancoOpenHelper(context);
    }

    public void abrir(){
        db = banco.getWritableDatabase();
    }

    public void fechar(){
        db.close();
    }
    public Copo criarCopo(Copo copo){
        ContentValues content = new ContentValues();
        content.put("descricao",copo.getDescricao());
        content.put("capacidade",copo.getCapacidade());
        content.put("preco",copo.getPreco());
        long id = db.insert(dbname,null,content);
        copo.setId(id);
        return copo;
    }

    public Copo pegarPelaDescricao(String descricao){
        Cursor cursor = db.query(dbname,null,"descricao = '"+descricao+"'",null,null,null,null);
        while (cursor.moveToNext()){

            Copo c;
            c = new Copo(cursor.getString(1),cursor.getInt(2),cursor.getDouble(3));
            c.setId(cursor.getLong(0));
            return c;
        }
        return null;

    }

    public void removerCopo(Copo copo){
        long id = copo.getId();
        db.delete(dbname,"_id ="+id,null);
    }

    public List<Copo> lerCopos(){
        List<Copo> lista = new ArrayList<Copo>();
        Cursor cursor = db.query(dbname,null,null,null,null,null,null);
        while (cursor.moveToNext()){

            Copo c;
            c = new Copo(cursor.getString(1),cursor.getInt(2),cursor.getDouble(3));
            c.setId(cursor.getLong(0));
            lista.add(c);
        }
        cursor.close();
        return lista;
    }

    public int atualizarPreco(String descricao, double preco){
        ContentValues content = new ContentValues();
        content.put("preco",preco);
        int update = db.update(dbname, content, "descricao = '" + descricao+"'", null);
        return update;

    }



}
