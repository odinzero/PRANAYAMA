package com.pranayama.menu;

import com.pranayama.PRANAYAMA;
import com.pranayama.pattern.patternWindow;
import com.pranayama.menu.ui.separator;
import com.pranayama.settingsBreathing;
import com.pranayama.ui.buttons;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class submenu_Breath {
    
    //Menu 'Breath' -> submenu 'Create Breath'
    //                         'Breathing Falcon 1:7',
    //                         'Breathing Bear 7:1',
    //                         'Breathing Volf 1:1',
    //                         'Breathing Snake 1:1' 
    //                         'others'
    public buttons createBreath, falconBreath, bearBreath, volfBreath, snakeBreath, othersBreath;
    public patternWindow breath;
    public settingsBreathing settingsBreath;
    
    PRANAYAMA pranaMain;
    public menu mainMenu;

    public submenu_Breath(PRANAYAMA prana, menu menu) {
       this.pranaMain = prana;
       this.mainMenu = menu;
       
       settingsBreath = new settingsBreathing(this.pranaMain);
       breathWindow();
    }

    private void breathWindow() {
        breath = new patternWindow(pranaMain, "", false, 1, false, false, 115, 35, 220, 225);
        breath.base.setBackground(new Color(190, 225, 220));
        
        // Menu 'Breath' ->  Submenu  'Breathing Falcon 1:7'
        Rectangle rect0 = new Rectangle(5, 2, 205, 30);
        createBreath = new buttons("Create breath", rect0);
        createBreath.setBounds(5, 2, 205, 30);
        createBreath.addMouseListener(mainMenu.ma);
        createBreath.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
//                Object o =  (e.getSource()).toString();
//                System.out.println(o);
////                settingsBreath = new settingsBreathing("Breathing Falcon 1:7");
//                settingsBreath.breathChoice(falconBreath.nameMenu); 
                // show breath settings
                settingsBreath.settings.setWindowVisibility(true);
                
                breath.setWindowVisibility(false);
             }
        });
        
        separator s3 = new separator();
        s3.setBounds(17, 38, 160, 10);
        breath.base.add(s3);
        separator s4 = new separator();
        s4.setBounds(107, 38, 93, 10);
        breath.base.add(s4);

        // Menu 'Breath' ->  Submenu  'Breathing Falcon 1:7'
        Rectangle rect1 = new Rectangle(5, 2, 210, 30);
        falconBreath = new buttons("Breathing Falcon 1:7", rect1);
        falconBreath.setBounds(5, 48, 210, 30);
        falconBreath.addMouseListener(mainMenu.ma);
        falconBreath.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                Object o =  (e.getSource()).toString();
                System.out.println(o);
//                settingsBreath = new settingsBreathing("Breathing Falcon 1:7");
                settingsBreath.breathChoice(falconBreath.nameMenu); 
                // show breath settings
                settingsBreath.settings.setWindowVisibility(true);
                
                breath.setWindowVisibility(false);
             }
        });

        // Menu 'Breath' ->  Submenu  'Breathing Bear 7:1'
        Rectangle rect2 = new Rectangle(5, 2, 200, 30);
        bearBreath = new buttons("Breathing Bear 7:1 ", rect2);
        bearBreath.setBounds(5, 78, 200, 30);
        bearBreath.addMouseListener(mainMenu.ma);
        bearBreath.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              
                Object o =  (e.getSource()).toString();
                System.out.println(o);
//                 settingsBreath = new settingsBreathing("Breathing Bear 7:1 ");
                settingsBreath.breathChoice(bearBreath.nameMenu);
                breath.setWindowVisibility(false);
                 // show breath settings
                settingsBreath.settings.setWindowVisibility(true);
            }
        });

        // Menu 'Breath' ->  Submenu  'Breathing Volf 1:1'
        Rectangle rect3 = new Rectangle(5, 2, 200, 30);
        volfBreath = new buttons("Breathing Volf 1:1    ", rect3);
        volfBreath.setBounds(5, 108, 200, 30);
        volfBreath.addMouseListener(mainMenu.ma);
        volfBreath.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
               Object o =  (e.getSource()).toString();
                System.out.println(o);
//                settingsBreath = new settingsBreathing("Breathing Volf 1:1    ");
                settingsBreath.breathChoice(volfBreath.nameMenu);
                breath.setWindowVisibility(false);
                // show breath settings
                settingsBreath.settings.setWindowVisibility(true);
            }
        });
        
         // Menu 'Breath' ->  Submenu  'Breathing Snake 1:1'
        Rectangle rect4 = new Rectangle(5, 2, 200, 30);
        snakeBreath = new buttons("Breathing Snake 1:1", rect4);
        snakeBreath.setBounds(5, 138, 200, 30);
        snakeBreath.addMouseListener(mainMenu.ma);
        snakeBreath.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                Object o =  (e.getSource()).toString();
                System.out.println(o);
//                settingsBreath = new settingsBreathing("Breathing Snake 1:1");
                settingsBreath.breathChoice(snakeBreath.nameMenu);
                breath.setWindowVisibility(false);
                // show breath settings
                settingsBreath.settings.setWindowVisibility(true);
            }
        });
        
        separator s1 = new separator();
        s1.setBounds(17, 174, 160, 10);
        breath.base.add(s1);
        separator s2 = new separator();
        s2.setBounds(107, 174, 93, 10);
        breath.base.add(s2);
        
         // Menu 'Breath' ->  Submenu  'Others'
        Rectangle rect5 = new Rectangle(5, 2, 200, 30);
        othersBreath = new buttons("Others ..... ", rect5);
        othersBreath.setBounds(5, 184, 200, 30);
        othersBreath.addMouseListener(mainMenu.ma);
        othersBreath.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               
                Object o =  (e.getSource()).toString();
                System.out.println(o);
//                settingsBreath = new settingsBreathing("Breathing Snake 1:1");
                settingsBreath.breathChoice(othersBreath.nameMenu);
                breath.setWindowVisibility(false);
                // show breath settings
                settingsBreath.settings.setWindowVisibility(true);
            }
        });

        breath.window.addMouseListener(mainMenu.ma);

        breath.base.add(createBreath);
        breath.base.add(falconBreath);
        breath.base.add(bearBreath);
        breath.base.add(volfBreath);
        breath.base.add(snakeBreath);
        breath.base.add(othersBreath);
        breath.setWindowVisibility(false);
    }
    
    private void setBreath() {
        
    }

}
