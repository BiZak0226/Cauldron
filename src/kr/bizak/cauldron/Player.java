package kr.bizak.cauldron;

public class Player {
    private String displayname;
    private int credits;


    public Player(String displayname){
        this.displayname = displayname;
        this.credits = 1000;
    }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public String getDisplayname() { return displayname; }
    public void setDisplayname(String displayname) { this.displayname = displayname; }
}
