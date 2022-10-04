package com.example.deducirnumero;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminBase extends SQLiteOpenHelper {

    public AdminBase (Context context, String nombre, SQLiteDatabase.CursorFactory, int version){
       super(context,nombre,factory,version);

       @Override
       public void onCreate(SQLiteDatabase db) {
           db.execSQL("create table usuario (dni integer primary key, nombre text, ciudad text, numero integer");
       }
       @Override
       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       }
   }
}
