package kr.bizak.cauldron;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;


public class MaterialJSONObject {

    public MaterialJSONObject(/*JSONObject jsonObject*/){
        /* Search File List */
        File dir;
        String path = "src/config/material/";
        File[] file;
        dir = new File(path);
        file = dir.listFiles();

        for (int i = 0; i < file.length; i++) {
            String dummyString = String.valueOf(file[i]);
            dummyString = dummyString.substring(path.length());

//            System.out.println("\n[SYSTEM] :: READ FILE / "+dummyString);

            Reader reader;
            JSONObject obj;
            JSONObject materialObject, essenceObject;
            try{
                /* Convert JSON Data */
                reader = new FileReader(file[i]);
                obj = (JSONObject)new JSONParser().parse(reader);
                Material[] material = new Material[obj.size()];

                /* get material keylist */
                Iterator materialkey = obj.keySet().iterator();
                String keyList = "";
                int count = 0;
                while (materialkey.hasNext()){
                    materialObject = (JSONObject)obj.get(materialkey.next().toString());

//                System.out.println(materialObject);

                    Iterator key = materialObject.keySet().iterator();

                    String name = String.valueOf(materialObject.get("name"));
                    String icon = String.valueOf(materialObject.get("icon"));
                    String type = String.valueOf(materialObject.get("type"));

                    essenceObject = (JSONObject) materialObject.get("essence");
                    Iterator iterator = essenceObject.keySet().iterator();

                    keyList = "";
                    while (iterator.hasNext()){
                        String b = iterator.next().toString();
                        keyList += b + " ";

                    }
                    StringTokenizer st = new StringTokenizer(keyList);
                    HashMap<String, Integer> hashMap = new HashMap<>();
                    while(st.hasMoreTokens()){
                        String key_ = st.nextToken();
                        String value = String.valueOf( essenceObject.get(key_));
                        hashMap.put(key_, Integer.valueOf(value));
                    }
                    material[count] = new Material(name, icon, type, hashMap);

                    count++;
                }
            /* View Object Data */
            for (int j = 0; j < material.length; j++) {
//                System.out.println("[System] :: " + material[j].toString());
//                System.out.println();

            }

            }catch(Exception e){
                e.printStackTrace();
            }


        }


    }

}