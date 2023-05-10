/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package factura;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author eli
 */
// interfaz de factura con todos los métodos necesarios para que la aplicación funcione correctamente
public interface IFactura {
        // Método para obtener todos los registros de la tabla
    List<FacturaVO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    FacturaVO findByPk(int pk) throws SQLException;
    
    // Método para insertar un registro
    FacturaVO insertFactura (FacturaVO factura) throws SQLException;
    
    // Método para insertar varios registros
    int insertFactura (List<FacturaVO> lista) throws SQLException;
    
    // Método para borrar una fcatura
    int deleteFactura (int codigoUnico) throws SQLException;
    
    // Método para borrar toda la tabla
    int deleteFactura() throws SQLException;
    
    // Método para modificar una fcatura. Se modifica la fcatura que tenga esa 'pk'
    // con los nuevos datos que traiga la factura 'nuevosDatos'
    FacturaVO updateFactura (int pk, FacturaVO nuevosDatos) throws SQLException;
}
