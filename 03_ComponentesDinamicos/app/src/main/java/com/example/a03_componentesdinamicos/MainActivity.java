package com.example.a03_componentesdinamicos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

//Aplicación de un boton que crea botones al pulsar
//Si pulsamos el boton creado nos dira su nombre
//Si dejamos pulsado el boton creado se eliminara
public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutBotones;
    private Button botonCrearBotones;
    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutBotones = findViewById(R.id.layoutBotones);

        botonCrearBotones = findViewById(R.id.crearBoton);

        botonCrearBotones.setOnClickListener((v) -> {
            //Pare crear View o Componentes dinamicamente debemos
            //de objeter el contexto de android.
            //El contexto es un espacio dentro de la JVM donde
            //se van a depositar objetos. Estos objetos a diferencia
            //de java no son anonimos, sino que tienen un Id para
            //objeterlos (findViewById).
            //Ademas el contexto de Android me va a ayudar a mantener
            //el ciclo de vida de los objetos (IoC)

            //Para crear componentes necesitamos el contexto de
            //Android.

            //El contexto en android se puede sacar de varios
            //sitios:
            // 1. this
            // 2. MainActivity.this
            // 3. getApplicationContext()
            // 4. v.getContext()

            Button botonCreado = new Button(this);

            //Tenemos que dar al boton unos parametos como lo que va a ocupar en la pantanlla
            //ActionBar.LayLayoutParams es una clase que lleva la anchura y la altura
            //de los componentes
            ActionBar.LayoutParams layoutParams;
            //El primer valor del parametro representa el width
            //El segundo valor del parametro represnta el height
            layoutParams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            //Add al boton el layout
            botonCreado.setLayoutParams(layoutParams);
            //le decimos que el boton se situe lo más arriba posible
            botonCreado.setGravity(Gravity.TOP);
            //Establecemos el texto del boton
            botonCreado.setText("Boton Creado " + contador++);
            //Al boton creado le metemos un evento cuando alguien lo pulse
            botonCreado.setOnClickListener(v2 -> {
                Button boton = (Button) v2;
                //Se puede imprimir por consola mediante la clase Log
                Log.v("MainActivity", "Pulsado el boton " + boton.getText());
                Toast.makeText(this,
                        "Ha pulsado el boton " + boton.getText(),
                        Toast.LENGTH_SHORT).show();
            });

            //Establecemos un comportamiento para cuando dejen pulsado el boton
            //En este caso queremos borrar el boton del linear layout
            botonCreado.setOnLongClickListener(v3 -> {
                //Primero preguntamos cual es la vista padre del componente
                //Que en este caso sabemos que es un viewGroup
                ViewGroup vg = (ViewGroup)v3.getParent();
                //Una vez obtenido el elemento padre, le dijo que borre el objeto
                //hijo, que en este caso sera el propio boton que desencadena
                //el evento (v3)
                vg.removeView(v3);
                return true;
            });
            //Añadimos al layout creado desde el principio el boton creado
            layoutBotones.addView(botonCreado);
        });

    }
}