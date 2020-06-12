package com.login.estadios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db extends SQLiteOpenHelper {
    private static final String DB_Name = "ESTADIO";
    private static final int version  = 1;
    private static final String tabla = "CREATE TABLE ESTADIO (NOMBRE TEXT PRIMARY KEY, PAISCIUDAD TEXT, EQUIPO TEXT, CAPACIDAD TEXT, LATITUD TEXT, LONGITUD TEXT)";

    public db(Context context) {
        super(context, DB_Name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ tabla);
        db.execSQL(tabla);
    }
    public void agregarEstadio (Estadio i){
        try {
            SQLiteDatabase bd = getWritableDatabase();
            if (bd != null) {
                bd.execSQL("INSERT INTO ESTADIO VALUES('"+ i.getNombre()+"', '"+ i.getPaisCiudad() +"', '"+i.getEquipo()+"', '"+ i.getCapacidad()+"', '"+ i.getLatitud()+"' , '"+ i.getLongitud()+"')");
                bd.close();
            }
        }catch (Exception ex){
            System.out.println("Error.");
        }
    }
    public Cursor todosEstadio() {
        try {
            SQLiteDatabase bd = getReadableDatabase();
            Cursor c = bd.rawQuery("SELECT NOMBRE AS _id , PAISCIUDAD, EQUIPO, CAPACIDAD, LATITUD, LONGITUD FROM ESTADIO", null);
                return c;
        } catch (Exception ex) {
                     System.out.println("Error.");return null;
        }
    }

    public boolean buscarEstadio (String i){
        String[] args = new String[] {i};
        SQLiteDatabase bd = getReadableDatabase();
        Cursor c = bd.query("ESTADIO", null, "NOMBRE=?", args, null, null, null);
        if (c.getCount()>0) {
            bd.close();
            return true;
        }
        else{
            bd.close();
            return false;}
    }
            public boolean eliminarEstadio (String i){
        try{
            SQLiteDatabase bd = getWritableDatabase();
            String[] args = new String[] {i};
            bd.delete("ESTADIO", "NOMBRE=?",args);
            bd.close();
            return true;
        }
        catch (Exception ex){
            System.out.println("Error.");
            return false;
        }
    }

}
