package kr.bizak.cauldron;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Event /*extends GUI*/ implements ActionListener {
    /* Material */


    /* Cauldron */
    HashMap<String, Integer> essence = new HashMap<>();

    public HashMap<String, Integer> getEssence() { return essence; }
    public void setEssence(HashMap<String, Integer> essence) { this.essence = essence; }
    public void clearEssence() { this.essence.clear(); }


    /* Event */
    @Override
    public void actionPerformed(ActionEvent e) {

        /* Select Herb Button */
        JButton button = (JButton) e.getSource();

            String herbname = button.getIcon().toString();
            herbname = herbname.substring(24, herbname.length()-4);

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
            }else if (herbname.equals("herb_6")){
                if (essence.containsKey("healing")){
                    essence.put("healing", essence.get("healing")+2);
                }else{
                    essence.put("healing", 2);
                }
                if (essence.containsKey("sugar")){
                    essence.put("sugar", essence.get("sugar")+14);
                }else{
                    essence.put("sugar", 14);
                }
            }else if (herbname.equals("herb_7")){
                if (essence.containsKey("healing")){
                    essence.put("healing", essence.get("healing")+7);
                }else{
                    essence.put("healing", 7);
                }
                if (essence.containsKey("poison")){
                    essence.put("poison", essence.get("poison")-15);
                }else{
                    essence.put("poison", -13);
                }
            }
            if (!herbname.contains("empty")){
                System.out.println("[Event]:: input "+ herbname + " size : " + essence.size() +" / Essence -> " + essence);
            }

    }
}
