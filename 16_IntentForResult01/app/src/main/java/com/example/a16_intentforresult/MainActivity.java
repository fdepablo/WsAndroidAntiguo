package com.example.a16_intentforresult;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a16_intentforresult.entidad.Usuario;


public class MainActivity extends AppCompatActivity {

    public TextView tvNombre;
    public TextView tvEdad;
    public Button botonSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "************ Creando la actividad ***************");

        tvNombre = findViewById(R.id.tvNombre);
        tvEdad = findViewById(R.id.tvEdad);
        botonSiguiente = findViewById(R.id.botonSiguente);

        //Creamos un objeto capaz de invocar una función de callback cuando haya acabado
        //la actividad que hemos lanzado
        //Mejor crearlo antes de que la actividad sea mostrada
        final ActivityResultLauncher<Intent> activityForResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == SecondActivity.OK) {
                        Intent intent = result.getData();
                        Usuario usuario = (Usuario) intent.getSerializableExtra(SecondActivity.USUARIO);
                        Toast.makeText(this, "Usuario: " + usuario, Toast.LENGTH_SHORT).show();
                        tvNombre.setText(usuario.getNombre());
                        tvEdad.setText(String.valueOf(usuario.getEdad()));
                    } else {
                        Toast.makeText(this, "Algun campo de usuario vacio", Toast.LENGTH_SHORT).show();
                        tvNombre.setText("");
                        tvEdad.setText("");
                    }
                }
        );

        botonSiguiente.setOnClickListener(view -> {
            Intent intent = new Intent(this,SecondActivity.class);
            activityForResultLauncher.launch(intent);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "************ Parando la actividad ***************");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "************ Volviendo a la actividad ***************");
    }
 }
