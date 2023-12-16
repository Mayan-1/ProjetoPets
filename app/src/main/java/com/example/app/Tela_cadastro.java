package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.HashMap;
import java.util.Map;

public class Tela_cadastro extends AppCompatActivity {
    private EditText email,senha,nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        email = findViewById(R.id.edit_email);
        senha = findViewById(R.id.edit_senha);
        nome = findViewById(R.id.edit_nome);

    }

    public void cadastar (View v){
        String mail = email.getText().toString();
        String password = senha.getText().toString();
        String name = nome.getText().toString();

        if (mail.isEmpty()||password.isEmpty()||name.isEmpty()){
            Snackbar sb=
                    Snackbar.make(v,"Preencha todos os campos", Snackbar.LENGTH_SHORT);
            sb.setBackgroundTint(Color.WHITE);
            sb.setTextColor(Color.RED);
            sb.show();
        } else {
            cadastraruser(v);
        }

    }

    private void cadastraruser(View v){
        String mail = email.getText().toString();
        String password = senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Snackbar sb=
                            Snackbar.make(v,"Usuario cadastrado",Snackbar.LENGTH_SHORT);
                    sb.setBackgroundTint(Color.WHITE);
                    sb.setTextColor(Color.WHITE);
                    sb.show();
                } else {
                    String erro;
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e){
                        erro = "Digite uma senha com no minino 6 caracteres";
                    } catch (FirebaseAuthUserCollisionException e){
                        erro = "Está conta já foi criada";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mail inválido";
                    } catch (Exception e){
                        erro="Erro ao cadastrar o usuário";
                    }
                    Snackbar sb =
                            Snackbar.make(v, erro,Snackbar.LENGTH_SHORT);
                    sb.setBackgroundTint(Color.RED);
                    sb.setTextColor(Color.WHITE);
                    sb.show();

                }

            }
        });

    }


    
}