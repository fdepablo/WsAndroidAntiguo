package com.example.a11_listassimples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Es muy habitual en las aplicaciones el tener un lista de elementos del mismo
tipo que queremos mostrar de una manera ordenada.

Para este tipo de casos podemos usar de manera basica el componente ListView
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        //Obtenemos los paises que hemos declarado en arrays.xml
        final String[] arrayPaises = getResources().getStringArray(
                R.array.paises);

        //Lo convertimos a la clase ArrayList
        final List<String> arrayListPaises = new ArrayList<>(Arrays.asList(arrayPaises));

        //Siempre que trabajemos con listas necesitamos un objeto intermedio
        //entre la vista y el modelo. En este caso la vista sería ListView
        //y el modelo seria el arrayListPaises.
        //Dicho objeto sera un adaptador (Adapter). Asi pues tenemos que
        //crear un adaptador para inyectarselo al ListView.

        //Un adaptador es un objeto de una clase que implementa la interfaz Adapter.
        //Este actúa como un enlace entre un conjunto de datos y un ListView

        // El conjunto de datos puede ser cualquier cosa que presente datos en una
        // manera estructurada. Arreglos, objetos List y objetos Cursor(BBDD) con usados,
        // por lo general, con conjuntos de datos.

        //Un adaptador es responsable de recuperar información desde un conjunto de datos y
        //de generar objetos View mediante esos datos.
        //Los objetos View generados son usados, hasta entonces, para llenar cualquier
        //adaptador vista que esté sujeto al adaptador.

        //Un adaptador basico lo podemos crearlos a partir de
        //1- El contexto de android
        //2- El layout que vamos a utilizar para pintar los datos del array, para esto
        //android nos da una serie de layouts preestablecidos.
        //3- El conjunto de datos que queremos pintar.
        final ArrayAdapter<String> arrayStringAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                arrayListPaises
        );

        //Inyectamos el adaptador a la vista
        listView.setAdapter(arrayStringAdapter);

        //Al pulsar simplemente sacamos un Toast con el elemento pulsado
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //Parent, es el adaptador que estamos utilizando
            //View, es el componente que lanza el evento
            //position, es la posicion dentro del listView con la que hemos
            //interactuado.
            //id, id del elemento con el que hemos interactuado.
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {

                String s = (String) parent.getItemAtPosition(position);
                //Tambien podemos hacer referencia al adaptador directamente
                Log.v("MainActivity","**Pais selecccionado: "+arrayStringAdapter.getItem(position));
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

        //Este evento se lanza si dejamos presionado un item del ListView
        //En este caso el objetivo es borrar el elemento del ListView
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view,
                                           int position,
                                           long id) {
                //Podemos hacerlo de dos maneras

                //Una manera con las referencias declaradas
                //arrayListPaises.remove(position);

                //La otra obteniendo el arrayAdapter a partir de la referencia parent
                ArrayAdapter<String> arrayAdapter = (ArrayAdapter)parent.getAdapter();
                String itemABorrar = arrayAdapter.getItem(position);
                String s = "Borrado el pais " + itemABorrar;
                arrayAdapter.remove(itemABorrar);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                //Refrescamos el adapter para que salgan los nuevos cambios
                arrayAdapter.notifyDataSetChanged();

                //Evitamos que se siga propagando al evento onClick normal devolviendo true
                return true;
            }
        });


    }
}