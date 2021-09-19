package gui.controller.splash;

import gui.desing.test.SplashLoaderLauncher;
import javax.swing.*;

public class GUISplash extends GUIListen{
    SplashLoaderLauncher dialog;

    GUISplash(GUIManager gMan){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              dialog = new SplashLoaderLauncher();
                dialog.setDefaultCloseOperation(dialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
                dialog.setTitle("Loading... ");
                dialog.setSize(560, 280);
                dialog.setLocationRelativeTo(null);
                dialog.updateProgressBar();
            }
        });
    }

    @Override
    public void update() {
        dialog.setInfoSplash_Txt("Cambio Detectado");
    }
}
