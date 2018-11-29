package com.coldbeer.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Fernando on 17/01/2018.
 */

public class BancoOpenHelper extends SQLiteOpenHelper {

    public BancoOpenHelper(Context context){
        super(context,"coldbeer.db",null,1);


    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String comando = "CREATE TABLE IF NOT EXISTS copos (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "descricao TEXT,"+
                "capacidade INTEGER,"+
                "preco REAL)";
        sqLiteDatabase.execSQL(comando);
        comando = "CREATE TABLE IF NOT EXISTS barils (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "data_instalacao TEXT,"+
                "capacidade INTEGER," +
                "status INTEGER)";
        sqLiteDatabase.execSQL(comando);
        comando = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login TEXT,"+
                "senha TEXT)";
        sqLiteDatabase.execSQL(comando);
        ContentValues content = new ContentValues();
        content.put("descricao","P");
        content.put("capacidade",300);
        content.put("preco",10);
        sqLiteDatabase.insert("copos",null,content);
        content = new ContentValues();
        content.put("descricao","G");
        content.put("capacidade",500);
        content.put("preco",20);
        long id = sqLiteDatabase.insert("copos",null,content);

        content = new ContentValues();
        content.put("login","cold");
        content.put("senha","123456");
        id = sqLiteDatabase.insert("usuarios",null,content);

        content = new ContentValues();
        content.put("data_instalacao",new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()));
        content.put("capacidade",5000);
        content.put("status",1);
        sqLiteDatabase.insert("barils",null,content);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
