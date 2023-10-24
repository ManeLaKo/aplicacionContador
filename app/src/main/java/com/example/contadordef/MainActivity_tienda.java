package com.example.contadordef;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_tienda extends AppCompatActivity {

    double num = 0;
    int valorClicks = 1;
    double coste = 0;
    Button boton_mejora;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tienda);
        boton_mejora = (Button) findViewById(R.id.boton_mejora);

        Intent intent = getIntent();
        if (intent.hasExtra("valor")) {
            num = intent.getDoubleExtra("valor", 0);
        }
        if (intent.hasExtra("clicks")) {
            valorClicks = intent.getIntExtra("clicks", 1);
        }
        if (intent.hasExtra("costoMejora")) {
            coste = intent.getDoubleExtra("costoMejora", 100);
            boton_mejora.setText(coste + " $");
        }

    }

    public void mejora(View view) {
        if (num >= coste) {
            num = num - coste;
            valorClicks++;
            coste = coste + 20;
            boton_mejora.setText(coste + " $");

            Intent intent = new Intent();
            intent.putExtra("nuevoValor", num);
            intent.putExtra("nuevoClick", valorClicks);
            intent.putExtra("costoMejora", coste);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}