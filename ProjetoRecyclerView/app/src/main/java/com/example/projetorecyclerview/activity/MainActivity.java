package com.example.projetorecyclerview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projetorecyclerview.R;
import com.example.projetorecyclerview.model.Filme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonCadastar;
    private Button buttonCatalogo;
    private List<Filme> listaFilmes = new ArrayList<>();
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
        buttonCadastar = findViewById(R.id.buttonCadastro);
        buttonCatalogo = findViewById(R.id.buttonCatalogo);
        //eventos
        buttonCadastar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CadastroActivity.class);
                resultado.launch(intent);
            }
        });
        buttonCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FilmeActivity.class);
                intent.putExtra("listaFilmes", (Serializable) listaFilmes);
                startActivity(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> resultado = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        Intent intent = result.getData();
                        String codigo = intent.getStringExtra("codigo");
                        if (intent != null && codigo.equals("1") ){
                            listaFilmes.add((Filme) intent.getSerializableExtra("filme"));
                        }
                    }
                }
            });
}