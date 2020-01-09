package com.pranayama.complex;

import com.pranayama.PRANAYAMA;
import com.pranayama.menu.ui.separator;
import com.pranayama.pattern.patternWindow;
import com.pranayama.ui.buttons;
import com.pranayama.ui.list.AnimationList;
import com.pranayama.ui.list.AnimationListCellRenderer;
import com.pranayama.ui.list.LightScrollPane;
import com.pranayama.ui.list.TwoSideList;
import com.pranayama.util.Listeners;
import com.pranayama.util.utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ComplexCreate {

    public patternWindow createComplexWindow;
    PRANAYAMA pranaMain;
    utils.buttonsWindow nextButton, applyButton; // 1
    utils.buttonsWindow backButton, cancelButton, changeSettingsButton, generateSettingsButton, saveToFileButton; // 2 
    JTextField complexName, orderField;
    String[] arr1 = {"Bear", "Volf", "Snake"};
    String[] arr2 = {"Falcon"};
    String[] arr3 = {};
    TwoSideList twolist; // 1
    JList newComplex; // 2
    LightScrollPane newComplexScroll;
    separator s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
    separator s11, s12, s13, s14, s15; // 2
    buttons complexCreateBut, selBreathBut, orderBut, newComplexBut, complexNameBut, settingsBut;
    Font font1 = new Font("Book Antiqua", Font.PLAIN, 20);

    public ComplexCreate() {
        init1();
        createComplexWindow.base.revalidate();
        createComplexWindow.base.repaint();
    }

    private void init1() {
        createComplexWindow = new patternWindow(null, "Create complex", true, 0, true, true, 0, 0, 400, 530); // 540 480
        createComplexWindow.setWindowVisibility(true);
        Font font = new Font("Monotype Corsiva", Font.BOLD, 25);
// 1 ========================= BUTTONS  'Apply','Cancel' =========================
        // add button 'Apply'     
        nextButton = new utils.buttonsWindow("Next >> ", 110, 35, 12, 23);
        nextButton.addMouseListener(next());
        nextButton.setDisabledState(true);
        nextButton.setBounds(80, 470, 115, 40);

        // Action 0 <- it means close window
//        createComplexWindow.addButtonsWindow2("Cancel", 210, 470, 85, 35, 7, 23, 90, 40)
//                .addMouseListener(cancel());
        cancelButton = new utils.buttonsWindow("Cancel", 95, 35, 12, 23);
        cancelButton.addMouseListener(cancel());
        cancelButton.setDisabledState(false);
        cancelButton.setBounds(210, 470, 100, 40);

        createComplexWindow.base.add(nextButton);
        createComplexWindow.base.add(cancelButton);
// 1 ============================ Complex create ===================================                
        s1 = new separator();
        s1.setBounds(20, 40, 95, 10);

        Rectangle rect1 = new Rectangle(5, 2, 150, 30);
        complexCreateBut = new buttons(" Complex name ", rect1);
        complexCreateBut.setBounds(110, 25, 150, 30);

        s2 = new separator();
        s2.setBounds(265, 40, 113, 10);

        complexName = new JTextField(6);
        complexName.setText("dd");
        complexName.setName("complexName");
        complexName.setForeground(new Color(184, 109, 255));
        complexName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        complexName.setFont(font1);
        complexName.setBounds(30, 65, 335, 30);
        // listeners
        complexName.getDocument().addDocumentListener(textControl(complexName, nextButton));
        complexName.addCaretListener(Listeners.limitName(complexName, 45));
        //FOCUS listener
        //complexName.addFocusListener(tfocus);
// 1 ============================ Select breath ===================================                
        s3 = new separator();
        s3.setBounds(20, 110, 95, 10);

        Rectangle rect2 = new Rectangle(5, 2, 150, 30);
        selBreathBut = new buttons(" Select breath ", rect2);
        selBreathBut.setBounds(110, 95, 150, 30);

        s4 = new separator();
        s4.setBounds(265, 110, 113, 10);

        twolist = new TwoSideList(
                arr1, arr2, "./src/main/recources/img/br6t.png",
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION,
                25, 135, 350, 200);
        twolist.rightSideList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (twolist.rightSideList.getModel().getSize() == 0 && complexName.getText().equals("")) {
                    nextButton.setDisabledState(true);
                } else if (twolist.rightSideList.getModel().getSize() != 0 && complexName.getText().equals("")) {
                    nextButton.setDisabledState(true);
                } else if (twolist.rightSideList.getModel().getSize() == 0 && !complexName.getText().equals("")) {
                    nextButton.setDisabledState(true);
                } else {
                    nextButton.setDisabledState(false);
                    System.out.println("s4:");
                }

                createComplexWindow.base.repaint();
            }
        });
// 1 ==================== layout components ========================================        
        createComplexWindow.base.add(s1);
        createComplexWindow.base.add(complexCreateBut);
        createComplexWindow.base.add(s2);

        createComplexWindow.base.add(complexName);

        createComplexWindow.base.add(s3);
        createComplexWindow.base.add(selBreathBut);
        createComplexWindow.base.add(s4);

        createComplexWindow.base.add(twolist);
    }

    private void init2() {
// 2 ========================= BUTTONS  ' << Back ' =========================
        // add button 'back'      
        backButton = new utils.buttonsWindow("<< Back ", 110, 35, 12, 23);
        backButton.addMouseListener(back());
        backButton.setDisabledState(false);
        backButton.setVisible(false);
        backButton.setBounds(40, 470, 115, 40);
        // add button 'save'
        saveToFileButton = new utils.buttonsWindow("Save", 70, 35, 12, 23);
        saveToFileButton.addMouseListener(back());
        saveToFileButton.setDisabledState(false);
        saveToFileButton.setVisible(false);
        saveToFileButton.setBounds(170, 470, 75, 40);
        
        cancelButton.setBounds(260, 470, 100, 40);
// 2 =========================== set order ======================================== 
        s7 = new separator();
        s7.setVisible(false);
        s7.setBounds(20, 40, 115, 10);

        Rectangle rect3 = new Rectangle(5, 2, 150, 30);
        orderBut = new buttons(" set order ", rect3);
        orderBut.setVisible(false);
        orderBut.setBounds(110, 25, 150, 30);

        s8 = new separator();
        s8.setVisible(false);
        s8.setBounds(245, 40, 133, 10);

        orderField = new JTextField(6);
        orderField.setVisible(false);
        orderField.setName("order");
        orderField.setForeground(new Color(184, 109, 255));
        orderField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        orderField.setFont(font1);
        orderField.setBounds(30, 65, 335, 30);
        // listeners
//        orderField.getDocument().addDocumentListener(textControl(complexName, nextButton));
//        orderField.addCaretListener(Listeners.limitName(complexName, 45));  
// 2 =========================== new complex ===================================
        s9 = new separator();
        s9.setVisible(false);
        s9.setBounds(20, 110, 115, 10);

        Rectangle rect4 = new Rectangle(5, 2, 150, 30);
        newComplexBut = new buttons(" Complex ", rect4);
        newComplexBut.setVisible(false);
        newComplexBut.setBounds(110, 95, 150, 30);

        s10 = new separator();
        s10.setVisible(false);
        s10.setBounds(245, 110, 133, 10);

        complexNameBut = new buttons("Name: " + complexName.getText() + " ", rect4);
        complexNameBut.setVisible(false);
        complexNameBut.setBounds(10, 125, 150, 30);

        DefaultListModel model = (DefaultListModel) twolist.rightSideList.getModel();
        arr3 = new String[twolist.rightSideList.getModel().getSize()];
        for (int i = 0; i < model.getSize(); i++) {
            arr3[i] = ((AnimationList) model.get(i)).getTitle();
            //System.out.println("list :" + arr3[i]);
        }
        newComplex = createList(arr3, "./src/main/recources/img/br6t.png");

        newComplexScroll = new LightScrollPane(newComplex);
        newComplexScroll.setBounds(25, 165, 350, 175);
// 2 ====================== complex settings ===================================
        s11 = new separator();
        s11.setVisible(false);
        s11.setBounds(20, 360, 115, 10);

        Rectangle rect6 = new Rectangle(5, 2, 150, 30);
        settingsBut = new buttons(" Settings ", rect6);
        settingsBut.setVisible(false);
        settingsBut.setBounds(110, 345, 150, 30);

        s12 = new separator();
        s12.setVisible(false);
        s12.setBounds(245, 360, 133, 10);

        changeSettingsButton = new utils.buttonsWindow("Change", 100, 35, 12, 23);
        changeSettingsButton.setDisabledState(true);
        changeSettingsButton.addMouseListener(changeSettings());
        changeSettingsButton.setBounds(45, 385, 105, 40);

        generateSettingsButton = new utils.buttonsWindow("Generate", 110, 35, 12, 23);
        generateSettingsButton.setDisabledState(true);
        generateSettingsButton.addMouseListener(saveToFile());
        generateSettingsButton.setBounds(255, 385, 115, 40);

        s13 = new separator();
        s13.setBounds(20, 440, 160, 10);
        s14 = new separator();
        s14.setBounds(170, 440, 160, 10);
        s15 = new separator();
        s15.setBounds(320, 440, 58, 10);

// 2 ==================== layout components ====================================
        createComplexWindow.base.add(s7);
        createComplexWindow.base.add(orderBut);
        createComplexWindow.base.add(s8);

        createComplexWindow.base.add(orderField);

        createComplexWindow.base.add(s9);
        createComplexWindow.base.add(newComplexBut);
        createComplexWindow.base.add(s10);

        createComplexWindow.base.add(complexNameBut);

        createComplexWindow.base.add(newComplexScroll);

        createComplexWindow.base.add(s11);
        createComplexWindow.base.add(settingsBut);
        createComplexWindow.base.add(s12);
        
        createComplexWindow.base.add(changeSettingsButton);
        createComplexWindow.base.add(generateSettingsButton);

        createComplexWindow.base.add(s13);
        createComplexWindow.base.add(s14);
        createComplexWindow.base.add(s15);

        createComplexWindow.base.add(backButton);
        createComplexWindow.base.add(saveToFileButton);
    }

    private void displayElements1(boolean bool) {
        s1.setVisible(bool);
        complexCreateBut.setVisible(bool);
        s2.setVisible(bool);

        complexName.setVisible(bool);

        s3.setVisible(bool);
        selBreathBut.setVisible(bool);
        s4.setVisible(bool);

        twolist.setVisible(bool);

        nextButton.setVisible(bool);
    }

    private void displayElements2(boolean bool) {
        s7.setVisible(bool);
        orderBut.setVisible(bool);
        s8.setVisible(bool);

        orderField.setVisible(bool);

        s9.setVisible(bool);
        newComplexBut.setVisible(bool);
        s10.setVisible(bool);

        complexNameBut.setVisible(bool);

        newComplexScroll.setVisible(bool);
        
        s11.setVisible(bool); 
        settingsBut.setVisible(bool);
        s12.setVisible(bool);
        
        changeSettingsButton.setVisible(bool);
        generateSettingsButton.setVisible(bool);

        s13.setVisible(bool);
        s14.setVisible(bool);
        s15.setVisible(bool);

        backButton.setVisible(bool);
        saveToFileButton.setVisible(bool);
    }

    private JList createList(String[] text, String imagePath) { // final JList list

        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model); // al

        AnimationList[] al = new AnimationList[text.length];
        for (int i = 0; i < text.length; i++) {
            String f = text[i];
            al[i] = new AnimationList(f, imagePath);
            model.addElement(al[i]);
        }

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new AnimationListCellRenderer(list));
        list.setBackground(Color.LIGHT_GRAY);
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {

                if (list.getSelectedIndex() != -1) {
                    changeSettingsButton.setDisabledState(false);
                    generateSettingsButton.setDisabledState(false);
                    System.out.println("!-1:");
                } else {
                    changeSettingsButton.setDisabledState(true);
                    generateSettingsButton.setDisabledState(true);
                    System.out.println("> -1:");
                }
                createComplexWindow.base.repaint();
                // System.out.println("JList:" + soundListIndex);
            }
        });
        return list;
    }

    private DocumentListener textControl(final JTextField tfield,
            final utils.buttonsWindow next) {

        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //  System.out.println("changedUpdate: ");
            }

            public void changed() {

                if (twolist.rightSideList.getModel().getSize() == 0 && tfield.getText().equals("")) {
                    next.setDisabledState(true);
                } else if (twolist.rightSideList.getModel().getSize() != 0 && tfield.getText().equals("")) {
                    next.setDisabledState(true);
                } else if (twolist.rightSideList.getModel().getSize() == 0 && !tfield.getText().equals("")) {
                    next.setDisabledState(true);
                } else {
                    next.setDisabledState(false);
                    System.out.println("t4:");
                }

                createComplexWindow.base.repaint();
            }
        };
        return dl;
    }

    private MouseAdapter changeSettings() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        };
        return ma;
    }

    private MouseAdapter saveToFile() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        };
        return ma;
    }

    private MouseAdapter next() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                displayElements1(false);
                init2();
                displayElements2(true);
                // hide 'createComplexWindow' window
                //createComplexWindow.setWindowVisibility(false);
                // Show SETTINGS window
                //pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
            }
        };
        return ma;
    }

    private MouseAdapter back() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                displayElements1(true);
                // return location 'cancelButton' to init position
                cancelButton.setBounds(210, 470, 100, 40);
                displayElements2(false);
                // hide 'createComplexWindow' window
                //createComplexWindow.setWindowVisibility(false);
                // Show SETTINGS window
                //pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
            }
        };
        return ma;
    }

    private MouseAdapter cancel() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // hide createComplexWindow window
                createComplexWindow.setWindowVisibility(false);
                // Show SETTINGS window
                //pranaMain.mainMenu.Breath.settingsBreath.settings.setWindowVisibility(true);
                System.exit(0);
            }
        };
        return ma;
    }

    public static void main(String[] args) {

        new ComplexCreate();
    }
}
