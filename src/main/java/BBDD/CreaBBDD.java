/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author eli
 */
public class CreaBBDD {

    private Connection con = null;

    public CreaBBDD() {
        con = Conexion.getInstance();
        crearBBDD();
        Conexion.setNOMBRE_BASE_DATOS("p81Elisabet?zeroDateTimeBehavior=CONVERT_TO_NULL");
        con = Conexion.getInstance();
        crearTabla();
    }

    private void crearBBDD() {
        String sql = "CREATE DATABASE  IF NOT EXISTS `p81Elisabet`";

        try {
            System.out.println("BBDD creada con exito");
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            con.prepareCall(sql);
        } catch (Exception e) {
            System.out.println(e);
            
        }
    }

    private void crearTabla() {
        try {
            PreparedStatement use = con.prepareStatement("USE `p81Elisabet`; ");
            use.execute();
            PreparedStatement drop = con.prepareStatement("DROP TABLE IF EXISTS `factura`;");
            drop.execute();
            PreparedStatement  stmt = con.prepareStatement("""
                                        CREATE TABLE `factura` (
                                          `codigoUnico` int4(5) Not  NULL,
                                          `fechaEmision` datetime  DEFAULT NULL,
                                          `descripcion`  varchar(45) DEFAULT NULL,
                                          `totalImporte` decimal(12,2) default 0, 
                                          PRIMARY KEY (`codigoUnico`)
                                        ) ;
                                        """);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("no se ha creado la tabla factura" + e);
        }

    }

}
