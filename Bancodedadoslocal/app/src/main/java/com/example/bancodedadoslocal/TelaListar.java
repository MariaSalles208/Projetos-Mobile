package com.example.bancodedadoslocal;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class TelaListar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_listar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView lvProdutos = findViewById(R.id.lvProdutos);
        //Acessar o Banco de dados
        BancoDeDados bd =
                new BancoDeDados(TelaListar.this,1);
        List<Produto> listaProdutos =  bd.buscarTodos();
        //Criar um adapter do listview
        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(TelaListar.this,
                android.R.layout.simple_list_item_1, listaProdutos);

        //Colocar o adapter no lisview
        lvProdutos.setAdapter(adapter);

    }
}