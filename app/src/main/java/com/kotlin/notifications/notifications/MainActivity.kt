package com.kotlin.notifications.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun actualizar(view: View){
        val id = "my_channel_01"
        val nombre = getString(
                R.string.abc_action_bar_home_description)
        val descripcion = getString(
                R.string.abc_action_bar_home_description)
        val importancia = NotificationManager.IMPORTANCE_HIGH
        val canal = NotificationChannel(id, nombre, importancia)
        canal.description = descripcion
        canal.enableLights(true)
        canal.lightColor = Color.RED
        canal.enableVibration(true)

        val canal_id = "my_channel_01"

        val builderNot = NotificationCompat.Builder(
                this, canal_id)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("Mis Notificaciones")
                .setContentText("Verificar mis notificaciones")

        val intentResultado = Intent(this,
                activity_resultado::class.java)

        val resultadoPila = TaskStackBuilder.create(this)
        resultadoPila.addNextIntent(intentResultado)
        resultadoPila.addParentStack(MainActivity::class.java)
        val pendingResultado = resultadoPila.getPendingIntent(
                0, PendingIntent.FLAG_UPDATE_CURRENT)

        builderNot.setContentIntent(pendingResultado)
        val administradorNot = getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager

        administradorNot.notify(1, builderNot.build())
        administradorNot.createNotificationChannel(canal)

    }
}
