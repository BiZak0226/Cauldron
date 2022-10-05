package kr.bizak.cauldron;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Event /*extends GUI*/ implements ActionListener {
    /* Material */

    /* Cauldron */
    HashMap<String, Integer> essence = new HashMap<>();

    public HashMap<String, Integer> getEssence() {
        return essence;
    }

    /* Event */
    @Override
    public void actionPerformed(ActionEvent e) {

        /* Select Herb Button */
        JButton button = (JButton) e.getSource();

            String herbname = button.getIcon().toString();
            herbname = herbname.substring(20, herbname.length()-4);

            if (herbname.equals("herb_1")){
                if (essence.containsKey("healing")){
                    essence.put("healing", essence.get("healing")+6);
                }else{
                    essence.put("healing", 6);
                }
            }else if (herbname.equals("herb_2")){
                if (essence.containsKey("healing")){
                    essence.put("healing", essence.get("healing")+3);
                }else{
                    essence.put("healing", 3);
                }
                if (essence.containsKey("fire")){
                    essence.put("fire", essence.get("fire")+6);
                }else{
                    essence.put("fire", 6);
                }
            }else if (herbname.equals("herb_3")){
                if (essence.containsKey("healing")){
                    essence.put("healing", essence.get("healing")+14);
                }else{
                    essence.put("healing", 14);
                }
            }else if(herbname.equals("herb_4")) {
                if (essence.containsKey("healing")) {
                    essence.put("healing", essence.get("healing") + 21);
                } else {
                    essence.put("healing", 21);
                }

                if (essence.containsKey("poison")) {
                    essence.put("poison", essence.get("poison") + 8);
                } else {
                    essence.put("poison", 8);
                }
            }else if (herbname.equals("herb_5")){
                if (essence.containsKey("healing")){
                    essence.put("healing", essence.get("healing")+4);
                }else{
                    essence.put("healing", 4);
                }
                if (essence.containsKey("fire")){
                    essence.put("fire", essence.get("fire")+15);
                }else{
                    essence.put("fire", 15);
                }
            }
            System.out.println("[Event]:: input "+ herbname + " size : " + essence.size() +" / Essence -> " + essence);


    }
}
