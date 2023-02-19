package com.example.a12_listascomplejas03recycler.singleton;

import com.example.a12_listascomplejas03recycler.entidad.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarioSingleton {

    private static ListaUsuarioSingleton instance;
    private List<Usuario> listaUsuarios;
    private int contador = 1;

    private ListaUsuarioSingleton(){
        super();
    }

    public static ListaUsuarioSingleton getInstance() {
        if(instance == null){
            instance = new ListaUsuarioSingleton();
        }
        return instance;
    }

    public void inicializar(){
        listaUsuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setId(contador++);
        usuario.setNombre("Tony Stark");
        usuario.setEdad(45);
        usuario.setPeso(89.6);
        usuario.setFechaNacimiento("19/02/1975");

        listaUsuarios.add(usuario);

        usuario = new Usuario();
        usuario.setId(contador++);
        usuario.setNombre("Natasha Romanoff");
        usuario.setEdad(33);
        usuario.setPeso(62.5);
        usuario.setFechaNacimiento("19/02/1995");

        listaUsuarios.add(usuario);

        usuario = new Usuario();
        usuario.setId(contador++);
        usuario.setNombre("Steve Rogers");
        usuario.setEdad(38);
        usuario.setPeso(92.5);
        usuario.setFechaNacimiento("09/07/1927");

        listaUsuarios.add(usuario);
        System.out.println("#########################" + listaUsuarios);
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void borrar(Usuario usuario){
        listaUsuarios.remove(usuario);
    }
}
