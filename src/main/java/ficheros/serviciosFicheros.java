/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficheros;

import factura.FacturaVO;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eli
 */
public class serviciosFicheros {
    // método que crea un List<String> con las lineas del archivo
    public static List<String> obtenerLista(String archivo) {
        List<String> lineas = new ArrayList<>();
        try {
            lineas = Files.readAllLines(Paths.get(archivo),
                    StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Error leyendo el fichero");
        }
        return lineas;
    }
    
    public static List<FacturaVO> obtenerLista(List<String> listaFacturasStrings){
    List<FacturaVO> listaFacturas = new ArrayList<>();
        for (int i = 0; i < listaFacturasStrings.size(); i++) {
            String get = listaFacturasStrings.get(i);
            String[] tokens = get.split(";");
            FacturaVO tmp = new FacturaVO();
            tmp.setCodigoUnico(Integer.parseInt(tokens[0]));
            tmp.setDescripcion(tokens[1]);
            tmp.setFechaEmision(LocalDate.parse(tokens[2]));
            tmp.setTotalImporte(Double.parseDouble(tokens[3]));
            listaFacturas.add(tmp);
    }
        return listaFacturas;
    }
}


