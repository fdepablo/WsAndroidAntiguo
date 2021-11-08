package com.example.a04_intenciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.a04_intenciones.modelo.entidad.Usuario;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewNombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewNombreUsuario = findViewById(R.id.tvResultadoNombreUsuario);

        String nombreUsuario = getIntent().getStringExtra(MainActivity.K_NOMBRE_USUARIO);
        Log.d("SecondActivity", nombreUsuario);
        Usuario usuario =
                (Usuario)getIntent().getSerializableExtra(MainActivity.K_USUARIO);

        textViewNombreUsuario.setText("Datos introducidos: "
                + usuario.getNombre() + " " + usuario.getPassword());
    }
}