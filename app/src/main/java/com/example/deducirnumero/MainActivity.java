package com.example.deducirnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    //int nroCpu;
    int i;
    int[] cpu = new int[4]; // va a ser unico y no se va a cambiar (4)
    int[] jugador = new int[4]; // este se va a reemplazar por cada intento, dimension es fija (4)
    int[] intentos = new int[100];
    // no sabemos la dimension, que corte cada 6 (4 del nro, 1 de bien y 1 de regular)
    int cantIntentos;
    int cantIngreso;
    Button btnValidar, salir, nuevoIntento, button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
    Integer[] botones;
    TextView nro_cpu, textViewNroIngresado, msjUsuario, nroIntentos;
    ScrollView listaIntentos;

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

        btnValidar = findViewById(R.id.btnValidar);
        btnValidar.setEnabled(false);

        nuevoIntento = findViewById(R.id.btnNuevoIntento);
        nuevoIntento.setEnabled(false);

        cantIngreso=0;
        for (int i = 0; i<4; i++){
            jugador[i]=-1;
        }
    }

    protected void generarNroCpu() {
        Random rand = new Random();
        int primerDigito = rand.nextInt(8); // 8 porque techo es 9
        primerDigito++;

        int segundoDigito = rand.nextInt(9);
        while (segundoDigito==primerDigito){
            segundoDigito = ThreadLocalRandom.current().nextInt(0, 10);
        }

        int tercerDigito = rand.nextInt(9);
        while (tercerDigito==primerDigito || tercerDigito==segundoDigito){
            tercerDigito = ThreadLocalRandom.current().nextInt(0, 10);
        }

        int cuartoDigito = rand.nextInt(9);
        while (cuartoDigito==primerDigito || cuartoDigito==segundoDigito || cuartoDigito==tercerDigito){
            cuartoDigito = ThreadLocalRandom.current().nextInt(0, 10);
        }

        cpu[0] = primerDigito;
        cpu[1] = segundoDigito;
        cpu[2] = tercerDigito;
        cpu[3] = cuartoDigito;

        nro_cpu = findViewById(R.id.nro_cpu);
        String aux = cpu[0]+""+cpu[1]+""+cpu[2]+""+cpu[3];
        nro_cpu.setText(aux);


    }

    public void clickUsuario(View v){
        int numBoton = Arrays.asList(botones).indexOf(v.getId());
        Button boton= (Button)v;
        boton.setEnabled(false);
        if (cantIngreso==0){
            button_0.setEnabled(true);
        }
        jugador[cantIngreso] = numBoton;
        cantIngreso++;

        //Se va escribiendo el num mientras se clickea.
        String aux="";
        textViewNroIngresado = findViewById(R.id.textViewNroIngresado);
        //int auxIndice = cantIntentos*6;
        for (int i = 0; i<4; i++){
            if (jugador[i]==-1){
                aux= aux + "_";
            }else
                aux= aux + jugador[i];
        }
        textViewNroIngresado.setText(aux);

        if (cantIngreso==4){
            button_0 = findViewById(R.id.button_0);
            button_1 = findViewById(R.id.button_1);
            button_2 = findViewById(R.id.button_2);
            button_3 = findViewById(R.id.button_3);
            button_4 = findViewById(R.id.button_4);
            button_5 = findViewById(R.id.button_5);
            button_6 = findViewById(R.id.button_6);
            button_7 = findViewById(R.id.button_7);
            button_8 = findViewById(R.id.button_8);
            button_9 = findViewById(R.id.button_9);
            button_0.setEnabled(false);
            button_1.setEnabled(false);
            button_2.setEnabled(false);
            button_3.setEnabled(false);
            button_4.setEnabled(false);
            button_5.setEnabled(false);
            button_6.setEnabled(false);
            button_7.setEnabled(false);
            button_8.setEnabled(false);
            button_9.setEnabled(false);

            textViewNroIngresado = findViewById(R.id.textViewNroIngresado);
            //int auxIndice = cantIntentos*6;
            String aux_2 = jugador[0] + "" + jugador[1] + "" + jugador[2] + "" + jugador[3];
            textViewNroIngresado.setText(aux_2);

            btnValidar = findViewById(R.id.btnValidar);
            btnValidar.setEnabled(true);
        }
    }

    public int comparar(){
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

    public void validarGanador(View v){
            if (comparar()==4){
                msjUsuario = findViewById(R.id.msjUsuarioGanaste);
                msjUsuario.setText("¡¡¡¡ GANASTE !!!!!");
                nuevoIntento = findViewById(R.id.btnNuevoIntento);
                nuevoIntento.setEnabled(false);
                btnValidar.setEnabled(false);
            }
            else{
                nroIntentos = findViewById(R.id.nroIntentos);
                nroIntentos.setText(cantIntentos+"");
                nuevoIntento = findViewById(R.id.btnNuevoIntento);
                nuevoIntento.setEnabled(true);
                cantIngreso = 0;
                msjUsuario = findViewById(R.id.msjUsuarioPerdiste);
                msjUsuario.setText("¡VUELVE A INTENTARLO!");
                btnValidar.setEnabled(false);
                limpiarLista();
                actualizarLista();
            }
    }

    public void limpiarLista(){
        for (int i = 0; i<4; i++){
            jugador[i]=-1;
        }
    }

    public void actualizarLista(){
        listaIntentos = findViewById(R.id.listaIntentos);
        LinearLayout layout = (LinearLayout)findViewById(R.id.layoutListaIntentos);

        layout.removeAllViewsInLayout();

        TextView subtitulo = new TextView(this);
        subtitulo.setText("Numero       Bien    Regular");
        layout.addView(subtitulo);

        for (int i= 0; i<cantIntentos*6; i=i+6) {
            String aux = intentos[i]+""+intentos[i+1]+""+intentos[i+2]+""+intentos[i+3]+"               "+intentos[i+4]+"           "+intentos[i+5];
            TextView a = new TextView(this);
            a.setText(aux);
            layout.addView(a);
        }
    }

    public void nuevoIntento(View v){
        nuevoIntento = findViewById(R.id.btnNuevoIntento);
        nuevoIntento.setEnabled(false);

        cantIngreso=0;

        btnValidar = findViewById(R.id.btnValidar);
        btnValidar.setEnabled(false);

        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_1.setEnabled(true);
        button_2.setEnabled(true);
        button_3.setEnabled(true);
        button_4.setEnabled(true);
        button_5.setEnabled(true);
        button_6.setEnabled(true);
        button_7.setEnabled(true);
        button_8.setEnabled(true);
        button_9.setEnabled(true);

        textViewNroIngresado = findViewById(R.id.textViewNroIngresado);
        textViewNroIngresado.setText("____");
        msjUsuario=findViewById(R.id.msjUsuarioGanaste);
        msjUsuario.setText("");
        msjUsuario=findViewById(R.id.msjUsuarioPerdiste);
        msjUsuario.setText("");

    }

    public void salir(View v){
        finish();
        System.exit(0);
    }
    
    public void volverAJugar(View v){
        finish();
        startActivity(getIntent());
    }
}
