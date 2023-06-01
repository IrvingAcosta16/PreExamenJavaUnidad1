package com.example.preexamenjavaprgmovil;
import java.util.function.Function;



public class ReciboNomina {
    private int noNomina;
    private String nombreNomina;
    private String puesto;
    private double salario;
    private int horasTrab;
    private int horasExtraTrab;

    private double subtotal;

    ReciboNomina(){
        this.noNomina = 0;
        this.nombreNomina = "";
        this.puesto = "";
        this.salario = 0;
        this.horasTrab = 0;
        this.horasExtraTrab = 0;
    }

    ReciboNomina(int noNomina, String nombreNomina, String puesto, double salario, int horasTrab, int horasExtraTrab){
        this.noNomina = noNomina;
        this.nombreNomina = nombreNomina;
        this.puesto = puesto;
        this.salario = salario;
        this.horasTrab = horasTrab;
        this.horasExtraTrab = horasExtraTrab;
    }

    public double GenerarSubtotal(){
        subtotal = (salario * this.horasTrab) + ((salario * horasExtraTrab) * 2);
        return subtotal;
    }

    public double GenerarImpuestos(double salario){
        return salario * 0.16;
    }
}
