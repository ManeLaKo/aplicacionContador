package com.example.contadordef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity_juego extends AppCompatActivity {

    TextView contador;
    ImageButton billetes;
    double num = 0;
    int valorClick = 1;
    double millones = 0;
    double miles = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_juego);

        contador = findViewById(R.id.tw_contador);
        billetes = findViewById(R.id.ib_dolar);

        billetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumar(v);
            }
        });
    }

    public void tienda(View v) {
        Intent intent = new Intent(this, MainActivity_tienda.class);
        intent.putExtra("valor", num);
        intent.putExtra("clicks", valorClick);
        startActivity(intent);
    }

    public void sumar(View view) {
        Intent intent1 = getIntent();
        if (intent1.hasExtra("nuevoValor")) {
            num = intent1.getDoubleExtra("nuevoValor", num);
        }
        if (intent1.hasExtra("nuevoClick")) {
            valorClick = intent1.getIntExtra("nuevoClick", 1);
        }

        num = num + valorClick;
        actualizarContador();
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
        billetes.startAnimation(anim);


    }

    public void actualizarContador() {
        runOnUiThread(() ->{
            String texto;
            if (num >= 1000000) {
                millones = num / 1000000;
                texto = String.format(Locale.getDefault(), "%.1f Millones", millones);
            } else if (num >= 1000) {
                miles = num / 1000;
                texto = String.format(Locale.getDefault(), "%.1f Mil", miles);
            } else {
                texto = String.valueOf(num);
            }
            contador.setText(texto);
        });
    }

}