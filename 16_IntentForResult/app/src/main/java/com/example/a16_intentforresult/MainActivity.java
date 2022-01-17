package com.example.felix.p09_intents_01;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    public final static String K_NOMBRE = "nombre";
    public final static int CODIGO_PETICION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "************ Creando la actividad ***************");
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

    public void siguienteActividad(View view){
        Intent intent = new Intent(this,SecondActivity.class);

        EditText texto = (EditText)findViewById(R.id.etNombre);
        intent.putExtra(K_NOMBRE,texto.getText().toString());
        startActivityForResult(intent, CODIGO_PETICION);
    }

    /*
    requestCode: Codigo de peticion que enviamos en el startActivityForResult
    resultCode: Codigo de resultado que nos envia la actividad a la que llamamos
    data: Intent que nos devuelve el resultado y que puede contener datos devueltos
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODIGO_PETICION){
            if(resultCode == SecondActivity.RESULTADO_OK){
                Toast.makeText(this,data.getStringExtra("frase"),Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,data.getStringExtra("frase"),Toast.LENGTH_SHORT).show();
            }
        }
    }
 }
