package com.example.contadordef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity_ajustes extends AppCompatActivity {

    int[] iconos = {R.drawable.def_icono_musica, R.drawable.def_icono_volumen,
            R.drawable.def_icono_tema, R.drawable.def_icono_info};
    int[] boton = {android.R.drawable.checkbox_off_background,android.R.drawable.checkbox_off_background,
            android.R.drawable.checkbox_off_background, android.R.drawable.checkbox_off_background};
    String[] descripciones = {"MÚSICA", "SILENCIAR", "TEMA", "ACERDA DE"};
    RecyclerView rv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ajustes);

        rv1 = (RecyclerView) findViewById(R.id.rv1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv1.setLayoutManager(linearLayoutManager);
        rv1.setAdapter(new AdaptadorAjuste(this));
    }

    private class AdaptadorAjuste extends RecyclerView.Adapter<AdaptadorAjuste.AdaptadorAjusteHolder> {
        private Context context;
        public AdaptadorAjuste(Context context){
            this.context = context;
        }
        @NonNull
        @Override
        public AdaptadorAjusteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorAjusteHolder(getLayoutInflater().inflate(R.layout.itemajustes, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorAjusteHolder holder, int position) {
            holder.imprimir(position);

            if (position == 3) {
                holder.ib1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(context, MainActivity_AcercaDe.class);
                        context.startActivity(i);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return iconos.length;
        }

        private class AdaptadorAjusteHolder extends RecyclerView.ViewHolder{
            TextView tv1;
            ImageView iv1;
            ImageButton ib1;
            public AdaptadorAjusteHolder(@NonNull View itemView) {
                super(itemView);
                iv1 = itemView.findViewById(R.id.imagen_icono);
                tv1 = itemView.findViewById(R.id.textview_ajuste);
                ib1 = itemView.findViewById(R.id.imageButton_ajuste_verificado);
            }
            public void imprimir(int position) {
                iv1.setImageResource(iconos[position]);
                tv1.setText(descripciones[position]);
                ib1.setImageResource(boton[position]);
            }
        }

    }
}