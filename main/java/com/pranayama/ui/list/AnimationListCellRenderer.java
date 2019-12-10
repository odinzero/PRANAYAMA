
package com.pranayama.ui.list;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

public class AnimationListCellRenderer extends JLabel implements ListCellRenderer {
    private static Color HIGHLIGHT_COLOR =  Color.ORANGE; // 0,0,18

   public AnimationListCellRenderer(){
       setOpaque(true);
       setIconTextGap(12);
   }

    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
    {
        AnimationList entry = (AnimationList) value;
        setText(entry.getTitle());
        setIcon(entry.getImage());

        setMinimumSize(new Dimension(80 , 35));
        setPreferredSize(new Dimension(80 , 35));
        
        if(isSelected){
            setBackground(HIGHLIGHT_COLOR);
            setForeground(Color.BLUE);
            setFont(new Font("Monotype Corsiva" , Font.BOLD , 22));
        } else {
            setBackground(Color.GREEN);
            setForeground(Color.BLUE);
            setFont(new Font("Monotype Corsiva" , Font.BOLD , 22));
        }

        return this;
    }

}