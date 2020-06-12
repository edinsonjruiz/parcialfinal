package com.login.estadios;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class personalizado extends android.widget.CursorAdapter {
    public personalizado(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.dato_est, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nombre = (TextView) view.findViewById(R.id.txtnombre);
        TextView ciudad = (TextView) view.findViewById(R.id.txtciudad);
        TextView equipo = (TextView) view.findViewById(R.id.txtequipo);

        // Extract properties from cursor
        String nom = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String equipo1 = cursor.getString(cursor.getColumnIndexOrThrow("EQUIPO"));
        String paisciudad = cursor.getString(cursor.getColumnIndexOrThrow("PAISCIUDAD"));


        // Populate fields with extracted properties
        nombre.setText("Nombre: " + nom);
        ciudad.setText("Ubicación: "+ paisciudad);
        equipo.setText("Equipo: " + equipo1);

    }
}
