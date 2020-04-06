package com.example.bancotony;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bancotony.databinding.ActivityAcoesBancoBinding;

public class AcoesBancoActivity extends AppCompatActivity {
    private APIBanco api1 = new APIBanco();
    String clicado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAcoesBancoBinding mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_acoes_banco);

        Button button_depositar = findViewById(R.id.button_deposito);
        Button button_sacar = findViewById(R.id.button_sacar);
        Button button_pagar = findViewById(R.id.button_pagar_boleto);
        final Button button_ok = findViewById(R.id.button_ok);
        final EditText input_digite_valor = findViewById(R.id.digite_valor);
        final TextView sd_negativo = findViewById(R.id.saldo);



        mainBinding.setApi(api1);

        button_depositar.setOnClickListener(new Button.OnClickListener() {


            @SuppressLint("SetTextI18n")
            public void onClick(View a) {
                button_ok.setVisibility(View.VISIBLE);
                input_digite_valor.setVisibility(View.VISIBLE);
                button_ok.setText(R.string.msg_ok);
                clicado = "depositar";

            }

        });

        button_sacar.setOnClickListener(new Button.OnClickListener() {


            public void onClick(View a) {
                button_ok.setVisibility(View.VISIBLE);
                input_digite_valor.setVisibility(View.VISIBLE);
                button_ok.setText(R.string.msg_ok);
                clicado = "sacar";

            }

        });

        button_pagar.setOnClickListener(new Button.OnClickListener() {


            public void onClick(View a) {
                input_digite_valor.setVisibility(View.VISIBLE);
                button_ok.setVisibility(View.VISIBLE);
                button_ok.setText(R.string.msg_ok);
                clicado = "pagar";
            }

        });

        button_ok.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View a) {
                if (input_digite_valor.getText().toString().equals("") && !clicado.equals("sair")) {
                    mostrarMensagem(R.string.titulo_erro_usuario, R.string.sem_valor);
                } else if(clicado.equals("depositar")) {
                    api1.fazerDeposito(Float.parseFloat(input_digite_valor.getText().toString()));
                    mostrarMensagem(R.string.title_sucesso, R.string.deposito_sucesso);
                } else if(clicado.equals("sacar")){
                    api1.fazerSaque(Float.parseFloat(input_digite_valor.getText().toString()));
                    mostrarMensagem(R.string.title_sucesso, R.string.saque_sucesso);
                } else if(clicado.equals("pagar")){
                    api1.fazerSaque(Float.parseFloat(input_digite_valor.getText().toString()));
                    mostrarMensagem(R.string.coitado, R.string.foi_triste);
                }
                if(api1.saldoNegativo(api1.getSaldo(), 0)){
                    sd_negativo.setText(R.string.negativado);
                } else {
                    sd_negativo.setText(R.string.msg_saldo);
                }
            }
        });
    }

    private void mostrarMensagem(int title, int msg) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(AcoesBancoActivity.this);
        alerta
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(R.string.msg_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Button button_ok = findViewById(R.id.button_ok);
                        final EditText input_digite_valor = findViewById(R.id.digite_valor);
                        button_ok.setVisibility(View.GONE);
                        input_digite_valor.setVisibility(View.GONE);
                    }
                });

        AlertDialog alertDialog = alerta.create();
        alertDialog.show();
    }
}
