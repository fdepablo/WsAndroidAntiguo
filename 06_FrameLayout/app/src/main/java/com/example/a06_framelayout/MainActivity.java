package com.example.a06_framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    FrameLayout de los los ViewGroup más simple de todos.

    En él, todos los elementos se alinean teniendo en cuenta la esquina superior
    izquierda de la pantalla, no pudiendo ser ubicados en otro lugar, por lo que se
    colocarían unos encima de otros tapando completa o parcialmente a los demás,
    a menos que el nuevo elemento sea transparente.

    Por esta razón, se usa normalmente para mostrar un único elemento, ya que puede
    resultar difícil organizar la posición de los elementos.

    Si queremos posicionar los elementos de otra forma, deberíamos de usar los
    atributos android:layout_gravity que especifica como debe posicionarse el elemento,
    como pueden ser start(izquierda), end (derecha), top (arriba), botton (abajo),
    center_vertical, center_horizontal o center (que agruparia los dos anterior)
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}