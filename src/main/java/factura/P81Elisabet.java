/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package factura;

import BBDD.Conexion;
import BBDD.CreaBBDD;
import java.sql.Connection;
import java.time.LocalDate;

/**
 *
 * @author eli
 */
public class P81Elisabet {

    public static void main(String[] args) {
        CreaBBDD nuevaBBDD = new CreaBBDD();
        FacturaDAO facturas = new FacturaDAO();
        FacturaVO factura = new FacturaVO();
        factura.setCodigoUnico(12343);
        factura.setDescripcion("kjhklhklhlk");
        factura.setFechaEmision(LocalDate.now());
        factura.setTotalImporte(12.34);
        facturas.insertFactura(factura);
        try {
            facturas.getAll();
        } catch (Exception e) {
        }
        
    }
}
