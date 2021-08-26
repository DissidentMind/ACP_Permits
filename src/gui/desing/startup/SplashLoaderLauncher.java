package gui.desing.startup;

import gui.desing.imgs.ImgsLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SplashLoaderLauncher extends JDialog {
    private JPanel contentPane;
    private JProgressBar progressBarSplash;
    private JPanel headerSplashPanel;
    private JLabel infoSplash_Txt;
    private BufferedImage splashHeader;

    public SplashLoaderLauncher(){
        ImgsLoader imgL = new ImgsLoader();

        //setContentPane(contentPane);
        //setModal(true);
        //getRootPane().setDefaultButton(buttonOK);

        try {
            headerSplashPanel.setLayout(new BorderLayout());
            splashHeader = ImageIO.read(new File(imgL.getSplashHeaderImage()));
            JLabel pic = new JLabel(new ImageIcon(splashHeader));
            headerSplashPanel.add(pic,BorderLayout.CENTER);
            //headerSplashPanel.add(splashHeader,BorderLayout.CENTER);

        }catch (IOException e){
            System.out.println("Error in file: "+e);
        }
    }
}
