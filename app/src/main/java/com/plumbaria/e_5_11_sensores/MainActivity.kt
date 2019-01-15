package com.plumbaria.e_5_11_sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import kotlin.math.log

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var salida:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        salida = this.findViewById(R.id.salida)
        var sensorManager:SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        /*
        var listaSensores:List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (sensor in listaSensores){
            log(sensor.name)
        }
        */
        var listaSensores:List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION)
        if (!listaSensores.isEmpty()){
            var orientationSensor:Sensor = listaSensores.get(0)
            sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_UI)
        }
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER)
        if(!listaSensores.isEmpty()) {
            var acelerometerSensor = listaSensores.get(0)
            sensorManager.registerListener(this, acelerometerSensor, SensorManager.SENSOR_DELAY_UI)
        }
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD)
        if(!listaSensores.isEmpty()) {
            var magneticSensor = listaSensores.get(0)
            sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_UI)
        }
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE)
        if(!listaSensores.isEmpty()) {
            var temperatureSensor = listaSensores.get(0)
            sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_UI)
        }



    }

    private fun log(sensor: String?) {
        salida.append(sensor + "\n")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        synchronized(this){
            when(event?.sensor?.type){
                Sensor.TYPE_ORIENTATION ->{
                    for (i in 0..2) {
                        log("Orientación" + i + ":" + event.values[i])
                    }
                }
                Sensor.TYPE_ACCELEROMETER ->{
                    for (i in 0..2) {
                        log("Acelerómetro" + i + ":" + event.values[i])
                    }
                }
                Sensor.TYPE_MAGNETIC_FIELD->{
                    for (i in 0..2) {
                        log("Magnetismo" + i + ":" + event.values[i])
                    }
                }
                else ->{
                    for (i in 0..(event?.values?.size!!)) {
                        log("Temperatura" + i + ":" + event.values[i])
                    }
                }
            }
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


}
