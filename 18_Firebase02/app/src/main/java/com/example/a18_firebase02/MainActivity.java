package com.example.a18_firebase02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a18_firebase02.adaptador.AdaptadorUsuario;
import com.example.a18_firebase02.modelo.entidad.Usuario;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

//Este proyecto esta incompleto

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private AdaptadorUsuario adaptadorUsuario;
    private Button botonRefrescar;
    private TextView tvVacio;
    ProgressDialog mDefaultDialog = null;

    public final static String COLECCION = "usuarios";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            obtenerListaUsuarios();
        });
    }

    //Cada vez que mostramos esta activity, volvemos a cargar los datos
    @Override
    protected void onResume() {
        super.onResume();
        obtenerListaUsuarios();
    }

    public void obtenerListaUsuarios(){
        mostrarEspera();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(COLECCION).get()
                .addOnSuccessListener(documentSnapshots -> {
                    List<Usuario> listaUsuarios = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : documentSnapshots.getDocuments()) {
                        Usuario saludo = documentSnapshot.toObject(Usuario.class);
                        listaUsuarios.add(saludo);
                    }
                    adaptadorUsuario =
                            new AdaptadorUsuario(listaUsuarios);
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

                    cancelarEspera();
                }).addOnFailureListener(e -> {
            Toast.makeText(getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            cancelarEspera();
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