
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
public class team {
    private String teamName;
    private int nums;//number of members
    private List<member> members;
    private List<train> trains;
    private List<match> matches;

    public team(String name){
        teamName=name;
        members=new ArrayList<member>();
        trains=new ArrayList<train>();
        matches=new ArrayList<match>();
        nums=0;
    }

    public String getName(){
        return teamName;
    }
    public void setName(String newN){
        teamName=newN;
    }
    public int getNums(){
        return nums;
    }
    public List<member> getMembers(){
        return members;
    }
    public List<train> getTrains(){
        return trains;
    }
    public List<match> getMatches(){
        return matches;
    }
    public void increNums(){
        nums++;
    }

    public void addMem(int grade, String name, int height, int weight, String gender, int AT, boolean available){
        member newM=new member(grade,name,height,weight,gender,AT,available);
        members.add(newM);
        nums++;
    }
 
    public boolean removeMem(String name){
        member find=searchMem(name);
        if(find==null){
            return false;
        }else{
            members.remove(find);
            nums=members.size();
            return true;
        }
    }

    public member searchMem(String name){
        for(int i=0;i<members.size();i++){
            if(members.get(i).getName().equals(name)){
                return members.get(i);
            }
        }
        return null;
    }

    public ArrayList<member> searchMem(int grade){
        ArrayList<member> result=new ArrayList<member>();
        for(int i=0;i<members.size();i++){
            if(members.get(i).getGrade()==grade){
                result.add(members.get(i));
            }
        }
        return result;
    }

    public boolean aprLeave(String name){
        member temp=searchMem(name);
        if(temp==null){
            return false;
        }else{
            temp.setAvailable(false);
            return true;
        }
    }

    public train addTrain(String date, int time){
        train newT=new train(date,time,teamName,members);
        trains.add(newT);
        return newT;
    }
    
    public void addTrain(train newT){
        trains.add(newT);
    }

    public match addMatch(String opn, String date, boolean win, int ourScore, int oppScore){
        match temp=new match(teamName,opn,date,win,ourScore,oppScore);
        matches.add(temp);
        return temp;
    }
    
    public void addMatch(match newMatch){
        matches.add(newMatch);
    }

    public match searchMatch(String date){
        for(match m: matches){
            if(m.getDate().equals(date)){
                return m;
            }
        }
        return null;
    }

    public List<match> searchMatch(boolean win){
        List<match> res=new ArrayList<>();
        for(match m:matches){
            if(m.getWin()==win){
                res.add(m);
            }
        }
        return res;
    }

    public train searchTrain(String date){
        for(int i=0;i<trains.size();i++){
            if(trains.get(i).getDate().equals(date)){
                return trains.get(i);
            }
        }
        return null;
    }
    
    public boolean removeMatch(String date){
        match mat=searchMatch(date);
        if(mat==null){
            return false;
        }
        matches.remove(mat);
        return true;
    }

    public boolean cancelTrain(String date){
        train result=searchTrain(date);
        if(result==null){
            return false;
        }else{
            trains.remove(result);
            return true;
        }
    }

    public List<member> checkPerformance(){
        List<member> bad=new ArrayList<member>();
        for(int i=0;i<members.size();i++){
            if(members.get(i).getAT()>=3){
                bad.add(members.get(i));
            }
        }
        return bad;
    }

    public void rectify(){ //remove the members who have been late for training more than three times from the team
        List<member> bad=checkPerformance();
        for(int i=0;i<bad.size();i++){
            members.remove(bad.get(i));
        }
        nums=members.size();
    }
    
    public boolean containsMem(String name){
        for(member mem: members){
            if(mem.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean reportTrain(String date, String[] names){ //report the attendence of members in the train of a certain date by using a list of names who are late
        train t=searchTrain(date);
        List<String> due=t.getDue();
        List<String> arrive=new ArrayList<>();
        for(int i=0;i<names.length;i++){
            String name=names[i];
            if(!containsMem(name)){
                return false;
            }
            arrive.add(name);
        }
        for(String n: due){
            if (searchMem(n) != null&&!arrive.contains(n)) {
                member mem = searchMem(n);
                mem.addAT();
                t.getLate().add(mem.getName());
            }
        }
        return true;
    }
}
