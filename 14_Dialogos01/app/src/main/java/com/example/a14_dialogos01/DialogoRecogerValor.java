package com.example.a14_dialogos01;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Creamos un dialogo mediante la clase DialogFragment que nos permite personalizar
//el dialogo de alerta que vamos a mostrar. En este caso la clase que vamos a utilizar
//es DialogFragment.
public class DialogoRecogerValor extends DialogFragment {

    private TextView tvResultado;

    public DialogoRecogerValor(){
        super();
    }
    public void setView(TextView tvResultado){
        this.tvResultado = tvResultado;
    }

    //Metodo para construir tu propio dialogo
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Dialogo Personalizado");
        builder.setMessage("Escriba un mensaje para pasarlo a la actividad");

        //Podemos dar de alta Views en nuestro Cuadro de dialogo creando los objetos
        //Tambien podriamos hacer un getActivity.getLayoutInflater() para inflar un xml
        //que tengamos en nuestra app y pasarselo al builder.setView()
        final EditText etAlerta = new EditText(getActivity());
        builder.setView(etAlerta);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Texto Contenia " + etAlerta.getText().toString(), Toast.LENGTH_SHORT).show();
                tvResultado.setText(etAlerta.getText().toString());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Ha pulsado Cancelar", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }

    //Este metodo se lanza cuando se muestre el cuadro al usuario.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Establecemos que no se pueda cancelar al pulsar fuera de la pantalla
        getDialog().setCanceledOnTouchOutside(false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}