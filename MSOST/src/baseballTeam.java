
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yuxiangliu
 */
public class baseballTeam extends team{
    private Map<String, List<member>> position=new HashMap<>();

    public baseballTeam(String name){
        super(name);
        position.put("pitcher",new ArrayList<member>());
        position.put("cather",new ArrayList<member>());
        position.put("outfielder",new ArrayList<member>());
        position.put("first baseman",new ArrayList<member>());
        position.put("short stop",new ArrayList<member>());
        position.put("third baseman",new ArrayList<member>());
        position.put("infielder",new ArrayList<member>());
        position.put("second baseman",new ArrayList<member>());
        position.put("left fielder",new ArrayList<member>());
        position.put("right baseman",new ArrayList<member>());
        position.put("center baseman",new ArrayList<member>());
        position.put("designated hitter",new ArrayList<member>());
        position.put("starting pitcher",new ArrayList<member>());
        position.put("relief pitcher",new ArrayList<member>());
        position.put("closer",new ArrayList<member>());
    }

    public boolean addMem(int grade, String name, int height, int weight, String gender, int AT, boolean available, String ptn){
        member newM=new member(grade,name,height,weight,gender,AT,available);
        newM.setTeam("baseball");
        if(position.containsKey(ptn)){
            this.getMembers().add(newM);
            this.increNums();
            position.get(ptn).add(newM);
            return true;
        }else{
            return false;
        }
    }

    public boolean removeMem(String name){
        if(super.searchMem(name)!=null){
            super.removeMem(name);
            for(String key: position.keySet()){
                List<member> list=position.get(key);
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getName().equals(name)){
                        list.remove(i);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public List<member> findPosi(String psn){
        if(position.containsKey(psn)){
            return position.get(psn);
        }else{
            return null;
        }
    }

    public void rectify(){
        List<member> bad=this.checkPerformance();
        for(int i=0;i<bad.size();i++){
            member mem=bad.get(i);
            for(String key: position.keySet()){
                if(position.get(key).contains(mem)){
                    position.get(key).remove(mem);
                }
            }
        }
        super.rectify();
    }
    
    public Map<String, List<member>> findAllMembers(){
        return position;
    }
    
}
