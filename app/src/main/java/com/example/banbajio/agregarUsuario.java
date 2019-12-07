package com.example.banbajio;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class agregarUsuario extends AppCompatActivity {

    EditText user1, pass1;
    Button btnLogin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        user1 = findViewById(R.id.user1);
        pass1 = findViewById(R.id.password1);
        btnLogin1 = findViewById(R.id.btnLogin1);

        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(user1.getText().toString().isEmpty())||pass1.getText().toString().isEmpty()) {
                    agregar1Usuario();
                    Intent i = new Intent(v.getContext(), Inicio.class);
                    startActivityForResult(i, 0);
                }else{
                     Toast.makeText(getApplicationContext(),"Correo electronico y contraseña incorrectos valido",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public Connection conexionBD(){
        Connection conexion = null;
        try{
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy( politica );
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://"+getIntent().getStringExtra("ip")+";databaseName=BanBajio;user=sa;password=123;instance=MSSQLSERVER01","sa","123");
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }
    public void agregar1Usuario(){
        try {
            PreparedStatement pst = conexionBD().prepareStatement("insert into Usuarios values(?,?)");
            pst.setString(1, user1.getText().toString());
            pst.setString(2, pass1.getText().toString());
            pst.executeUpdate();
            Toast.makeText(getApplicationContext(),"Usuario Registrado Con Exito",Toast.LENGTH_SHORT).show();
        }catch(SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
