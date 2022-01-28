package com.example.alarmapp_arnau_palmer_mora;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class secondActivity extends AppCompatActivity implements View.OnClickListener{

    String hora;
    String minuto;
    String dias;
    String nombre;
    boolean[] seleccionarDias = {false,false,false,false,false,false,false};
    private static String[] informacion = new String[3];
    final Calendar calendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_main);


        Button dia = findViewById(R.id.dias);
        Button horas = findViewById(R.id.hora);
        Button guardar = findViewById(R.id.guardar);

        dia.setOnClickListener(this);
        horas.setOnClickListener(this);
        guardar.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {

        //-------------BOTON DIAS----------------
        if (v.getId() == R.id.dias) {
            CharSequence[] elementosDias = {getResources().getString(R.string.lunes), getResources().getString(R.string.martes),getResources().getString(R.string.miercoles),
                    getResources().getString(R.string.jueves),getResources().getString(R.string.viernes),
                    getResources().getString(R.string.sabado), getResources().getString(R.string.domingo)};

            AlertDialog.Builder builder = new AlertDialog.Builder(secondActivity.this);
            builder.setTitle(R.string.diasRep)
                    .setMultiChoiceItems(elementosDias, seleccionarDias, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            if (isChecked) {
                                seleccionarDias[which] = true;
                            } else {
                                seleccionarDias[which] = false;
                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        //------------BOTON GUARDAR-------------
        } else if(v.getId()== R.id.guardar){

            EditText editText = findViewById(R.id.nombreAlarma);
            diasString();

            if (!String.valueOf(editText.getText()).equals("")){
                nombre = String.valueOf(editText.getText());
            }
            if (dias !=null && hora !=null && nombre !=null){
                finish();
            } else {
                if (dias == null) {
                    Toast faltanDias = Toast.makeText(getApplicationContext(),
                            R.string.faltanDias, Toast.LENGTH_LONG);
                }else if(hora == null){
                    Toast faltanHora = Toast.makeText(getApplicationContext(),
                            R.string.faltanHora, Toast.LENGTH_LONG);
                }else if(nombre == null){
                    Toast faltanNombre = Toast.makeText(getApplicationContext(),
                            R.string.faltanNombre, Toast.LENGTH_LONG);
                }
            }


        //--------------BOTON DE HORA-------------
        } else if(v.getId() == R.id.hora) {
            TimePickerDialog time = new TimePickerDialog(secondActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hora = String.valueOf(hourOfDay);
                    if( minute == 0 || minute == 1 || minute == 2 || minute == 3 || minute == 4 || minute == 5 || minute == 6 || minute == 7 || minute == 8 || minute == 9){
                        minuto = "0"+ minute;
                    } else {
                        minuto = String.valueOf(minute);
                    }
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            time.show();
        }
    }


    //metodo para convertir los dias elegidos en string
    public void diasString(){
        for (int i =0;i <7; i++){
            if (seleccionarDias[i]){
                if (dias == null){
                    dias = "";
                }
                switch (i){
                    case 0:
                        dias += "L ";
                        seleccionarDias[i] = false;
                        break;
                    case 1:
                        dias += "M ";
                        seleccionarDias[i] = false;
                        break;
                    case 2:
                        dias += "X ";
                        seleccionarDias[i] = false;
                        break;
                    case 3:
                        dias += "J ";
                        seleccionarDias[i] = false;
                        break;
                    case 4:
                        dias += "V ";
                        seleccionarDias[i] = false;
                        break;
                    case 5:
                        dias += "S ";
                        seleccionarDias[i] = false;
                        break;
                    case 6:
                        dias += "D";
                        seleccionarDias[i] = false;
                        break;
                    default:
                }
            }
        }
    }

    @Override
    public void finish() {
        diasString();
        Intent guardarAlarma = new Intent(this, MainActivity.class);
        guardarAlarma.putExtra("nombre", nombre);
        guardarAlarma.putExtra("dias", dias);
        guardarAlarma.putExtra("hora",hora);
        guardarAlarma.putExtra("minuto", minuto);
        setResult(RESULT_OK, guardarAlarma);
        super.finish();
    }
}
