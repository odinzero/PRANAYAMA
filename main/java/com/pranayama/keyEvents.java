package com.pranayama;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyEvents implements KeyListener {

    PRANAYAMA pranaMain;

    keyEvents(PRANAYAMA prana) {
        this.pranaMain = prana;
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {

    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        closeAll();
        
        //========================== Close PROGRAMM ============================
        if (keyCode == KeyEvent.VK_E) {
            System.exit(0); 
        }

        //========================== MENU ====================================== 
        // Show 'menu'
        // key 'm'
        if (keyCode == KeyEvent.VK_M) {
            pranaMain.mainMenu.menuPanel.setVisible(true);
        }
        // show SUBMENU 'Options'
        // key 'o'
        if (keyCode == KeyEvent.VK_O) {
            pranaMain.mainMenu.menuPanel.setVisible(true);
            pranaMain.mainMenu.Options.options.setWindowVisibility(true);
        }
        // show SUBMENU 'Breath'
        // key 'b'
        if (keyCode == KeyEvent.VK_B) {
            pranaMain.mainMenu.menuPanel.setVisible(true);
            pranaMain.mainMenu.Breath.breath.setWindowVisibility(true);
        }
        // show SUBMENU 'Help'
        // key 'h'
        if (keyCode == KeyEvent.VK_H) {
            pranaMain.mainMenu.menuPanel.setVisible(true);
            pranaMain.mainMenu.Help.helpMenu.setWindowVisibility(true);
        }

        //========================== VIEWS =====================================
        // open SETTINGS Window
        // CTRL + t
        if ((keyCode == KeyEvent.VK_T) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // Show SETTINGS window
            pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
        }
        // open SOUND Window
        // CTRL + s
        if ((keyCode == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // Show SOUND window
            pranaMain.sound_window.sound.setWindowVisibility(true);
        }
        // open VIEWS Window
        // CTRL + v
        if ((keyCode == KeyEvent.VK_V) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // Show VIEWS window
            pranaMain.views_window.views.setWindowVisibility(true);
        }
        // open COLORS Window
        // CTRL + c
        if ((keyCode == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // Show COLOR window
            pranaMain.colorsWindow.colors.setWindowVisibility(true);
        }
        // CTRL + h
        if ((keyCode == KeyEvent.VK_H) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // Show Reference window
            pranaMain.mainMenu.Help.help.setWindowVisibility(true);
        }
        // CTRL + r
        if ((keyCode == KeyEvent.VK_R) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            // Show CopyRight window
            pranaMain.mainMenu.Help.about.setWindowVisibility(true);
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
    }

    private void closeAll() {
        pranaMain.mainMenu.menuPanel.setVisible(false);
        pranaMain.mainMenu.Options.options.setWindowVisibility(false);
        pranaMain.mainMenu.Breath.breath.setWindowVisibility(false);
        pranaMain.mainMenu.Help.helpMenu.setWindowVisibility(false);

        // Hide Sound window
        pranaMain.sound_window.sound.setWindowVisibility(false);
        // Hide Views window
        pranaMain.views_window.views.setWindowVisibility(false);
        // Hide Color window
        pranaMain.colorsWindow.colors.setWindowVisibility(false);
        // Hide Reference window
        pranaMain.mainMenu.Help.help.setWindowVisibility(false);
        // Hide CopyRight window
        pranaMain.mainMenu.Help.about.setWindowVisibility(false);

        // hide SETTINGS window
        pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(false);
    }

}
