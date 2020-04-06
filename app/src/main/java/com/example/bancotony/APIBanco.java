package com.example.bancotony;

import androidx.databinding.ObservableField;

public class APIBanco {
    private float saldo;

    public ObservableField<String> saldoFormatado;

    public APIBanco() {
        saldoFormatado = new ObservableField<>("R$0,00");
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
        saldoFormatado.set(formatarSaldo());
    }

    private String formatarSaldo() {
        return "R$" + saldo;
    }

    public float fazerDeposito(float depositarsaldo) {
        setSaldo(saldo + depositarsaldo);
        return saldo;
    }

    public float fazerSaque(float sacarsaldo) {
        setSaldo(saldo - sacarsaldo);
        return saldo;
    }

    public boolean saldoNegativo(float saldo, float valor) {
        return saldo < 0;
    }

}
