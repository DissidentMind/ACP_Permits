package gui.custom;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

@SuppressWarnings("serial")
public class ExButtonRenderer extends JButton implements TableCellRenderer {
    private final ColorUI colorUI;
    private final Border borde;
    
    public ExButtonRenderer(ColorUI colorUI) {
        setOpaque(true);
        this.colorUI = colorUI;
        borde = BorderFactory.createMatteBorder(0, 0, 2, 0, this.colorUI.getColorBorde());
    }

    public ExButtonRenderer(ColorUI colorUI, Border borde) {
        this.colorUI = colorUI;
        this.borde = borde;
    }
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            /*setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));*/
            setBackground(this.colorUI.getColorFondo());
            setForeground(this.colorUI.getColorForeground());
            setBorder(borde);
        }
        setText((value == null) ? "" : value.toString());
        setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        return this;
    }
}