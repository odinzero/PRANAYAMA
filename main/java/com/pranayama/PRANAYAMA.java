package com.pranayama;

import com.pranayama.menu.menu;
import com.pranayama.ui.panel.basicPanel;
import com.pranayama.ui.plusminus.PlusMinusActions;
import com.pranayama.veiw.soundWindow;
import com.pranayama.veiw.viewDefault;
import com.pranayama.veiw.views;
import com.pranayama.veiw.viewsColorWindow;
import com.pranayama.veiw.viewsWindow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class PRANAYAMA  { /// implements KeyListener

    // https://www.youtube.com/watch?v=YrMydptCI_E&list=PL0Zqu46zKe0o0OKzOtk1EGpRK7QmK_MVR
    public JFrame frame;
    public basicPanel basic;

    public PlusMinusActions pma;
    public menu mainMenu;
    public views view;
    public viewsWindow views_window;
    public soundWindow sound_window;
    public viewsColorWindow colorsWindow;
    public viewDefault vDefault;

    PRANAYAMA() {
        frame = new JFrame("PRANAYAMA");
        frame.setUndecorated(true);
        //  frame.setLayout(new GridLayout(1, 1)); 
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(190, 220, 220), 5, true));
//        bevel.setFloatable(false);
        frame.setSize(1015, 600);
        frame.addKeyListener(new keyEvents(this)); 
//        frame.setMinimumSize(new Dimension(1215, 600)); // 361, 300
//        frame.setPreferredSize(new Dimension(1215, 600));
        // frame.setLayout(null);
        // frame.getContentPane().setBackground(new Color(255, 255, 255));

        basic = new basicPanel(this);
        basic.setBounds(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        basic.setSize(frame.getWidth(), frame.getHeight());
        // basic.setLayout(new BorderLayout()); 
        // set background of panel
        basic.setType(1);
        //frame.setContentPane(basic); 
        // frame.getRootPane().setContentPane(basic); 
        

        center();
//--------------------------------- DEFAULT VIEW -------------------------------
        vDefault = new viewDefault(this);
//--------------------------------- MENU ---------------------------------------
        mainMenu = new menu(this);
//--------------------------------- VIEWS --------------------------------------
        view = new views(this);
        
        views_window = new viewsWindow(this);
        sound_window = new soundWindow(this); 
        colorsWindow = new viewsColorWindow(this);  // Breathing Snake 1:1
//        colorsWindow.colors.base.repaint();
//------------------------------ MINUS and PLUS --------------------------------
        pma = new PlusMinusActions(this);        
// --------------------- INCREASE and DECREASE screen --------------------------
        IncreaseDecreaseScreen ids = new IncreaseDecreaseScreen(this);
// -----------------------------------------------------------------------------    
        // basic.setOpaque(true); 
        frame.add(basic);
        frame.setVisible(true);
    }

    public void center() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);
    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//    }

//    @Override
//    public void keyPressed(KeyEvent e) {
//        int keyCode = e.getKeyCode();
//
//        // start breathing
//        if (keyCode == KeyEvent.VK_S) {
////            pauseGame();
//        }
//        // when need pause breathing    
//        if (keyCode == KeyEvent.VK_P) {
////            pauseGame();
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//    }

    public static void main(String[] args) {

        new PRANAYAMA();
    }

}
