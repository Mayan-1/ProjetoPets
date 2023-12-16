package com.example.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.Denuncias;
import com.example.app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class TelaCriarDenuncias extends AppCompatActivity {
    private EditText edtTipo, edtDescricao, edtEndereco;
    private Button btnEscolherImagem;


    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imagemUri;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_denuncias);

        // Inicialize o Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("denuncias");

        // Referencie os EditTexts no layout
        edtTipo = findViewById(R.id.edt_tipo);
        edtDescricao = findViewById(R.id.edt_descricao);
        edtEndereco = findViewById(R.id.edt_endereco);
        btnEscolherImagem = findViewById(R.id.btn_imagem);

        btnEscolherImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherImagem();
            }


        });
    }

    public void escolherImagem() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagemUri = data.getData();
        }
    }



    // Método chamado ao clicar no botão "Denunciar"
    public void denunciar(View view) {
        // Obtenha os valores dos EditTexts
        String tipo = edtTipo.getText().toString();
        String descricao = edtDescricao.getText().toString();
        String endereco = edtEndereco.getText().toString();


        // Salve os dados no Firebase Realtime Database
        salvarDenunciaNoFirebase(tipo, descricao, endereco);
    }


    private void salvarDenunciaNoFirebase(String tipo, String descricao, String endereco) {
        // Crie um nó "denuncia" no Firebase Realtime Database e salve os dados
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

}
