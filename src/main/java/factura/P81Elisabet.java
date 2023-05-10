/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package factura;

import BBDD.Conexion;
import BBDD.CreaBBDD;
import ficheros.serviciosFicheros;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eli
 */
public class P81Elisabet {

    public static void main(String[] args) {
        // la siguiente linea comentada es por si no tenemos creada la bbdd 
        CreaBBDD nuevaBBDD = new CreaBBDD();
        // leemos el archivo y hacemos un List<String> con el contenido
        List<String> listaFacturasStrings = serviciosFicheros.obtenerLista("facturas.csv");
        // Obtenemos una List<FacturaVo> con el contenido listaFacturasStrings
        List<FacturaVO> listaFacturas=serviciosFicheros.obtenerLista(listaFacturasStrings);
           
        // creamos facturas, para poder ejecutar las operaciones DAO de las facturas
        FacturaDAO facturas = new FacturaDAO();
        try {
            // creamos dos facturas para futuras pruebas
            FacturaVO nueva= new FacturaVO();
            nueva.setCodigoUnico(12345);
            nueva.setDescripcion("abdc");
            nueva.setFechaEmision(LocalDate.now());
            nueva.setTotalImporte(1.0);
            FacturaVO nuevaDos= new FacturaVO();
            nuevaDos.setCodigoUnico(123456);
            nuevaDos.setDescripcion("abdcd");
            nuevaDos.setFechaEmision(LocalDate.now());
            nuevaDos.setTotalImporte(1.0);
            FacturaVO tmp = new FacturaVO ();
            tmp.setDescripcion("abcd");
            tmp.setFechaEmision(LocalDate.now());
            tmp.setTotalImporte(1.2);
            
            // insertamos una factura
            System.out.println("Insertamos dos factura");
            System.out.println(facturas.insertFactura(nueva));
            System.out.println(facturas.insertFactura(nuevaDos));
            // probamos a volver a insertar la factura para comprobar el error
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Volvemos a insertar la misma factura para que de error");
            System.out.println(facturas.insertFactura(nuevaDos));
            // insertamos una lista de facturas con una fcatura que ya se encuentra en el sistema
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Insertamos una lista de facturas con 2 facturas que ya se encuentra en el sistema");
            listaFacturas.add(nueva);
            listaFacturas.add(nuevaDos);
            facturas.insertFactura(listaFacturas);
            // mostramos todas las facturas
            facturas.getAll().forEach(System.out::println);
            // buscamos la factura con codigoUnico 3
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Buscamos la factura con codigoUnico 3");
            System.out.println(facturas.findByPk(3));
            // buscamos una fcatura que no existe
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Buscamos la factura con codigoUnico 125693 que no existe");
            System.out.println(facturas.findByPk(125693));
            // hacemos el update en la factura 4 con nuevos datos
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Cambiamos la factura 4 con nuevos datos");
            System.out.println(facturas.updateFactura(4, tmp));
            // borramos la factura 5
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Borramos la factura 5. Número de lineas afectadas");
            System.out.println(facturas.deleteFactura(5));
            // borramos todas las facturas
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Borramos todas las facturas. Número de lineas borradas");
            System.out.println(facturas.deleteFactura());
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
