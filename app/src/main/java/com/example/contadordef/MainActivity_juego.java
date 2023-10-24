package com.example.contadordef;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
    double costoMejora = 100;


    private final ActivityResultLauncher<Intent> tiendaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        if (data.hasExtra("nuevoValor")) {
                            num = data.getDoubleExtra("nuevoValor", num);
                        }
                        if (data.hasExtra("nuevoClick")) {
                            valorClick = data.getIntExtra("nuevoClick", 1);
                        }
                        if (data.hasExtra("costoMejora")) {
                            costoMejora = data.getDoubleExtra("costoMejora", costoMejora);
                        }
                        actualizarContador();
                    }
                }
            }
    );

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

    private static final int REQUEST_CODE_TIENDA = 1;

    public void tienda(View v) {
        Intent intent = new Intent(this, MainActivity_tienda.class);
        intent.putExtra("valor", num);
        intent.putExtra("clicks", valorClick);
        intent.putExtra("costoMejora", costoMejora);
        tiendaLauncher.launch(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TIENDA && resultCode == RESULT_OK) {
            if (data.hasExtra("nuevoValor")) {
                num = data.getDoubleExtra("nuevoValor", num);
            }
            if (data.hasExtra("nuevoClick")) {
                valorClick = data.getIntExtra("nuevoClick", 1);
            }
            actualizarContador();
        }
    }


}