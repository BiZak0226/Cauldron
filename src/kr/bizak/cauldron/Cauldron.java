package kr.bizak.cauldron;

import java.util.HashMap;

public class Cauldron {
//    private String fill;
//    private int temperature;
    private HashMap<String, Integer> essence;
//    private int volume;

    public void setEssence(HashMap<String, Integer> essence) {
        this.essence = essence;
    }
    public void putEssence(String key, int value){
        this.essence.put(key, value);
    }

    public HashMap<String, Integer> getEssence() {
        return essence;
    }
}
