package com.example.a14_dialogos01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Dialogo de un solo boton");
                builder.setMessage("Esto es un mensaje!");
                builder.setPositiveButton("Aceptar", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        bDialogoDosBotones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Dialogo de dos botones");
                builder.setMessage("¿Desea cerrar la aplicacion?");

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
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