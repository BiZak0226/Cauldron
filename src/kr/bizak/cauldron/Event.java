package kr.bizak.cauldron;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Event /*extends GUI*/ implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        /* Select Herb Button */
        JButton button = (JButton) e.getSource();
        String herbname = button.getIcon().toString();
        herbname = herbname.substring(20, herbname.length()-4);
        System.out.println("[EVENT] Click Event :: " + herbname);

        /* Cauldron Button */

    }
}
