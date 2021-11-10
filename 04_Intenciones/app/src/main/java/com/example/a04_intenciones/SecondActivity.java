package com.example.a04_intenciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.a04_intenciones.modelo.entidad.Usuario;

public class SecondActivity extends AppCompatActivity {

    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvResultado = findViewById(R.id.tvResultado);

        //Con el metodo getIntent() obtengo el objeto intent que se uso para llamar
        //a esta actividad

        //Para obtener un valor primitivo a traves de su clave
        String nombreUsuario = getIntent().getStringExtra(MainActivity.K_NOMBRE_USUARIO);
        Log.d("SecondActivity","user: " + nombreUsuario);

        String passUsuario = getIntent().getStringExtra(MainActivity.K_PASSWORD_USUARIO);
        Log.d("SecondActivity", "pass: " + passUsuario);
        
        //Tambien podemos obtener a partir de un objeto
        Usuario usuario =
                (Usuario)getIntent().getSerializableExtra(MainActivity.K_USUARIO);

        tvResultado.setText("Datos introducidos: "
                + usuario.getNombre() + " " + usuario.getPassword());
    }
}