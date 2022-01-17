package com.example.a12_listascomplejas02.adaptador;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a12_listascomplejas02.R;
import com.example.a12_listascomplejas02.entidad.Coche;

import java.util.List;

public class AdaptadorCoches extends BaseAdapter{

    private Activity contexto;
    private int layout;
    private List<Coche> listado;

    public AdaptadorCoches(Activity contexto, int layout, List<Coche> listado) {
        this.contexto  = contexto;
        this.layout = layout;
        this.listado = listado;
    }

    @Override
    public int getCount() {
        return listado.size();
    }

    @Override
    public Coche getItem(int position) {
        return listado.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listado.get(position).getId();
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        //Comprobando si el View no existe
        if (convertView == null) {
            //Obteniendo una instancia del inflater
            LayoutInflater inflater = contexto.getLayoutInflater();
            convertView = inflater.inflate(layout,null);
        }

        //Obteniendo instancias de los text views
        TextView marca = convertView.findViewById(R.id.marcaCoche);
        TextView modelo = convertView.findViewById(R.id.modeloCoche);
        TextView id = convertView.findViewById(R.id.cocheId);
        ImageView imagenCoche = convertView.findViewById(R.id.imagenCoche);

        //Obteniendo instancia del coche
        Coche coche = getItem(position);

        marca.setText(coche.getMarca());
        modelo.setText(coche.getModelo());
        id.setText(String.valueOf(coche.getId()));
        imagenCoche.setImageResource(coche.getIdImagen());

        //Devolver al ListView la fila creada
        return convertView;
    }

}
