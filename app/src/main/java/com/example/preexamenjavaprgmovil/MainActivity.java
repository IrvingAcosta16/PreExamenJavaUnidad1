package com.example.preexamenjavaprgmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener una referencia al EditText
        EditText inputUsuario = findViewById(R.id.inputUsuario);
        //EditText inputPass = findViewById(R.id.inputPass);

        // Obtener una referencia al botón
        Button btnEntrar = findViewById(R.id.btnEntrar);
        Button btnSalir = findViewById(R.id.btnSalir);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreUsuario = inputUsuario.getText().toString().trim(); // Utiliza trim() para eliminar espacios en blanco al inicio y al final del texto

                if (nombreUsuario.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingrese un nombre de usuario", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("nombreUsuario", nombreUsuario);

                    Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, ReciboNominaActivity.class);
                    intent.putExtras(bundle); // Agrega el Bundle al Intent
                    startActivity(intent);
                }



            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro de querer salir?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acciones a realizar si se selecciona "Sí"
                        finishAffinity(); // Cierra todas las actividades y sale de la aplicación
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acciones a realizar si se selecciona "No"
                        dialog.dismiss(); // Cierra el diálogo sin realizar ninguna acción adicional
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}