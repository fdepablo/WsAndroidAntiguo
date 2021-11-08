package com.example.a08_relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    RelativeLayout permite que los elementos se dispongan en la pantalla de forma
    relativa al elemento padre o a cualquier otro elemento agregado al layout, por lo
    tanto, podríamos hacer cosas como alinear dos elementos a la derecha, o crear uno
    debajo del otro, centrarlos en la pantalla, a la izquierda, etc.

    Entre algunas de las muchas propiedades de diseño disponibles para las vistas de
    un RelativeLayout, se incluyen las siguientes:

    1. android:layout_alignParentTop
    Si el valor es "true", el borde superior de esta vista coincidirá con el del elemento superior.
    2. android:layout_below
    Posiciona el borde superior de esta vista debajo de la vista especificada con un ID
    de recurso.
    3.android:layout_toRightOf
    Posiciona el borde izquierdo de esta vista a la derecha de la vista especificada con un ID de recurso.

    Mas informacion
    https://developer.android.com/guide/topics/ui/layout/relative?hl=es-419
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}