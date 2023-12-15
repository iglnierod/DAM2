/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import view.MainJFrame;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.UnitValue;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author dides
 */
public class FrontControllerJFrame {

    private MainJFrame view;

    public FrontControllerJFrame(MainJFrame view) {
        this.view = view;
        this.view.setQuitMenuItemListener(this.setQuitMenuItemActionListener());
        this.view.setQueryButtonListener(setQueryActionListener());
        this.view.setExportToPDFButtonListener(this.setExportToPDFButtonListener());
    }

    private ActionListener setQuitMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                System.exit(0);
            }
        };
        return al;
    }

    private ActionListener setQueryActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setResultsTableModel(setResultsTableModel());
            }
        };
        return al;
    }

    private TableModel setResultsTableModel() {
        DefaultTableModel tableModel = (DefaultTableModel) this.view.getResultsTableModel();
        tableModel.setRowCount(0);// Borrar todas las filas de la tabla que ya había antes
        try {
            final String url = "http://192.168.250.2:8069",
                    db = "odoo",
                    username = "iglesias.nieto.rodrigo.ald@gmail.com",
                    password = "f4e6b1ed784ec7a2f400975d64a56dc2082a5c3a";

            final XmlRpcClient client = new XmlRpcClient();

            final XmlRpcClientConfigImpl common_config = new XmlRpcClientConfigImpl();
            common_config.setServerURL(new URL(String.format("%s/xmlrpc/2/common", url)));
            client.execute(common_config, "version", emptyList());
            int uid = (int) client.execute(common_config, "authenticate", asList(db, username, password, emptyMap()));

            final XmlRpcClient models = new XmlRpcClient() {
                {
                    setConfig(new XmlRpcClientConfigImpl() {
                        {
                            setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
                        }
                    });
                }
            };
            List ids = asList((Object[]) models.execute("execute_kw", asList(
                    db, uid, password,
                    "res.partner", "search",
                    asList(asList(
                            asList("is_company", "=", true)))
            )));

            List partnerResults = asList((Object[]) models.execute("execute_kw", asList(
                    db, uid, password,
                    "res.partner", "read",
                    asList(ids),
                    new HashMap() {
                {
                    put("fields", asList("name", "street", "city", "phone", "mobile"));
                }
            }
            )));
            // APARTADO 2.3
            // nome, rúa, cidade, teléfono e móbil
            System.out.printf("%-20s%-25s%-30s%-20s%-20s\n", "NOME", "RUA", "CIDADE", "TELEFONO", "MÓBIL");

            for (int i = 0; i < 120; i++) {
                System.out.print("-");
            }
            System.out.println();

            for (int i = 0; i < partnerResults.size(); i++) {
                String register = partnerResults.get(i).toString();
                String name = getField(", name=", ", mobile=", register);
                String street = getField(", street=", ", name=", register);
                String city = getField("{city=", ", phone=", register);
                String phone = getField(", phone=", ", street=", register);
                String mobile = getField(", mobile=", ", id=", register);

                System.out.printf("%-20s%-25s%-30s%-20s%-20s\n", name, street, city, phone, mobile);
                tableModel.addRow(new Object[]{name, street, city, phone, mobile});
            }
        } catch (XmlRpcException ex) {
            JOptionPane.showMessageDialog(view, "No se ha podido conectar al servidor", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(view, "No se ha podido conectar al servidor", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "No se ha podido hacer la consulta", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return tableModel;
    }

    private static String getField(String left, String right, String register) {
        int begin = register.lastIndexOf(left) + left.length();
        int end = register.lastIndexOf(right);
        return register.substring(begin, end);
    }

    private ActionListener setExportToPDFButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath;
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("odoo-enterprises.pdf"));
                int returnValue = fileChooser.showDialog(view, "Save");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println("Selected file: " + filePath);
                    tableTestPdf(filePath);
                }
            }
        };
        return al;
    }

    private void simplePdf(String filePath) {
        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(filePath));
            Document document = new Document(pdf);
            String line = "Hello! Welcome to iTextPdf";
            document.add(new Paragraph(line));
            document.close();

            System.out.println("Awesome PDF just got created.");

            System.out.println("PDF exportado correctamente a: " + filePath);
        } catch (FileNotFoundException ex) {
            System.err.println("Error al exportar a PDF: " + ex.getMessage());
        }
    }

    private void tableTestPdf(String filePath) {
        try {
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(filePath));
            Document doc = new Document(pdfDoc);

            Table table = new Table(UnitValue.createPercentArray(view.getResultsTableColumnCount())).useAllAvailableWidth();

            // Add table headers
            for (int col = 0; col < view.getResultsTableColumnCount(); col++) {
                table.addHeaderCell(view.getResultsTableColumnName(col));
            }

            // Add table data
            for (int row = 0; row < view.getResultsTableRowCount(); row++) {
                for (int col = 0; col < view.getResultsTableColumnCount(); col++) {
                    String cellContent = view.getResultsTableValueAt(col, row);
                    if (cellContent.equals("false")) {
                        table.addCell("n/a");
                    } else {
                        table.addCell(cellContent);
                    }
                }
            }
            Text title = new Text("Odoo enterprises");
            title.setFontSize(17.5f);

            Style titleStyle = new Style();
            titleStyle.setBold();
            title.addStyle(titleStyle);

            doc.add(new Paragraph(title));
            doc.add(table);

            doc.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
