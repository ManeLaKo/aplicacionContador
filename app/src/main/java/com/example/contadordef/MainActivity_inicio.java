package com.example.contadordef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity_inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio);
    }

    public void EmpezarPartida(View v){
        Intent i = new Intent(this, MainActivity_juego.class);
        startActivity(i);
    }

    public void Info(View v){
        Intent i = new Intent(this, MainActivity_info.class);
        startActivity(i);
    }

    public void Ajustes(View v){
        Intent i = new Intent(this, MainActivity_ajustes.class);
        startActivity(i);
    }

    public void Salir(View v){
        finish();
    }

}