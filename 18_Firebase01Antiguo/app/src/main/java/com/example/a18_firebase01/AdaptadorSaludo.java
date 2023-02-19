package com.example.a18_firebase01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorSaludo extends RecyclerView.Adapter<AdaptadorSaludo.ViewHolder> {

   private List<Saludo> listaSaludos;

   public AdaptadorSaludo(List<Saludo> listaSaludos) {
       this.listaSaludos = listaSaludos;
   }

    // Obtener referencias de los componentes visuales para cada elemento
    // Es decir, referencias de los EditText, TextViews, Buttons que haya en el view
    // que usaremos para inflar en cada fila del recicler
   public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView texto;

        public ViewHolder(View v) {
            super(v);
            texto = v.findViewById(android.R.id.text1);
        }
    }

    // El layout manager invocará este método
    // para renderizar cada elemento del RecyclerView
   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // Aquí podemos definir tamaños, márgenes, paddings, etc
       View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
   }

    // Este método se usa asigna valores para cada elemento de la lista o acciones de los
    //elementos
   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
       // - obtenemos un elemento del dataset según su posición
       // - reemplazamos el contenido usando tales datos
       holder.texto.setText(listaSaludos.get(position).getTexto());
   }

   // Método que define la cantidad de elementos del RecyclerView
   @Override
   public int getItemCount() {
       return listaSaludos.size();
   }

}