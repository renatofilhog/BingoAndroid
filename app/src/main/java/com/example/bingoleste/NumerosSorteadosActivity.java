package com.example.bingoleste;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class NumerosSorteadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bingo);

        List<Integer> numerosSorteados = (List<Integer>) getIntent().getSerializableExtra("numerosSorteados");
        Collections.sort(numerosSorteados);


        ListView listaNumerosSorteados = findViewById(R.id.listaNumerosSorteados);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.layout_numero_sorteado, R.id.txtNumero, numerosSorteados) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Configurar a bolinha
                ImageView imgBola = view.findViewById(R.id.imgBola);
                imgBola.setImageResource(R.drawable.bola);

                return view;
            }
        };

        listaNumerosSorteados.setAdapter(adapter);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());
    }
}
