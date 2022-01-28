package com.example.alarmapp_arnau_palmer_mora;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    ArrayList<ItemAlarma> items;
    Context context;

    public Adapter(Context context, ArrayList<ItemAlarma> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemAlarma item = (ItemAlarma) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_alarma, parent, false);
        TextView datosAlarma = convertView.findViewById(R.id.datosalarma);
        datosAlarma.setText( item.getHora() + ":"+item.getMinuto() + "  "+item.getDias() +"---->"+item.getNombre());
        return convertView;
    }
}