package com.example.ac2minhasfinancasteste;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GastoAdapter extends ArrayAdapter<Gasto> {

    public GastoAdapter(Context context, List<Gasto> gastos) {
        super(context, 0, gastos);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Gasto gasto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_gasto, parent, false);
        }

        TextView tvDescricao = convertView.findViewById(R.id.tvDescricao);
        TextView tvValor = convertView.findViewById(R.id.tvValor);
        TextView tvCategoria = convertView.findViewById(R.id.tvCategoria);
        TextView tvData = convertView.findViewById(R.id.tvData);

        tvDescricao.setText("Descrição do Gasto: " + gasto.getDescricao());
        tvValor.setText("Valor: R$ " + String.format("%.2f", gasto.getValor()));
        tvCategoria.setText("Categoria: " + gasto.getCategoria());
        tvData.setText("Data: " + gasto.getData());

        return convertView;
    }
}


