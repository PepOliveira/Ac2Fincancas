package com.example.ac2minhasfinancasteste;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnAdicionar, btnResumo;
    private ArrayList<Gasto> listaGastos;
    private GastoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdicionar = findViewById(R.id.btnAdicionarGasto);
        btnResumo = findViewById(R.id.btnResumoCategoria);

        listaGastos = new ArrayList<>();
        adapter = new GastoAdapter(this, listaGastos);
        listView.setAdapter(adapter);

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroGastoActivity.class);
            startActivityForResult(intent, 1);
        });

        btnResumo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ResumoActivity.class);
            intent.putExtra("listaGastos", listaGastos);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1 && data != null) {
            Gasto novoGasto = (Gasto) data.getSerializableExtra("novoGasto");
            listaGastos.add(novoGasto);
            adapter.notifyDataSetChanged();
        }
    }
}