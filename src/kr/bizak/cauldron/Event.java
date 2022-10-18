package kr.bizak.cauldron;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;

public class Event /*extends GUI*/ implements ActionListener {
    /* Material */


    /* Cauldron */
    HashMap<String, Integer> essence = new HashMap<>();

    public HashMap<String, Integer> getEssence() { return essence; }
    public void setEssence(HashMap<String, Integer> essence) { this.essence = essence; }
    public void clearEssence() { this.essence.clear(); }


    /* Event */
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        /* Select plant Button */
        JButton button = (JButton) ae.getSource();

        String materialname = button.getIcon().toString();
        materialname = materialname.substring(24, materialname.length()-4);

        System.out.println(materialname);
        System.out.println(materialname.substring(0,materialname.length()-2)
        );
        File dir;
        String path = "src/config/material/";
        File[] file;
        dir = new File(path);
        file = dir.listFiles();

        Reader reader = null;
        JSONObject jsonObject = null;
        JSONObject materialObject, essenceObject;

        /* Load JSON */
        try {
            for (int i = 0; i < file.length; i++) {
                System.out.println(file[i]);
                System.out.println(String.valueOf(file[i]).contains(materialname.substring(0,materialname.length()-2)));


                if (String.valueOf(file[i]).contains(materialname.substring(0,materialname.length()-2))){
                    reader = new FileReader(file[i]);
                    jsonObject = (JSONObject)new JSONParser().parse(reader);
                    materialObject = (JSONObject)jsonObject.get(materialname);
                    essenceObject = (JSONObject) materialObject.get("essence");
                    Iterator essenceKey = essenceObject.keySet().iterator();

                    while (essenceKey.hasNext()){
                        String key = essenceKey.next().toString();
                        int value = (int) essenceObject.get(key);
                        System.out.println("[SYSTEM:: Event Compoment Essence /" + essenceKey);
                        if (essence.containsKey(key)){

                        }else{
                            int e_int = (int) essenceObject.get(key);
//                    essence.put(e_int);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
