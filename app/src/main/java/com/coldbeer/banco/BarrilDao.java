package com.coldbeer.banco;

/**
 * Created by Fernando on 28/01/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coldbeer.entidades.Barril;

import java.util.ArrayList;
import java.util.List;

public class BarrilDao {
    private SQLiteDatabase db;
    private BancoOpenHelper banco;

    private String dbname = "barils";

    public BarrilDao(Context context){
        banco = new BancoOpenHelper(context);

    }

    public void abrir(){
        db = banco.getWritableDatabase();
    }

    public void fechar(){
        db.close();
    }
    public Barril criarBarril(Barril barril){
        ContentValues content = new ContentValues();
        content.put("data_instalacao",barril.getData_instalacao());
        content.put("capacidade",barril.getCapacidade());
        content.put("status",barril.getStatus());
        long id = db.insert(dbname,null,content);
        barril.setId(id);
        return barril;
    }

    public Barril pegarBarrilAtual(){
        Barril retorno = null;
        Cursor cursor = db.query(dbname,null,"status = 0",null,null,null,null);
        while (cursor.moveToNext()){

            Barril barril;
            barril = new Barril(cursor.getString(1),cursor.getInt(2));
            barril.setId(cursor.getLong(0));
            retorno = barril;
        }
        cursor.close();
        return retorno;

    }

    public void removerBarril(Barril barril){
        long id = barril.getId();
        db.delete(dbname,"_id ="+id,null);
    }

    public List<Barril> lerBarril(){
        List<Barril> lista = new ArrayList<Barril>();
        Cursor cursor = db.query(dbname,null,null,null,null,null,null);
        while (cursor.moveToNext()){

            Barril barril;
            barril = new Barril(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
            lista.add(barril);
        }
        cursor.close();
        return lista;
    }

    public int atualizarCapacidade(int capacidade){
        ContentValues content = new ContentValues();
        content.put("capacidade",capacidade);
        int update = db.update(dbname, content, "status = " + 0, null);
        return update;

    }
    public int fecharBarrils(){
        ContentValues content = new ContentValues();
        content.put("status",1);
        int update = db.update(dbname, content, "status = " + 0, null);
        return update;

    }



}
