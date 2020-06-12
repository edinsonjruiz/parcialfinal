package com.login.estadios;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class List extends AppCompatActivity {
    ListView lista;
    Button mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lista = (ListView) findViewById(R.id.list);
        mapa = findViewById(R.id.ubicaciones);
        final db db = new db(getApplicationContext());
        Cursor estadio = db.todosEstadio();
        personalizado p = new personalizado(this, estadio, 0);
        lista.setAdapter(p);
        p.notifyDataSetChanged();

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });
    }
}
