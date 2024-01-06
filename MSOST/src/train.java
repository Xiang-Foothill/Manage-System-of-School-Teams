
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
public class train {
    private String date; //the date of the train
    private int time; //time in hours
    private String tName;
    private List<String> memDue;//memebers who should arrive to train
    private List<String> memLate;//members who are late to the train

    public train(String date, int time, String tName, List<member> members){
        this.date=date;
        this.time=time;
        this.tName=tName;
        memDue=new ArrayList<>();
        memLate=new ArrayList<>();
        for(int i=0;i<members.size();i++){
            if(members.get(i).getAvailable()){
                memDue.add(members.get(i).getName());
            }
        }
    }
    
    public train(String date, int time, String tName){
        this.date=date;
        this.time=time;
        this.tName=tName;
        memDue=new ArrayList<>();
        memLate=new ArrayList<>();
    }
    
    public String getDate(){
        return date;
    }
    public int getTime(){
        return time;
    }
    public String getName(){
        return tName;
    }
    public List<String> getLate(){
        return memLate;
    }
    public List<String> getDue(){
        return memDue;
    }
    public void setTime(int newT){
        time = newT;
    }
    public void setDate(String newD){
        date=newD;
    }
    public void setLate(List<String> late){
        memLate=late;
    }
    public void setDue(List<String> due){
        memDue=due;
    }
    public void addLate(String late){
        memLate.add(late);
    }
    public void addDue(String due){
        memDue.add(due);
    }
    
    public String lateToCSV(){
        StringBuffer late=new StringBuffer();
        for(int i=0;i<memLate.size();i++){
            late.append(memLate.get(i)+",");
        }
        return late.toString();
    }
    
    public String dueToCSV(){
        StringBuffer due=new StringBuffer();
        for(int i=0;i<memDue.size();i++){
            due.append(memDue.get(i)+",");
        }
        return due.toString();
    }
    
    public String toString(){
        StringBuffer late=new StringBuffer();
        StringBuffer due=new StringBuffer();
        for(int i=0;i<memDue.size();i++){
            due.append(memDue.get(i)+",");
        }
        for(int i=0;i<memLate.size();i++){
            late.append(memLate.get(i)+",");
        }
        return "Date: "+getDate()+
                "\nTime: "+getTime()+" hours"+
                "\nMembers who should participate in the train: "+due.toString()+
                "\nMembers who should join but are absent: "+late.toString()+"\n";
    }
    
}
