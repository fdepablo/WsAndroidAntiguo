package com.example.a12_listascomplejas03recycler.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_listascomplejas03recycler.R;
import com.example.a12_listascomplejas03recycler.entidad.Usuario;
import com.example.a12_listascomplejas03recycler.singleton.ListaUsuarioSingleton;

import java.util.List;

public class AdaptadorUsuarioPersonalizado extends RecyclerView.Adapter<AdaptadorUsuarioPersonalizado.ViewHolder> {

   private List<Usuario> listaUsuarios;

   public AdaptadorUsuarioPersonalizado(List<Usuario> listaUsuarios) {
       this.listaUsuarios = listaUsuarios;
   }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       private TextView id;
       private TextView nombre;
       private TextView edad;
       private TextView peso;
       private TextView fechaNacimiento;
       private Button botonEditar;
       private Button botonEliminar;

        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.idUsuario);
            nombre = v.findViewById(R.id.nombreUsuario);
            edad = v.findViewById(R.id.edadUsuario);
            peso = v.findViewById(R.id.pesoUsuario);
            fechaNacimiento = v.findViewById(R.id.fechaUsuario);

            botonEditar = v.findViewById(R.id.btnEditarUsuario);
            botonEliminar = v.findViewById(R.id.btnEliminarUsuario);
        }
    }

   //será quien devuelva el ViewHolder con el layout seteado que previamente definimos
   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_view, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
   }

   //será quien se encargue de establecer los objetos en el ViewHolder
   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
       String sId = String.valueOf(listaUsuarios.get(position).getId());
       holder.id.setText(sId);
       holder.nombre.setText(listaUsuarios.get(position).getNombre());
       String sEdad = String.valueOf(listaUsuarios.get(position).getEdad());
       holder.edad.setText(sEdad);
       String sPeso = String.valueOf(listaUsuarios.get(position).getPeso());
       holder.peso.setText(sPeso);
       holder.fechaNacimiento.setText(listaUsuarios.get(position).getFechaNacimiento());

       holder.botonEditar.setOnClickListener(view -> {
           Toast.makeText(holder.id.getContext(), "Editando usuario " + sId, Toast.LENGTH_SHORT).show();
           //Aqui habria que abrir otra actividad para editar los contactos o lo
           //que se estimara
       });

       holder.botonEliminar.setOnClickListener(view -> {
           Toast.makeText(holder.id.getContext(), "Eliminando usuario " + sId, Toast.LENGTH_SHORT).show();
           ListaUsuarioSingleton.getInstance().borrar(listaUsuarios.get(position));
           notifyDataSetChanged();
       });
   }

   //será quien devuelva la cantidad de items que se encuentra en la lista
   @Override
   public int getItemCount() {
       return listaUsuarios.size();
   }

}