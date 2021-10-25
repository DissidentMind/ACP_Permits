package gui.utils;

import javax.swing.*;

public class TestFileChooser extends JFrame {

    public TestFileChooser(){

        //createAndShowGUI(2);
    }

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
        //System.out.println("Path -> "+FileChooser_Utility.FileChooserGetPath(OptionChooser));
        return FileChooser_Utility.FileChooserGetPath(OptionChooser);
    }

}
