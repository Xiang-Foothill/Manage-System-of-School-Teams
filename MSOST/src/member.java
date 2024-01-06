/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yuxiangliu
 */
public class member {
    private int grade;
    private int height; //height in centimeters
    private int weight; //weight in kilograms
    private String name;
    private String team;
    private String gender;
    private boolean available=true;//do java have the time count function?
    private int AT=0;//times of being absent

    public member(int grade, String name, int height, int weight, String gender, int AT, boolean available){
        this.grade=grade;
        this.name=name;
        this.height=height;
        this.weight=weight;
        this.gender=gender;
        this.AT=AT;
        this.available=available;
    }
    public int getGrade(){
        return grade;
    }
    public String getName(){
        return name;
    }
    public String getSports(){
        return team;
    }
    public int getHeight(){
        return height;
    }
    public int getWeight(){
        return weight;
    }
    public String getGender(){
        return gender;
    }
    public boolean getAvailable(){
        return available;
    }
    public int getAT(){
        return AT;
    }
    public String getTeam(){
        return team;
    }
    public void setGrade(int newG){
        grade=newG;
    }
    public void setHeight(int newH){
        height=newH;
    }
    public void setWeight(int newW){
        weight=newW;
    }
    public void setName(String newN){
        name=newN;
    }
    public void setSports( String newT){
        team=newT;
    }
    public void setGender( String newG){
        gender=newG;
    }
    public void setAvailable(boolean newA){
        available =newA;
    }
    public void setTeam(String tName){
        team=tName;
    }
    public void addAT(){
        AT++;
    }
    
    public String toString(){
        String state=new String();
        if(available){
            state="available";
        }else{
            state="unavailable";
        }
        return "Name: "+getName()+
                "\nGrade: "+getGrade()+
                "\nGender: "+getGender()+
                "\nWeight: "+getWeight()+ "kg"+
                "\nHeight: "+getHeight()+ "cm"+
                "\nState: "+state+
                "\nTimes of being late: "+getAT()+"\n";
    }
    
    public String toCSV(){
        return grade+","+name+","+height+","+weight+","+gender+","+AT+","+available+",";
    }
}
