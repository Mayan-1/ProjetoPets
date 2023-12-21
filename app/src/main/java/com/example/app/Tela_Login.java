package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Tela_Login extends AppCompatActivity {

    private TextView tex_tela_cadastro;
    private EditText edtEmail, edtSenha;
    private Button btnLogin;
    private FirebaseAuth  auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        tex_tela_cadastro = findViewById(R.id.tex_tela_cadastro);
        auth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edit_email);
        edtSenha = findViewById(R.id.edit_senha);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();
                if (email.isEmpty() || senha.isEmpty()){
                    Snackbar sb = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT);
                    sb.setBackgroundTint(Color.RED);
                    sb.setTextColor(Color.WHITE);
                    sb.show();

                }else {
                    signIn(email, senha);
                }

            }
        });

        tex_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Login.this, Tela_cadastro.class);
                startActivity(intent);
            }
        });


    }
    private void signIn(String email, String senha) {
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            startActivity(new Intent(Tela_Login.this, VerDenuncias.class));

                        }

                    }
                });
        }

}
