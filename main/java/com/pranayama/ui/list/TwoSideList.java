package com.pranayama.ui.list;

import com.pranayama.pattern.patternWindow;
import com.pranayama.ui.buttons;
import static com.pranayama.util.utils2.listFilesForFolder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TwoSideList extends JComponent {

    buttons toRightButton;
    buttons toLeftButton;
    String imagePath;

    public TwoSideList(String folderPath1,  String folderPath2, String imagePath, int typeSelection,
             int x, int y, int w, int h) {

        this.imagePath = imagePath;
        
        JList leftSideList = createFileList(folderPath1, this.imagePath, typeSelection);
        JList rightSideList = createFileList(folderPath2, this.imagePath, typeSelection);

        leftSideList.addListSelectionListener(leftListener(leftSideList));
        rightSideList.addListSelectionListener(rightListener(rightSideList));

        LightScrollPane leftScroll = new LightScrollPane(leftSideList);
        LightScrollPane rightScroll = new LightScrollPane(rightSideList);

//        this.setMinimumSize(new Dimension(300,200));
//        this.setPreferredSize(new Dimension(300,200));
//        this.setMaximumSize(new Dimension(300,200));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.setBounds(x, y, w, h);

        this.add(leftScroll);
        this.add(createButtonPanel(leftSideList, rightSideList));
        this.add(rightScroll);
    }

    public TwoSideList(String[] text1, String[] text2, String filePath, int typeSelection,
              int x, int y, int w, int h) {
        
        this.imagePath = filePath;

        JList leftSideList = createList(text1, this.imagePath, typeSelection);
        JList rightSideList = createList(text2, this.imagePath, typeSelection);

        leftSideList.addListSelectionListener(leftListener(leftSideList));
        rightSideList.addListSelectionListener(rightListener(rightSideList));

        LightScrollPane leftScroll = new LightScrollPane(leftSideList);
        LightScrollPane rightScroll = new LightScrollPane(rightSideList);

//        this.setMinimumSize(new Dimension(300,200));
//        this.setPreferredSize(new Dimension(300,200));
//        this.setMaximumSize(new Dimension(300,200));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.setBounds(x, y, w, h);

        this.add(leftScroll);
        this.add(createButtonPanel(leftSideList, rightSideList));
        this.add(rightScroll);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    private JPanel createButtonPanel(JList list1, JList list2) {
        toRightButton = new buttons(3); // button for show second content help menu
        toRightButton.setEnabled(false);
        toRightButton.addMouseListener(changeList(list1, list2));
//        toRightButton.setBounds(0, 25, 24, 24);
        toRightButton.setVisible(true);

        toLeftButton = new buttons(2); // button for show first content help menu
        toLeftButton.setEnabled(false);
        toLeftButton.addMouseListener(changeList(list2, list1));
//        toLeftButton.setBounds(0, 0, 24, 24);
        toLeftButton.setVisible(true);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setMinimumSize(new Dimension(30, 50));
        buttonPanel.setPreferredSize(new Dimension(30, 50));
        buttonPanel.setMaximumSize(new Dimension(30, 50));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(toRightButton);
        buttonPanel.add(toLeftButton);

        return buttonPanel;
    }

    private JList createFileList(String folderPath, String imagePath, int typeSelection) { // final JList list

        final File folder = new File(folderPath);
        String[] files = listFilesForFolder(folder);

        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model); // al

        AnimationList[] al = new AnimationList[files.length];
        for (int i = 0; i < files.length; i++) {
            String f = files[i];
            al[i] = new AnimationList(f, imagePath);
            model.addElement(al[i]);
        }

        list.setSelectionMode(typeSelection);
        list.setCellRenderer(new AnimationListCellRenderer(list));
        list.setBackground(Color.LIGHT_GRAY);
        return list;
    }

    private JList createList(String[] text, String imagePath, int typeSelection) { // final JList list

        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model); // al

        AnimationList[] al = new AnimationList[text.length];
        for (int i = 0; i < text.length; i++) {
            String f = text[i];
            al[i] = new AnimationList(f, imagePath);
            model.addElement(al[i]);
        }

        list.setSelectionMode(typeSelection);
        list.setCellRenderer(new AnimationListCellRenderer(list));
        list.setBackground(Color.LIGHT_GRAY);
        return list;
    }

    private ListSelectionListener leftListener(JList list) {

        ListSelectionListener listener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (list.getSelectedIndex() != -1) {
                    toRightButton.setEnabled(true);
                } else {
                    toRightButton.setEnabled(false);
                }
                repaint();
            }
        };
        return listener;
    }

    private ListSelectionListener rightListener(JList list) {

        ListSelectionListener listener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (list.getSelectedIndex() != -1) {
                    toLeftButton.setEnabled(true);
                } else {
                    toLeftButton.setEnabled(false);
                }
                repaint();
            }
        };
        return listener;
    }

    private MouseAdapter changeList(JList list1, JList list2) {
        MouseAdapter ma;
        ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                switch (list1.getSelectionMode()) {
                    default:
                        break;
                    // int SINGLE_SELECTION = 0;
                    case 0:
                        if (list1.getSelectedIndex() != -1) {

                            DefaultListModel model1 = (DefaultListModel) list1.getModel();
                            DefaultListModel model2 = (DefaultListModel) list2.getModel();

                            AnimationList al = new AnimationList(((AnimationList) list1.getSelectedValue()).getTitle(),
                                    getImagePath() );
                            // remove from list1
                            model1.remove(list1.getSelectedIndex());
                            // add new element to list2
                            model2.addElement(al);
                            //  set selected state replaced list element
                            list2.setSelectedValue(al, true);
                        }
                        break;
                    // int SINGLE_INTERVAL_SELECTION = 1;    
                    case 1:
                    // int MULTIPLE_INTERVAL_SELECTION = 2;    
                    case 2:
                        int[] indices = list1.getSelectedIndices();
                        List objects = list1.getSelectedValuesList();

                        if (indices.length != 0) {

                            DefaultListModel model1 = (DefaultListModel) list1.getModel();
                            DefaultListModel model2 = (DefaultListModel) list2.getModel();

                            int count = list1.getSelectedIndices().length;
                            for (int i = 0; i < count; i++) {
                                model1.removeElementAt(list1.getSelectedIndex());
                            }

                            int[] selArr2 = new int[indices.length];
                            int i = -1;
                            for (Object obj : objects) {
                                AnimationList al = new AnimationList(((AnimationList) obj).getTitle(),
                                        "./src/main/recources/img/audio4.png");
                                // add new element to list2
                                model2.addElement(al);
                                i++;
                                selArr2[i] = model2.indexOf(al);
                                // set selected state replaced list element
                                // list2.setSelectedValue(al, true);
                            }

                            list2.setSelectedIndices(selArr2);
                        }
                        System.out.println("multiple");
                        break;
                }
            }
        };
        return ma;
    }

    public static void main(String[] args) {

        patternWindow w = new patternWindow(null, "Create complex", true, 0, true, true, 0, 0, 400, 530); // 540 480

//        TwoSideList twolist = new TwoSideList("./src/main/recources/sound", "./src/main/recources/sound",
//                "./src/main/recources/img/audio4.png",
//                 ListSelectionModel.SINGLE_SELECTION,
//                25, 100, 300, 200);

        String[] arr1 = {"Java1", "PHP1", "Ruby1", "C++1", "Rust1", "Perl1"};
        String[] arr2 = {"Java2", "PHP2", "Ruby2", "C++2", "Rust2", "Perl2"};
        TwoSideList twolist = new TwoSideList(
                arr1, arr2, "./src/main/recources/img/audio4.png",
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION,
                25, 100, 350, 200);

        w.base.add(twolist);

        w.setWindowVisibility(true);
    }
}
