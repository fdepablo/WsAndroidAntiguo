package com.example.a14_dialogos01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
Los dialogos son ventanas emergentes que salen encima de las actividades cuyo
objetivo es interactuar con el usuario de alguna manera.

En este ejemplo tenemos 3 tipos de dialogos:

1. Mostramos un dialogo con un solo boton, sirven para avisar a nuestro usuario
de algo
2. Mostramos un dialogo con dos botones, sirven para que el usuario confirme una
accion.
3. Mostramos un dialogo para recoger un valor y mostrarlo en actividad.
 */
public class MainActivity extends AppCompatActivity {

    private Button bDialogoUnBoton;
    private Button bDialogoDosBotones;
    private Button bDialogoRecogerValor;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bDialogoUnBoton = findViewById(R.id.bDialogoUnBoton);
        bDialogoDosBotones = findViewById(R.id.bDialogoDosBotones);
        bDialogoRecogerValor = findViewById(R.id.bDialogoRecogerValor);
        tvResultado = findViewById(R.id.tvResultado);

        bDialogoUnBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para crear dialogos se usa la clase AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //Los dialogos en su manera más sencilla tiene un titulo y un mensaje
                //a mostrar
                builder.setTitle("Gardar Datos");
                builder.setMessage("Se han guardado los datos satisfactoriamente!");
                //Al menos tenemos que incluir un boton de afirmacion
                //Si no le pasamos ningun listener, por defecto cerramos el dialogo
                builder.setPositiveButton("Aceptar", null);

                //Instaciamos el objeto
                AlertDialog dialog = builder.create();
                //Mostramos al usuario el dialogo
                dialog.show();
            }
        });

        bDialogoDosBotones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Dialogo de dos botones");
                builder.setMessage("¿Desea cerrar la aplicacion?");

                //En este caso si que voy a poner un listener al boton, ya que
                //quiero cerrar la actividad
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                //Es este caso tambien meto el boton en caso de que no quiera
                //hacer la accion
                builder.setNegativeButton("Cancelar", null);

                AlertDialog dialog = builder.create();
                //Establecemos que no cancele el dialogo si pulsa fuera del cuadro
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        bDialogoRecogerValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos un objeto del tipo Dialogo que hemos creado antes y
                //lo mostramos.
                DialogoRecogerValor dialogoRecogerValor = new DialogoRecogerValor();
                dialogoRecogerValor.setView(tvResultado);
                dialogoRecogerValor.show(getFragmentManager(),"MainActivity");
            }
        });
    }
}