/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factura;

import BBDD.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author eli
 */
public class FacturaDAO implements IFactura {

    private Connection con = null;

    public FacturaDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<FacturaVO> getAll() throws SQLException {
        List<FacturaVO> listaFacturas = new ArrayList<>();
        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try ( Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from factura");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos

            while (res.next()) {
                FacturaVO f = new FacturaVO();
                // Recogemos los datos de la persona, guardamos en un objeto

                f.setCodigoUnico(res.getInt("codigoUnico"));
                f.setDescripcion(res.getString("descripcion"));
                res.getDate("fechaEmision");
                f.setFechaEmision(res.getDate("fechaEmision").toLocalDate());
                f.setTotalImporte(res.getDouble("totalImporte"));
//                // Añadimos el objeto a la lista
                listaFacturas.add(f);
            }
        }
        listaFacturas.forEach(System.out::println);
        return listaFacturas;
    }

    @Override
    public FacturaVO findByPk(int pk) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insertFactura(FacturaVO factura) {
        int numFilas = 0;
        try {

            String sql = "insert into factura (codigoUnico, descripcion, fechaEmision, totalImporte) values (?,?,?,?)";

            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try ( PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, factura.getCodigoUnico());
                prest.setString(2, factura.getDescripcion());
                prest.setDate(3, Date.valueOf(factura.getFechaEmision()));
                prest.setDouble(4, (double)Math.round(factura.getTotalImporte() * 100d) / 100d);
                numFilas = prest.executeUpdate();

                return numFilas;
            }
        } catch (Exception e) {
            System.out.println("Error al introcudir la factura" + e);
        }
        return numFilas;
    }

    @Override
    public int insertFactura(List<FacturaVO> lista) throws SQLException {
        for (int i = 0; i < lista.size(); i++) {
            FacturaVO get = lista.get(i);
            insertFactura(get);
        }
        return lista.size();
    }

    @Override
    public int deleteFactura(FacturaVO p) throws SQLException {
        try {
            String sql ="select * from factura where codigoUnico=?";
             try ( PreparedStatement prest = con.prepareStatement(sql)) {
                prest.setInt(1, p.getCodigoUnico());
                return p.getCodigoUnico();
            }
        } catch (Exception e) {
        }
        return 1;
    }

    @Override
    public int deleteFactura() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int updateFactura(int pk, FacturaVO nuevosDatos) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
