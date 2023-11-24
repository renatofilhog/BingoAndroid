package com.example.bingoleste;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Integer> numerosBingo = new ArrayList<>();
    private Button btnSortear;
    private LinearLayout layoutUltimosNumeros;
    private TextView txtNumeroSorteado; // Adicionando a declaração para o TextView
    private List<Integer> ultimosNumerosSorteados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 1; i <= 75; i++) {
            numerosBingo.add(i);
        }

        btnSortear = findViewById(R.id.btnSortear);
        layoutUltimosNumeros = findViewById(R.id.layoutUltimosNumeros);
        txtNumeroSorteado = findViewById(R.id.txtNumeroSorteado); // Adicionando a referência ao TextView


        btnSortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortearNumero();
            }
        });

        Button btnEncerrar = findViewById(R.id.btnConferir);
        btnEncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NumerosSorteadosActivity.class);
                intent.putExtra("numerosSorteados", (Serializable) ultimosNumerosSorteados);
                startActivity(intent);
            }
        });
    }

    private void sortearNumero() {
        if (!numerosBingo.isEmpty()) {
            Collections.shuffle(numerosBingo);
            int numeroSorteado = numerosBingo.remove(0);

            txtNumeroSorteado.setText(String.valueOf(numeroSorteado));

            ultimosNumerosSorteados.add(numeroSorteado);

            exibirPopUp(numeroSorteado);
        } else {
            txtNumeroSorteado.setText("Todos os números foram sorteados!");
        }
    }

    private void atualizarUltimosNumeros() {
        layoutUltimosNumeros.removeAllViews();

        for (int i = ultimosNumerosSorteados.size() - 1; i >= 0; i--) {
            View view = getLayoutInflater().inflate(R.layout.layout_numero_sorteado, null);
            TextView txtNumero = view.findViewById(R.id.txtNumero);
            ImageView imgBola = view.findViewById(R.id.imgBola);

            txtNumero.setText(String.valueOf(ultimosNumerosSorteados.get(i)));
            imgBola.setImageResource(R.drawable.bola); // Substitua "bola_bingo" pelo nome da sua imagem

            layoutUltimosNumeros.addView(view);
        }
    }

    private void exibirPopUp(int numero) {
        View view = getLayoutInflater().inflate(R.layout.layout_numero_sorteado, null);

        ImageView imgBola = view.findViewById(R.id.imgBola);
        TextView txtNumero = view.findViewById(R.id.txtNumero);

        imgBola.setImageResource(R.drawable.bola);
        txtNumero.setText(String.valueOf(numero));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("Número Sorteado");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // Chamado quando o popup for fechado
                atualizarUltimosNumeros();
            }
        });

        builder.show();
    }
}