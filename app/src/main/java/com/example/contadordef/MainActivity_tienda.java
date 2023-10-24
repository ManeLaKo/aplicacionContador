package com.example.contadordef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_tienda extends AppCompatActivity {

    double num = 0;
    int valorClicks = 1;
    double coste = 100;
    Button boton_mejora;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tienda);
        boton_mejora = (Button) findViewById(R.id.boton_mejora);
    }

    public void mejora(View view){
        Intent intent = getIntent();
        if (intent.hasExtra("valor")){
            num = intent.getDoubleExtra("valor", 0);
        }
        if (intent.hasExtra("clicks")){
            valorClicks = intent.getIntExtra("nuevoClick", 0);
        }

        if (num >= coste){
            num = num - coste;
            valorClicks++;
            coste = coste + 20;
            boton_mejora.setText(coste + " $");

            Intent intent1 = new Intent(this, MainActivity_juego.class);
            intent1.putExtra("nuevoValor", num);
            intent1.putExtra("nuevoClick", valorClicks);
        }
    }
}