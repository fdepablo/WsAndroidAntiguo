package com.example.a13_menu01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    En este ejemplo vamos a ver como podemos trabajar con un menu de
    opciones en android

    En primer lugar, debes ubicar crear un archivo de menú y ubicarlo en la carpeta
    res/menu/nombre_archivo.xml.

    Desde Android Studio se resume a dar click derecho en tu carpeta res y luego seleccionar
    New > Android Resource File.

    Al desplegarse la ventana de configuración, selecciona "Menu" en el tipo de recursos y
    luego ponemos su nombre "main_menu"
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}