package com.pranayama.ui.list;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

public class AnimationListCellRenderer extends JLabel implements ListCellRenderer {

    private static Color HIGHLIGHT_COLOR = Color.ORANGE; // 0,0,18
    private JList list;

    public AnimationListCellRenderer(JList list) {
        this.list = list;
        setOpaque(true);
        setIconTextGap(12);
    }

    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        AnimationList entry = (AnimationList) value;
        setText(entry.getTitle());
        setIcon(entry.getImage());

        setMinimumSize(new Dimension(80, 35));
        setPreferredSize(new Dimension(80, 35));
        
        setFont(new Font("Monotype Corsiva", Font.BOLD, 22));

        if (list.isEnabled()) {
            if (isSelected) {
                setBackground(HIGHLIGHT_COLOR);
                setForeground(Color.BLUE);
            } else {
                setBackground(Color.GREEN);
                setForeground(Color.BLUE);
            }
        } else {
                setBackground(Color.GRAY);
                setForeground(Color.BLUE);
        }

        return this;
    }

}
