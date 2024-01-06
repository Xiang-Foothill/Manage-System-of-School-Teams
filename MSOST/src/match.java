
import java.util.ArrayList;
import java.util.List;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yuxiangliu
 */
public class match {
    private String opn; //name of the opponent team
    private String date; //date of the match
    private boolean win; //whether the school team wins or not
    private int ourScore;
    private int oppScore;
    private String tName; 
    private List<String> present; //members who play in the match
    
    public match(String opn, String date, String tName){
        this.opn=opn;
        this.date=date;
        this.tName=tName;
        present=new ArrayList<>();
    }

    public match(String tName,String opn, String date, boolean win, int ourScore, int oppScore){
        this.tName=tName;
        this.opn=opn;
        this.date=date;
        this.win=win;
        this.ourScore=ourScore;
        this.oppScore=oppScore;
        present=new ArrayList<>();
    }
    
    public void addPresent(String newMem){
        present.add(newMem);
    }
    
    public String getOpn(){
        return opn;
    }
    public String getDate(){
        return date;
    }
    public boolean getWin(){
        return win;
    }
    public String getName(){
        return tName;
    }
    public List<String> getPre(){
        return present;
    }
    public int getOurScore(){
        return ourScore;
    }
    public int getOppScore(){
        return oppScore;
    }
    public void setPre(List<String> list){
        present=list;
    }
    public void setOpn(String opn){
        this.opn=opn;
    }
    public void setDate(String date){
        this.date=date;
    }
    public void setWin(boolean win){
        this.win=win;
    }
    public void setName(String name){
        tName=name;
    }
    public void setScore(int ourScore, int oppScore){
        this.ourScore=ourScore;
        this.oppScore=oppScore;
    }
    
    public String toString(){
        StringBuffer attend=new StringBuffer();
        for(String name: present){
            attend.append(name+",");
        }
        String res=new String();
        if(win){
            res="win";
        }else{
            res="lose";
        }
        return "Name of the opponent team: "+getOpn()+
                "\nDate: "+getDate()+
                "\nmatch result: "+res+
                "\nOur Score:"+getOurScore()+
                "\nOpponent Score: "+getOppScore()+
                "\nMembers who attend the match: "+attend.toString()+"\n";
    }
}
