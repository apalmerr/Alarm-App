package com.example.alarmapp_arnau_palmer_mora;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int REQUEST_CODE = 1;
    private ListView lista;
    private Adapter adapter;
    ArrayList<ItemAlarma> listaAl = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button anadirAlarma = findViewById(R.id.button_anadirAlarma);
        anadirAlarma.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        //CREAR ALARMA
        if (v.getId() == R.id.button_anadirAlarma){
            //si hay menos de 5 alarmas creadas
            if (listaAl.size() <5){
                Intent explicitIntent = new Intent(this, secondActivity.class);
                startActivityForResult(explicitIntent, REQUEST_CODE);
            }
            //si hay mas NO crees ninguna alarma mas y enseña mensaje
            else{
                Toast maxalarm = Toast.makeText(getApplicationContext(),
                        R.string.limite, Toast.LENGTH_LONG);
                maxalarm.show();
            }
        }
}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent nuevaAlarma) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {

            if (nuevaAlarma.hasExtra("dias")) {

                String nombreAlarma = nuevaAlarma.getStringExtra("nombre");
                String dias = nuevaAlarma.getStringExtra("dias");
                String hora = nuevaAlarma.getStringExtra("hora");
                String minuto = nuevaAlarma.getStringExtra("minuto");

                //añadimos informacion al item
                ItemAlarma item = new ItemAlarma(nombreAlarma, dias, hora, minuto);

                listaAl.add(item);
                lista = findViewById(R.id.listaAl);
                adapter = new Adapter(MainActivity.this, listaAl);

                //añade la alarma a la lista
                lista.setAdapter(adapter);

                if (listaAl.size() == 5) {
                    Toast maximoDeAlarmas = Toast.makeText(getApplicationContext(),
                            R.string.limite, Toast.LENGTH_LONG);
                    maximoDeAlarmas.show();
                }

                //si clickamos en un item de la lista
                lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        //que salga una alerta para eliminar item anteriormente clickado
                        AlertDialog.Builder eliminarAlarma = new AlertDialog.Builder(MainActivity.this);
                        eliminarAlarma.setTitle(R.string.borrar)
                                .setPositiveButton(R.string.SI, new DialogInterface.OnClickListener() {
                                    //elimina item
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        listaAl.remove(position);
                                        adapter = new Adapter(MainActivity.this, listaAl);
                                        lista.setAdapter(adapter);
                                    }
                                })
                                .setNegativeButton(R.string.NO, new DialogInterface.OnClickListener() {
                                    //no se hace nada
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        eliminarAlarma.create().show();
                        return true;
                    }
                });
            }
        }
        super.onActivityResult(requestCode, resultCode, nuevaAlarma);
    }
}