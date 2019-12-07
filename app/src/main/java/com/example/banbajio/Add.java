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
import java.sql.SQLException;

public class Add extends AppCompatActivity{

    EditText etRem, etCons, etGuia, etDest, etDate, etCont;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etRem = findViewById(R.id.etRem);
        etCons = findViewById(R.id.etCons);
        etGuia = findViewById(R.id.etGuia);
        etDest = findViewById(R.id.etDest);
        etDate = findViewById(R.id.etDate);
        etCont = findViewById(R.id.etCont);

        btnReg = findViewById(R.id.btnAdd);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                agregarEnvio();
                Intent i = new Intent(v.getContext(), registreOk.class);
                i.putExtra("idEnvio",etCont.getText().toString());
                i.putExtra("ip",getIntent().getStringExtra("ip"));
                startActivityForResult(i, 0);

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
    public void agregarEnvio(){
        try {
            PreparedStatement pst = conexionBD().prepareStatement("insert into Envios values(?,?,?,?,?,?)");
            pst.setString(1, etCont.getText().toString());
            pst.setString(2, etRem.getText().toString());
            pst.setString(3, etCons.getText().toString());
            pst.setString(4, etGuia.getText().toString());
            pst.setString(5, etDest.getText().toString());
            pst.setString(6, etDate.getText().toString());
            pst.executeUpdate();
            Toast.makeText(getApplicationContext(),"Registro Exitoso",Toast.LENGTH_SHORT).show();
        }catch(SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}
