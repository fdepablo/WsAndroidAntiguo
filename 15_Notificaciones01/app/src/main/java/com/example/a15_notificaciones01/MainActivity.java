package com.example.felix.p13_notificaciones_01;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText etMensaje;
    public final static String MENSAJE = "mensaje";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMensaje = (EditText)findViewById(R.id.etMensaje);
    }

    public void enviarNotificacion(View v){
        //Establecemos a que actividad queremos ir cuando pulse la notificación
        Intent intent = new Intent(this, NotificacionActivity.class);
        //Add todos los parametros que queramos añadir
        intent.putExtra(MENSAJE,etMensaje.getText().toString());
        //Con el pedingIntent permitimos a una aplicacion que no es la nuestra
        //usar nuestros permisos de aplicacion para ejecutar una pieza predefinida
        //de nuestro codigo, en este caso la aplicacion sera NotificationManager
        //Además, especifica que la accion va a ser tomada en el futuro, una intención
        //futura que otras aplicaciones pueden usar
        //Los parametros son:
        //1 - Contexto
        //2 - Codigo, codigo de peticion privaro para la aplicacion que mandara la
        // intencion, en este caso NotificationManager
        //3 - intent que queremos mandar con la información
        //4 - flags, con FLAG_UPDATE_CURRENT, en caso de que exista ya este pendin intent
        //los extras del intent seran reemplazados por el ultimo
        PendingIntent pIntent = PendingIntent.getActivity(this,
                                43434344,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

        // Construimos la notificacion
        Notification notificacion  = new Notification.Builder(this)
                .setContentTitle("Notificacion")
                .setContentText("Mensaje: " + etMensaje.getText().toString())
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(pIntent)//Metemos la intención
                .setAutoCancel(true)//Cuando la tocamos, se elimina
                .build();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Le pasamos el identificador de notificación
        final int idNotificacion = 1;
        notificationManager.notify(idNotificacion, notificacion);

    }

}
