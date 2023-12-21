package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaAdocao extends AppCompatActivity {
    EditText edtTipo, edtDesc, edtEnd;
    Button btnAdotar;

    private DatabaseReference databaseReference;
    private String denunciaKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adocao);

        edtTipo = findViewById(R.id.edt_adocao_tipo);
        edtDesc = findViewById(R.id.edt_adocao_descricao);
        edtEnd = findViewById(R.id.edt_adocao_endereco);
        btnAdotar = findViewById(R.id.btn_adotar);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("denuncias");


        verificarParametro();

        btnAdotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removerDadosNoFireBase();
                Snackbar sb = Snackbar.make(v, "Obrigado por resgatar este animalzinho :)", Snackbar.LENGTH_SHORT);
                sb.setBackgroundTint(Color.GREEN);
                sb.setTextColor(Color.WHITE);
                sb.show();

            }
        });
    }

    private void verificarParametro(){
        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey(("DENUNCIA")))){

            Denuncias denuncias = (Denuncias) bundle.getSerializable("DENUNCIA");
            denunciaKey = denuncias.getKey();
            edtTipo.setText(denuncias.getTipo());
            edtDesc.setText(denuncias.getDescricao());
            edtEnd.setText(denuncias.getEndereco());
        }
    }

    private void removerDadosNoFireBase(){
        if (denunciaKey != null){
            databaseReference.child(denunciaKey).removeValue();
        }
    }
}