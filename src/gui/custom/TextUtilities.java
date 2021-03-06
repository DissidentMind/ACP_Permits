package gui.custom;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextUtilities extends BasicTextFieldUI{
    protected static ColorUI colorUI;

    public TextUtilities() {
    }

    public TextUtilities(ColorUI colorUI) {
        TextUtilities.colorUI = colorUI;
    }
    
    
    public static TextUtilities createUI(JComponent c) {
        return new TextUtilities();
    }
    
    public static void addPlaceHolder(JTextComponent componente, String textoActual, String nuevoTexto) {
        if (componente.getText().equals(textoActual)) {
            componente.setText(nuevoTexto);
        }
    }

    public static void addFocusBorder(final JTextComponent textField) { /*final*/
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ExUtilities.addFocusOnSelectedComponent(textField, ColorUI.colorFocus);
            }

            @Override
            public void focusLost(FocusEvent e) {
                ExUtilities.addFocusOnSelectedComponent(textField, ColorUI.colorNoFocus);
            }
        });
    }
    
    protected static void addFocusOnSelectedComponent(JTextComponent componente, Color newColor) {
        componente.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, newColor));
    }

    public ColorUI getColorUI() {
        return colorUI;
    }

    public void setColorUI(ColorUI colorUI) {
        TextUtilities.colorUI = colorUI;
    }
}
