package kr.bizak.cauldron;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

class Material{
    String name;
    String icon;
    String type;
    HashMap<String, Integer> essence;

    public Material(String name, String icon, String type, HashMap<String, Integer> essence) {
        this.name = name;
        this.icon = icon;
        this.type = type;
        this.essence = essence;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public HashMap<String, Integer> getEssence() {
        return essence;
    }
    public void setEssence(HashMap<String, Integer> essence) { this.essence = essence; }
}

public class MaterialDAO {
    public MaterialDAO(/*JSONObject jsonObject*/){
        Reader reader;
        JSONObject obj;
        JSONObject dummyObject, essenceObject;
        try{
            reader = new FileReader("src/config/material/plant.json");
            obj = (JSONObject)new JSONParser().parse(reader);
            Material[] material = new Material[obj.size()];



            for (int i = 0; i < obj.size(); i++) {
                dummyObject = (JSONObject)obj.get("plant_0"+(i+1));

                essenceObject = (JSONObject)dummyObject.get("essence");
                Iterator iterator = essenceObject.keySet().iterator();

                String keyList = "";
                while (iterator.hasNext()){
                    String b = iterator.next().toString();
                    keyList += b + " ";

                }
                StringTokenizer st = new StringTokenizer(keyList);
                HashMap<String, Integer> hashMap = new HashMap<>();
                while(st.hasMoreTokens()){
                    String key = st.nextToken();
                    String value = String.valueOf( essenceObject.get(key));
//                    System.out.println("[System] :: key : " + key + " / " +value);
                    hashMap.put(key, Integer.valueOf(value));
                }

                material[i] = new Material(String.valueOf(dummyObject.get("name")),
                        String.valueOf(dummyObject.get("icon")), String.valueOf(dummyObject.get("type")), hashMap);

            }
            for (int i = 0; i < material.length; i++) {
                System.out.println("[System] :: " + material[i].getEssence());
            }

        }catch(Exception e){
            e.printStackTrace();
        }



    }

}