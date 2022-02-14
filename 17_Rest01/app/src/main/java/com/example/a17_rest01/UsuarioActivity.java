package com.example.a17_rest01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a17_rest01.modelo.entidad.Usuario;
import com.example.a17_rest01.modelo.negocio.GestorUsuario;
import com.example.a17_rest01.modelo.servicio.GoRestUsuarioApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioActivity extends AppCompatActivity {

    private String id;
    private EditText etNombre;
    private EditText etEmail;
    private TextView tvId;

    private ProgressDialog mDefaultDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        id = (String) getIntent().getExtras().get("id");
        Log.d("UsuarioActivity", "ID: " + id);

        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEMail);
        tvId = findViewById(R.id.tvId);

        tvId.setText(id);

        accederUsuario();
    }

    public void accederUsuario(){
        mostrarEspera();

        GoRestUsuarioApiService goRestUsuarioApiService =
                GestorUsuario.getInstance().getGoRestUserApiService();

        Call<Usuario> llamadaAcceder = goRestUsuarioApiService.getUsuarioPorId(id);

        llamadaAcceder.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuario = response.body();
                etNombre.setText(usuario.getName());
                etEmail.setText(usuario.getEmail());

                cancelarEspera();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("UsuarioActivity", "Error al mostrar usuario: " + id);
                cancelarEspera();
            }
        });
    }

    public void borrarUsuario(){
        GoRestUsuarioApiService goRestUsuarioApiService =
                GestorUsuario.getInstance().getGoRestUserApiService();

        Call llamadaBorrar = goRestUsuarioApiService.borrarUsuario(id);

        llamadaBorrar.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void modificarUsuario(){
        GoRestUsuarioApiService goRestUsuarioApiService = GestorUsuario.getInstance().getGoRestUserApiService();

        Call llamadaBorrar = goRestUsuarioApiService.borrarUsuario(id);

        llamadaBorrar.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void mostrarEspera() {
        mDefaultDialog = new ProgressDialog(this);
        // El valor predeterminado es la forma de círculos pequeños
        mDefaultDialog.setProgressStyle (android.app.ProgressDialog.STYLE_SPINNER);
        mDefaultDialog.setMessage("Solicitando datos ...");
        mDefaultDialog.setCanceledOnTouchOutside(false);// Por defecto true
        mDefaultDialog.show();
    }

    public void cancelarEspera(){
        mDefaultDialog.cancel();
    }
}