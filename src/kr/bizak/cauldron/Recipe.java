package kr.bizak.cauldron;

import java.util.HashMap;

public class Recipe {
    HashMap<String, Integer> essence;

    public void setEssence(HashMap<String, Integer> essence) { this.essence = essence; }
    public HashMap<String, Integer> getEssence() { return essence; }

    public void Recipe(){
        System.out.println("[System] :: essence.keySet() >> "+essence.keySet());
        String[] essenceKey = new String[essence.size()];
        for (int i = 0; i < essenceKey.length; i++) {

        }










    }






}
