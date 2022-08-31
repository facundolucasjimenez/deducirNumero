package com.example.deducirnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    int nroCpu;
    int[] cpu; // va a ser unico y no se va a cambiar (4)
    int[] jugador; // este se va a reemplazar por cada intento, dimension es fija (4)
    String[] intentos; // no sabemos la dimension, que corte cada 6 (4 del nro, 1 de regular y 1 de bien)
    int cantIntentos;
    Button abandonar, volverAIntentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nroCpu = generarNroCpu();
    }

    protected int generarNroCpu() {
        int randomNum = ThreadLocalRandom.current().nextInt(1023, 9877); //techo es 9876+1 porq sino no lo incluye
        return randomNum;
    }
}