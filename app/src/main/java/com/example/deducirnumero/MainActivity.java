package com.example.deducirnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    //int nroCpu;
    int[] cpu; // va a ser unico y no se va a cambiar (4)
    int[] jugador; // este se va a reemplazar por cada intento, dimension es fija (4)
    int[] intentos; // no sabemos la dimension, que corte cada 6 (4 del nro, 1 de bien y 1 de regular)
    int cantIntentos;
    int cantIngreso;
    Button abandonar, volverAIntentar, button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
    Integer[] botones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generarNroCpu();

        botones= new Integer[]{
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6,
                R.id.button_7, R.id.button_8, R.id.button_9
        };

        button_0 = findViewById(R.id.button_0);
        button_0.setEnabled(false);

        cantIngreso=0;
    }

    protected void generarNroCpu() {
        int primerDigito = ThreadLocalRandom.current().nextInt(1, 10); // bound es 9+1

        int segundoDigito = ThreadLocalRandom.current().nextInt(0, 10);
        while (segundoDigito==primerDigito){
            segundoDigito = ThreadLocalRandom.current().nextInt(0, 10);
        }

        int tercerDigito = ThreadLocalRandom.current().nextInt(0, 10);
        while (tercerDigito==primerDigito || tercerDigito==segundoDigito){
            tercerDigito = ThreadLocalRandom.current().nextInt(0, 10);
        }

        int cuartoDigito = ThreadLocalRandom.current().nextInt(0, 10);
        while (cuartoDigito==primerDigito || cuartoDigito==segundoDigito || cuartoDigito==tercerDigito){
            cuartoDigito = ThreadLocalRandom.current().nextInt(0, 10);
        }

        cpu[0] = primerDigito;
        cpu[1] = segundoDigito;
        cpu[2] = tercerDigito;
        cpu[3] = cuartoDigito;
    }

    protected void clickUsuario(View v){
        int numBoton = Arrays.asList(botones).indexOf(v.getId());
        Button boton= (Button)v;
        boton.setEnabled(false);
        if (cantIngreso==1){
            button_0.setEnabled(true);
        }
        jugador[cantIngreso] = numBoton;
        cantIngreso++;

    }

    protected int comparar(){
        int bien = 0;
        int regular = 0;
        for (int i = 0; i<4; i++){ // como si fuese una matriz
            if (jugador[i]==cpu[i]){
                bien++;
            }
            else {
                for (int j = 0; j < 4; j++) {
                    if (jugador[i]==cpu[j]){
                        regular++;
                    }
                }
            }
        }
        int aux = cantIntentos*6;
        intentos[aux] = jugador[0];
        intentos[aux+1] = jugador[1];
        intentos[aux+2] = jugador[2];
        intentos[aux+3] = jugador[3];
        intentos[aux+4] = bien;
        intentos[aux+5] = regular;

        cantIntentos++;

        return bien;
    }

    protected void validarGanador(){
        if (cantIngreso==3){ // cuando termine de ingresar el nro
            if (comparar()==4){

            }
            else{
                cantIngreso = 0;
                // agregar el intento a la lista de la interfaz
            }
        }
    }
}