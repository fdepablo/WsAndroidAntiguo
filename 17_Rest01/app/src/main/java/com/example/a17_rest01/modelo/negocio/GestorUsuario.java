package com.example.a17_rest01.modelo.negocio;

import com.example.a17_rest01.modelo.servicio.GoRestUsuarioApiService;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GestorUsuario {

    private static GestorUsuario instance = null;
    private GoRestUsuarioApiService goRestUsuarioApiService = null;

    private GestorUsuario(){

    }

    public static GestorUsuario getInstance(){
        if(instance == null){
            instance = new GestorUsuario();
        }
        return instance;
    }

    public void inicializar(){
        //Tenemos que configurar Retrofit para acceder al servicio
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public/v2/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                )).build();

        //Establecemos la relacion entre el servicio y Retrofit
        goRestUsuarioApiService =
                retrofit.create(GoRestUsuarioApiService.class);
    }

    public GoRestUsuarioApiService getGoRestUserApiService(){
        return goRestUsuarioApiService;
    }
}
