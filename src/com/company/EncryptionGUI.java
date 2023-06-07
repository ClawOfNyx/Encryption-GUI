package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptionGUI implements ActionListener {

    private static JTextField enText;
    private static JTextField deText;
    private static JTextField keyText;
    private static JButton en;
    private static JButton de;
    private static JLabel success;
    private static JLabel success2;
    private static final JProgressBar bar = new JProgressBar();
    private static final JProgressBar bar2 = new JProgressBar();

    static ToFromEncryption te = new ToFromEncryption();

    public static void main(String[] args) {
        //-------------------GUI-Base-----------------------------------------------------------------------------------
        JFrame frame = new JFrame(); //creates a frame
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panel1 = new JPanel(); //creates a panel
        JPanel panel2 = new JPanel(); //creates a panel

        ImageIcon icon = new ImageIcon("icon.png"); //create an ImageIcon
        frame.setIconImage(icon.getImage()); //change icon of the frame

        frame.setVisible(true); //makes the frame visible

        frame.setTitle("Encrypt-Decrypt"); //sets a title for a frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //with 'x' it now exists the frame (default = hide)
        frame.setSize(620, 220); //sets the x-dimension and the y-dimension
        frame.setResizable(false); //won't allow to resize the frame
        frame.getContentPane().setBackground(new Color(106, 106, 106)); //changes colour of the frame

        frame.add(panel1);
        frame.add(panel2);
        frame.add(tabbedPane);

        //-------------------Panel-Of-Encryption------------------------------------------------------------------------
        panel1.setLayout(null);
        panel1.setBackground(new Color(211, 211, 211));

        bar.setValue(0);
        bar.setBounds(10, 120, 580, 25);
        bar.setStringPainted(true);
        bar.setForeground(new Color(128, 128, 128));
        panel1.add(bar);

        JLabel labelE = new JLabel(); //creates a label
        labelE.setText("Text: ");
        labelE.setBounds(10, 20, 80, 25);
        panel1.add(labelE);

        enText = new JTextField();
        enText.setBounds(100, 20, 480, 25);
        panel1.add(enText);

        ImageIcon lock = new ImageIcon("lock.png");

        en = new JButton("Encrypt");
        en.addActionListener(new EncryptionGUI());
        en.setFocusable(false);
        en.setIcon(lock);
        en.setBounds(480, 60, 100, 25);
        panel1.add(en);

        success = new JLabel("");
        success.setBounds(10, 90, 500, 25);
        panel1.add(success);
        success.setText("");

        tabbedPane.addTab("Encryption", panel1);

        //-------------------Panel-Of-Decryption------------------------------------------------------------------------
        panel2.setLayout(null);
        panel2.setBackground(new Color(211, 211, 211));

        bar2.setValue(0);
        bar2.setBounds(10, 120, 580, 25);
        bar2.setStringPainted(true);
        bar2.setForeground(new Color(128, 128, 128));
        panel2.add(bar2);

        JLabel label2 = new JLabel(); //creates a label
        label2.setText("Text: ");
        label2.setBounds(10, 20, 80, 25);
        panel2.add(label2);

        deText = new JTextField();
        deText.setBounds(100, 20, 480, 25);
        panel2.add(deText);

        JLabel keyLabel = new JLabel();
        keyLabel.setText("Key word: ");
        keyLabel.setBounds(10, 50, 80, 25);
        panel2.add(keyLabel);

        keyText = new JTextField();
        keyText.setBounds(100, 50, 480, 25);
        panel2.add(keyText);

        ImageIcon openLock = new ImageIcon("open_lock.png");

        de = new JButton("Decrypt");
        de.addActionListener(new EncryptionGUI());
        de.setFocusable(false);
        de.setIcon(openLock);
        de.setBounds(480, 90, 100, 25);
        panel2.add(de);

        success2 = new JLabel("");
        success2.setBounds(10, 90, 500, 25);
        panel2.add(success2);
        success2.setText("");

        tabbedPane.addTab("Decryption", panel2);
    }

    //------------------------------------------------------------------------------------------------------------------

    static void fill(JProgressBar b) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <= 100; ++i) {
                        b.setValue(i);

                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            success2.setText("");

            String sentence;

            if (e.getSource() == en) {
                success.setText("Encryption started! Look for results in the output.txt file.");

                fill(bar);

                sentence = enText.getText();
                te.toEncrypt(sentence);
            }

            //----------------------------------------------------------------------------------------------------------

            if (e.getSource() == de) {
                String key = keyText.getText();

                if (key.equalsIgnoreCase("enc03")) {
                    success2.setText("Decryption started! Look for results in the output.txt file.");

                    fill(bar2);

                    sentence = deText.getText();
                    te.toDecrypt(sentence);
                } else {
                    success2.setText("Wrong key word.");
                }
            }
        } catch (Exception er) {
            System.out.println("Error: " + er.getMessage());
        }
    }
}
