package com.example.a05_linearlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    Tenemos dos grandes tipos de componentes en android:

    1. View (o Vista). Esta clase es básica para la creación de todos los componentes usados en
    la interfaz. Una View ocupa un área rectangular en la pantalla y es el responsable de dibujar
    los componentes y manejar los eventos que definamos sobre ellos, o dicho de una forma más simple,
    dibuja los componentes en la pantalla con los que el usuario puede interaccionar.
    Es la clase base para los widgets, que son Views ‘preconstruidas’ que vienen incluidas en la
    plataforma Android, como los botones, campos de textos, check boxes, radio buttons, etc.

    2. ViewGroup (o Grupo de Vista). Hereda directamente de View y se usa para, como su nombre indica,
    contener y controlar la lista de Views y de otros ViewGroups. Es la clase base para los Layouts,
    mediante los cuales podemos diseñar una estructura para un conjunto de Views.

    Unidades de medidas
    Otra consideración a tener muy en cuenta es que, al existir tanta diversidad de dispositivos
    Android, es conveniente usar siempre medidas relativas al tamaño de pantalla de cada uno.
    Esto lo podemos hacer usando:

    1. La medida dp (density-independent pixel ó dp, que al caso es la misma), recomendada para
    especificar el tamaño de los views en nuestro layout (siendo 160dp el equivalente a una pulgada
    (2,54 cm) de pantalla física)

    2. La medida sp (scaled-independent pixel), similar a dp y recomendada para definir tamaños de
    fuentes.

    Layout
    Los Layouts son los elementos sobre los cuales se sustentan los diferentes componentes de la
    interfaz de usuario, y controlan la distribución, la posición y las dimensiones de dichos
    componentes. Es decir, un layout define la estructura o diseño del UI.

    En este tema vamos a trabajar con el LinearLayout que es de los más sencillos de utilizar.

    Este layout alinea los elementos en una única dirección, que puede ser vertical u horizontal,
    dependiendo del valor que le demos al atributo "android:orientation".

    Todos los elementos aparecerán uno detrás de otro, sin solaparse entre ellos.

    El LinearLayout respeta los márgenes entre los elementos hijos

    En este layout los elementos hijos deben establecer sus atributos android:layout_height y
    android:layout_width para determinar sus dimensiones dentro del layout,
    aunque en este caso dispondremos de otro atributo llamado android:layout_weight.
    Este atributo permite que un elemento se expanda para llenar cualquier espacio que quede libre.
    Por defecto este valor es 0, es decir, no se expande.

    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}