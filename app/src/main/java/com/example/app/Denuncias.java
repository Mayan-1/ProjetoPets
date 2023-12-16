package com.example.app;

import java.util.Date;

public class Denuncias {

    private String tipo, descricao, endereco;

    public Denuncias() {
    }

    public Denuncias(String tipo, String descricao, String endereco){
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


}
