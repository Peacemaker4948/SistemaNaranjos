package Reportes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Modelo.Conexion;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class Excel {
   public static void reporte() {
    Workbook book = new XSSFWorkbook();
    Sheet sheet = book.createSheet("Productos");

    try {
        // Establecer la conexión a la base de datos
        Conexion con = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = con.getConnection();

        // Crear fila para el título del reporte
        Row filaTitulo = sheet.createRow(0);
        Cell celdaTitulo = filaTitulo.createCell(0);
        celdaTitulo.setCellValue("Reporte de Productos");

        // Crear estilo para los encabezados
        CellStyle headerStyle = book.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = book.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        // Crear fila para los encabezados
        Row filaEncabezados = sheet.createRow(2);
        Cell celdaCodigo = filaEncabezados.createCell(0);
        celdaCodigo.setCellValue("CÓDIGO");
        celdaCodigo.setCellStyle(headerStyle);

        Cell celdaNombre = filaEncabezados.createCell(1);
        celdaNombre.setCellValue("NOMBRE");
        celdaNombre.setCellStyle(headerStyle);

        Cell celdaPrecio = filaEncabezados.createCell(2);
        celdaPrecio.setCellValue("PRECIO");
        celdaPrecio.setCellStyle(headerStyle);

        Cell celdaExistencia = filaEncabezados.createCell(3);
        celdaExistencia.setCellValue("EXISTENCIA");
        celdaExistencia.setCellStyle(headerStyle);

        // Ejecutar la consulta SQL para obtener los datos de los productos
        ps = conn.prepareStatement("SELECT codigo, nombre, precio, stock FROM productos");
        rs = ps.executeQuery();

        // Obtener el número de fila donde empezar a agregar los datos
        int numFilaDatos = 3;

        // Procesar los resultados de la consulta y agregar los datos al reporte
        while (rs.next()) {
            Row filaDatos = sheet.createRow(numFilaDatos);
            filaDatos.createCell(0).setCellValue(rs.getString("codigo"));
            filaDatos.createCell(1).setCellValue(rs.getString("nombre"));
            filaDatos.createCell(2).setCellValue(rs.getDouble("precio"));
            filaDatos.createCell(3).setCellValue(rs.getInt("stock"));
            numFilaDatos++;
        }

        // Ajustar el ancho de las columnas
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar el archivo y abrirlo
        String fileName = "productos";
        String home = System.getProperty("user.home");
        File file = new File(home + "/Downloads/" + fileName + ".xlsx");
        FileOutputStream fileOut = new FileOutputStream(file);
        book.write(fileOut);
        fileOut.close();
        Desktop.getDesktop().open(file);
        JOptionPane.showMessageDialog(null, "Reporte Generado");

    } catch (FileNotFoundException ex) {
        Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException | SQLException ex) {
        Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
    }
}


}