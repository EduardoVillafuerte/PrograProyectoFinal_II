/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capainterfaz;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author zapat
 */
class BotonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton boton;
    private String texto;
    private JTable tabla;
    private int fila;

    public BotonEditor(JTable tabla, String texto) {
        this.tabla = tabla;
        this.texto = texto;
        boton = new JButton(texto);
        boton.addActionListener(this);
    }

    @Override
    public Object getCellEditorValue() {
        return texto;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        fila = row; // Guardar la fila para usar en la acción
        return boton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (texto.equals("Eliminar")) {
            // Acción de eliminar
            ((DefaultTableModel) tabla.getModel()).removeRow(fila);
            JOptionPane.showMessageDialog(tabla, "Fila eliminada con éxito.");
        } else if (texto.equals("Modificar")) {
            // Acción de modificar
            String nuevoValor = JOptionPane.showInputDialog(tabla, "Ingrese un nuevo valor para la Total:");
            if (nuevoValor != null && !nuevoValor.isEmpty()) {
                tabla.setValueAt(nuevoValor, fila, 2); // Cambia el valor en la columna de Total
            }
        }
        fireEditingStopped(); // Detener la edición de la celda
    }
}
