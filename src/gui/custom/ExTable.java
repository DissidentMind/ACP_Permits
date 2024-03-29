package gui.custom;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ExTable extends BasicTableUI implements IComponents {
    private static ColorUI colorUI;
    private Color gridColor;
    private Border cellBorder;
    private Font fuenteHeader;
    private Border scrollPaneBorder;
    private Border borderCellHightlight;

    public ExTable(ColorUI colorUI) {
        ExTable.colorUI = colorUI;
        gridColor = new Color(226,234,245);
        cellBorder = BorderFactory.createLineBorder(ExTable.colorUI.getColorFondo());
        fuenteHeader = new Font("Segoe UI", Font.BOLD,12);
        scrollPaneBorder = BorderFactory.createLineBorder(ExTable.colorUI.getColorBorde(),1);
        borderCellHightlight = BorderFactory.createEmptyBorder();
    }

    public ExTable(ColorUI colorUI, Color gridColor, Border cellBorder, Font fuenteHeader, Border scrollPaneBorder, Border borderCellHightlight) {
        ExTable.colorUI = colorUI;
        this.gridColor = gridColor;
        this.cellBorder = cellBorder;
        this.fuenteHeader = fuenteHeader;
        this.scrollPaneBorder = scrollPaneBorder;
        this.borderCellHightlight = borderCellHightlight;
    }
    
    @Override
    public void crearDisenio(){
        UIManager.put("Table.background", ExTable.colorUI.getColorFondo());
        UIManager.put("Table.darkShadow", ExTable.colorUI.getColorFondo());
        UIManager.put("Table.shadow", ExTable.colorUI.getColorFondo());
        UIManager.put("Table.grid", gridColor);
        UIManager.put("TableHeader.cellBorder", cellBorder);
        UIManager.put("TableHeader.foreground", ExTable.colorUI.getColorForeground());
        UIManager.put("TableHeader.background", ExTable.colorUI.getColorFondo());
        UIManager.put("TableHeader.font", fuenteHeader);
        UIManager.put("Table.scrollPaneBorder", scrollPaneBorder);
        UIManager.put("Table.focusCellBackground", ExTable.colorUI.getColorTerciario());
        UIManager.put("Table.focusCellHighlightBorder", borderCellHightlight);
        UIManager.put("Table.light", colorUI.getColorFondo());
        UIManager.put("Table.highlight", colorUI.getColorFondo());
        UIManager.put("Table.selectionForeground", colorUI.getColorFondo());
        UIManager.put("Table.selectionBackground", colorUI.getColorTerciario());
    }
    public static ColorUI getColorUI() {
        return colorUI;
    }

    public static void setColorUI(ColorUI aColorUI) {
        colorUI = aColorUI;
    }
    
    public static void modificarUI(JTable tp){
        tp.setUI(new ExTable(colorUI));
    }
    
    public Color getGridColor() {
        return gridColor;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    public Border getCellBorder() {
        return cellBorder;
    }

    public void setCellBorder(Border cellBorder) {
        this.cellBorder = cellBorder;
    }

    public Font getFuenteHeader() {
        return fuenteHeader;
    }

    public void setFuenteHeader(Font fuenteHeader) {
        this.fuenteHeader = fuenteHeader;
    }

    public Border getScrollPaneBorder() {
        return scrollPaneBorder;
    }

    public void setScrollPaneBorder(Border scrollPaneBorder) {
        this.scrollPaneBorder = scrollPaneBorder;
    }

    public Border getBorderCellHightlight() {
        return borderCellHightlight;
    }

    public void setBorderCellHightlight(Border borderCellHightlight) {
        this.borderCellHightlight = borderCellHightlight;
    }
    
    public static void modificarGrid(JTable componente){
        componente.setShowGrid(true);
        componente.setShowHorizontalLines(true);
        componente.setShowVerticalLines(true);
        componente.setAutoCreateRowSorter(true);
        componente.setForeground(new Color(16,43,66));
        
        componente.getSelectionModel().addListSelectionListener(
        		new ListSelectionListener() {
        			public void valueChanged(ListSelectionEvent e) {
        				//System.out.println("Event: "+e);
        				componente.getSelectedRow();
        			}
        });
        
        //componente.setCellSelectionEnabled(true);
        componente.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        //componente.setColumnSelectionAllowed(false);
        //componente.setRowSelectionAllowed(true);
        
        //componente.getAutoResizeMode();
        //componente.setRowSelectionAllowed(true);
        //componente.getCellSelectionEnabled();
        //componente.setAutoscrolls(true);
        //componente.setBackground(new Color(0,62,114));
    }
    
    public static void modificarAlturaFilas(JTable componente) {
        componente.setRowHeight(40);
    }
    
    public static void modifyHeadersTable(JTable componente) {
    	 JTableHeader header = componente.getTableHeader();
         //header.setBackground(new Color(226,234,245));
         header.setBackground(new Color(16,43,66));
         //header.setForeground(new Color(0,62,113));
         header.setForeground(new Color(255,255,255));
         header.setFont(new Font("SansSerif", Font.BOLD, 12));
    }
    
        
    public static void modificarTabla(JTable... tabla) throws IllegalArgumentException{
        if(tabla.length == 0){
            throw new IllegalArgumentException("La funci�n debe de tener uno o m�s par�metros de entrada que coincidan con la clase o clases padres de esta");
        }
        
        for(int i = 0; i < tabla.length; i++){
            modificarGridTabla(tabla[i]);
            modificarAlturaFilas(tabla[i]);
            renderizarTabla(tabla[i]);
            modifyHeadersTable(tabla[i]);
        }
        
    }

    public static void modificarGridTabla(JTable tabla) {
        ExTable.modificarGrid(tabla);
    }

    public static void renderizarTabla(JTable tabla) {
        tabla.setIntercellSpacing(new Dimension(0, 0));
        ExTable.CustomRenderer cr = new ExTable.CustomRenderer(tabla.getDefaultRenderer(Object.class), Color.WHITE, Color.WHITE, new Color(204, 204, 204), Color.WHITE);
        tabla.setDefaultRenderer(Object.class, cr);
    }

    @Override
    public void modificarUI(JComponent c) {
        if(c instanceof JTable){
            JTable tb = (JTable)c;
            tb.setUI(this);
        }
    }
    
    @SuppressWarnings("serial")
    public static class CustomRenderer extends DefaultTableCellRenderer {

        private final DefaultTableCellRenderer render;
        private Border b;

        public CustomRenderer(TableCellRenderer r, Color top, Color left, Color bottom, Color right) {
            this.render = (DefaultTableCellRenderer)r;
            construirBorde(top, left, bottom, right);
            alinearElementos();
            
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            final JComponent result = (JComponent) render.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            result.setBorder(b);
            return result;
        }

        private void alinearElementos() {
            render.setHorizontalAlignment(SwingConstants.CENTER);
        }

        private void construirBorde(Color top, Color left, Color bottom, Color right) {
            b = BorderFactory.createCompoundBorder();
            //b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0, 0, 2, 0, top));
            //b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0, 0, 2, 0, left));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0, 0, 2, 0, bottom));
            //b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0, 0, 2, 0, right));
        }
    }
}