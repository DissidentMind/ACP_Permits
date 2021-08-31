package gui.desing.startup;

import gui.desing.imgs.ImgsLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class SplashLoaderLauncher extends JDialog {
    private JPanel contentPane;
    private JProgressBar progressBarSplash;

    public String getInfoSplash_Txt() {
        return infoSplash_Txt.getText();
    }

    public void setInfoSplash_Txt(String infoSplash_Txt) {
        this.infoSplash_Txt.setText(infoSplash_Txt);
    }

    private JLabel infoSplash_Txt;
    private JPanel splashMainContent;
    private JPanel splashProBarFooter;
    private JPanel splashFooter;
    private JLabel imgSplash_Txt;
    private BufferedImage splashHeader;

    SplashLoaderLauncher() {
        super();
        setContentPane(contentPane);
        progressBarSplash.setForeground(new Color(46, 139, 100));
        imgSplash_Txt = new JLabel(new ImageIcon(ImgsLoader.getSplashHeaderImage()));
        splashMainContent.add(imgSplash_Txt, BorderLayout.NORTH);
    }

    public void updateProgressBar() {
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < 80; i++) {
                    Thread.sleep(100);// Simulate loading
                    publish(i);// Notify progress
                    //TODO Update Text
                    if (i % 20 == 0) {
                        setInfoSplash_Txt("Update: " + i);
                    }
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                progressBarSplash.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                setVisible(false);
                dispose();
            }
        };
        worker.execute();
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SplashLoaderLauncher dialog = new SplashLoaderLauncher();
                dialog.setDefaultCloseOperation(dialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
                dialog.setTitle("Loading... ");
                dialog.setSize(560, 280);
                dialog.setLocationRelativeTo(null);
                dialog.updateProgressBar();
            }
        });
    }
}