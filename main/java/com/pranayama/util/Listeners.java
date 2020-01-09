

package com.pranayama.util;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class Listeners {
    
    
    public static CaretListener limitName(final JTextField tfield, final int limit) {

        CaretListener cl = new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                limitation(e.getDot());
            }

            protected void limitation(final int dot) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (dot > limit) {
                            tfield.setCaretPosition(0);
                            String t = tfield.getText().substring(0, limit);
                            tfield.setText("");
                            tfield.setText(t);
                        }
                    }
                });
            }
        };
        return cl;
    }
}
