package utils.choosers;

import javax.swing.*;

public class RunFileChooser extends JFrame {
    public static String createAndShowGUI(int OptionChooser) {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return FileChooser_Utility.FileChooserGetPath(OptionChooser);
    }
}
