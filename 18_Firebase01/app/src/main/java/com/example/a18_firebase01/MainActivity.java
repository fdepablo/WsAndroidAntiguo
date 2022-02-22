package com.example.a18_firebase01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/*
https://firebase.google.com/docs/guides

Un proyecto de Firebase es un proyecto de Google Cloud con configuraciones y
servicios adicionales específicos de Firebase. Incluso puedes crear un proyecto de Google
Cloud primero y agregar Firebase al proyecto posteriormente.

Todas las aplicaciones por defecto se encuentran en modo desarrollo y bajo el
plan gratis que soporta hasta 100 conexiones concurrentes, 1GB de almacenamiento
y 10GB de transferencia en el backend.

Para crear un proyecto Firebase tenemos 2 opciones.

Opción 1: Usa el flujo de trabajo de configuración de Firebase console (recomendada).

0. ir a https://console.firebase.google.com/

1. Creamos un proyecto y le damos la configuración por defecto.

2. Registramos la app al proyecto y seguir los pasos

3. Agregar el proyecto a nuestra aplicacion Android, para ello en la pantalla
de descripción general del proyecto debemos de pulsar el icono de Android y
seguir sus pasos.

Nota, hay que configurar el build.gradle del proyecto con

    buildscript {
        dependencies {
            classpath 'com.google.gms:google-services:4.3.10'
        }
    }

Y el build.gradle del modulo con

    plugins {
        id 'com.android.application'
        id 'com.google.gms.google-services'
    }

    dependencies{
        // Import the Firebase BoM
        implementation platform('com.google.firebase:firebase-bom:29.1.0')
        implementation 'com.google.firebase:firebase-firestore'

        // Add the dependency for the Firebase SDK for Google Analytics
        // When using the BoM, don't specify versions in Firebase dependencies
        implementation 'com.google.firebase:firebase-analytics'
    }

Opción 2: Usa Firebase Assistant de Android Studio

1. Abre Firebase Assistant: tools > Firebase.

2. En el panel Asistente, elige el producto Firebase Database

3. Sincroniza tu app para garantizar que todas las dependencias tengan las
versiones necesarias.

4. En el panel Asistente, sigue las instrucciones de configuración restantes
para el producto de Firebase que selecciones.

---

En este ejemplo vamos a trabajar con una Firebase DataBase

---

https://firebase.google.com/docs/reference/android/com/google/firebase/database/package-summary
https://firebase.google.com/docs/firestore/quickstart?hl=es-419#java
https://www.c-sharpcorner.com/article/crud-operations-with-firebase-cloud-fire-store/

 */

public class MainActivity extends AppCompatActivity {

    private Button botonAltaMensaje;
    private EditText etSaludo;
    private RecyclerView rvSaludos;
    ProgressDialog mDefaultDialog = null;

    public final static String COLECCION = "saludos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonAltaMensaje = findViewById(R.id.botonAltaMensaje);
        etSaludo = findViewById(R.id.etSaludo);
        rvSaludos = findViewById(R.id.rvSaludos);
        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        rvSaludos.setHasFixedSize(true);
        // Nuestro RecyclerView usará un linear layout manager
        rvSaludos.setLayoutManager(new LinearLayoutManager(this));

        cargarSaludos();

        botonAltaMensaje.setOnClickListener( v -> {
            //Map<String, Object> saludo = new HashMap<>();
            //saludo.put("texto", etSaludo.getText().toString());
            Saludo saludo = new Saludo();
            saludo.setTexto(etSaludo.getText().toString());

            mostrarEspera();

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection(COLECCION).add(saludo)
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
        });
    }

    private void cargarSaludos() {
        mostrarEspera();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(COLECCION).get()
            .addOnSuccessListener(documentSnapshots -> {
                List<Saludo> listaSaludos = new ArrayList<>();
                for (DocumentSnapshot documentSnapshot : documentSnapshots.getDocuments()) {
                    Saludo saludo = documentSnapshot.toObject(Saludo.class);
                    listaSaludos.add(saludo);
                }
                AdaptadorSaludo adaptadorSaludo =
                        new AdaptadorSaludo(listaSaludos);
                rvSaludos.setAdapter(adaptadorSaludo);
                cancelarEspera();
            }).addOnFailureListener(e -> {
                Toast.makeText(getApplicationContext(),
                        e.getMessage(),
                        Toast.LENGTH_SHORT).show();
                cancelarEspera();
            });
    }

    public void mostrarEspera() {
        if(mDefaultDialog == null){
            mDefaultDialog = new ProgressDialog(this);
            // El valor predeterminado es la forma de círculos pequeños
            mDefaultDialog.setProgressStyle (android.app.ProgressDialog.STYLE_SPINNER);
            mDefaultDialog.setMessage("Solicitando datos ...");
            mDefaultDialog.setCanceledOnTouchOutside(false);// Por defecto true
            mDefaultDialog.show();
        }
    }

    public void cancelarEspera(){
        if(mDefaultDialog != null){
            mDefaultDialog.cancel();
            mDefaultDialog = null;
        }
    }
}