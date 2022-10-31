package kr.bizak.cauldron;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;

public class SelectEvent {
    /* Material */
    String dummy = "";

    Reader reader;
    JSONObject jsonObject;
    JSONObject materialObject, essenceObject;

    File dir;
    String path = "src/config/material/";
    File[] file;

    /* Cauldron */
    HashMap<String, Integer> essence = new HashMap<>();
    int impurity = 0;

    public HashMap<String, Integer> getEssence() { return essence; }
    public void setEssence(HashMap<String, Integer> essence) { this.essence = essence; }
    public void clearEssence() { this.essence.clear(); }

    public void SelectEvent(String materialname){
        /* Event */
        dummy = "";
        if (impurity>=100){
            return;
        }
        materialname = materialname.substring(24, materialname.length()-4);
        System.out.println("materialname :: " + materialname);
        dir = new File(path);
        file = dir.listFiles();

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
                    impurity += Integer.valueOf(String.valueOf(materialObject.get("impurity")));
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
