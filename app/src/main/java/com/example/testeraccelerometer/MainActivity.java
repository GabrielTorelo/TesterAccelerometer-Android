package com.example.testeraccelerometer;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSensorValues();
    }

    private void getSensorValues() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null) {
            Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if (accelerometer != null) {
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            } else {
                Toast.makeText(this, "Acelerômetro não detectado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Serviço de sensor não detectado", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ((TextView) findViewById(R.id.txtValues)).setText(
                String.format(
                    "X: %s\nY: %s\nZ: %s",
                    sensorEvent.values[0],
                    sensorEvent.values[1],
                    sensorEvent.values[2]
                )
            );
        } else {
            ((TextView) findViewById(R.id.txtValues)).setText("Acelerômetro\nnão detectado");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}
}