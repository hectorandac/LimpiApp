package com.limpiapp.hecto.limpiapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

    }

    public void openMap(View v){
        Intent myIntent = new Intent(this, MapsActivity.class);
        this.startActivity(myIntent);
    }

    public void openFactura(View v){
        Intent myIntent = new Intent(this, Facturas.class);
        this.startActivity(myIntent);
    }

    public void openQuejas(View v){
        Intent myIntent = new Intent(this, Quejas.class);
        this.startActivity(myIntent);
    }

    public void openReportes(View v){
        Intent myIntent = new Intent(this, Reportes.class);
        this.startActivity(myIntent);
    }

    public void openContenedor(View v){
        Intent myIntent = new Intent(this, Contenedores.class);
        this.startActivity(myIntent);
    }

    public void openNovedades(View v){
        Intent myIntent = new Intent(this, Novedades.class);
        this.startActivity(myIntent);
    }

    public void showInfo(View v){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create(); //Read Update
        alertDialog.setTitle("Activar notificaciones");
        alertDialog.setMessage("Recibe notificaciones antes del que el camion de la basura pase cerca de tu sector.");

        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "CERRAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.show();
    }
}
