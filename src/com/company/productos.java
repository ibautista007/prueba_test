/*
 * Created by JFormDesigner on Fri Aug 07 17:08:24 PDT 2020
 */

package com.company;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;

import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Isaias
 */
public class productos extends JFrame {

    //global varibles
    PreparedStatement query;
    Connection con1;
    String indice;

    public productos() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {
        // TODO add your code here
        String name = "";
        int tipo = 0, precio = 0, inventario = 0;

        //obtener valores del texto
        name = textField1.getText();
        tipo = Integer.parseInt(textField2.getText());
        precio = Integer.parseInt(textField3.getText());
        inventario = Integer.parseInt(textField4.getText());


        Class.forName("com.mysql.jdbc.Driver");

        //Prueba local
        con1 = DriverManager.getConnection("jdbc:mysql://localhost:3308/inventario", "root", "");


        //revisar si ya existe el valor
        if(e.getSource()==button1) {
            query = con1.prepareStatement("SELECT * FROM productos where nombre = ? ");
            query.setString(1,name);
            ResultSet rs = query.executeQuery();
            if(rs.isBeforeFirst()){          //res.isBeforeFirst() is true if the cursor

                JOptionPane.showMessageDialog(null,"Este product ya existe ");
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField1.requestFocus();
                return;
            }

            //Query para seleccionar
            query = con1.prepareStatement("INSERT INTO productos (nombre, tipo, precio, inventario) VALUES (?,?,?,?);");

            //ponemos valores a los parametros
            query.setString(1, name);
            query.setInt(2, tipo);
            query.setInt(3, precio);
            query.setInt(4, inventario);

            //Ejecutar query
            query.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record added");
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField1.requestFocus();
            print();
        }


        if(e.getSource()==table1) {

        }

    }

//evento para tomar valores al dar clic sobre un campo
    private void table1MouseClicked(MouseEvent e) {
        // TODO add your code here

        DefaultTableModel df = (DefaultTableModel)table1.getModel();

        int index1 = table1.getSelectedRow();

        textField1.setText(df.getValueAt(index1,0).toString());
        textField2.setText(df.getValueAt(index1,1).toString());
        textField3.setText(df.getValueAt(index1,2).toString());
        textField4.setText(df.getValueAt(index1,3).toString());


        indice=textField1.getText();

    }

    private void button2ActionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {
        // TODO add your code here
        //El boton de actualizar

        String name = "";
        int tipo = 0, precio = 0, inventario = 0;

        //obtener valores del texto
        name = textField1.getText();
        tipo = Integer.parseInt(textField2.getText());
        precio = Integer.parseInt(textField3.getText());
        inventario = Integer.parseInt(textField4.getText());



        con1= DriverManager.getConnection("jdbc:mysql://localhost:3308/inventario","root","");

        //Query para seleccionar
        query = con1.prepareStatement("UPDATE productos set nombre=?, tipo=?, precio=?, inventario=? where nombre = ? ");



        //ponemos valores a los parametros
        query.setString(1,name);
        query.setInt(2,tipo);
        query.setInt(3,precio);
        query.setInt(4,inventario);
        query.setString(5,indice);

        //ejecutar actualizacion
        query.executeUpdate();


        JOptionPane.showMessageDialog(null, "Valores actualizados");

        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField1.requestFocus();

        print();


    }

    private void button3ActionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {
        // TODO add your code here
        // Eliminar campo

        //Agregar conexion de BD e imprimir valores

        Class.forName("com.mysql.jdbc.Driver");

        //Prueba local
        con1= DriverManager.getConnection("jdbc:mysql://localhost:3308/inventario","root","");


        //Query para seleccionar


        //validacion de eliminar si o no
        int result = JOptionPane.showConfirmDialog(null,"Seguro que desea eliminarlo?", "Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){

            query = con1.prepareStatement("DELETE FROM productos where nombre = ?");

            query.setString(1,indice);
            query.execute();

            JOptionPane.showMessageDialog(null, "Producto eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Producto no fue eliminado");
        }


        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField1.requestFocus();

        print();


    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Isaias
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Nombre");
        contentPane.add(label1, "cell 0 0");
        contentPane.add(textField1, "cell 1 0 2 1");

        //---- label2 ----
        label2.setText("Tipo");
        contentPane.add(label2, "cell 0 1");
        contentPane.add(textField2, "cell 1 1 2 1");

        //---- label3 ----
        label3.setText("Precio");
        contentPane.add(label3, "cell 0 2");
        contentPane.add(textField3, "cell 1 2 2 1");

        //---- label4 ----
        label4.setText("Inventario");
        contentPane.add(label4, "cell 0 3");
        contentPane.add(textField4, "cell 1 3 2 1");

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 4 3 1");

        //---- button1 ----
        button1.setText("Agregar");
        button1.addActionListener(e -> {
            try {
                button1ActionPerformed(e);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        contentPane.add(button1, "cell 0 5");

        //---- button2 ----
        button2.setText("Actualizar");
        button2.addActionListener(e -> {
            try {
                button2ActionPerformed(e);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        contentPane.add(button2, "cell 1 5");

        //---- button3 ----
        button3.setText("Eliminar");
        button3.addActionListener(e -> {
            try {
                button3ActionPerformed(e);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        contentPane.add(button3, "cell 2 5");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Isaias
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables



    //funciones adicionales
    //ponerle nombre en la parte de la tabla
    public void sss(){

        String[] cols = {"Category Code", "Category Description","Category Code", "Category Description"};
        String[][] data = {{"d1", "d1.1"},{"d2", "d2.1"},{"d3", "d3.1"},{"d4", "d4.1"}};
        DefaultTableModel model = new DefaultTableModel(data, cols);
        table1.setModel(model);

        //  add(table1);

    }




    //agregar valores a la tabla--- Select * from table
    public void print() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        con1= DriverManager.getConnection("jdbc:mysql://localhost:3308/inventario","root","");

        query = con1.prepareStatement("SELECT * FROM productos");

        ResultSet rs = query.executeQuery();

        int c;
        ResultSetMetaData res = rs.getMetaData();

        c = res.getColumnCount();

        DefaultTableModel df = (DefaultTableModel) table1.getModel();

        df.setRowCount(0);

        while(rs.next()){

            Vector v2 = new Vector();

            for(int a =1;a<=c;a++){
                v2.add(rs.getString("nombre"));
                v2.add(rs.getString("tipo"));
                v2.add(rs.getString("precio"));
                v2.add(rs.getString("inventario"));
            }
            df.addRow(v2);
        }


        int v=1;

    }


}
