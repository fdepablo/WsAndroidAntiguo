package com.example.a12_listascomplejas03recycler.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_listascomplejas03recycler.entidad.Usuario;

import java.util.List;

public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.ViewHolder> {

   private List<Usuario> listaUsuarios;

   public AdaptadorUsuario(List<Usuario> listaUsuarios) {
       this.listaUsuarios = listaUsuarios;
   }

    // Obtener referencias de los componentes visuales para cada elemento
    // Es decir, referencias de los EditText, TextViews, Buttons que haya en el view
    // que usaremos para inflar en cada fila del recicler
   public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre;
        private TextView edad;

        public ViewHolder(View v) {
            super(v);
            nombre = v.findViewById(android.R.id.text1);
            edad = v.findViewById(android.R.id.text2);
        }
    }

    // El layout manager invocará este método
    // para renderizar cada elemento del RecyclerView
   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // Aquí podemos definir tamaños, márgenes, paddings, etc
       View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.two_line_list_item, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
   }

    // Este método se usa asigna valores para cada elemento de la lista o acciones de los
    //elementos
   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
       // - obtenemos un elemento del dataset según su posición
       // - reemplazamos el contenido usando tales datos
       holder.nombre.setText(listaUsuarios.get(position).getNombre());
       String sEdad = String.valueOf(listaUsuarios.get(position).getEdad());
       holder.edad.setText(sEdad);
   }

   // Método que define la cantidad de elementos del RecyclerView
   @Override
   public int getItemCount() {
       return listaUsuarios.size();
   }

}