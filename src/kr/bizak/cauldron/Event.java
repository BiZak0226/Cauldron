package kr.bizak.cauldron;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;

public class Event {
    /* Material */
    String dummy = "";


    /* Cauldron */
    HashMap<String, Integer> essence = new HashMap<>();
    double impurity = 0.0;

    public HashMap<String, Integer> getEssence() { return essence; }
    public void setEssence(HashMap<String, Integer> essence) { this.essence = essence; }
    public void clearEssence() { this.essence.clear(); }

    public void Event(String materialname){
        /* Event */
        dummy = "";
        if (impurity>=6.5){
            return;
        }
        materialname = materialname.substring(24, materialname.length()-4);

        File dir;
        String path = "src/config/material/";
        File[] file;
        dir = new File(path);
        file = dir.listFiles();

        Reader reader;
        JSONObject jsonObject;
        JSONObject materialObject, essenceObject;
        materialObject = new JSONObject();

        /* Load JSON */
        try {
            for (int i = 0; i < file.length; i++) {
                if (String.valueOf(file[i]).contains(materialname.substring(0,materialname.length()-2))){
                    reader = new FileReader(file[i]);
                    jsonObject = (JSONObject)new JSONParser().parse(reader);
                    Iterator scanKey = jsonObject.keySet().iterator();
                    while (scanKey.hasNext()){
                        dummy = String.valueOf(scanKey.next());
                        materialObject = (JSONObject)jsonObject.get(dummy);

                        if (materialObject.containsValue(materialname+".png")){
                            materialObject = (JSONObject)jsonObject.get(dummy);
                            dummy = String.valueOf(materialObject.get("name"));
                            break;
                        }
                    }
                    essenceObject = (JSONObject) materialObject.get("essence");

                    Iterator essenceKey = essenceObject.keySet().iterator();
                    while (essenceKey.hasNext()){
                        String key = essenceKey.next().toString();
                        int value = Integer.valueOf(String.valueOf(essenceObject.get(key)));
                        if (essence.containsKey(key)){
                            essence.put(key, essence.get(key) + value);
                        }else{
                            essence.put(key, value);
                        }
                    }
                    impurity += Double.valueOf(String.valueOf(materialObject.get("impurity")));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!dummy.contains("empty")){
            System.out.println("[System]:: Input < " + dummy + " > Essence : " + essence + " impurity : " + impurity);
        }
    }
    public String getEvent(){
        if (dummy.contains("empty")){ return ""; }
        return dummy;
    }
}
