package com.example.app;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.Date;

public class Denuncias implements Serializable {

    private String tipo, descricao, endereco, key;

    public Denuncias() {
    }

    public Denuncias(String tipo, String descricao, String endereco) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
