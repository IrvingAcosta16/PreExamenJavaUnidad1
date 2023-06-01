package com.example.preexamenjavaprgmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ReciboNominaActivity extends AppCompatActivity{

    TextView lblSubtotal;
    TextView lblImpuesto;
    TextView lblTotal;


    EditText numeroRecibo;
    EditText nombreRecibo;
    EditText horasTrabajadas;

    EditText horasExtras;

    RadioGroup radioGroup;

    Button btnCalcular;
    Button btnLimpiar;
    Button btnRegresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityrecibo);

        lblSubtotal = findViewById(R.id.lblSubtotal);
        lblImpuesto = findViewById(R.id.lblImpuesto);
        lblTotal = findViewById(R.id.lblTotal);

        numeroRecibo = findViewById(R.id.numeroRecibo);
        nombreRecibo = findViewById(R.id.nombreRecibo);
        horasTrabajadas = findViewById(R.id.horasTrabajadas);
        horasExtras = findViewById(R.id.horasExtras);

        radioGroup = findViewById(R.id.radioGroup);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnRegresar = findViewById(R.id.btnRegresar);




        TextView textViewNombreUsuario = findViewById(R.id.putNombreUsuario);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // Verificar si el Bundle contiene la clave "nombreUsuario"
            if (extras.containsKey("nombreUsuario")) {
                // Obtener el valor de "nombreUsuario" del Bundle
                String nombreUsuario = extras.getString("nombreUsuario");

                textViewNombreUsuario.setText(nombreUsuario);
            }
        }


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (validarInputs()){
                    int noNomina = Integer.parseInt(numeroRecibo.getText().toString().trim());
                    String nombreNomina = nombreRecibo.getText().toString().trim();
                    String puesto = "";
                    //
                    int salarioBase = 200;
                    double salario = 0;

                    int hrsTrabajadas = Integer.parseInt(horasTrabajadas.getText().toString().trim());
                    int  horasExtrasText = Integer.parseInt(horasExtras.getText().toString().trim());

                    int radioButtonId = radioGroup.getCheckedRadioButtonId();

                    if (radioButtonId == R.id.radioBtn1) {
                        // Opcion seleccionada Auxiliar
                        puesto = "auxiliar";
                        salario = salarioBase * 1.20;

                    } else if (radioButtonId == R.id.radioBtn2) {
                        // pcion seleccionada Albañil
                        puesto = "Albañil";
                        salario = salarioBase * 1.50;

                    } else if (radioButtonId == R.id.radioBtn3) {
                        // opcion seleccionada inge
                        puesto = "inge";
                        salario = salarioBase * 2;
                    }

                    ReciboNomina recibo = new ReciboNomina(noNomina, nombreNomina, puesto, salario, hrsTrabajadas, horasExtrasText);

                    double subtotal = recibo.GenerarSubtotal();
                    double impuesto = recibo.GenerarImpuestos(subtotal);
                    double totalPagar = subtotal - impuesto;

                    lblSubtotal.setText("" + subtotal);
                    lblImpuesto.setText("" + impuesto);
                    lblTotal.setText("" + totalPagar);
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroRecibo.setText("");
                nombreRecibo.setText("");
                horasTrabajadas.setText("");
                horasExtras.setText("");
                radioGroup.clearCheck();

                lblSubtotal.setText("");
                lblImpuesto.setText("");
                lblTotal.setText("" );
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReciboNominaActivity.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro de querer regresar?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acciones a realizar si se selecciona "Sí"
                        Intent intent = new Intent(ReciboNominaActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Finaliza la actividad actual (CalculadorActivity)
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

    public boolean validarInputs() {

        String numeroReciboText = numeroRecibo.getText().toString().trim();
        String nombreReciboText = nombreRecibo.getText().toString().trim();
        String horasTrabajadasText = horasTrabajadas.getText().toString().trim();
        String horasExtrasText = horasExtras.getText().toString().trim();

        int radioButtonId = radioGroup.getCheckedRadioButtonId();


        if (!numeroReciboText.isEmpty() && !nombreReciboText.isEmpty() && !horasTrabajadasText.isEmpty() && !horasExtrasText.isEmpty() && radioButtonId != -1) {
            // Todos los campos están llenos y se ha seleccionado un RadioButton
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
            return false;
        }


    }
}
