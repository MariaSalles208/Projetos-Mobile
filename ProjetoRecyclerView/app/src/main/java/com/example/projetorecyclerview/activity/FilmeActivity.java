package com.example.projetorecyclerview.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetorecyclerview.R;
import com.example.projetorecyclerview.adapter.AdapterFilmes;
import com.example.projetorecyclerview.model.Filme;

import java.util.List;

public class FilmeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFilmes;
    private List<Filme> listaFilmes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filme );
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle dados = getIntent().getExtras();
        listaFilmes = (List<Filme>) dados.getSerializable("listaFilmes");


        recyclerViewFilmes = findViewById(R.id.rec);
        //criarFilmes();
        //Configurar Adapter
        AdapterFilmes adapterFilmes = new AdapterFilmes(listaFilmes);
        //ConfigurarRecyclerView

        //objeto do gerenciador de Layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewFilmes.setLayoutManager(layoutManager);
        // fixar o numero de view que será reciclada
        recyclerViewFilmes.setHasFixedSize(true);
        //cria uma divisão entre as views
        recyclerViewFilmes.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        recyclerViewFilmes.setAdapter(adapterFilmes); //precisa configurar o adaptador
    }

}