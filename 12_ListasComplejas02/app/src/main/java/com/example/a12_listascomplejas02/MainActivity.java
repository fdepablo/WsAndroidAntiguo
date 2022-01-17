package com.example.a12_listascomplejas02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.a12_listascomplejas02.adaptador.AdaptadorCoches;
import com.example.a12_listascomplejas02.entidad.Coche;

import java.util.ArrayList;
import java.util.List;

//Ejercicio parecido al anterior pero esta vez el layout que vamos a utilizar
//para cada fila del adapter lo hemos creado nosotros
//a la izquierda pondremos una foto y a la derecha pondremos la marca y el modelo
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Coche> listaCoches = new ArrayList<Coche>();

        Coche coche = new Coche();
        coche.setMarca("Renault");
        coche.setModelo("Megane");
        coche.setId(1);
        coche.setIdImagen(R.drawable.renault);

        listaCoches.add(coche);

        coche = new Coche();
        coche.setMarca("Peugeot");
        coche.setModelo("406");
        coche.setId(2);
        coche.setIdImagen(R.drawable.peugeot);

        listaCoches.add(coche);

        //Add la lista personalizada que hemos creado
        final AdaptadorCoches adaptadorCoches =
                new AdaptadorCoches(this,
                        R.layout.lista_item_coche,
                        listaCoches);

        final ListView lista = (ListView)findViewById(R.id.listaCoches);
        lista.setAdapter(adaptadorCoches);

    }

}
