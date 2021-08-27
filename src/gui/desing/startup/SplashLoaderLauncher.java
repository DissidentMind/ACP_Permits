package gui.desing.startup;

import gui.desing.imgs.ImgsLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SplashLoaderLauncher extends JDialog {
    private JPanel contentPane;
    private JProgressBar progressBarSplash;
    private JPanel headerSplashPanel;
    private JLabel infoSplash_Txt;
    private JLabel imgHeader_Lbl;
    private BufferedImage splashHeader;

    public static final int OK_OPTION = 0;
    public static final int CANCEL_OPTION = 1;
    private int result = -1;

    JPanel content;
    ImgsLoader imgL = new ImgsLoader();

    public SplashLoaderLauncher(Frame parent) {
        super(parent, true);

        headerSplashPanel = new JPanel(new BorderLayout(3, 3));
        headerSplashPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        content = new JPanel(new BorderLayout());

        headerSplashPanel.add(content, BorderLayout.CENTER);
        JPanel buttons = new JPanel(new FlowLayout(4));
        headerSplashPanel.add(buttons, BorderLayout.SOUTH);

        imgHeader_Lbl = new JLabel(new ImageIcon(imgL.getSplashHeaderImage()));
        headerSplashPanel.add(imgHeader_Lbl,BorderLayout.NORTH);

        JButton ok = new JButton("OK");
        buttons.add(ok);
        ok.addActionListener(e -> {
            result = OK_OPTION;
            setVisible(false);
        });

        JButton cancel = new JButton("Cancel");
        buttons.add(cancel);
        cancel.addActionListener(e -> {
            result = CANCEL_OPTION;
            setVisible(false);
        });

        setContentPane(headerSplashPanel);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        final SplashLoaderLauncher dialog = new SplashLoaderLauncher(f);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setTitle("Loading... ");
        dialog.setSize(560, 400);
    }

    public int showConfirmDialog(JComponent child, String title) {
        setTitle(title);
        content.removeAll();
        content.add(child, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
        return result;
    }

}
