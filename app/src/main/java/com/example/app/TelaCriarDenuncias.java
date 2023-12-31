package com.example.app;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.Denuncias;
import com.example.app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


public class TelaCriarDenuncias extends AppCompatActivity {
    private EditText edtTipo, edtDescricao, edtEndereco;
    private DatabaseReference databaseReference;

    Uri imagemUri;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_denuncias);

        // Inicializando o firebase realtime database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("denuncias");




        // Referenciando os EditTexts no layout
        edtTipo = findViewById(R.id.edt_tipo);
        edtDescricao = findViewById(R.id.edt_descricao);
        edtEndereco = findViewById(R.id.edt_endereco);


    }


    // Método chamado ao clicar no botão "Denunciar"
    public void denunciar(View view) {
        // Obtenha os valores dos EditTexts
        String tipo = edtTipo.getText().toString();
        String descricao = edtDescricao.getText().toString();
        String endereco = edtEndereco.getText().toString();


        // Salvando os dados no Firebase Realtime Database
        salvarDenunciaNoFirebase(tipo, descricao, endereco);
        Snackbar sb = Snackbar.make(view, "Obrigado por nos informar sobre este animalzinho :)", Snackbar.LENGTH_SHORT);
        sb.setBackgroundTint(Color.GREEN);
        sb.setTextColor(Color.WHITE);
        sb.show();
    }




    private void salvarDenunciaNoFirebase(String tipo, String descricao, String endereco) {
        // Criando um nó "denuncia" no Firebase Realtime Database e salve os dados
        String denunciaId = databaseReference.push().getKey();

        Denuncias denuncia = new Denuncias(tipo, descricao, endereco);

        databaseReference.child(denunciaId).setValue(denuncia);
        limparCampos();
    }


    private void limparCampos() {
        edtTipo.setText("");
        edtDescricao.setText("");
        edtEndereco.setText("");
    }

    public void irVerDenuncias(View v){
        Intent intent = new Intent(TelaCriarDenuncias.this, VerDenuncias.class);
        startActivity(intent);
    }

}
