package com.example.a17_rest01.modelo.servicio;

import com.example.a17_rest01.modelo.entidad.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/*
Para hacer el acceso al servicio, vamos usar retrofit

Debemos de crear una interfaz que será la encargada de hacer las peticiones
Http a nuestro servicio Rest.

A continuacion anotamos los metodos que queramos con las anotaciones
de retrofit para agregar la funcionalidad.
 */
public interface GoRestUsuarioApiService {
    @GET("users")
    Call<List<Usuario>> getUsuarios();
    @GET("users/{id}")
    Call<Usuario> getUsuarioPorId(@Path("id") String id);

    //Para modificar el API rest "GoRest" hay que logarse en la página y generar
    //un token de autenticación, luego hay que incluirlo en la cabecera, dentro
    //del parametros Authorization
    @Headers({
            "Authorization: Bearer c2013086d51347da56494c501d63f7c14f51b908a43b9c0ec0145cfab4b901cd"
    })
    @POST("users")
    Call<Usuario> crearUsuario(@Body Usuario usuario);

    @Headers({
            "Authorization: Bearer c2013086d51347da56494c501d63f7c14f51b908a43b9c0ec0145cfab4b901cd"
    })
    @PUT("users/{id}")
    Call modificarUsuario(@Path("id") String id, @Body Usuario usuario);

    @Headers({
            "Authorization: Bearer c2013086d51347da56494c501d63f7c14f51b908a43b9c0ec0145cfab4b901cd"
    })
    @DELETE("users/{id}")
    Call borrarUsuario(@Path("id") String id);
}
