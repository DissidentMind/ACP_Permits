package gui.custom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ExTextField extends TextUtilities implements IComponents {
    private Font fuente;
    private Border border;

    public ExTextField(Font fuente, Border border, ColorUI colorUI) {
        super(colorUI);
        this.fuente = fuente;
        this.border = border;
    }

    public ExTextField(ColorUI colorUI) {
        super(colorUI);
        //this.fuente = new Font("Segoe UI", Font.BOLD, 14);
        this.fuente = new Font("SansSerif", Font.BOLD, 13);
        this.border = BorderFactory.createMatteBorder(0, 0, 1, 0, colorUI.getColorBorde());
    }

    public ExTextField(Font fuente, ColorUI colorUI) {
        super(colorUI);
        this.fuente = fuente;
        this.border = BorderFactory.createMatteBorder(0, 0, 1, 0, colorUI.getColorBorde());
    }

    public ExTextField(Border border, ColorUI colorUI) {
        super(colorUI);
        this.border = border;
        this.fuente = new Font("Segoe UI", 14, Font.BOLD);
    }

    public static TextUtilities createUI(JComponent c) {
        return new TextUtilities(colorUI);
    }
    
    @Override
    public void crearDisenio(){
        UIManager.put("TextField.border", this.border);
        UIManager.put("TextField.background", colorUI.getColorFondo());
        UIManager.put("TextField.font", this.fuente);
        UIManager.put("TextField.foreground", colorUI.getColorForeground());
    }
    
    @Override
    public void modificarUI(JComponent c){
        if(c instanceof JTextField){
            JTextField tf = (JTextField)c;
            tf.setUI(this);
        }
    }

    public Font getFuente() {
        return fuente;
    }

    public void setFuente(Font fuente) {
        this.fuente = fuente;
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }
    
    public static void setBackground(JTextField componente) {
    	componente.setBackground(new Color(239,243,250));
    	componente.setForeground(new Color(0,0,113));
    	componente.setFont(new Font("SansSerif", Font.BOLD, 12));
   }
    
    
}