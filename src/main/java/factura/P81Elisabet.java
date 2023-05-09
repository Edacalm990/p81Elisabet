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
        //CreaBBDD nuevaBBDD = new CreaBBDD();
//        List<String> listaFacturasStrings = serviciosFicheros.obtenerLista("facturas.csv");
//        List<FacturaVO> listaFacturas= new ArrayList<>();
//        for (int i = 0; i < listaFacturasStrings.size(); i++) {
//            String get = listaFacturasStrings.get(i);
//            String[] tokens = get.split(";");
//            FacturaVO tmp = new FacturaVO();
//            tmp.setCodigoUnico(Integer.parseInt(tokens[0]));
//            tmp.setDescripcion(tokens[1]);
//            tmp.setFechaEmision(LocalDate.parse(tokens[2]));
//            tmp.setTotalImporte(Double.parseDouble(tokens[3]));
//            listaFacturas.add(tmp);
//        }
        FacturaDAO facturas = new FacturaDAO();
//        try {
//            facturas.insertFactura(listaFacturas);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        try {
//            facturas.getAll().forEach(System.out::println);
//            System.out.println(facturas.findByPk(520));
            FacturaVO f= new FacturaVO();
            f.setCodigoUnico(3);
            facturas.deleteFactura(f);
            facturas.deleteFactura();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            facturas.getAll().forEach(System.out::println);
        } catch (Exception e) {
        }
        

    }
}
