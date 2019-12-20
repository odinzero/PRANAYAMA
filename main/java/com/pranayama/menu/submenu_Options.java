package com.pranayama.menu;

import com.pranayama.PRANAYAMA;
import com.pranayama.pattern.patternWindow;
import com.pranayama.menu.ui.separator;
import com.pranayama.ui.buttons;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class submenu_Options {

    public patternWindow options;
    //Menu 'Options' -> 'Create complex','Modify complex','Remove complex' 
    public buttons createComplexItem, modifyComplexItem, removeComplexItem,
            // 'views','sound','colors'
            viewsItem, soundItem, colorsItem,
            // 'Settings...','exit' 
            settingsMenuItem, exitMenuItem;
    PRANAYAMA pranaMain;

    public submenu_Options(PRANAYAMA prana) {
        this.pranaMain = prana;

        optionsWindow();
    }

    private void optionsWindow() {
        options = new patternWindow(pranaMain, "", false, 1, false, false, 6, 35, 265, 280);
        options.base.setBackground(new Color(190, 225, 220));

        // Menu 'Options' ->  Submenu  'Create complex'
        Rectangle rect1 = new Rectangle(5, 2, 200, 30);
        createComplexItem = new buttons("Create complex  -  s ", rect1);
        createComplexItem.setBounds(5, 2, 200, 30);
        createComplexItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                options.setWindowVisibility(false);
            }
        });

        // Menu 'Options' ->  Submenu  'Modify complex'
        Rectangle rect2 = new Rectangle(5, 2, 200, 30);
        modifyComplexItem = new buttons("Modify complex  -  p ", rect2);
        modifyComplexItem.setBounds(5, 32, 200, 30);
        modifyComplexItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                options.setWindowVisibility(false);
            }
        });

        // Menu 'Options' ->  Submenu  'Remove complex'
        Rectangle rect3 = new Rectangle(5, 2, 210, 30);
        removeComplexItem = new buttons("Remove complex  -  x ", rect3);
        removeComplexItem.setBounds(5, 62, 210, 30);
        removeComplexItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                options.setWindowVisibility(false);
            }
        });

        separator s1 = new separator();
        s1.setBounds(12, 98, 160, 10);
        options.base.add(s1);
        separator s2 = new separator();
        s2.setBounds(102, 98, 160, 10);
        options.base.add(s2);

        // Menu 'Options' ->  Submenu  'Views'
        Rectangle rect4 = new Rectangle(5, 2, 165, 30);
        viewsItem = new buttons("Views  -  Ctrl + v ", rect4);
        viewsItem.setBounds(5, 108, 165, 30);
        viewsItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                options.setWindowVisibility(false);
                // Hide Views window
                pranaMain.views_window.views.setWindowVisibility(true);
            }
        });

        // Menu 'Options' ->  Submenu  'Sound'
        Rectangle rect5 = new Rectangle(5, 2, 170, 30);
        soundItem = new buttons("Sound  -  Ctrl + s ", rect5);
        soundItem.setBounds(5, 138, 170, 30);
        soundItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                options.setWindowVisibility(false);
                // Hide Sound window
                pranaMain.sound_window.sound.setWindowVisibility(true);
            }
        });

        // Menu 'Options' ->  Submenu  'Colors'
        Rectangle rect6 = new Rectangle(5, 2, 170, 30);
        colorsItem = new buttons("Colors  -  Ctrl + c ", rect6);
        colorsItem.setBounds(5, 168, 170, 30);
        colorsItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                options.setWindowVisibility(false);
                // Hide Color window
                pranaMain.colorsWindow.colors.setWindowVisibility(true);
            }
        });

        // Menu 'Options' ->  Submenu  'Settings'
        Rectangle rect7 = new Rectangle(5, 2, 235, 30);
        settingsMenuItem = new buttons("Settings ........ - Ctrl + t ", rect7);
        settingsMenuItem.setBounds(5, 198, 235, 30);
        settingsMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                options.setWindowVisibility(false);
                // hide SETTINGS window
                pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
            }
        });

        separator s3 = new separator();
        s3.setBounds(12, 234, 160, 10);
        options.base.add(s3);
        separator s4 = new separator();
        s4.setBounds(102, 234, 160, 10);
        options.base.add(s4);

        // Menu 'Options' ->  Submenu  'Exit'
        Rectangle rect8 = new Rectangle(5, 2, 50, 30);
        exitMenuItem = new buttons("Exit ", rect8);
        exitMenuItem.setBounds(107, 244, 50, 30);
        exitMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

        options.base.add(createComplexItem);
        options.base.add(modifyComplexItem);
        options.base.add(removeComplexItem);
        options.base.add(viewsItem);
        options.base.add(soundItem);
        options.base.add(colorsItem);
        options.base.add(settingsMenuItem);
        options.base.add(exitMenuItem);
        options.setWindowVisibility(false);
    }
}
