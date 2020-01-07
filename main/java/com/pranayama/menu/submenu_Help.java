package com.pranayama.menu;

import com.pranayama.PRANAYAMA;
import com.pranayama.pattern.patternWindow;
import com.pranayama.menu.ui.separator;
import com.pranayama.ui.buttons;
import com.pranayama.ui.list.LightScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class submenu_Help {

    int cntPage = 1;
    JLabel pageLabel;
    JLabel L1, L2, L3, L4, L5, L6, L7, L8, L9, L10, L11, L12, L13, L14, L15, L16, L17, L18, L19, L20,
           L21, L22, L23, L24;
    separator s1,s2,s3,s4,s5,s6;
    //Menu 'Help'  -> submenu 'Help','About'
    public buttons helpMenuSubItem, aboutMenuSubItem;
    // helpMenu -> is window which contains 'help' and 'about' windows
    public patternWindow helpMenu, help, about;
    public buttons showHelpContent1, showHelpContent2;
    PRANAYAMA pranaMain;
    public menu mainMenu;

    public submenu_Help(PRANAYAMA prana, menu menu) {
        this.pranaMain = prana;
        this.mainMenu = menu;

        helpSubMenu();
        helpWindow();
        aboutWindow();
    }

    private void helpSubMenu() {
        helpMenu = new patternWindow(pranaMain, "", false, 1, false, false, 325, 35, 120, 70);
        helpMenu.base.setBackground(new Color(190, 225, 220));

        // Menu 'Help' ->  Submenu  'Help'
        Rectangle rect1 = new Rectangle(5, 2, 115, 30);
        helpMenuSubItem = new buttons("Reference  ", rect1);
        helpMenuSubItem.setBounds(5, 2, 150, 30);
        helpMenuSubItem.addMouseListener(mainMenu.ma);
        helpMenuSubItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                help.setWindowVisibility(true);
                helpMenu.setWindowVisibility(false);
            }
        });

        // Menu 'Help' ->  Submenu  'About ...'
        Rectangle rect2 = new Rectangle(5, 2, 105, 30);
        aboutMenuSubItem = new buttons("About ...  ", rect2);
        aboutMenuSubItem.setBounds(5, 32, 150, 30);
        aboutMenuSubItem.addMouseListener(mainMenu.ma);
        aboutMenuSubItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                about.setWindowVisibility(true);
                helpMenu.setWindowVisibility(false);
            }
        });

        helpMenu.base.add(helpMenuSubItem);
        helpMenu.base.add(aboutMenuSubItem);
        helpMenu.setWindowVisibility(false);
    }

    private void aboutWindow() {
        about = new patternWindow(pranaMain, "Help", true, 0, true, true, 0, 0, 450, 195);
        about.base.setBackground(new Color(190, 225, 220));

        about.addContent("Copyright by Alexey Kravchenko", 30, Color.BLUE, 15, 40, 440, 35);
        about.addContent(" 2014 to 2018", 30, Color.BLUE, 125, 80, 205, 35);

        about.addButtonsWindow("Close", 191, 130, 72, 37).setButtonAction(0);// +50 +65 
        about.setWindowVisibility(false);
    }

    private void helpWindow() {
        help = new patternWindow(pranaMain, "Help", true, 0, true, true, 0, 0, 400, 515);

        showFirstHelpContent();
        initHelpContentButtons();

        // Action 0 <- it means close window
        help.addButtonsWindow("Close", 171, 450, 72, 37).setButtonAction(0); // +45 +65
        help.setWindowVisibility(false);
    }

    private void initHelpContentButtons() {
        showHelpContent1 = new buttons(3); // button for show second content help menu
        showHelpContent1.setName("firstHelp");
        showHelpContent1.setBounds(188, 395, 24, 24);
        showHelpContent1.setVisible(true);

        showHelpContent2 = new buttons(2); // button for show first content help menu
        showHelpContent2.setName("secondHelp");
        showHelpContent2.setBounds(188, 395, 24, 24);
        showHelpContent2.setVisible(false);

        showHelpContent1.addMouseListener(manageHelpContent(showHelpContent1, showHelpContent2)); // add mouse listener
        showHelpContent2.addMouseListener(manageHelpContent(showHelpContent2, showHelpContent1)); // add mouse listener

        help.base.add(showHelpContent1);
        help.base.add(showHelpContent2);
    }

    private MouseAdapter manageHelpContent(buttons but1, buttons but2) {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (but1.isVisible()) {
                    but1.setVisible(false);
                    but2.setVisible(true);

//                    help.base.remove(L1);
//                    help.base.remove(L2);
                     removeHelpComponents(help.base);
                    if (but1.getName().equals("firstHelp")) {
                        System.out.println("firdt");
                        showSecondHelpContent();
                    }
                    if (but1.getName().equals("secondHelp")) {
                        System.out.println("second");
                        showFirstHelpContent();
                    }
                    addHelpComponents(help.base);
//                    help.base.add(L1);
//                    help.base.add(L2);
                }

                help.base.repaint();
                help.base.revalidate();
            }
        };
        return ma;
    }

    private void showFirstHelpContent() {
         // (String str, int fontSize, int x, int y, int width, int height)
        L1 = help.addContent("m - ", 26, Color.BLUE, 30, 30, 85, 35);
        L2 = help.addContent("show menu ", 26, Color.BLUE, 100, 30, 205, 35);

        L3 = help.addContent("o  - ", 26, Color.BLUE, 30, 55, 85, 35);
        L4 = help.addContent("show submenu 'Options' ", 26, Color.BLUE, 100, 55, 305, 35);

        L5 = help.addContent("b  - ", 24, Color.BLUE, 30, 80, 85, 35);
        L6 = help.addContent("show submenu 'Breath' ", 24, Color.BLUE, 100, 80, 305, 35);

        L7 = help.addContent("b  - ", 24, Color.BLUE, 30, 105, 85, 35);
        L8 = help.addContent("show submenu 'Statistics' ", 24, Color.BLUE, 100, 105, 305, 35);

        L9 = help.addContent("h  - ", 24, Color.BLUE, 30, 130, 85, 35);
        L10 = help.addContent("show submenu 'Help' ", 24, Color.BLUE, 100, 130, 305, 35);

        s1 = new separator();
        s1.setBounds(30, 165, 160, 10);
        s2 = new separator();
        s2.setBounds(150, 165, 160, 10);
        s3 = new separator();
        s3.setBounds(210, 165, 160, 10);
        help.base.add(s1);
        help.base.add(s2);
        help.base.add(s3);

        L11 = help.addContent("Ctrl+t  - ", 24, Color.BLUE, 30, 175, 135, 35);
        L12 = help.addContent("open 'Settings' window ", 24, Color.BLUE, 120, 175, 305, 35);

        L13 = help.addContent("Ctrl+s  - ", 24, Color.BLUE, 30, 200, 130, 35);
        L14 = help.addContent("open 'Sound' window ", 24, Color.BLUE, 120, 200, 305, 35);

        L15 = help.addContent("Ctrl+v  - ", 24, Color.BLUE, 30, 225, 135, 35);
        L16 = help.addContent("open 'Views' window ", 24, Color.BLUE, 120, 225, 305, 35);

        L17 = help.addContent("Ctrl+c  - ", 24, Color.BLUE, 30, 250, 135, 35);
        L18 = help.addContent("open 'Colors' window ", 24, Color.BLUE, 120, 250, 305, 35);

        L19 = help.addContent("Ctrl+h  - ", 24, Color.BLUE, 30, 275, 135, 35);
        L20 = help.addContent("open 'Help' window ", 24, Color.BLUE, 120, 275, 305, 35);

        L21 = help.addContent("Ctrl+a  - ", 24, Color.BLUE, 30, 300, 135, 35);
        L22 = help.addContent("open 'Statistics' window ", 24, Color.BLUE, 120, 300, 305, 35);

        L23 = help.addContent("Ctrl+r  - ", 24, Color.BLUE, 30, 325, 135, 35);
        L24 = help.addContent("open 'CopyRight' window ", 24, Color.BLUE, 120, 325, 305, 35);

        s4 = new separator();
        s4.setBounds(30, 360, 160, 10);
        s5 = new separator();
        s5.setBounds(150, 360, 160, 10);
        s6 = new separator();
        s6.setBounds(210, 360, 160, 10);
        help.base.add(s4);
        help.base.add(s5);
        help.base.add(s6);
    }

    private void showSecondHelpContent() {
         // (String str, int fontSize, int x, int y, int width, int height)
        L1 = help.addContent("s  - ", 26, Color.BLUE, 30, 30, 85, 35);
        L2 = help.addContent("create complex ", 26, Color.BLUE, 100, 30, 205, 35);

        L3 = help.addContent("p  - ", 26, Color.BLUE, 30, 55, 85, 35);
        L4 = help.addContent("modify complex ", 26, Color.BLUE, 100, 55, 305, 35);

        L5 = help.addContent("x  - ", 24, Color.BLUE, 30, 80, 85, 35);
        L6 = help.addContent("remove complex ", 24, Color.BLUE, 100, 80, 305, 35);

        s1 = new separator();
        s1.setBounds(30, 115, 160, 10);
        s2 = new separator();
        s2.setBounds(150, 115, 160, 10);
        s3 = new separator();
        s3.setBounds(210, 115, 160, 10);
        help.base.add(s1);
        help.base.add(s2);
        help.base.add(s3);
        
        L7 = help.addContent("Ctrl+n  - ", 24, Color.BLUE, 30, 125, 105, 35);
        L8 = help.addContent("create breath ", 24, Color.BLUE, 120, 125, 305, 35);

        L9 = help.addContent("Ctrl+f  - ", 24, Color.BLUE, 30, 150, 85, 35);
        L10 = help.addContent("breathing 'Falcon' ", 24, Color.BLUE, 120, 150, 305, 35);

        L11 = help.addContent("Ctrl+b  - ", 24, Color.BLUE, 30, 175, 135, 35);
        L12 = help.addContent("breathing 'Bear' ", 24, Color.BLUE, 120, 175, 305, 35);

        L13 = help.addContent("Ctrl+v  - ", 24, Color.BLUE, 30, 200, 130, 35);
        L14 = help.addContent("breathing 'Volf' ", 24, Color.BLUE, 120, 200, 305, 35);
        
        L13 = help.addContent("Ctrl+u  - ", 24, Color.BLUE, 30, 225, 130, 35);
        L14 = help.addContent("breathing 'Snake' ", 24, Color.BLUE, 120, 225, 305, 35);

        L15 = help.addContent("Ctrl+i  - ", 24, Color.BLUE, 30, 250, 135, 35);
        L16 = help.addContent("breathing others ", 24, Color.BLUE, 120, 250, 305, 35);
        
        L17 = help.addContent("e  - ", 24, Color.BLUE, 30, 295, 135, 35);
        L18 = help.addContent("exit pranayama ", 24, Color.BLUE, 120, 295, 305, 35);
        
        s4 = new separator();
        s4.setBounds(30, 360, 160, 10);
        s5 = new separator();
        s5.setBounds(150, 360, 160, 10);
        s6 = new separator();
        s6.setBounds(210, 360, 160, 10);
        help.base.add(s4);
        help.base.add(s5);
        help.base.add(s6);

//        L19 = help.addContent("Ctrl+h  - ", 24, Color.BLUE, 30, 275, 135, 35);
//        L20 = help.addContent("open 'Help' window ", 24, Color.BLUE, 120, 275, 305, 35);
//
//        L21 = help.addContent("Ctrl+a  - ", 24, Color.BLUE, 30, 300, 135, 35);
//        L22 = help.addContent("open 'Statistics' window ", 24, Color.BLUE, 120, 300, 305, 35);
//
//        L23 = help.addContent("Ctrl+r  - ", 24, Color.BLUE, 30, 325, 135, 35);
//        L24 = help.addContent("open 'CopyRight' window ", 24, Color.BLUE, 120, 325, 305, 35);

    }
    
    private void removeHelpComponents(JPanel common) {
        if (common != null) {
            Component[] components = common.getComponents();

            for (Component component : components) {
                if (component instanceof JLabel) {
                    common.remove(component);
                }
                if (component instanceof separator) {
                    common.remove(component);
                }
            }
        }
    }
    
    private void addHelpComponents(JPanel common) {
        if (common != null) {
            Component[] components = common.getComponents();

            for (Component component : components) {
                if (component instanceof JLabel) {
                    common.add(component);
                }
                if (component instanceof separator) {
                    common.add(component);
                }
            }
        }
    }

}
