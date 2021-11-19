package gui.utils;

import gui.components.JFrameTemplate;

public class JTableDemo {
    public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            JFrameTemplate tbView = new JFrameTemplate();
            tbView.pack();
            //tbView.setLocationRelativeTo(null);
            tbView.setSize(1320, 470);
            tbView.setVisible(true);
        }
    });
    }
}
