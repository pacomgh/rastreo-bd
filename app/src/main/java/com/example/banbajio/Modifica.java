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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modifica extends AppCompatActivity{

    EditText etRem, etCons, etGuia, etDest, etDate, etCont;
    Button btnMod, btnBsqm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica);

        etRem = findViewById(R.id.etRemm);
        etCons = findViewById(R.id.etConsm);
        etGuia = findViewById(R.id.etGuiam);
        etDest = findViewById(R.id.etDestm);
        etDate = findViewById(R.id.etDatem);
        etCont = findViewById(R.id.etContm);

        btnMod = findViewById(R.id.btnModm);
        btnBsqm = findViewById(R.id.btnBusquedam);

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            modEnvio();
            Intent i = new Intent(v.getContext(), registreOk.class);
            i.putExtra("idEnvio",etCont.getText().toString());
            i.putExtra("ip",getIntent().getStringExtra("ip"));
            startActivityForResult(i, 0);

            }
        });
        btnBsqm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarEnvio();
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

    public void modEnvio(){
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("update Envios set " +
                    "idPaquete = '"+etRem.getText().toString()+"' , " +
                    "idDeptoEnvia = '"+etCons.getText().toString()+"' ," +
                    "CiudadDestino = '"+etGuia.getText().toString()+"' ,"+
                    "CiudadProcedencia = '"+etDest.getText().toString()+"' ,"+
                    "idDeptoRecibe = '"+etDate.getText().toString()+"' "+
                    "where idEnvio = '"+etCont.getText().toString()+"'");
            Toast.makeText(getApplicationContext(),"Registro Exitoso",Toast.LENGTH_SHORT).show();
        }catch(SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public void consultarEnvio(){
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("Select * From Envios where idEnvio ='" + etCont.getText().toString() + "'");

            if(rs.next()){
                etRem.setText(rs.getString(2));
                etCons.setText(rs.getString(3));
                etGuia.setText(rs.getString(4));
                etDest.setText(rs.getString(5));
                etDate.setText(rs.getString(6));
            }else{
                etCont.setText("");
            }
        }catch(SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}
