package com.example.estudantebr.myapplicationteste.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.estudantebr.myapplicationteste.models.Usuario;

public class DBHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE = "DataBaseNovo";

    public static final String TABLE_USUARIO = "USUARIO";


    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists " + TABLE_USUARIO +
                " (" +
                "id text primary key, " +
                "nome text, " +
                "sobrenome text, " +
                "idade integer" +
                ")"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //Start -- [Usuario]
    public String changeData(Usuario usuario) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues content = new ContentValues();

        content.put("nome", usuario.getNome());
        content.put("sobrenome", usuario.getSobrenome());
        content.put("idade", usuario.getIdade());

        //definir se inserir ou atualizar
        if (usuario.getId().equals("")) {
            usuario.setId(getUniqueStringId());

            content.put("id", usuario.getId());
            db.insert(TABLE_USUARIO, null, content);

        } else {

            content.put("id", usuario.getId());
            db.update(TABLE_USUARIO, content, "id=?", new String[]{usuario.getId()});

        }

        //todo: salvar no Cloud Firestore

        db.close();
        return usuario.getId();
    }

    public boolean deletar(Usuario usuario) {

        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_USUARIO, "id=?", new String[]{usuario.getId()});

        //todo: salvar no Cloud Firestore

        return true;
    }

    private String getUniqueStringId() {

        //return FirebaseDatabase.getInstance().getReference().push().getKey();

        return "329094301";

    }

    //END -- [Usuario]

}

