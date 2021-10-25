package com.example.a02_checkboxradiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CheckBox cbMarcame = findViewById(R.id.cbMarcame);
        final CheckBox cbMarcame2 = findViewById(R.id.cbMarcame2);
        final CheckBox cbMarcame3 = findViewById(R.id.cbMarcame3);
        final CheckBox cbMarcame4 = findViewById(R.id.cbMarcame4);

        //Primera manera de meter eventos mediante clases anonimas
        cbMarcame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                boolean isChecked = checkBox.isChecked();

                if (isChecked) {
                    checkBox.setText("Checkbox marcado!");
                } else {
                    checkBox.setText("Checkbox desmarcado!");
                }
            }
        });

        //Segunda manera, si vamos a usar muchas veces el mismo comportamiento,
        //mejor hacer una clase de verdad
        cbMarcame2.setOnClickListener(new MiOnClickListenerCheckbox());
        cbMarcame3.setOnClickListener(new MiOnClickListenerCheckbox());

        //Tercera manera, usando funciones lambda
        //
        cbMarcame4.setOnClickListener((v)->{
            CheckBox checkBox = (CheckBox) v;
            boolean isChecked = checkBox.isChecked();

            if (isChecked) {
                checkBox.setText("Checkbox marcado!");
            } else {
                checkBox.setText("Checkbox desmarcado!");
            }
        });

        final TextView tvMensaje = findViewById(R.id.tvMensaje);

        RadioGroup rgOpciones = findViewById(R.id.rgGrupo1);

        //Este vento se desencadena cuando hay un cambio en el radiogroup
        //Queremos al elegir una nueva opcion que cambie el texto del mensaje
        //Como parametros de entrada tenemos
        //1. El radiogrupo que desencadena el evento
        //2. El id de radiobutton seleccionado
        rgOpciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                String opcion = "";
                switch (checkedId) {
                    case R.id.rbOpcion1:
                        opcion = getResources().getString(R.string.opcion1);
                        //Podemos obtener los recursos de diferentes maneras
                        //getResources().getString(R.string.app_name);
                        //this.getResources().getIdentifier("nameOfDrawable", "drawable", this.getPackageName());
                        //int o = MainActivity.this.getResources().getIdentifier("app_name","values",MainActivity.this.getPackageName());
                        //System.out.println("El codigo devuelto es: "+o);
                        //opcion = getResources().getString(o);
                        break;
                    case R.id.rbOpcion2:
                        opcion = "opcion 2";
                        break;
                }

                tvMensaje.setText("ID opcion seleccionada: " + opcion);
            }
        });
    }
}