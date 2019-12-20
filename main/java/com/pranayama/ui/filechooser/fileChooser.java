package com.pranayama.ui.filechooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class fileChooser extends JFrame {

    fileChooser() {
        super("-== with FileFilter ==-");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JButton but = new JButton("Start");
        final JLabel lab = new JLabel("Your choice will be displayed here");

        but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ee) {
                    System.out.println("Unable to load Windows look and feel");
                }
                
                JFileChooser chooser = new JFileChooser();
//                    MyFileChooser2 chooser = new MyFileChooser2();
                //chooser.customizeFileChooser(comp); 
                chooser.setAcceptAllFileFilterUsed(false);

                String[] audio = {".wav", ".au", ".wma", ".mp3"};

//                    chooser.addChoosableFileFilter(new 
//                               fileFilter(audio, "Audio(*.wav, *.au, *.wma, *.mp3)" ));
                chooser.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Action");
                    }
                });

                int option = chooser.showOpenDialog(fileChooser.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    if (chooser.getSelectedFile() != null) {
                        lab.setText("You choose: " + chooser.getSelectedFile().getAbsolutePath()
                        //  " " + chooser.getSelectedFile().getName() 
                        );
                    }
                } else {
                    lab.setText("You canceled");
                }
            }
        }
        );
        c.add(but);
        c.add(lab);
        setVisible(true);
    }

    public static void main(String[] args) {
        new fileChooser();
    }
}
