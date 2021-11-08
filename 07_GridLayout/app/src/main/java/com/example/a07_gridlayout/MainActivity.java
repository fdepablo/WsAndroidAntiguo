package com.example.a07_gridlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    El GridLayout define un diseño situando a sus hijos en una forma de rejilla rectangular.

    Dicho grid o rejilla se compone de un conjunto de líneas que separan el área de
    visualización de las celdas. Las líneas de cuadrícula son referenciadas por índices.
    Un grid con N columnas tiene n + 1 índices del grid que van de 0 a N, ambos inclusive.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}