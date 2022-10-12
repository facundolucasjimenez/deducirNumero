package com.example.deducirnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PuntajeActivity extends AppCompatActivity {

    private Button btnRegistrar, btnSalir;
    private TextView textViewDni, textViewNombre, textViewCantIntentos, textViewIntentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntaje);

        textViewDni = findViewById(R.id.textViewDni);
        textViewNombre = findViewById(R.id.textViewNombre);
        textViewCantIntentos = findViewById(R.id.textViewCantIntentos);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener((v) -> { alta(v);});

        btnSalir = findViewById(R.id.btnSalir2);
        btnSalir.setOnClickListener((v) -> { finish();});

        /*
        int cantIntentos = MainActivity.cantIntentos;

        textViewIntentos = findViewById(R.id.textViewIntentos);
        textViewIntentos.setText(cantIntentos);
         */
    }

    public void alta(View v){
        String dni = textViewDni.getText().toString();
        String nombre = textViewNombre.getText().toString();
        String cantIntentos = textViewIntentos.getText().toString();

        boolean devolver = OperarConBase.Alta(this, dni, nombre, cantIntentos);
        if(devolver){
            //textViewDni.setText("");
            //textViewNombre.setText("");
            //textViewIntentos.setText("");
            Toast.makeText(this, "Datos del jugador cargados", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "ERROR no se cargaron los datos", Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v){
        AdminBase admin = new AdminBase(this, "baseDeducirNro", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = textViewDni.getText().toString();
        Cursor fila;
        try{
            fila = bd.rawQuery("select nombre, cantIntentos from usuario where dni=" + dni, null);
            if(fila.moveToFirst()){
                modificacion(v);
            }
            else{
                alta(v);
                bd.close();
            }
        }
        catch(Exception ex){
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion(View v){
        AdminBase admin = new AdminBase(this, "baseDeducirNro", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = textViewDni.getText().toString();
        String nombre = textViewNombre.getText().toString();
        String cantIntentos = textViewIntentos.getText().toString();

        ContentValues registro = new ContentValues();

        //datos nuevos
        registro.put("nombre",nombre);
        registro.put("cantIntentos",cantIntentos);

        int cant = bd.update("usuario", registro, "dni="+dni, null);
        bd.close();

        if(cant==1)
            Toast.makeText(this, "Datos del jugador cargados", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No se actualizaron datos", Toast.LENGTH_SHORT).show();
    }
}