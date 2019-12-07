package com.example.banbajio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Search extends AppCompatActivity {


    EditText edSearch;
    Button btnBuscar;
    TextView idPaquete,idDeptoEnvia,ciudadDestino,ciudadProcedencia,idDeptoRecibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edSearch = findViewById(R.id.edSearch);
        btnBuscar = findViewById(R.id.btnBuscar);
        idPaquete = findViewById(R.id.idPaquete);
        idDeptoEnvia = findViewById(R.id.idDeptoEnvia);
        ciudadDestino = findViewById(R.id.ciudadDestino);
        ciudadProcedencia = findViewById(R.id.ciudadProcedencia);
        idDeptoRecibe = findViewById(R.id.idDeptoRecibe);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
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
    public void consultarEnvio(){
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("Select * From Envios where idEnvio ='" + edSearch.getText().toString() + "'");

            if(rs.next()){
                idPaquete.setText(rs.getString(2));
                idDeptoEnvia.setText(rs.getString(3));
                ciudadDestino.setText(rs.getString(4));
                ciudadProcedencia.setText(rs.getString(5));
                idDeptoRecibe.setText(rs.getString(6));
            }else{
                idPaquete.setText("");
            }
        }catch(SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
