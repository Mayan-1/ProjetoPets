package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VerDenuncias extends AppCompatActivity {

    RecyclerView rv;
    DatabaseReference database;
    ArrayList<Denuncias> list   ;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;

    Button btnIrCriacaoDenuncia;
    ImageView imgIrDicas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_denuncias);
        rv = findViewById(R.id.rvDenuncias);
        btnIrCriacaoDenuncia = findViewById(R.id.btnIrCriacao);
        imgIrDicas = findViewById(R.id.imageDicas);

        database = FirebaseDatabase.getInstance().getReference("denuncias");
        rv.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(VerDenuncias.this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(linearLayoutManager);


        list = new ArrayList<>();
        myRvAdapter = new MyRvAdapter(this, list);
        rv.setAdapter(myRvAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Denuncias denuncias = dataSnapshot.getValue(Denuncias.class);

                    list.add(denuncias);
                }


                Log.d("TAG", "Tamanho da lista: " + list.size());

                myRvAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "Erro ao obter dados do Firebase: " + error.getMessage());
            }
        });

    }

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder>{

        Context context;
        ArrayList<Denuncias> list;
        public MyRvAdapter(Context context, ArrayList<Denuncias> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_denuncia, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            Denuncias denuncias = list.get(position);
            holder.tipo.setText(denuncias.getTipo());
            holder.descricao.setText(denuncias.getDescricao());
            holder.endereco.setText(denuncias.getEndereco());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{
            TextView tipo, descricao, endereco;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                tipo = itemView.findViewById(R.id.tv_tipo);
                descricao = itemView.findViewById(R.id.tv_descricao);
                endereco = itemView.findViewById(R.id.tv_endereco);


            }
        }
    }

    public void irDicas(View v){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.petz.com.br/blog/cachorros/saude-e-cuidados-cachorros/saude-pet/")));
    }

    public void irTelaCriacao(View v){
        Intent intent = new Intent(VerDenuncias.this, TelaCriarDenuncias.class);
        startActivity(intent);
    }
}