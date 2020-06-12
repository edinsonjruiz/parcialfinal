package com.login.estadios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button buscar, guardar, borrar;
    EditText ciudad, pais, nombre, capacidad, equipo;
    final String[] lat = new String[1];
    final String[] lon = new String[1];

    Estadio e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ciudad = findViewById(R.id.ciudad);
        pais = findViewById(R.id.pais);
        nombre = findViewById(R.id.nombre);
        capacidad = findViewById(R.id.capacidad);
        equipo = findViewById(R.id.equipo);
        final db asd = new db(getApplicationContext());
        buscar = findViewById(R.id.lista);
        guardar = findViewById(R.id.save);
        borrar = findViewById(R.id.eliminar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.login.estadios.List.class);
                startActivity(i);
            }
        });



        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (nombre.getText().toString().equals("") || ciudad.getText().toString().equals("") || pais.getText().toString().equals("") || capacidad.getText().toString().equals("") || equipo.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Rellene", Toast.LENGTH_SHORT).show();
                    }else{
                        LatLng latitudYlongitud = getLatLngFromAddress(ciudad.getText().toString()+", "+ pais.getText().toString());
                        lat[0] = String.valueOf(latitudYlongitud.latitude);
                        lon[0] = String.valueOf(latitudYlongitud.longitude);
                            if (!asd.buscarEstadio(nombre.getText().toString())) {
                                e = new Estadio(nombre.getText().toString(), ciudad.getText().toString()+", "+ pais.getText().toString(), equipo.getText().toString(), capacidad.getText().toString(), lat[0], lon[0]);
                                asd.agregarEstadio(e);
                                Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).show();
                                vac();
                            } else {
                                Toast.makeText(getApplicationContext(), "Se repite", Toast.LENGTH_SHORT).show();
                            }

                    }
                }catch (Exception x ){
                    Log.e(null, "Error "+ x.getMessage());
                    Toast.makeText(getApplicationContext(), "Busque ciudad y pais para poder continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Nombre que quiere borrar", Toast.LENGTH_SHORT).show();
                }else{
                    if (!asd.buscarEstadio(nombre.getText().toString())){
                        Toast.makeText(getApplicationContext(), "No se encontró", Toast.LENGTH_SHORT).show();
                    }else{
                        asd.eliminarEstadio(nombre.getText().toString());
                        vac();
                        Toast.makeText(getApplicationContext(), "Se ha borrado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }
    private void vac() {
        ciudad.setText("");
        pais.setText("");
        capacidad.setText("");
        equipo.setText("");
        nombre.setText("");

        lat[0] = "";

    }

    private LatLng getLatLngFromAddress(String i){
        Geocoder geocoder=new Geocoder(MainActivity.this);
        List<Address> addressList;
        try {
            addressList = geocoder.getFromLocationName(i, 1);
            if(addressList!=null){
                Address singleaddress=addressList.get(0);
                LatLng latLng = new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());
                return latLng;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
