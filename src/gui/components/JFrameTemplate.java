package gui.components;

import javax.swing.*;
import java.awt.*;

public class JFrameTemplate extends JFrame {
    JTableTemplate table = new JTableTemplate();
    public JFrameTemplate(){
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        this.setTitle("Custom Objects Java Design");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
        this.setFocusable(true);
    }
}
