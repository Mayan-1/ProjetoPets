package com.example.app;

import android.content.Intent;
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

    private void uploadImagem() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.ENGLISH);
        Date now = new Date();
        String filename = format.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+filename);

        storageReference.putFile(imagemUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(TelaCriarDenuncias.this, "Denuncia criada com sucesso", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TelaCriarDenuncias.this, "Algo deu errado!", Toast.LENGTH_SHORT).show();
            }
        });
    }




    // Método chamado ao clicar no botão "Denunciar"
    public void denunciar(View view) {
        // Obtenha os valores dos EditTexts
        String tipo = edtTipo.getText().toString();
        String descricao = edtDescricao.getText().toString();
        String endereco = edtEndereco.getText().toString();


        // Salvando os dados no Firebase Realtime Database
        salvarDenunciaNoFirebase(tipo, descricao, endereco);
        uploadImagem();
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
