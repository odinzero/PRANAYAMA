package com.pranayama.ui.list;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class test  extends JFrame{

    JList choiceAnimation;
    //JScrollPane scroll;
    AnimationList st[] = {new AnimationList("Rolling", "src/Icons_Cursors/N_RESIZE.png"),
        new AnimationList("Text(type1)", ""),
        new AnimationList("Text(type2)", ""),
        new AnimationList("Text(type3)", "src/Icons_Cursors/textCursor_a3/textCursor.png"),
        new AnimationList("Text(type4)", "src/Icons_Cursors/textCursor_a4/textCursor.png"),
        new AnimationList("Text(type5)", "src/Icons_Cursors/textCursor_a5/textCursor.png"),};

    test() {
        choiceAnimation = new JList(st);
//        choiceAnimation.setCellRenderer(new AnimationListCellRenderer());
        
//        Border in = BorderFactory.createEmptyBorder(2,2,2,2);
//        Border out = BorderFactory.createBevelBorder(BevelBorder.RAISED);
//        choiceAnimation.setBorder(BorderFactory.createCompoundBorder(out, in)); 
//        choiceAnimation.addListSelectionListener(animation);  /// only for check Rolling( first item )
//        choiceAnimation.addListSelectionListener(new aniCursors());
        
          LightScrollPane  scroll = new LightScrollPane(choiceAnimation );
          
          scroll.setBounds(50, 50, 200, 100);
          
          setSize(200, 200);
          setPreferredSize(new Dimension(400,400)); 
          setMinimumSize(new Dimension(400,400)); 
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLayout(null); 
          add(scroll);
          pack();
          setVisible(true); 
          
    }

    public static void main(String[] args) {
 
         new test();
    }
}
