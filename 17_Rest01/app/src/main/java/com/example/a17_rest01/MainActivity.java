package com.example.a17_rest01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a17_rest01.adaptador.AdaptadorUsuario;
import com.example.a17_rest01.modelo.entidad.Usuario;
import com.example.a17_rest01.modelo.negocio.GestorUsuario;
import com.example.a17_rest01.modelo.servicio.GoRestUsuarioApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Para hacer llamadas a un API Rest debemos de importar alguna librería de terceros,
como puede ser RetroFit. Agregamos al gradle

    implementation 'com.squareup.retrofit2:retrofit:2.9.0'

Retrofit es un cliente REST extremadamente simple de configurar.
Nos permitirá tratar las llamadas a la API como funciones Java, así definiremos
solamente las URLs que queremos llamar y los tipos de petición y respuesta que
esperamos. Retrofit requiere un de Java 8+ o Android API 21+.

https://square.github.io/retrofit/

Tambien agregaremos alguna librería que nos permita trabajar con Json. En este
caso usaremos Gson, una libreria de google muy popular para serializar y
deserializar objetos en formato Json.

    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.google.code.gson:gson:2.8.9'

Tambien debemos dar permisos en nuestra aplicacion para contectarnos a internet.
Tenemos que agregar la siguiente sentencia en el AndroidManifest.xml

    <uses-permission android:name="android.permission.INTERNET"/>

Siempre que agregamos liberías a nuestro proyecto, debemos de construirlo de
nuevo (Make proyect).

Para nuestro ejemplo vamos a usar un servicio Rest remoto llamado

    https://gorest.co.in/

 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private AdaptadorUsuario adaptadorUsuario;
    private Button botonRefrescar;
    private TextView tvVacio;
    ProgressDialog mDefaultDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GestorUsuario.getInstance().inicializar();

        recyclerViewUser = findViewById(R.id.rViewUsuario);
        recyclerViewUser.setHasFixedSize(true);

        tvVacio = findViewById(R.id.tvVacio);

        recyclerViewUser.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false));

        botonRefrescar = findViewById(R.id.botonRefrescar);

        botonRefrescar.setOnClickListener(v -> {
            obtenerListaUsuariosRest();
        });
    }

    //Cada vez que mostramos esta activity, volvemos a cargar los datos
    @Override
    protected void onResume() {
        super.onResume();
        obtenerListaUsuariosRest();
    }

    public void obtenerListaUsuariosRest(){
        mostrarEspera();

        GoRestUsuarioApiService goRestUsuarioApiService = GestorUsuario.getInstance().getGoRestUserApiService();

        Call<List<Usuario>> call = goRestUsuarioApiService.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    Log.d("Success", "Datos traidos del servicio");
                    List<Usuario> listaUsuarios = response.body();

                    adaptadorUsuario = new AdaptadorUsuario(listaUsuarios);
                    recyclerViewUser.setAdapter(adaptadorUsuario);

                    //En caso de que la lista que nos traemos este vacia
                    //ocultamos la vista y mostramos un textview de vacio
                    if (listaUsuarios.isEmpty()) {
                        recyclerViewUser.setVisibility(View.GONE);
                        tvVacio.setVisibility(View.VISIBLE);
                    }
                    else {
                        recyclerViewUser.setVisibility(View.VISIBLE);
                        tvVacio.setVisibility(View.GONE);
                    }
                } else {
                    Log.d("Error", "Something happened");
                    return;
                }

                cancelarEspera();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("Error", t.toString());
                cancelarEspera();
            }
        });
    }

    public void mostrarEspera() {
        mDefaultDialog = new ProgressDialog(this);
        // El valor predeterminado es la forma de círculos pequeños
        mDefaultDialog.setProgressStyle (android.app.ProgressDialog.STYLE_SPINNER);
        mDefaultDialog.setMessage("Solicitando datos ...");
        mDefaultDialog.setCanceledOnTouchOutside(false);// Por defecto true
        mDefaultDialog.show();
    }

    public void cancelarEspera(){
        mDefaultDialog.cancel();
    }
}