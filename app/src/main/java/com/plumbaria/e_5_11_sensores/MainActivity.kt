package com.plumbaria.e_5_11_sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var salida:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        salida = findViewById(R.id.salida)
        var sensorManager:SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        var listaSensores:List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (sensor in listaSensores){
            log(sensor.name)

        }
    }

    private fun log(sensor: String?) {
        salida.append(sensor + "\n")
    }
}
