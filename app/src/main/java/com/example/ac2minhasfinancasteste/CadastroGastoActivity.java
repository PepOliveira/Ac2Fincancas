package com.example.ac2minhasfinancasteste;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CadastroGastoActivity extends AppCompatActivity {
    private EditText etDescricao, etValor, etData;
    private Spinner spCategoria;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_gasto);

        etDescricao = findViewById(R.id.etDescricao);
        etValor = findViewById(R.id.etValor);
        etData = findViewById(R.id.etData);
        spCategoria = findViewById(R.id.spCategoria);
        btnSalvar = findViewById(R.id.btnSalvar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategoria.setAdapter(adapter);
        etData.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String data = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                etData.setText(data);
            }, ano, mes, dia);
            dpd.show();
        });

        btnSalvar.setOnClickListener(v -> {String descricao = etDescricao.getText().toString();String valorStr = etValor.getText().toString();String categoria = spCategoria.getSelectedItem().toString();String data = etData.getText().toString();

            if (descricao.isEmpty() || valorStr.isEmpty() || data.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double valor = Double.parseDouble(valorStr);
            Gasto novoGasto = new Gasto(descricao, valor, categoria, data);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("novoGasto", novoGasto);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}