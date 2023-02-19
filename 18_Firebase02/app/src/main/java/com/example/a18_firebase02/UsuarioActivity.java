package com.example.a18_firebase02;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class UsuarioActivity extends AppCompatActivity {

    private String id;
    private EditText etNombre;
    private EditText etEmail;
    private TextView tvId;

    private ProgressDialog mDefaultDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        id = (String) getIntent().getExtras().get("id");
        Log.d("UsuarioActivity", "ID: " + id);

        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEMail);
        tvId = findViewById(R.id.tvId);

        tvId.setText(id);

        accederUsuario();
    }


    public void altaUsuario(){
        mostrarEspera();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        /*
        db.collection(MainActivity.COLECCION).document().set(saludo)
                .addOnSuccessListener( documentReference -> {
                    Toast.makeText(getApplicationContext(),
                            documentReference.getId(),
                            Toast.LENGTH_SHORT).show();
                    cargarSaludos();
                }).addOnFailureListener( e -> {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    cancelarEspera();
                }
        );
        */
    }
    public void accederUsuario(){
        mostrarEspera();


    }

    public void borrarUsuario(){

    }

    public void modificarUsuario(){

    }

    public void mostrarEspera() {
        mDefaultDialog = new ProgressDialog(this);
        // El valor predeterminado es la forma de círculos pequeños
        mDefaultDialog.setProgressStyle (ProgressDialog.STYLE_SPINNER);
        mDefaultDialog.setMessage("Solicitando datos ...");
        mDefaultDialog.setCanceledOnTouchOutside(false);// Por defecto true
        mDefaultDialog.show();
    }

    public void cancelarEspera(){
        mDefaultDialog.cancel();
    }
}