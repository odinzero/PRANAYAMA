package com.pranayama.audio;

import com.pranayama.PRANAYAMA;
import com.pranayama.gradientSampleIcon;
import com.pranayama.menu.ui.separator;
import com.pranayama.pattern.patternWindow;
import com.pranayama.ui.MyCheckBoxUI;
import com.pranayama.ui.MyComboBoxUI;
import com.pranayama.ui.buttons;
import com.pranayama.ui.list.AnimationList;
import com.pranayama.ui.list.AnimationListCellRenderer;
import com.pranayama.ui.list.LightScrollPane;
import com.pranayama.ui.panel.basicPanel;
import com.pranayama.util.utils;
import static com.pranayama.util.utils2.listFilesForFolder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.plaf.ComboBoxUI;

public class soundWindow {

    public patternWindow sound;
    PRANAYAMA pranaMain;

    utils.buttonsWindow apply;
    public JCheckBox sound_box;
    public JComboBox comboSound;

    public soundWindow() {

        init();
    }

    private void init() {
        sound = new patternWindow(pranaMain, "Audio", true, 0, true, true, 0, 0, 400, 625); // 540 480
        sound.setWindowVisibility(true);
        Font font = new Font("Monotype Corsiva", Font.BOLD, 25);
// ========================= BUTTONS  'Apply','Cancel' =========================
        // add button 'Apply' to start download game     
        apply = new utils.buttonsWindow("Apply", 80, 35, 12, 23);
        apply.addMouseListener(apply());
        apply.setBounds(100, 565, 85, 40);

        // Action 0 <- it means close window
        sound.addButtonsWindow2("Cancel", 200, 565, 85, 35, 7, 23, 90, 40)
                .addMouseListener(cancel());

        sound.base.add(apply);
// ============================ Header Sound ===================================                
        separator s1 = new separator();
        s1.setBounds(20, 40, 160, 10);

        Rectangle rect1 = new Rectangle(5, 2, 115, 30);
        buttons headerColors = new buttons(" Mode ", rect1);
        headerColors.setBounds(155, 25, 110, 30);

        separator s2 = new separator();
        s2.setBounds(260, 40, 118, 10);
//==================== sound checkbox ==========================================
        sound_box = new JCheckBox("set audio");
        sound_box.setOpaque(false);
        sound_box.setForeground(Color.BLUE);
        sound_box.setFont(font);
        sound_box.setBounds(25, 60, 360, 30);
        sound_box.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (sound_box.isSelected()) {

                } else {

                }
            }
        });
        sound_box.setUI((MyCheckBoxUI) MyCheckBoxUI.createUI(sound_box));
// ============================ Header Sound ===================================                
        separator s3 = new separator();
        s3.setBounds(20, 95, 160, 10);

        Rectangle rect2 = new Rectangle(5, 2, 115, 30);
        buttons headerSounds = new buttons(" Sounds ", rect2);
        headerSounds.setBounds(155, 80, 110, 30);

        separator s4 = new separator();
        s4.setBounds(260, 95, 118, 10);
//==============================================================================
        final File folder = new File("./src/main/recources/sound");
        String[] files = listFilesForFolder(folder);

        AnimationList[] al = new AnimationList[files.length];
        for (int i = 0; i < files.length; i++) { 
            String f = files[i];
            al[i] = new AnimationList(f, "./src/main/recources/img/audio3.png");
        }
        
        JList choiceAnimation = new JList(al);
        choiceAnimation.setCellRenderer(new AnimationListCellRenderer());

        LightScrollPane scroll = new LightScrollPane(choiceAnimation);

        scroll.setBounds(25, 125, 350, 100);

//        comboSound = new JComboBox(files);
//        comboSound.setUI((ComboBoxUI) MyComboBoxUI.createUI(comboSound));
//        comboSound.setSelectedIndex(0);
//        comboSound.setBounds(25, 125, 350, 30);
//        comboSound.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//        comboSound.setFont(new Font("Serif", Font.BOLD, 16));
//        comboSound.setEditable(false);
//        changeFonts(comboSound);
//==================== layout components ========================================        
        sound.base.add(s1);
        sound.base.add(headerColors);
        sound.base.add(s2);

        sound.base.add(sound_box);

        sound.base.add(s3);
        sound.base.add(headerSounds);
        sound.base.add(s4);

        sound.base.add(scroll);
    }

    private MouseAdapter cancel() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // hide COLORS window
                sound.setWindowVisibility(false);
                // Show SETTINGS window
                pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
            }
        };
        return ma;
    }

    private MouseAdapter apply() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // hide COLORS window
                //colors.setWindowVisibility(false);
                // Show SETTINGS window
                //pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
            }
        };
        return ma;
    }

    public static void main(String[] args) throws IOException {

        soundWindow sb = new soundWindow();  // Breathing Snake 1:1
//        sb.colors.base.repaint();
//        sb.setWindowVisibility(true);
    }

}
