package com.login.estadios;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.fragment.app.FragmentActivity;

public class map extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final db db = new db(getApplicationContext());
        Cursor esta = db.todosEstadio();
        while (esta.moveToNext()) {
            String latitud = esta.getString(esta.getColumnIndexOrThrow("LATITUD"));
            String longitud = esta.getString(esta.getColumnIndexOrThrow("LONGITUD"));
            String nombre = esta.getString(esta.getColumnIndexOrThrow("_id"));

            LatLng x = new LatLng(Double.parseDouble(latitud), Double.parseDouble(longitud));
            mMap.addMarker(new MarkerOptions().position(x).title(nombre));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(x));
        }

    }
}