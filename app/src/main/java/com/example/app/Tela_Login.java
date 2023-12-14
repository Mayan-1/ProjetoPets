package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Tela_Login extends AppCompatActivity {

    private TextView tex_tela_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        Iniciarcomponentes();

        tex_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Login.this, Tela_cadastro.class);
                startActivity(intent);
            }
        });


    }
    private void Iniciarcomponentes(){
        tex_tela_cadastro = findViewById(R.id.tex_tela_cadastro);
    }


}