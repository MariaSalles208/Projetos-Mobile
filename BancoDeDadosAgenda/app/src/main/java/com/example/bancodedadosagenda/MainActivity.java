package com.example.bancodedadosagenda;

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
    private EditText campoID, campoNome, campoSobrenome, campoTelefone;
    private Button btCadastrar, btAtualizar, btApagar, btbuscarsobrenome, btbuscartodos;

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
        campoSobrenome = findViewById(R.id.campoSobrenome);
        campoTelefone = findViewById(R.id.campoTelefone);
        btCadastrar = findViewById(R.id.btcadastrar);
        btApagar= findViewById(R.id.btApagar);
        btAtualizar= findViewById(R.id.btAtualizar);
        btbuscarsobrenome= findViewById(R.id.btbuscarsobrenome);
        btbuscartodos = findViewById(R.id.btbuscartodos);

        btbuscartodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity.this, TelaAgendamentos.class));
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Agenda a = new Agenda(0, campoNome.getText().toString(),
                        (campoSobrenome.getText().toString()),
                        Integer.parseInt(campoTelefone.getText().toString()));


                BancoDeDados bd =new BancoDeDados(MainActivity.this, 1); //contexto da MainActivity e versão 1 é a que ele vai utilizar
                if(bd.cadastrar(a)){

                    Toast.makeText(MainActivity.this, "Cadastrado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }

            }
            ;});
        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Agenda a = new Agenda(Integer.parseInt(campoID.getText().toString()), campoNome.getText().toString(),
                        (campoSobrenome.getText().toString()),
                        Integer.parseInt(campoTelefone.getText().toString()));


                BancoDeDados bd = new BancoDeDados(MainActivity.this, 1); //contexto da MainActivity e versão 1 é a que ele vai utilizar
                if (bd.atualizar(a)) {

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
        btbuscarsobrenome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BancoDeDados bd = new BancoDeDados(MainActivity.this, 1);
                String sobrenome = (campoSobrenome.getText().toString());

                Agenda a = bd.buscarSobrenome(sobrenome);
                if (a == null){
                    Toast.makeText(MainActivity.this, "ID não encontrado", Toast.LENGTH_SHORT).show();
                }else {
                    campoNome.setText(a.getNome());
                    campoSobrenome.setText(a.getsobrenome() + "");
                    campoTelefone.setText(a.getTelefone() + "");

                }
            }
        });
    }
}

