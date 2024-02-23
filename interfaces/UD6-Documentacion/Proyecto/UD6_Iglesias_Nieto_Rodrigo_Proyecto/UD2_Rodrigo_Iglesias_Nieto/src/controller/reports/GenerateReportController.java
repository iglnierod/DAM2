/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import view.reports.GenerateReportDialog;
import java.io.InputStream;
import java.sql.*;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class GenerateReportController {

    private final GenerateReportDialog view;

    public GenerateReportController(GenerateReportDialog view) {
        this.view = view;
        this.view.setAutoFillButtonActionListener(setAutoFillButtonListener());
        this.view.setGenerateButtonActionListener(this.setGenerateButtonActionListener());
    }

    private ActionListener setAutoFillButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder URL = new StringBuilder();
                String jdbc = "jdbc:mariadb://";
                String serverHost = "192.168.250.2";
                int port = 3306;
                String database = "aldComputerService";
                String username = "user";
                String password = "abc123.";

                URL.append(jdbc);
                URL.append(serverHost);
                URL.append(":" + port);
                URL.append("/" + database);

                System.out.println("URL: " + URL);

                view.setServerHostTextFieldText(serverHost);
                view.setPortSpinnerValue(port);
                view.setDatabaseTextFieldText(database);
                view.setUsernameTextFieldText(username);
                view.setPasswordPasswordFieldText(password);
            }
        };
        return al;
    }

    private ActionListener setGenerateButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StringBuilder URL = new StringBuilder();
                String jdbc = "jdbc:mariadb://";
                String serverHost = view.getServerHostTextFieldText();
                int port = view.getPortSpinnerValue();
                String database = view.getDatabaseTextFieldText();
                String username = view.getUsernameTextFieldText();
                String password = view.getPasswordPasswordFieldText();

                URL.append(jdbc);
                URL.append(serverHost);
                URL.append(":" + port);
                URL.append("/" + database);

                System.out.println("Generate - URL: " + URL);

                Connection conn = null;

                String currentDirectory = System.getProperty("user.dir");
                File reportsDir = new File(currentDirectory, "Reports");
                if (!reportsDir.exists()) {
                    reportsDir.mkdir();
                }

                File selectedFile = null;
                JFileChooser fileChooser = new JFileChooser(reportsDir);
                fileChooser.setSelectedFile(new File("Report_5_5.pdf"));
                int returnValue = fileChooser.showSaveDialog(view);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                } else {
                    return;
                }

                try {

                    conn = DriverManager.getConnection(URL.toString(), username, password);
                    conn.setAutoCommit(false);

                    InputStream is = this.getClass().getClassLoader().getResourceAsStream("jasperReports/Report_5_4.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(is);
                    JasperPrint print = JasperFillManager.fillReport(report, null, conn);
                    JasperExportManager.exportReportToPdfFile(print, selectedFile.getAbsolutePath());
                    JasperViewer.viewReport(print, false);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error al conectar con la base de datos.\nURL: " + URL, "ERROR", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (JRException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(view, "Error al generar el informe.", "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        };
        return al;
    }
}
