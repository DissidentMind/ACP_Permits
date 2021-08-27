package gui.desing.startup;

import gui.desing.imgs.ImgsLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SplashLoaderLauncher extends JDialog {
    private JPanel contentPane;
    private JProgressBar progressBarSplash;
    private JLabel infoSplash_Txt;
    private JPanel splashMainContent;
    private JPanel splashProBarFooter;
    private JPanel splashFooter;
    private JLabel imgSplash_Txt;
    private BufferedImage splashHeader;

    SplashLoaderLauncher(){
        super();
        imgSplash_Txt = new JLabel(new ImageIcon(ImgsLoader.getSplashHeaderImage()));
        splashMainContent.add(imgSplash_Txt,BorderLayout.CENTER);
        setContentPane(splashMainContent);
    }

    protected void initUI(JDialog dialog) throws MalformedURLException {
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(100);// Simulate loading
                    publish(i);// Notify progress
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                progressBarSplash.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                //showFrame();
                //hideSplashScreen();
                dialog.setVisible(false);
                dialog.dispose();
            }

        };
        worker.execute();
    }

    public static void main(String[] args)throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        /*final SplashLoaderLauncher dialog = new SplashLoaderLauncher();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setTitle("Loading... ");
        dialog.setSize(560, 400);*/

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    //new TestSplashScreen().initUI();
                    SplashLoaderLauncher dialog = new SplashLoaderLauncher();
                    dialog.pack();
                    dialog.setVisible(true);
                    dialog.setTitle("Loading... ");
                    dialog.setSize(560, 400);
                    new SplashLoaderLauncher().initUI(dialog);

                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        //dialog.initUI(dialog);
        //dialog.setSize(560, 400);
        }
    }
