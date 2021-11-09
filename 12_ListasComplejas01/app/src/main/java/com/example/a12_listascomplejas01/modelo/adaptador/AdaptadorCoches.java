package com.example.a12_listascomplejas01.modelo.adaptador;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.a12_listascomplejas01.modelo.entidad.Coche;

import java.util.List;

/*
Implementamos un adaptador extendiendo de BaseAdapter ya que el ArrayAdapter
no nos vale.

Este adaptador se encargará de hacer de puente entre el modelo de datos y
el ListView
 */
public class AdaptadorCoches extends BaseAdapter{

    private Activity activity;
    private int layout;
    private List<Coche> listado;

    public AdaptadorCoches(Activity activity, int layout, List<Coche> lista) {
        this.activity  = activity;
        this.layout = layout;
        this.listado = lista;
    }

    //Para saber el tamaño del array adapter
    @Override
    public int getCount() {
        return listado.size();
    }

    //para recuperar un item del adaptador
    @Override
    public Object getItem(int position) {
        return listado.get(position);
    }

    //Para obtener un id del un elemento
    @Override
    public long getItemId(int position) {
        return listado.get(position).getId();
    }

    //Para añadir un item a la lista
    public void addItem(Coche coche){
        listado.add(coche);
    }

    //para borrar un item de la lista
    public void deleteItem(Coche coche){
        listado.remove(coche);
    }

    @Override
    /*
    El metodo getView se llamara cada vez que queramos add un elemento al ListView.
    El proceso de agregar una View al xml de una actividad se le conoce como "inflar"

    Podemos encontrar los siguientes parametros de entrada:

    1. position: Es la posicion del item que deseamos inflar en la lista.
    2. convertView: Representa la instancia donde asignaremos el View creado.
    La primera vez que es llamado para un elemento especifico su valor sera null
    debido a que aun no existe el view para esa fila de lista, en ese caso debemos
    de instanciar el View y rellenar los datos.
    Una vez es haya sido creado ya se quedará ese view asociado a esa fila, por lo
    que normalmente debemos evitar volver a inflarlo, ya que esto consumiria
    procesamiento extra.
    3. parent: View padre donde se asignara nuestro item, en este caso sera el ListView.
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        //Comprobando si el View no existe
        if (convertView == null) {
            //Obteniendo una instancia del inflater
            LayoutInflater inflater = (LayoutInflater)activity.getLayoutInflater();
            //Si no existe, entonces inflarlo con el layout con que lo inicializamos
            convertView = inflater.inflate(layout,null);
        }

        //Obteniendo instancias de los text views.
        //R.layout.two_line_list_item (listItemView) tiene dos TextView por defecto
        //predefinidos (text1, text2)
        TextView marca = (TextView)convertView.findViewById(android.R.id.text1);
        TextView modelo = (TextView)convertView.findViewById(android.R.id.text2);

        //Obteniendo instancia del coche
        Coche coche = (Coche)getItem(position);

        marca.setText(coche.getMarca());
        modelo.setText(coche.getModelo());

        //Devolver al ListView la fila inflada
        return convertView;
    }
}
