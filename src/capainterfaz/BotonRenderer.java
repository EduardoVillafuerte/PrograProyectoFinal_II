/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capainterfaz;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 *
 * @author zapat
 */
class BotonRenderer extends JButton implements TableCellRenderer {
    public BotonRenderer(String texto){
        setText(texto);
        setOpaque(true);
    }
    
   @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "Botón" : value.toString());

        // Cambiar color del botón si la celda está seleccionada
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(UIManager.getColor("Button.background"));
            setForeground(UIManager.getColor("Button.foreground"));
        }

        return this;
    }
    
}
