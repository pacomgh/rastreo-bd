package com.example.banbajio;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class registreOk extends AppCompatActivity {


    Button btnImp1;
    TextView idEnvio1,idPaquete1,idDeptoEnvia1,ciudadDestino1,ciudadProcedencia1,idDeptoRecibe1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registre_ok);

        idEnvio1 = findViewById(R.id.idEnvio1);
        idPaquete1 = findViewById(R.id.idPaquete1);
        idDeptoEnvia1 = findViewById(R.id.idDeptoEnvia1);
        ciudadDestino1 = findViewById(R.id.ciudadDestino1);
        ciudadProcedencia1 = findViewById(R.id.ciudadProcedencia1);
        idDeptoRecibe1 = findViewById(R.id.idDeptoRecibe1);
        btnImp1 = findViewById(R.id.btnImp1);

        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("Select * From Envios where idEnvio ='" + getIntent().getStringExtra("idEnvio") + "'");

            if(rs.next()){
                idEnvio1.setText(getIntent().getStringExtra("idEnvio"));
                idPaquete1.setText(rs.getString(2));
                idDeptoEnvia1.setText(rs.getString(3));
                ciudadDestino1.setText(rs.getString(4));
                ciudadProcedencia1.setText(rs.getString(5));
                idDeptoRecibe1.setText(rs.getString(6));
            }else{
                idPaquete1.setText("");
            }
        }catch(SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        btnImp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Imprimiendo",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public Connection conexionBD() {
        Connection conexion = null;
        try {
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + getIntent().getStringExtra("ip") + ";databaseName=BanBajio;user=sa;password=123;instance=MSSQLSERVER01", "sa", "123");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }
}
