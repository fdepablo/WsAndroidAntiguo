package com.example.a12_listascomplejas03recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a12_listascomplejas03recycler.adaptador.AdaptadorUsuarioPersonalizado;
import com.example.a12_listascomplejas03recycler.entidad.Usuario;
import com.example.a12_listascomplejas03recycler.singleton.ListaUsuarioSingleton;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private AdaptadorUsuarioPersonalizado adaptadorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerViewUser = findViewById(R.id.rViewUsuario);
        recyclerViewUser.setHasFixedSize(true);

        // use a linear layout manager, esta vez horizontal
        recyclerViewUser.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false));

        List<Usuario> listaUsuarios = ListaUsuarioSingleton.getInstance().getListaUsuarios();
        adaptadorUsuario = new AdaptadorUsuarioPersonalizado(listaUsuarios);
        recyclerViewUser.setAdapter(adaptadorUsuario);
    }
}