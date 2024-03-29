package gui.custom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ExUtilities {
        
    protected static void addFocusOnSelectedComponent(JComponent componente, Color newColor) {
        if (componente instanceof JTextField) {
            componente.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, newColor));
        } else if (componente instanceof JPasswordField) {
            componente.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, newColor));
        } else if (componente instanceof JComboBox) {
            componente.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, newColor));
        }
    }
    
    public static void preparePlaceHolder(JComponent componente, String textoActual, String nuevoTexto) {
        if (componente instanceof JTextField) {
            if (((JTextField) componente).getText().equals(textoActual)) {
                ((JTextField) componente).setText(nuevoTexto);
            }
        } else if (componente instanceof JTextPane) {
            if (((JTextPane) componente).getText().equals(textoActual)) {
                ((JTextPane) componente).setText(nuevoTexto);
            }
        } else if (componente instanceof JPasswordField) {
            String cad = new String(((JPasswordField) componente).getPassword());
            if (cad.equals(textoActual)) {
                ((JPasswordField) componente).setText(nuevoTexto);
            }
        }
    }
    
    public static void addPlaceHolder(final JComponent componente, final String textoAct, final String textoNuevo){ /*final*/
        
        componente.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                preparePlaceHolder(componente, textoAct, textoNuevo);
            }

            @Override
            public void focusLost(FocusEvent e) {
                preparePlaceHolder(componente, textoNuevo, textoAct);
            }
        });
    }

    public static void addFocusBorder(final ColorUI colorUI, JPanel contenedor) { /*final*/
        for (int i = 0; i < contenedor.getComponentCount(); i++) {
            if (contenedor.getComponent(i) instanceof JTextField) {
                final JTextField texto = ((JTextField) contenedor.getComponent(i));
                texto.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        ExUtilities.addFocusOnSelectedComponent(texto, colorUI.getColorPrincipal());
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        ExUtilities.addFocusOnSelectedComponent(texto, colorUI.getColorBorde());
                    }
                });
            } else if (contenedor.getComponent(i) instanceof JComboBox) {
                final JComboBox comp = ((JComboBox) contenedor.getComponent(i));
                comp.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        ExUtilities.addFocusOnSelectedComponent(comp, colorUI.getColorPrincipal());
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        ExUtilities.addFocusOnSelectedComponent(comp, colorUI.getColorBorde());
                    }
                });

            }
        }
    }
    
    public static void agregarFocusBorder(ColorUI colorUI, JPanel... contenedor) throws IllegalArgumentException{
        if(contenedor.length <= 0){
            throw new IllegalArgumentException("La funci�n debe tener uno o m�s par�metros de entrada");
        }
        
        for(int i = 0 ; i < contenedor.length; i++){
            addFocusBorder(colorUI, contenedor[i]);
        }
        
    }
    
    public static void modificarUIComboBox(ColorUI colorUI, JPanel... panel) throws IllegalArgumentException{
        if (panel.length <= 0) {
            throw new IllegalArgumentException("El parametro de entrada debe de tener por lo menos uno o m�s elementos");
        }

        for (int k = 0; k < panel.length; k++) {
            for (int i = 0; i < panel[k].getComponentCount(); i++) {
                if (panel[k].getComponent(i) instanceof JComboBox) {
                    JComboBox cb = (JComboBox) panel[k].getComponent(i);
                    cb.setUI(new ExComboBox(colorUI));
                }

            }
        }
    }
    
    public static void modificarTemaComboBox(ExComboBox exComboBox, JPanel... panel) throws IllegalArgumentException{
        if (panel.length <= 0) {
            throw new IllegalArgumentException("El parametro de entrada debe de tener por lo menos uno o m�s elementos");
        }

        for (int k = 0; k < panel.length; k++) {
            for (int i = 0; i < panel[k].getComponentCount(); i++) {
                if (panel[k].getComponent(i) instanceof JComboBox) {
                    JComboBox cb = (JComboBox) panel[k].getComponent(i);
                    cb.setUI(exComboBox);
                }

            }
        }
    }
    
    public void ingresarPosicionComboBox(int index, JPanel... panel) throws IllegalArgumentException{
        if(panel.length == 0){
            throw new IllegalArgumentException("La funci�n debe de tener uno o m�s par�metros de entrada que coincidan con la clase o clases padres de esta");
        }
        
        for(int i = 0; i < panel.length; i++){
            for(int componente = 0; componente < panel[i].getComponentCount(); componente++){
                if(panel[i].getComponent(componente) instanceof JComboBox){
                    JComboBox cb = (JComboBox)panel[i].getComponent(componente);
                    cb.setSelectedIndex(index);
                }
            }
        }
    }
    
    public Object obtenerInformacionCelda(JTable tabla, int celda){
        DefaultTableModel tm = (DefaultTableModel) tabla.getModel();
        //aca capturo el primer dato de la celda seleccionada 
        return tm.getValueAt(tabla.getSelectedRow(), celda);
    }
    
    public static void modificarElementos(ColorUI colorUI, ExComboBox exComboBox, JPanel panel){
        agregarFocusBorder(colorUI, panel);
        modificarTemaComboBox(exComboBox, panel);
    }
    
    public static void modificarBotones(ExButton exButton, JPanel... pn) throws IllegalArgumentException{
        if(pn.length == 0){
            throw new IllegalArgumentException("La funcion debe de tener uno o m�s par�metros de entrada que coincidan con la clase o clases padres de esta");
        }
        
        for(int i = 0; i < pn.length; i++){
            for(int componente = 0; componente < pn[i].getComponentCount(); componente++){
                if(pn[i].getComponent(componente) instanceof JButton){
                    JButton bt = (JButton)pn[i].getComponent(componente);
                    exButton.modificarUI(bt);
                }
            }
        }
    }
    
    public static void eliminarBordesScrollPane(JPanel... paneles) throws IllegalArgumentException{
        if(paneles.length == 0){
            throw new IllegalArgumentException("La funci�n debe de tener uno o m�s par�metros de entrada que coincidan con la clase o clases padres de esta");
        }
        
        for(int i = 0; i < paneles.length; i++){
            for(int componentes = 0; componentes < paneles[i].getComponentCount(); componentes++){
                if(paneles[i].getComponent(componentes) instanceof JScrollPane){
                    JScrollPane js = (JScrollPane)paneles[i].getComponent(componentes);
                    js.getViewport().setBorder(null);
                    js.setViewportBorder(null);
                    js.setBorder(null);
                }
            }
        }
    }
    
}