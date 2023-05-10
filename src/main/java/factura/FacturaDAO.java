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
import java.util.List;

/**
 *
 * @author eli
 */
// El patrón Data Access Object (DAO) pretende principalmente independizar la aplicación de la forma de acceder a la base de datos, 
// o cualquier otro tipo de repositorio de datos.
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
                // Recogemos los datos de la factura, guardamos en un objeto

                f.setCodigoUnico(res.getInt("codigoUnico"));
                f.setDescripcion(res.getString("descripcion"));
                res.getDate("fechaEmision");
                f.setFechaEmision(res.getDate("fechaEmision").toLocalDate());
                f.setTotalImporte(res.getDouble("totalImporte"));
//                // Añadimos el objeto a la lista
                listaFacturas.add(f);
            }
        }
        return listaFacturas;
    }

    @Override
    // este método lo usamos en la mayoria de los otros métodos para comprobar si existe o no una factura
    public FacturaVO findByPk(int codigoUnico) {
        String sql = "select * from factura where codigoUnico=?";
        FacturaVO factura = new FacturaVO();
        ResultSet res = null;
        try ( PreparedStatement prest = con.prepareStatement(sql)) {
            prest.setInt(1, codigoUnico);
            res = prest.executeQuery();
            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.next()) {
                // Recogemos los datos de la factura, guardamos en un objeto
                factura.setCodigoUnico(res.getInt("codigoUnico"));
                factura.setDescripcion(res.getString("descripcion"));
                factura.setFechaEmision(res.getDate("fechaEmision").toLocalDate());
                factura.setTotalImporte(res.getDouble("totalImporte"));
                return factura;
            }
        } catch (Exception e) {
            System.out.println(e + " Error al buscar");
            return null;
        }
        return null;
    }

    @Override
    public FacturaVO insertFactura(FacturaVO factura) {
        int numFilas = 0;
        if (findByPk(factura.getCodigoUnico()) != null) {
            System.out.println("No se ha podido hacer la inserción porque la factura: %s ya existe".formatted(factura.getCodigoUnico()));
            return null;
        } else {
            try {

                String sql = "insert into factura (codigoUnico, descripcion, fechaEmision, totalImporte) values (?,?,?,?)";

                // Instanciamos el objeto PreparedStatement para inserción
                // de datos. Sentencia parametrizada
                try ( PreparedStatement prest = con.prepareStatement(sql)) {

                    // Establecemos los parámetros de la sentencia
                    prest.setInt(1, factura.getCodigoUnico());
                    prest.setString(2, factura.getDescripcion());
                    prest.setDate(3, Date.valueOf(factura.getFechaEmision()));
                    prest.setDouble(4, (double) Math.round(factura.getTotalImporte() * 100d) / 100d);
                    numFilas = prest.executeUpdate();

                    return findByPk(factura.getCodigoUnico());
                }
            } catch (Exception e) {
                System.out.println("Error al introcudir la factura" + e);
            }
        }

        return null;
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
    public int deleteFactura(int codigoUnico) throws SQLException {
        int numFilas = 0;

        String sql = "delete from factura where codigoUnico=?";
        if (findByPk(codigoUnico) != null) {
            // Sentencia parametrizada
            try ( PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, codigoUnico);
                // Ejecutamos la sentencia
                numFilas = prest.executeUpdate();
            }
        } else {
            System.out.println("No se puede borrar porque la factura no existe");
        }
        return numFilas;
    }

    @Override
    public int deleteFactura() throws SQLException {
        String sql = "delete from factura";

        int nfilas = 0;

        // Preparamos el borrado de datos  mediante un Statement
        // No hay parámetros en la sentencia SQL
        try ( Statement st = con.createStatement()) {
            // Ejecución de la sentencia
            nfilas = st.executeUpdate(sql);
        }

        // El borrado se realizó con éxito, devolvemos filas afectadas
        return nfilas;
    }

    @Override
    public FacturaVO updateFactura(int pk, FacturaVO nuevosDatos) {
        if (findByPk(pk) != null) {
            String sql = " update factura set fechaEmision=?, descripcion=?, totalImporte=? where codigoUnico=?";
            try ( PreparedStatement prest = con.prepareStatement(sql)) {
                prest.setDate(1, Date.valueOf(nuevosDatos.getFechaEmision()));
                prest.setString(2, nuevosDatos.getDescripcion());
                prest.setDouble(3, nuevosDatos.getTotalImporte());
                prest.setInt(4, pk);
                prest.executeUpdate();
                return findByPk(pk);
            } catch (Exception e) {
                System.out.println("Fallo al actualizar la factura");
            }

        } else {
            System.out.println("No se puede actualizar porque la factura no existe");
        }
        return null;
    }

}
