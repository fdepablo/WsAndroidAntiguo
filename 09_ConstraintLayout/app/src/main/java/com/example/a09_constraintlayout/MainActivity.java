package com.example.a09_constraintlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    El ConstraintLayout es un descendiente de ViewGroup, cuyo fin es permitirte
    posicionar y redimensionar views de forma flexible a partir de una gran variedad
    de reglas de restricción. "Constraint" significa "Restriccion"

    Las restricciones son similares al concepto de posicionamiento relativo del RelativeLayout,
    no obstante el ConstraintLayout posee muchos más tipos de condicionamientos para crear
    diseños más libres.

    Android Studio añadira una dependecia para usar ConstraintLayout en nuestro build-gradle

    ConstraintLayout será el layout por defecto que creará android para nuestras actividades,
    además de traer un text view en su interior

    Este constraint esta pensado para poner los componentes a traves de la vista de diseño
    de android studio (arrastrando y soltando). Hacerlo directamente sobre XML puede
    resultar muy complicado.

    En la vista de diseño tendremos dos pantallas Design y Blueprint. Design es como vera el
    la actividad el usuario final, Blueprint es para ver los componentes a nivel interno (por
    ejemplo si tenemos algun componente invisible, el id de los componentes, etc)

    En esta vista a la derecha tendremos el panel de "atributtes" donde podemos alterar y ver
    todos los atributos de nuestra actividad.

    Para este ejemplo vamos a ir a la vista de diseño y crear arrastrando y soltando 2 TextView,
    2 EditText y 2 botones. Además vamos a situar arriba de la actividad el TextView inicial como
    si fuera una cabecera de bienvenida.

    Como segundo paso, para cada uno de los componentes que hemos creado debemos de asociar
    unas restricciones de donde deben estar situados. Para ello, a partir de los límites de
    cada componente que hemos creado (view), que estan representados por círculos en los lados,
    arrastra una línea de conexión hacia otro elemento con el que deseas que este condicionado.

    Si el componente no esta condicionado por alguno de sus lados, el circulo sera blanco, en
    caso contrario el circulo sera azul. En este tipo de layout, todos los componentes deben
    tener restricciones en cada uno de sus lados.

    Podemos borrar alguna constraint puslando boton derecho sobre ella y eliminandola.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}