package com.example.app;

import android.widget.ImageView;

import java.util.Date;

public class Denuncias {

    private String tipo, descricao, endereco, imgUrl;

    public Denuncias() {
    }

    public Denuncias(String tipo, String descricao, String endereco) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.endereco = endereco;
    }

    public Denuncias(String tipo, String descricao, String endereco, String imgUrl){
        this.tipo = tipo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.imgUrl = imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }
}
