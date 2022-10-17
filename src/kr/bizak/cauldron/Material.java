package kr.bizak.cauldron;

import java.util.HashMap;

class Material {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, Integer> getEssence() {
        return essence;
    }

    public void setEssence(HashMap<String, Integer> essence) {
        this.essence = essence;
    }

    @Override
    public String toString() {
        return "\n" +
                "Material{ \n" +
                "  name='" + name + '\n' +
                "  icon='" + icon + '\n' +
                "  type='" + type + '\n' +
                "  essence=" + essence +
                "\n}";
    }
}
