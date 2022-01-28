package com.example.alarmapp_arnau_palmer_mora;

public class ItemAlarma {

        String nombre;
        String dias;
        String hora;
        String minuto;

        public String getNombre() {
            return nombre;
        }

        public String getHora() {
            return hora;
        }

        public String getMinuto() {
            return minuto;
        }

        public String getDias() {
            return dias;
        }

        ItemAlarma(String nombre, String dias, String hora, String minuto){
            this.nombre = nombre;
            this.dias = dias;
            this.hora = hora;
            this.minuto = minuto;
        }
    }
