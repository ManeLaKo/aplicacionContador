package com.example.contadordef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity_tienda extends AppCompatActivity {

    double num = 0;
    int valorClicks = 1;
    double coste = 0;
    double coste2 = 200;
    boolean automatico = false;
    Button boton_mejora;
    ImageButton boton_mejora1;
    boolean cambioImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tienda);
        boton_mejora = (Button) findViewById(R.id.boton_mejora);
        boton_mejora1 = (ImageButton) findViewById(R.id.boton_mejora1);

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
        if (intent.hasExtra("automatico")) {
            automatico = intent.getBooleanExtra("automatico", false);
            if (automatico == true) {
                boton_mejora1.setImageResource(android.R.drawable.checkbox_on_background);
            }
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
        }
    }

    public void mejora1(View view) {
        if (automatico == true) {
            Toast.makeText(getApplicationContext(), "Ya tienes esta mejora", Toast.LENGTH_SHORT).show();
            finish();
        } else if (num >= coste2) {
            num = num - coste2;
            automatico = true;
            Intent intent = new Intent();
            intent.putExtra("nuevoValor", num);
            intent.putExtra("nuevoClick", valorClicks);
            intent.putExtra("costoMejora", coste);
            intent.putExtra("automatico", automatico);
            boton_mejora1.setImageResource(android.R.drawable.checkbox_on_background);
            cambioImagen = true;
            setResult(RESULT_OK, intent);
        } else{
            mostrarMensaje();
        }
    }

    public void mostrarMensaje() {
        Toast.makeText(getApplicationContext(), "No tienes suficientes créditos", Toast.LENGTH_SHORT).show();
    }

    public void Volver(View v){
        Intent intent = new Intent();
        intent.putExtra("nuevoValor", num);
        intent.putExtra("nuevoClick", valorClicks);
        intent.putExtra("costoMejora", coste);
        intent.putExtra("automatico", automatico);
        setResult(RESULT_OK, intent);
        finish();
    }

}