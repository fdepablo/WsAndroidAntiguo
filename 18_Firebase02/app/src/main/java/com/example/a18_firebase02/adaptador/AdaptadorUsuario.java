package com.example.a18_firebase02.adaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a18_firebase02.R;
import com.example.a18_firebase02.UsuarioActivity;
import com.example.a18_firebase02.modelo.entidad.Usuario;

import java.util.List;

public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.ViewHolder> {

   private List<Usuario> listaUsuarios;

   public AdaptadorUsuario(List<Usuario> listaUsuarios) {
       this.listaUsuarios = listaUsuarios;
   }

   public void setListaUsuarios(List<Usuario> listaUsuarios){
       this.listaUsuarios = listaUsuarios;
   }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       private TextView id;
       private TextView nombre;
       private TextView email;
       private TextView genero;
       private TextView estado;
       private Button botonDetalle;

        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.idUsuario);
            nombre = v.findViewById(R.id.nombreUsuario);
            email = v.findViewById(R.id.emailUsuario);
            genero = v.findViewById(R.id.generoUsuario);
            estado = v.findViewById(R.id.estadoUsuario);

            botonDetalle = v.findViewById(R.id.btVerUsuario);
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
       holder.nombre.setText(listaUsuarios.get(position).getName());
       holder.email.setText(listaUsuarios.get(position).getEmail());
       holder.genero.setText(listaUsuarios.get(position).getGender());
       holder.estado.setText(listaUsuarios.get(position).getStatus());

       holder.botonDetalle.setOnClickListener(view -> {
           Toast.makeText(view.getContext(), "Detalle de usuario " + sId, Toast.LENGTH_SHORT).show();
           Intent usuarioIntent = new Intent(view.getContext(), UsuarioActivity.class);
           usuarioIntent.putExtra("id",holder.id.getText());
           view.getContext().startActivity(usuarioIntent);
       });
   }

   //será quien devuelva la cantidad de items que se encuentra en la lista
   @Override
   public int getItemCount() {
       return listaUsuarios.size();
   }

}