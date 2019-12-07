package com.example.banbajio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    Button search, add , agregarUsuario, mod, elim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        add = findViewById(R.id.btnAdd);
        search = findViewById(R.id.btnSearch);
        agregarUsuario = findViewById(R.id.btnAddUser);
        mod = findViewById(R.id.btnMod);
        elim = findViewById(R.id.btnElim);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Add.class);
                i.putExtra("ip",getIntent().getStringExtra("ip"));
                startActivityForResult(i, 0);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Search.class);
                i.putExtra("ip",getIntent().getStringExtra("ip"));
                startActivityForResult(i, 0);
            }
        });
        agregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), agregarUsuario.class);
                i.putExtra("ip",getIntent().getStringExtra("ip"));
                startActivityForResult(i, 0);
            }
        });
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Modifica.class);
                i.putExtra("ip",getIntent().getStringExtra("ip"));
                startActivityForResult(i, 0);
            }
        });
        elim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Eliminar.class);
                i.putExtra("ip",getIntent().getStringExtra("ip"));
                startActivityForResult(i, 0);
            }
        });
    }
}
