/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factura;

import java.time.LocalDate;

/**
 *
 * @author eli
 */
// clase POJO
public class FacturaVO {
    private int codigoUnico;
    private LocalDate fechaEmision;
    private String descripcion;
    private double totalImporte;

    public FacturaVO() {
    }

    public int getCodigoUnico() {
        return codigoUnico;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTotalImporte() {
        return totalImporte;
    }

    public void setCodigoUnico(int codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTotalImporte(double totalImporte) {
        this.totalImporte = totalImporte;
    }

    @Override
    public String toString() {
        return "FacturaVO{" + "codigoUnico=" + codigoUnico + ", fechaEmision=" + fechaEmision + ", descripcion=" + descripcion + ", totalImporte=" + totalImporte + '}';
    }

    
    
    
}
