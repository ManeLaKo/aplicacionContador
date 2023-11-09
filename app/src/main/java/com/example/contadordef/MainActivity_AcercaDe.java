package com.example.contadordef;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity_AcercaDe extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_acerca_de);

        List<Centro> centros = new ArrayList<>();
        centros.add(new Centro("IES Doctor Fleming", "Doctor Fleming, 7. Oviedo", R.drawable.logo_fleming));
        centros.add(new Centro("IES Monte Naranco", "Pedro Caravía, 9. Oviedo", R.drawable.logo_monte_naranco));
        centros.add(new Centro("CIFP Avilés", "Marqués S/N. Avilés", R.drawable.logo_cifp_aviles));
        centros.add(new Centro("IES Lopez de Mendoza", " Pl. Luis Martín Santos, 0. Burgos", R.drawable.logo_mendoza));
        centros.add(new Centro("IES Alfonso II", " C. Sta. Susana, s/n. Oviedo", R.drawable.logo_alfonso2));

        CentrosAdapter adapter = new CentrosAdapter(this, R.layout.itemcentro, centros);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Centro centro = (Centro) adapterView.getItemAtPosition(i);
        Toast.makeText(this, centro.getNombre(), Toast.LENGTH_LONG).show();
    }

    private static class CentrosAdapter extends ArrayAdapter<Centro> {

        public CentrosAdapter(@NonNull Context context, int resource, @NonNull List<Centro> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Centro c = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemcentro, parent, false);
            }

            ImageView imageView = convertView.findViewById(R.id.imagen_logo);
            TextView nombreTextView = convertView.findViewById(R.id.texto_nombre_centro);
            TextView direccionTextView = convertView.findViewById(R.id.texto_direccion_centro);

            if (c != null) {
                imageView.setImageResource(c.getImagen());
                nombreTextView.setText(c.getNombre());
                direccionTextView.setText(c.getDireccion());
            }

            return convertView;
        }
    }

    public static class Centro {
        private final String nombre;
        private final String direccion;
        private final int imagen;

        public Centro(String nombre, String direccion, int imagen) {
            this.nombre = nombre;
            this.direccion = direccion;
            this.imagen = imagen;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public int getImagen() {
            return imagen;
        }
    }
}
