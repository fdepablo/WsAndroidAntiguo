package com.example.a12_listascomplejas03recycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a12_listascomplejas03recycler.adaptador.AdaptadorUsuario;
import com.example.a12_listascomplejas03recycler.entidad.Usuario;
import com.example.a12_listascomplejas03recycler.singleton.ListaUsuarioSingleton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView reyclerViewUser;
    private AdaptadorUsuario adaptadorUsuario;
    private Button botonSegunda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reyclerViewUser = findViewById(R.id.rViewUsuario);
        botonSegunda = findViewById((R.id.segunda));

        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        reyclerViewUser.setHasFixedSize(true);

        // Nuestro RecyclerView usará un linear layout manager
        reyclerViewUser.setLayoutManager(new LinearLayoutManager(this));

        ListaUsuarioSingleton.getInstance().inicializar();
        List<Usuario> listaUsuario = ListaUsuarioSingleton.getInstance().getListaUsuarios();
        // Asociamos un adapter (ver más adelante cómo definirlo)
        adaptadorUsuario = new AdaptadorUsuario(listaUsuario);
        reyclerViewUser.setAdapter(adaptadorUsuario);

        botonSegunda.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        });

     }

    @Override
    protected void onResume() {
        super.onResume();
        reyclerViewUser.getAdapter().notifyDataSetChanged();
    }
}