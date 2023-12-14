package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class VerDenuncias extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<String> dataSource;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_denuncias);
        rv = findViewById(R.id.rvDenuncias);

        dataSource = new ArrayList<>();
        dataSource.add("Olá");
        dataSource.add("Mundo");
        dataSource.add("Bem");
        dataSource.add("Vindo");
        dataSource.add("Ao");
        dataSource.add("Aplicativo");

        linearLayoutManager = new LinearLayoutManager(VerDenuncias.this, LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter = new MyRvAdapter(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);


    }

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder>{

        ArrayList<String> data;
        public MyRvAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(VerDenuncias.this).inflate(R.layout.item_denuncia, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.tvDescription.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{
            TextView tvDescription;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                tvDescription = itemView.findViewById(R.id.tvDescription);

            }
        }
    }
}