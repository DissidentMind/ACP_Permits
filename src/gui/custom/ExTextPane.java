package gui.custom;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextPaneUI;
import java.awt.*;

public class ExTextPane extends BasicTextPaneUI implements IComponents {
    private static ColorUI colorUI;
    private Border border;
    private Font fuente;
    
    public ExTextPane(ColorUI colorUI) {
        ExTextPane.colorUI = colorUI;
        border = BorderFactory.createLineBorder(ExTextPane.colorUI.getColorBorde());
        fuente = new Font("Segoe UI", Font.BOLD,14);
    }

    public ExTextPane(ColorUI colorUI, Border border, Font fuente) {
        ExTextPane.colorUI = colorUI;
        this.border = border;
        this.fuente = fuente;
    }

    public static ComponentUI createUI(JComponent c){
        return new ExTextPane(colorUI);
    }
    
    @Override
    public void crearDisenio(){
        UIManager.put("TextPane.border", border);
        UIManager.put("TextPane.background", colorUI.getColorFondo());
        UIManager.put("TextPane.foreground", colorUI.getColorForeground());
        UIManager.put("TextPane.font", fuente);
    }
    
    public static ColorUI getColorUI() {
        return colorUI;
    }

    public static void setColorUI(ColorUI aColorUI) {
        colorUI = aColorUI;
    }
    
    @Override
    public void modificarUI(JComponent c){
        if (c instanceof JTextPane){
            JTextPane tp = (JTextPane)c;
            tp.setUI(this);
        }
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public Font getFuente() {
        return fuente;
    }

    public void setFuente(Font fuente) {
        this.fuente = fuente;
    }
    
    
}