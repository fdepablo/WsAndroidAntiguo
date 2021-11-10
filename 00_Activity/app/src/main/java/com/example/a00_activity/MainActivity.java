package com.example.a00_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Las actividades son objetos que sirven de intermediarios entre las vistas y la logica.
//Hacen funcion de controlador

//Estos objetos nosotros como programadores no llevamos el control de su ciclo de vida, nos
//limitamos a programarlos. El que lleve el control de su ciclo de vida sería Android. Esta
//carateristica se llama Inversion de Control(IoC). Es decir ,en un entorno nomal nosostros como
//programadores creamos los objetos y mantenemos el objeto vivo mediante referencias. En
//Un entorno de IoC nostros no creamos los objetos ni nosotros nos ocupamos de manener el
//objeto con vida

//https://developer.android.com/guide/components/activities/activity-lifecycle?hl=es
public class MainActivity extends AppCompatActivity {

    private Button boton1;
    private Button boton2;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //En este apartado le decimos cual de nuestros XMLs asociar a esta actividad
        //"R" es una clase que contiene todos los recursos que hayamos creado en nuestra
        //aplicacion android, es decir, lo que haya en la carpeta "res"
        setContentView(R.layout.activity_linear_main);

        //Una vez cargados los objetos podemos acceder a ellos a traves de us id
        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        textView1 = findViewById(R.id.textView1);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Ha pulsado el boton", Toast.LENGTH_SHORT).show();
            }
        });

        //Equivalente con funciones lambda
        boton2.setOnClickListener((v) -> {
            textView1.setText(getResources().getString(R.string.mensaje_1));
        });

    }
}