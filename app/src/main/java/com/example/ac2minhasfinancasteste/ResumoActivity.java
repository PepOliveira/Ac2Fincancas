package com.example.ac2minhasfinancasteste;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResumoActivity extends AppCompatActivity {

    private TextView tvResumo, tvProcessando;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        tvResumo = findViewById(R.id.tvResumo);
        tvProcessando = findViewById(R.id.tvProcessando);
        progressBar = findViewById(R.id.progressBar);
        ArrayList<Gasto> listaGastos = (ArrayList<Gasto>) getIntent().getSerializableExtra("listaGastos");

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                Map<String, Double> totaisPorCategoria = new HashMap<>();
                double totalGeral = 0;
                String categoriaMaior = "";
                double maiorValor = 0;

                for (Gasto g : listaGastos) {
                    double valor = g.getValor();
                    String cat = g.getCategoria();

                    totalGeral += valor;
                    totaisPorCategoria.put(cat, totaisPorCategoria.getOrDefault(cat, 0.0) + valor);
                }

                for (Map.Entry<String, Double> entry : totaisPorCategoria.entrySet()) {
                    if (entry.getValue() > maiorValor) {
                        maiorValor = entry.getValue();
                        categoriaMaior = entry.getKey();
                    }
                }

                StringBuilder sb = new StringBuilder();
                sb.append("Resumo das Categorias:\n");
                for (Map.Entry<String, Double> entry : totaisPorCategoria.entrySet()) {
                    sb.append(entry.getKey())
                            .append(": R$ ")
                            .append(String.format("%.2f", entry.getValue()))
                            .append("\n");
                }
                sb.append("\nTotal de gastos salvos: R$ ").append(String.format("%.2f", totalGeral));
                sb.append("\nA Categoria com mais gastos é: ").append(categoriaMaior);

                runOnUiThread(() -> {
                    progressBar.setVisibility(ProgressBar.GONE);
                    tvProcessando.setText("Resumo de gastos calculados:");
                    tvResumo.setText(sb.toString());
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
