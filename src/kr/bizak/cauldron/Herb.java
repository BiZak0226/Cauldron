package kr.bizak.cauldron;

import java.util.HashMap;

public class Herb {
    private String name;
    private HashMap<String, Integer> essence;

    public Herb(String name, HashMap<String, Integer> essence){
        this.name = name;
        this.essence = essence;
    }

    public String getName() { return name; }
    public HashMap<String, Integer> getEssence() { return essence; }
}
