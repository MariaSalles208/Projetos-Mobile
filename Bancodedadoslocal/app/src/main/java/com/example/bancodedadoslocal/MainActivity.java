package com.example.bancodedadoslocal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText campoID, campoNome, campoPreco, campoQtde;
    private Button btCadastrar, btAtualizar, btApagar, btBuscarID, btListar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        campoID = findViewById(R.id.campoID);
        campoNome = findViewById(R.id.campoNome);
        campoPreco = findViewById(R.id.campoPreco);
        campoQtde = findViewById(R.id.campoQuantidade);
        btCadastrar = findViewById(R.id.btcadastrar);
        btApagar= findViewById(R.id.btapagar);
        btAtualizar= findViewById(R.id.btatualizar);
        btBuscarID= findViewById(R.id.btbuscarId);
        btListar = findViewById(R.id.btlistar);

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity.this, TelaListar.class));
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto p = new Produto(0, campoNome.getText().toString(),
                        Double.parseDouble(campoPreco.getText().toString()),
                        Integer.parseInt(campoQtde.getText().toString()));


                BancoDeDados bd =new BancoDeDados(MainActivity.this, 1); //contexto da MainActivity e versão 1 é a que ele vai utilizar
                if(bd.cadastrar(p)){

                    Toast.makeText(MainActivity.this, "Cadastrado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }

            }
        ;});
        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto p = new Produto(Integer.parseInt(campoID.getText().toString()), campoNome.getText().toString(),
                        Double.parseDouble(campoPreco.getText().toString()),
                        Integer.parseInt(campoQtde.getText().toString()));


                BancoDeDados bd = new BancoDeDados(MainActivity.this, 1); //contexto da MainActivity e versão 1 é a que ele vai utilizar
                if (bd.atualizar(p)) {

                    Toast.makeText(MainActivity.this, "Atualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            }
            });
        btApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BancoDeDados bd = new BancoDeDados(MainActivity.this, 1); //contexto da MainActivity e versão 1 é a que ele vai utilizar
                if (bd.apagar(Integer.parseInt(campoID.getText().toString()))) {

                    Toast.makeText(MainActivity.this, "Apagado", Toast.LENGTH_SHORT).show();
                    campoID.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //usar o ID e buscar o Produto
        btBuscarID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        BancoDeDados bd = new BancoDeDados(MainActivity.this, 1);
                int id = Integer.parseInt(campoID.getText().toString());

                Produto p = bd.buscarID(id);
                if (p == null){
                    Toast.makeText(MainActivity.this, "ID não encontrado", Toast.LENGTH_SHORT).show();
                }else {
                    campoNome.setText(p.getNome());
                    campoPreco.setText(p.getPreco() + "");
                    campoQtde.setText(p.getQuantidade() + "");

                }
            }
        });
    }
}

