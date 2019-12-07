package com.example.banbajio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    EditText user, pass, ip;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.password);
        ip = findViewById(R.id.ipServer);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(user.getText().toString().equals(""))||pass.getText().toString().equals("")) {
                    iniciarSesion();
                    if(validacion) {
                        Intent i = new Intent(v.getContext(), Inicio.class);
                        i.putExtra("ip",ip.getText().toString());
                        startActivityForResult(i, 0);
                    }
                }else{
                        Toast.makeText(getApplicationContext(),"Correo electronico y contraseña incorrectos valido",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public boolean validacion = false;
    public Connection conexionBD(){
        Connection conexion = null;
        try{
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy( politica );
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip.getText().toString()+";databaseName=BanBajio;user=sa;password=123;instance=MSSQLSERVER01","sa","123");
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }
    public void iniciarSesion(){
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("Select * From Usuarios where usuario = '"+ user.getText().toString() +"'");

            if(rs.next()){
                if(rs.getString(3).equals(pass.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Acceso Permitido",Toast.LENGTH_SHORT).show();
                    validacion = true;
                }else{
                    Toast.makeText(getApplicationContext(),"Nombre de usuario y contraseña incorrectos",Toast.LENGTH_SHORT).show();
                }
            }
        }catch(SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
