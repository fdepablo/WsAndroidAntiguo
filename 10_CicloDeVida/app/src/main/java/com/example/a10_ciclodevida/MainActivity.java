package com.example.a10_ciclodevida;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
    una actividad puede atravesar los siguientes estados para mantener:

    1. Inexistente, la actividad no existe
    2. Detenida, la actividad esta en segundo plano
    3. Pausada, la actividad pierde el foco del usuario
    4. En marcha, la actividad tiene el foco del usuario y esta en primer plano

    Podemos ver la siguiente tabla para entender mejor los estados de una actividad

    -Estado	        ¿En memoria?	¿Visible al usuario?	¿En primer plano?
    Inexistente	        No	                No	                    No
    Detenida	        Si	                No	                    No
    Pausada	            Si	                Si	                    No
    En Marcha	        Si	                Si	                    Si

    Cada vez que haya un cambio entre los estados, se disparará un evento que sera
    recogido por los diferentes metodos de callback que heredamos de la clase Activity.

                 onCreate()            onStart()           onResume()
    Inexistente ------------> Detenida ----------> Pausada -----------> En Marcha

               onPause()            onStop()             onDestroy()
    En Marcha -----------> Pausada ----------> Detenida -------------> Inexistente

    Podemos encontrar más información sobre el ciclo de vida en el siguiente enlace:
    https://developer.android.com/guide/components/activities/activity-lifecycle?hl=es

    De momento hemos visto solo uno, el metodo onCreate(), pero en este ejemplo veremos
    el resto de ellos.
    */



    /*
    En este ejemplo vamos a ver una actividad que sume dos numeros enteros para poder ver
    los estados del ciclo de vida de la actividad. Antes de hacer estos pasos, es mejor ver
    los diferentes metodos de callback que hemos creado.


    1- Lanzamiento de la Actividad
    Este es el caso más básico, donde Android abre un proceso nuevo para nuestra app

    Lanzar una app requiere que te sitúes en el Launcher y luego presiones el icono de la misma.
    Podemos ejecutar la app y verificar en qué orden son impresos los métodos en el
    logcat de Android Studio luego del lanzamiento. Desde Android Studio pulsaremos el boton
    de play.

    2- Navegación Hacia Atrás
    Iinsertamos los 2 números y presiona el botón de suma. Podemos ver el resultado

    Seguidamente navega hacia atrás con el Back Button de la barra de navegación del sistema y
    nos fijamos en las callbacks del ciclo de vida de cada uno de los metodos ya que vamos a
    salir de nuestra aplicacion

    Una vez fuera de la aplicacion podemos buscar el "launcher" de nuestra app para volver a entrar
    o volver a lanzar la aplicacion en el simulador

    3- Presionar El Home Button
    Suma de nuevo dos números y esta vez probamos que estados recorre la actividad al presiona el
    botón Home de la barra de navegación.

    Al terminar observaremos que solo se han llamado a la secuencia onPause() > onStop().
    Esto se debe a que el propósito del Home button es ir al Launcher para cambiar de app y
    realizar otras acciones que el usuario desee.

    El no llegar al estado Inexistente evita que la instancia de la actividad sea destruida,
    por lo que si abres de nuevo la aplicación presionando su icono verás que se conservan
    los valores de la suma.

    Esta vez se invoca onRestart() -> on Start() y onResume(), notese que no se invocará
    el metodo onCreate() y que no hemos perdido los valores

    4- Esta vez vamos a presionar el botón Overview de la barra de navegación para abrir la
    screen de recientes. El log muestra los mismos casos que el caso anterior.

    Al volver a la app,  tendremos exactamente el flujo onRestart() > onStart() > onResume() y
    la actividad mantendrá sus valores.

    5- Si vamos a la actividad de restar podemos ver como tenemos un comportamiento muy parecido
    a los anteriores ejemplos, pero esta vez tambien pasaremos por los estados del ciclo de vida
    de RestarActivity

    6- Android está diseñado para acabar con los procesos de apps de baja prioridad si en
    el primer plano hay una app que requiere recursos adicionales.
    Por ejemplo, si estas interactuando con la actividad suma, presionas Home y luego lanzas
    una app que consuma una gran porción de tu memoria, es muy probable que Android elimine
    el proceso de SumaActivity.

    No solo eso, si no que android evita llamar el onDestroy() para acabar lo más pronto posible
    con el proceso.

    7- Cambios en la configuracion de la app
    Un cambio de configuración fácil de percibir es la rotación de pantalla.
    Si rotas la actividad de suma verás que esta es reconstruida completamente, pasando por
    los 4 estados. Por lo que perderemos el valor de nuestra variable de suma (afortunadamente
    el framework nos ayuda a conservar el contenido de los EditText, pero no el de los TextView).

    Pulsando ctrl+(left|right) en el emulador para cambiar de postrait a landscape.
    Si no cambia, debemos ver si el simulador puede cambiar de postrait a landscape en la
    configuracion.

    8- Android te permite guardar el estado de las instancias mediante un objeto Bundle,
    el cual te permite insertar parejas clave-valor.

    El momento donde almacenas dichos valores es en el método onSaveInstanceState() donde
    viene el parámetro bundle. Por ejemplo, para guardar el valor de la suma en nuestro
    actividad lo añadiremos con el método Bundle.putString()  y le asignamos una clave
    asociada al valor. Dicho metodo se ejecutará antes que el metodo onStop().

     */

    private EditText mNumberAInput;
    private EditText mNumberBInput;
    private TextView mSumTotalLabel;
    private Button mAddButton;
    private Button bIrARestar;
    private Button bCerrarApp;

    //El resultado de la suma lo mantendremos en este atributo para mantener su estado
    private String sResultado;

    //La clave con la que guardaremos el resultado de la suma
    private final static String CLAVE_RESULTADO_SUMA = "CLAVE_RESULTADO_SUMA";

    /**
     * Este metodo se dispara cuando el sistema crea una nueva instancia en memoria de la actividad.
     *
     * Es el lugar donde se incluye la mayor de la inicialización. Tareas prioritarias como:
     *
     * 1- Inflar la UI de la actividad con setContentView()
     * 2- Agregar fragmentos
     * 3- Obtener referencias de views con findViewById()
     * 4- Iniciar consultas iniciales a fuentes de datos
     * 5- Crear instancias de los componentes de tu arquitectura
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate()");

        // Obtenemos las instancias(objetos) de las Views
        mNumberAInput = findViewById(R.id.number_a_input);
        mNumberBInput = findViewById(R.id.number_b_input);
        mSumTotalLabel = findViewById(R.id.sum_label);
        mAddButton = findViewById(R.id.add_button);
        bIrARestar = findViewById(R.id.bIrARestar);
        bCerrarApp = findViewById(R.id.cerrarAppbutton);

        //Recumeramos el estado de la actividad
        if(savedInstanceState!=null){
            sResultado = savedInstanceState.getString(CLAVE_RESULTADO_SUMA);
        }else {
            sResultado = "0";
        }
        mSumTotalLabel.setText(sResultado);

        // Actualización
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputA = mNumberAInput.getText().toString();
                String inputB = mNumberBInput.getText().toString();
                int numberA = inputA.isEmpty() ? 0 : Integer.parseInt(inputA);
                int numberB = inputB.isEmpty() ? 0 : Integer.parseInt(inputB);
                int iResultado = numberA + numberB;

                sResultado = String.valueOf(iResultado);
                mSumTotalLabel.setText(sResultado);
            }
        });

        bIrARestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Yendo a Restar Activity");
                Intent intent = new Intent(MainActivity.this, RestaActivity.class);
                startActivity(intent);
            }
        });

        bCerrarApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Cerrando Actividad");
                finish();
            }
        });
    }

    /**
     * Este método es llamado luego de onCreate().
     *
     * En el podemos escribir instrucciones asociadas a la UI, ya que la actividad es visible
     * al usuario. Sin embargo no es muy usado ya que son pocos los casos donde es trascendental
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart()");
    }



    /**
     * Este metodo se ejecuta solo cuando la actividad se ha puesto en el estado Detenida.      *
     * Este es ejecutado antes de onStart() y luego de onStop().     *
     * Su uso no es muy popular, pero es de utilidad cuando deseas diferenciar entre una
     * recreación y un reinicio
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart()");
    }

    /**
     * Se ejecuta antes de que la actividad se ponga En Marcha para interactuar con el
     * usuario en primer plano.
     *
     * Normalmente en su interior se suele ejecutar animaciones, iniciar la aplicación
     * Camara, cargar datos actualizados, etc.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume()");
    }

    /**
     * Este metodo es llamado cuando el sistema quita del primer plano a la actividad y así
     * entrar en el estado Pausada. Por ejemplo cuando pulsamos el boton de recientes de
     * un movil android
     *
     * En esta callback podemos ejecutar instrucciones para parar de ejecutar algun
     * tipo de accion que este realizando la interfaz
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause()");
    }

    /**
     * Es llamado antes de llegar al estado Detenida donde la actividad no será visible
     * para el usuario. En consecuencia, aquí minimizaremos los recursos y acciones que
     * requieran visibilidad. Ejemplos de ello sería pausar animaciones
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop()");
    }

    /**
     * El sistema llama a este método antes de destruir la instancia de la actividad
     * En su interior incluiremos instrucciones de limpieza de recursos asociados
     *
     * Ojo, es posible que este metodo no se llegue a ejecutar si android necesita
     * recursos de memoria para ejecutar otras actividades en primer plano. Es mejor
     * usar onStop() para guardar datos y no perderlos
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy()");
    }

    /**
     * Este metodo lo usaremos para guardar estados de nuestra actividad
     * @param outState objeto que guarda pares de clave-valor.
     *
     * Este objeto sera el que se inyecte en el metodo onCreate()
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d("MainActivity", "onSaveInstanceState()");
        outState.putString(CLAVE_RESULTADO_SUMA, sResultado);
    }
}