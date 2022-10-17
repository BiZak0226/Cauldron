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

        /* Select plant Button */
        JButton button = (JButton) e.getSource();

            String plantname = button.getIcon().toString();
            plantname = plantname.substring(24, plantname.length()-4);

            if (plantname.equals("plant_1")){
                if (essence.containsKey("healing")){
                    essence.put("healing", essence.get("healing")+6);
                }else{
                    essence.put("healing", 6);
                }
            }else if (plantname.equals("plant_2")){
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
            }else if (plantname.equals("plant_3")){
                if (essence.containsKey("healing")){
                    essence.put("healing", essence.get("healing")+14);
                }else{
                    essence.put("healing", 14);
                }
            }else if(plantname.equals("plant_4")) {
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
            }else if (plantname.equals("plant_5")){
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
            }else if (plantname.equals("plant_6")){
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
            }else if (plantname.equals("plant_7")){
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
            if (!plantname.contains("empty")){
                System.out.println("[Event]:: input "+ plantname + " size : " + essence.size() +" / Essence -> " + essence);
            }

    }
}
