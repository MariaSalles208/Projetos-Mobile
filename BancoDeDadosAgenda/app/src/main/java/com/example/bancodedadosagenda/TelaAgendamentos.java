package com.example.bancodedadosagenda;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class TelaAgendamentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_agendamentos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView lvAgendamentos = findViewById(R.id.lvAgendamentos);
        //Acessar o Banco de dados
        BancoDeDados bd =
                new BancoDeDados(TelaAgendamentos.this,1);
        List<Agenda> listaAgendamento =  bd.buscarTodos();
        //Criar um adapter do listview
        ArrayAdapter<Agenda> adapter = new ArrayAdapter<>(TelaAgendamentos.this,
                android.R.layout.simple_list_item_1, listaAgendamento);

        //Colocar o adapter no lisview
        lvAgendamentos.setAdapter(adapter);

    }
}