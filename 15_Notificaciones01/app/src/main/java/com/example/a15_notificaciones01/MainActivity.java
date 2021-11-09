package com.example.a15_notificaciones01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/*
El framework de Android nos provee de las clases
    NotificationManagerCompat, NotificationCompat y NotificationChannel
para instanciar a las notificaciones y mostrarlas.

En esencia, ensamblamos el código con las siguientes participaciones:

    1. Crear y registrar un canal de notificaciones (NotificationChannel),
    esto es obligatorio desde Android 8.0
    2. Crear la notificación (NotificationCompat.Builder)
    3. Mostrar la notificación con NotificationManagerCompat.notify()

Mas informacion: https://developer.android.com/training/notify-user/build-notification?hl=es-419
 */
public class MainActivity extends AppCompatActivity {

    private EditText etMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMensaje = (EditText)findViewById(R.id.etMensaje);
        createNotificationChannel();
    }

    /*
    Desde Android 8.0 (API 26) es necesario incluir el registro de canales de
    notificaciones para configurar su estilo visual y la importancia desde el sistema.

    Los canales son representados por la clase NotificationChannel e instanciamos
    sus objetos con el método createNotificationChannel().

    Este metodo crea dicho canal
    */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1234", "CanalNotificacion", importance);
            channel.setDescription("Canal de prueba");
            // Registramos el canal en el sistema
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void enviarNotificacion(View v){
        //Pedimos el canal creado anteriormente
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1234")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Notificacion")
                .setContentText("Mensaje: " + etMensaje.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Notificacion id deberia de ser unico por cada notificacion
        int notificationId = 1;
        notificationManager.notify(notificationId, builder.build());
    }
}