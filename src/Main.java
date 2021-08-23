import gui.desing.test.MainAppLauncher;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MainAppLauncher();
                frame.setSize(1060, 560);
                frame.setVisible(true);
            }
        });
    }
}
