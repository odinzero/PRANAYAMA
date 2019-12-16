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
import com.pranayama.ui.list.GenericListModel;
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
import static java.nio.file.Files.list;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ComboBoxUI;

public class soundWindow {

    public patternWindow sound;
    PRANAYAMA pranaMain;

    DefaultListModel model;
    JList soundList;
    LightScrollPane scroll;
    int soundListIndex = -1;
    utils.buttonsWindow apply, downloadButton, removeButton, playButton, stopButton;
    public JCheckBox sound_box;
    public JComboBox comboSound;

    public soundWindow() {

        init();
    }

    private void init() {
        sound = new patternWindow(pranaMain, "Audio", true, 0, true, true, 0, 0, 400, 530); // 540 480
        sound.setWindowVisibility(true);
        Font font = new Font("Monotype Corsiva", Font.BOLD, 25);
// ========================= BUTTONS  'Apply','Cancel' =========================
        // add button 'Apply' to start download game     
        apply = new utils.buttonsWindow("Apply", 80, 35, 12, 23);
        apply.addMouseListener(apply());
        apply.setBounds(100, 470, 85, 40);

        // Action 0 <- it means close window
        sound.addButtonsWindow2("Cancel", 200, 470, 85, 35, 7, 23, 90, 40)
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
        sound_box.setSelected(true);
        sound_box.setOpaque(false);
        sound_box.setForeground(Color.BLUE);
        sound_box.setFont(font);
        sound_box.setBounds(25, 60, 360, 30);
        sound_box.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (sound_box.isSelected()) {
                    enableAllElements();
                } else {
                    disableAllElements();
                    soundList.clearSelection();
                    setSoundListIndex(-1);
                    soundList.setSelectedIndex(getSoundListIndex());
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
//=============================== sounds ========================================
        downloadButton = new utils.buttonsWindow("Download", 130, 35, 12, 23);
        downloadButton.setDisabledState(false);
        downloadButton.addMouseListener(apply());
        downloadButton.setBounds(45, 120, 135, 40);

        removeButton = new utils.buttonsWindow("Remove", 100, 35, 12, 23);
        removeButton.setDisabledState(true);
        removeButton.addMouseListener(removeSound());
        removeButton.setBounds(265, 120, 105, 40);

//        model = new DefaultListModel( );
        soundList = createList();
        scroll = new LightScrollPane(soundList); 
        scroll.setBounds(25, 175, 350, 175);
//        comboSound = new JComboBox(files);
//        comboSound.setUI((ComboBoxUI) MyComboBoxUI.createUI(comboSound));
//        comboSound.setSelectedIndex(0);
//        comboSound.setBounds(25, 125, 350, 30);
//        comboSound.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//        comboSound.setFont(new Font("Serif", Font.BOLD, 16));
//        comboSound.setEditable(false);
//        changeFonts(comboSound);
//=========================== Check sound ======================================
        separator s5 = new separator();
        s5.setBounds(20, 370, 105, 10);

        Rectangle rect3 = new Rectangle(10, 2, 115, 30);
        buttons checkSounds = new buttons("Check sounds", rect3);
        checkSounds.setBounds(135, 355, 140, 30);

        separator s6 = new separator();
        s6.setBounds(280, 370, 98, 10);

        playButton = new utils.buttonsWindow("Play", 70, 35, 12, 23);
        playButton.setDisabledState(true);
        playButton.addMouseListener(playSound());
        playButton.setBounds(45, 395, 75, 40);

        stopButton = new utils.buttonsWindow("Stop", 70, 35, 12, 23);
        stopButton.setDisabledState(true);
        stopButton.addMouseListener(stopSound());
        stopButton.setBounds(265, 395, 75, 40);

        separator s7 = new separator();
        s7.setBounds(20, 445, 160, 10);
        separator s8 = new separator();
        s8.setBounds(170, 445, 160, 10);
        separator s9 = new separator();
        s9.setBounds(320, 445, 58, 10);
//==================== layout components ========================================        
        sound.base.add(s1);
        sound.base.add(headerColors);
        sound.base.add(s2);

        sound.base.add(sound_box);

        sound.base.add(s3);
        sound.base.add(headerSounds);
        sound.base.add(s4);

        sound.base.add(downloadButton);
        sound.base.add(removeButton);
        sound.base.add(scroll);

        sound.base.add(s5);
        sound.base.add(checkSounds);
        sound.base.add(s6);
        sound.base.add(playButton);
        sound.base.add(stopButton);

        sound.base.add(s7);
        sound.base.add(s8);
        sound.base.add(s9);
    }

    public void setSoundListIndex(int soundListIndex) {
        this.soundListIndex = soundListIndex;
    }

    public int getSoundListIndex() {
        return soundListIndex;
    }
    
    private JList createList() { // final JList list
        
        final File folder = new File("./src/main/recources/sound");
        String[] files = listFilesForFolder(folder);
        
        DefaultListModel model = new DefaultListModel( );
        JList list = new JList(model); // al

        AnimationList[] al = new AnimationList[files.length];
        for (int i = 0; i < files.length; i++) {
            String f = files[i];
            al[i] = new AnimationList(f, "./src/main/recources/img/audio4.png");
            model.addElement(al[i]); 
        }
        
        //list.setModel(new GenericListModel<AnimationList>()); 
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new AnimationListCellRenderer(list));
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                
                setSoundListIndex(list.getSelectedIndex()); 

                if (getSoundListIndex() != -1) {
                    removeButton.setDisabledState(false);
                    playButton.setDisabledState(false);
                    sound.base.repaint();
                    System.out.println("-1:");
                    //model.remove(selectedIndex);
                } else {
                    removeButton.setDisabledState(true);
                    playButton.setDisabledState(true);
                    sound.base.repaint();
                    System.out.println("> -1:");
                }

                System.out.println("JList:" + soundListIndex);
            }
        });
        return list;
    }

    private MouseAdapter cancel() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // hide COLORS window
                sound.setWindowVisibility(false);
                // Show SETTINGS window
               // pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
               System.exit(0); 
               
            }
        };
        return ma;
    }

    protected void disableAllElements() {
        downloadButton.setDisabledState(true);
        soundList.setEnabled(false);
        // soundList.repaint();
        sound.base.repaint();
    }

    protected void enableAllElements() {
        downloadButton.setDisabledState(false);
        soundList.setEnabled(true);
        //soundList.repaint();
        sound.base.repaint();
    }
    
    private MouseAdapter removeSound() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               
               // soundList.remove(soundList.getSelectedIndex()); 
                //soundList.clearSelection();
                System.out.println("" + soundList.getSelectedValue().toString());
                
                String fileName = soundList.getSelectedValue().toString();
                
                //./src/main/recources/
                File f= new File("./src/main/recources/sound/" + fileName); //
                f.delete();
                
                DefaultListModel model = (DefaultListModel) soundList.getModel();
                model.remove(soundListIndex);
                
                soundList.repaint();
                sound.base.repaint();
            }
        };
        return ma;
    }
    
    private MouseAdapter playSound() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                stopButton.setDisabledState(false); 
                
                sound.base.repaint();
            }
        };
        return ma;
    }
    
    private MouseAdapter stopSound() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
               // stopButton.setDisabledState(false);
                stopButton.setDisabledState(true);
                sound.base.repaint();
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
        sb.sound.base.revalidate();
        sb.sound.base.repaint();
//        sb.colors.base.repaint();
//        sb.setWindowVisibility(true);
    }

}
