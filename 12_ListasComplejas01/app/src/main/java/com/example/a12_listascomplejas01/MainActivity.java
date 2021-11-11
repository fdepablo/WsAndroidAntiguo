package com.example.a12_listascomplejas01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a12_listascomplejas01.modelo.adaptador.AdaptadorCoches;
import com.example.a12_listascomplejas01.modelo.entidad.Coche;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private Button botonAddCoche;
    private EditText textoMarca, textoModelo;
    private ListView lista;
    private AdaptadorCoches adaptadorCoches;
    private int contador = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoMarca = (EditText)findViewById(R.id.textoMarca);
        textoModelo = (EditText)findViewById(R.id.textoModelo);
        botonAddCoche = (Button)findViewById(R.id.botonAddCoche);
        lista = (ListView)findViewById(R.id.listaCoches);

        //Creamos unos coches por defecto
        List<Coche> listaCoches = new ArrayList();
        Coche coche = new Coche();
        coche.setId(contador++);
        coche.setMarca("Renault");
        coche.setModelo("Megane");

        listaCoches.add(coche);

        coche = new Coche();
        coche.setId(contador++);
        coche.setMarca("Peugeot");
        coche.setModelo("406");

        listaCoches.add(coche);

        //En este caso no nos vale el ArrayAdapter ya que ese adaptador esta
        //pensado para arrays que guarden un solo valor.

        //Para ello nos crearemos nuestro propio adaptador
        //El layout que le pasamos a nuestro adaptador esta preparado para
        //mostrar dos elementos uno arriba y en negrita y el otro abajo
        //Podriamos crear nuestro layout personalizado en caso de que lo
        //necesitemos.
        adaptadorCoches = new AdaptadorCoches(this,android.R.layout.two_line_list_item,listaCoches);
        //Inyectamos el adaptador a la lista
        lista.setAdapter(adaptadorCoches);

        //Al pulsar en un elemento de la lista mostramos los datos del coche.
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Coche coche = (Coche) parent.getItemAtPosition(position);
                String s = coche.getMarca() + ":" + coche.getModelo() + ":" + id;
                Log.v(TAG, s);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

        //Al pulsar en el boton de añadir coche damos de alta el coche en
        //la lista.
        botonAddCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Coche coche = new Coche();
                coche.setId(contador++);
                coche.setModelo(textoModelo.getText().toString());
                coche.setMarca(textoMarca.getText().toString());

                adaptadorCoches.addItem(coche);
                //Siempre que cambiemos la fuente de datos tenemos que
                //notificarlo a la vista
                adaptadorCoches.notifyDataSetChanged();
            }
        });

        //Si dejemos pulsado un elemento de la lista lo eliminamos de ella
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Coche coche = (Coche) parent.getItemAtPosition(position);
                String s = "Borrando: " + coche.getMarca() + ":" + coche.getModelo() + ":" + id;
                Log.v(TAG, s);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                adaptadorCoches.deleteItem(coche);
                adaptadorCoches.notifyDataSetChanged();
                return true;
            }
        });
    }
}