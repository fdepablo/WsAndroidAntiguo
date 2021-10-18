package com.example.introduccion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    //Normalemente declararemos como atributo de la clase activity aquellos componentes
    //con lo que queremos actuar
    private Button botonPulsame;
    private TextView textView;
    private Button botonFactorial;
    private TextView resultadoFactorial;
    private EditText textoFactorial;


    //Este metodo se llama cuando Android crea el objeto activity por primera vez
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //En este apartado le decimos cual de nuestros XMLs asociar a esta actividad
        //"R" es una clase que contiene todos los recursos que hayamos creado en nuestra
        //aplicacion android, es decir, lo que haya en la carpeta "res"
        setContentView(R.layout.activity_main);

        //Android mediante la inversion de control nos creo todos los objetos que
        //encontro el el fichero activity_main.xml.
        //Pero si nosotros queremos trabajar con dichos objetos tendremos que
        //de alguna manera acceder al dicho objeto, es decir, queremos que android
        //nos inyecte un objeto que tenga creado. Este concepto se llama inyeccion
        //de dependencias
        //Normalmente le diremos a Android que queremos acceder a un objeto a traves
        //de su "id" usando para ello la funcion findElementById, pasandole el id
        //que queremos que nos inyecte
        //Desde el API 26, findViewById usa inferencia de tipos para obtener
        //el valor de retorno. En versiones anteriores hacia falta castear el objeto
        //devuelto
        //botonPulsame = (Button)findViewById(R.id.botonPulsame);
        botonPulsame = findViewById(R.id.botonPulsame);

        //Vamos a meterle logica al boton para que cuando le pulsemos nos cambie
        //el texto del textView

        textView = findViewById(R.id.texto1);

        //Metemos un evento onclick al boton para que se ejecute cuando lo
        //pulsemos. Esto es crear un objeto y una clase en el mismo momento
        //y en java se llaman clases anonimas
        botonPulsame.setOnClickListener(new View.OnClickListener() {
            int contador = 0;

            @Override
            public void onClick(View v) {
                textView.setText("Ha pulsado el boton " + contador++);
            }
        });

        botonFactorial = findViewById(R.id.botonFactorial);
        textoFactorial = findViewById(R.id.textoFactorial);
        resultadoFactorial = findViewById(R.id.resultadoFactorial);

        botonFactorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Los valores hay que cambiar el tipo, lo que nos entra
                //son Strings
                String sNumero = textoFactorial.getText().toString();
                //Lo convertimos a numero
                int iNumero = Integer.parseInt(sNumero);
                //Calculamos el factorial
                int resultado = 1;
                for(int i=1;i<=iNumero;i++){
                    resultado *= i;
                }
                //Establecemos el valor pero antes convertimos el tipo int
                //en tipo String
                resultadoFactorial.setText(String.valueOf(resultado));
            }
        });
    }

    //Podemos hacer que un boton ejecute directamente una funcion sin necesidad
    //de meterle un listener. En este caso debemos de decirle el nombre de la funcion
    //dentro del atributo "onclick" del boton
    public void cambiarTexto(View view){
        textView.setText("Has pulsado otro boton");
    }
}