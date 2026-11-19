package com.example.projetorecyclerview.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projetorecyclerview.R;

public class CartazActivity extends AppCompatActivity {
    private ImageButton imageButtonCartaz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cartaz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageButtonCartaz = findViewById(R.id.imageButtonCartaz);
        Bundle dados = getIntent().getExtras();
        String filme = dados.getString("filme");
        if (filme.equals("It a coisa")) imageButtonCartaz.setImageResource(R.drawable.it_coisa);
        if (filme.equals("A mumia")) imageButtonCartaz.setImageResource(R.drawable.mumia);

        imageButtonCartaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}


