package com.example.projetorecyclerview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projetorecyclerview.R;
import com.example.projetorecyclerview.model.Filme;

public class CadastroActivity extends AppCompatActivity {

    private EditText editTextTitulo;
    private EditText editTextAno;
    private EditText editTextGenero;
    private Button buttonCadastros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //referencias
        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextAno = findViewById(R.id.editTextAno);
        editTextGenero = findViewById(R.id.editTextGenero);
        buttonCadastros = findViewById(R.id.buttonCadastros);
        buttonCadastros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filme filme = new Filme(editTextTitulo.getText().toString(),
                        editTextAno.getText().toString(),
                        editTextGenero.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("filme",filme);
                intent.putExtra("codigo","1");
                setResult(CadastroActivity.RESULT_OK,intent);
                finish();
            }
        });
    }
}