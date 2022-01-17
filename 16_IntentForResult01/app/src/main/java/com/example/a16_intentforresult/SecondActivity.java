package com.example.a16_intentforresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a16_intentforresult.entidad.Usuario;

public class SecondActivity extends AppCompatActivity {

    private Button botonVolver;
    private EditText etNombre;
    private EditText etEdad;

    public static final int OK = 0;
    public static final int KO = 1;
    public static final String USUARIO = "usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        botonVolver = findViewById(R.id.botonVolver);
        etNombre = findViewById(R.id.tvNombre);
        etEdad = findViewById(R.id.tvEdad);

        botonVolver.setOnClickListener(view -> {
            Intent data = new Intent();

            String sNombre = etNombre.getText().toString();
            String sEdad = etEdad.getText().toString();

            if (sNombre.matches("") && sEdad.matches("")) {
                Log.v("SecondActivity", "Usuario mal introducido");
                setResult(KO);
            }else{
                Log.v("SecondActivity", etNombre.getText().toString());
                Log.v("SecondActivity", etEdad.getText().toString());
                Usuario usuario = new Usuario();
                usuario.setNombre(sNombre);
                usuario.setEdad(Integer.parseInt(sEdad));
                data.putExtra(USUARIO, usuario);
                setResult(OK,data);
            }

            finish();
        });
    }
}