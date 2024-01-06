
import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author yuxiangliu
 */
public class mainFace extends javax.swing.JFrame {

    /**
     * Creates new form mainFace
     */
    public static final String separator=",";
    private soccerTeam soccer;
    private volleyballTeam volleyball;
    private baseballTeam baseball;
    private badmintonTeam badminton;
    private basketballTeam basketball;
    private footballTeam football;
    private team temp;
    
    public mainFace() {
        soccer=new soccerTeam("soccer");
        volleyball=new volleyballTeam("volleyball");
        baseball=new baseballTeam("baseball");
        badminton=new badmintonTeam("badminton");
        basketball=new basketballTeam("basketball");
        football=new footballTeam("football");
        initTeam();
        initComponents();
    }
    
    private void initTeam(){
        try{
            Scanner reader=new Scanner(new File("Members.csv"));
            while(reader.hasNext()){
                String[] nextMemInfo=reader.nextLine().split(separator);
                if(nextMemInfo.length<=1){
                    break;
                }
                boolean available = false;
                if(nextMemInfo[7].equals("false")){
                    available=false;
                }else if(nextMemInfo[7].equals("true")){
                    available=true;
                }
                if(nextMemInfo[0].equals("soccer")){
                    soccer.addMem(Integer.parseInt(nextMemInfo[1]),nextMemInfo[2],Integer.parseInt(nextMemInfo[3]),Integer.parseInt(nextMemInfo[4]),nextMemInfo[5],Integer.parseInt(nextMemInfo[6]),available,nextMemInfo[8]);
                }
                if(nextMemInfo[0].equals("volleyball")){
                    volleyball.addMem(Integer.parseInt(nextMemInfo[1]),nextMemInfo[2],Integer.parseInt(nextMemInfo[3]),Integer.parseInt(nextMemInfo[4]),nextMemInfo[5],Integer.parseInt(nextMemInfo[6]),available,nextMemInfo[8]);
                }
                if(nextMemInfo[0].equals("baseball")){
                    baseball.addMem(Integer.parseInt(nextMemInfo[1]),nextMemInfo[2],Integer.parseInt(nextMemInfo[3]),Integer.parseInt(nextMemInfo[4]),nextMemInfo[5],Integer.parseInt(nextMemInfo[6]),available,nextMemInfo[8]);
                }
                if(nextMemInfo[0].equals("badminton")){
                    badminton.addMem(Integer.parseInt(nextMemInfo[1]),nextMemInfo[2],Integer.parseInt(nextMemInfo[3]),Integer.parseInt(nextMemInfo[4]),nextMemInfo[5],Integer.parseInt(nextMemInfo[6]),available,nextMemInfo[8]);
                }
                if(nextMemInfo[0].equals("basketball")){
                    basketball.addMem(Integer.parseInt(nextMemInfo[1]),nextMemInfo[2],Integer.parseInt(nextMemInfo[3]),Integer.parseInt(nextMemInfo[4]),nextMemInfo[5],Integer.parseInt(nextMemInfo[6]),available,nextMemInfo[8]);
                }
                if(nextMemInfo[0].equals("football")){
                    football.addMem(Integer.parseInt(nextMemInfo[1]),nextMemInfo[2],Integer.parseInt(nextMemInfo[3]),Integer.parseInt(nextMemInfo[4]),nextMemInfo[5],Integer.parseInt(nextMemInfo[6]),available,nextMemInfo[8]);
                }
            }
            //System.out.println(soccer.searchMem("Benson"));
            reader.close();
            
            File matchFolder=new File("matches");
            File trainFolder=new File("trains");
            File[] matchFiles=matchFolder.listFiles();
            File[] trainFiles=trainFolder.listFiles();
            for (File f : matchFiles) {
                File[] mf = f.listFiles();
                if (mf != null) {
                    for (int k = 0; k < mf.length; k++) {
                        File file = mf[k];
                        reader = new Scanner(file);
                        if(reader.hasNext()){
                            String[] matchInfo = new String[6];
                        for (int i = 0; i < 6; i++) {
                            matchInfo[i] = reader.nextLine();
                        }
                        boolean win = false;
                        if (matchInfo[3].equals("win")) {
                            win = true;
                        } else if (matchInfo[3].equals("lose")) {
                            win = false;
                        }
                        int ourScore = Integer.parseInt(matchInfo[4]);
                        int oppScore = Integer.parseInt(matchInfo[5]);
                        match mat = new match(matchInfo[0], matchInfo[1], matchInfo[2], win, ourScore, oppScore);
                            if (reader.hasNextLine()) {
                                String[] presentMem = reader.nextLine().trim().split(separator);
                                for (int i = 0; i < presentMem.length; i++) {
                                    //System.out.println(presentMem[i]);
                                    mat.addPresent(presentMem[i]);
                                }
                            }
                            if (matchInfo[0].equals("soccer")) {
                                soccer.addMatch(mat);
                            }
                            if (matchInfo[0].equals("volleyball")) {
                                volleyball.addMatch(mat);
                            }
                            if (matchInfo[0].equals("basketball")) {
                                basketball.addMatch(mat);
                            }
                            if (matchInfo[0].equals("baseball")) {
                                baseball.addMatch(mat);
                            }
                            if (matchInfo[0].equals("football")) {
                                football.addMatch(mat);
                            }
                            if (matchInfo[0].equals("badminton")) {
                                badminton.addMatch(mat);
                            }
                        }
                        reader.close();
                    }
                }
            }
            
            for (File f : trainFiles) {
                File[] tf = f.listFiles();
                if (tf != null) {
                    for (int k = 0; k < tf.length; k++) {
                        File file = tf[k];
                        reader = new Scanner(file);
                        if (reader.hasNext()) {
                            String[] trainInfo = new String[3];
                            for (int i = 0; i < 3; i++) {
                                trainInfo[i] = reader.next();
                            }
                            int time = Integer.parseInt(trainInfo[1]);
                            train newT = new train(trainInfo[0], time, trainInfo[2]);
                            String[] memDue = reader.next().trim().split(separator);
                            for (int i = 0; i < memDue.length; i++) {
                                newT.addDue(memDue[i]);
                            }
                            if (reader.hasNext()) {
                                String[] memLate = reader.next().trim().split(separator);
                                for (int i = 0; i < memLate.length; i++) {
                                    newT.addLate(memLate[i]);
                                }
                            }
                            if (trainInfo[2].equals("soccer")) {
                                soccer.addTrain(newT);
                            }
                            if (trainInfo[2].equals("baseball")) {
                                baseball.addTrain(newT);
                            }
                            if (trainInfo[2].equals("basketball")) {
                                basketball.addTrain(newT);
                            }
                            if (trainInfo[2].equals("badminton")) {
                                badminton.addTrain(newT);
                            }
                            if (trainInfo[2].equals("volleyball")) {
                                volleyball.addTrain(newT);
                            }
                            if (trainInfo[2].equals("football")) {
                                football.addTrain(newT);
                            }
                        }
                        reader.close();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainFace.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TeamFrame = new javax.swing.JFrame();
        teamLable = new javax.swing.JLabel();
        playersButton = new javax.swing.JButton();
        matchnButton = new javax.swing.JButton();
        trainButton = new javax.swing.JButton();
        memberFrame = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        memberTextArea = new javax.swing.JTextArea();
        memberTextField = new javax.swing.JTextField();
        nameSearch = new javax.swing.JCheckBox();
        positionSearch = new javax.swing.JCheckBox();
        performanceSearch = new javax.swing.JCheckBox();
        memberSearchButton = new javax.swing.JButton();
        rectifyButton = new javax.swing.JButton();
        memberAddButton = new javax.swing.JButton();
        memberRemoveButton = new javax.swing.JButton();
        memberApprLeaveButton = new javax.swing.JButton();
        addMemberFrame = new javax.swing.JFrame();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        addMemberNameTextField = new javax.swing.JTextField();
        addMemberHeightTextField = new javax.swing.JTextField();
        addMemberWeightTextField = new javax.swing.JTextField();
        addMemberGradeTextField = new javax.swing.JTextField();
        addMemberGenderTextField = new javax.swing.JTextField();
        addMemberPositionTextField = new javax.swing.JTextField();
        soccerAddMemberButton = new javax.swing.JButton();
        memberRemoveFrame = new javax.swing.JFrame();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        soccerMemberRemoveTextField = new javax.swing.JTextField();
        soccerRemoveMemberButton = new javax.swing.JButton();
        approveLeaveFrame = new javax.swing.JFrame();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        approveLeaveTextField = new javax.swing.JTextField();
        soccerApproveLeaveButton = new javax.swing.JButton();
        trainFrame = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        trainTextArea = new javax.swing.JTextArea();
        trainSearchButton = new javax.swing.JButton();
        trainTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        trainAddButton = new javax.swing.JButton();
        trainReportButton = new javax.swing.JButton();
        trainCancelButton = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        trainAddFrame = new javax.swing.JFrame();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        addTrainDateTextField = new javax.swing.JTextField();
        addTrainTimeTextField = new javax.swing.JTextField();
        addTrainButton = new javax.swing.JButton();
        trainReportFrame = new javax.swing.JFrame();
        trainReportMemberTextField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        trainReportDateTextField = new javax.swing.JTextField();
        reportTrainButton = new javax.swing.JButton();
        trainCancelFrame = new javax.swing.JFrame();
        jLabel20 = new javax.swing.JLabel();
        trainCancelTextArea = new javax.swing.JTextField();
        cancelTrainButton = new javax.swing.JButton();
        matchFrame = new javax.swing.JFrame();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        matchTextArea = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        matchSearchTextField = new javax.swing.JTextField();
        matchAddButton = new javax.swing.JButton();
        matchRemoveButton = new javax.swing.JButton();
        matchSearchButton = new javax.swing.JButton();
        matchAddFrame = new javax.swing.JFrame();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        matchAddDateTextFiled = new javax.swing.JTextField();
        matchAddOppScoreTextFiled = new javax.swing.JTextField();
        matchAddOurScrTextFiled = new javax.swing.JTextField();
        matchAddWinTextFiled = new javax.swing.JTextField();
        matchAddPreTextFiled = new javax.swing.JTextField();
        addMatchButton = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        matchOppNameTextFiled = new javax.swing.JTextField();
        matchRemoveFrame = new javax.swing.JFrame();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        matchRemoveTextArea = new javax.swing.JTextField();
        removeMatchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        badmintonButton = new javax.swing.JButton();
        baseballButton = new javax.swing.JButton();
        footballButton = new javax.swing.JButton();
        basketballButton = new javax.swing.JButton();
        soccerButton = new javax.swing.JButton();
        volleyballButton = new javax.swing.JButton();

        teamLable.setText("Soccer");

        playersButton.setText("Players");
        playersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playersButtonActionPerformed(evt);
            }
        });

        matchnButton.setText("Mathces");
        matchnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchnButtonActionPerformed(evt);
            }
        });

        trainButton.setText("Trains");
        trainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TeamFrameLayout = new javax.swing.GroupLayout(TeamFrame.getContentPane());
        TeamFrame.getContentPane().setLayout(TeamFrameLayout);
        TeamFrameLayout.setHorizontalGroup(
            TeamFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TeamFrameLayout.createSequentialGroup()
                .addGroup(TeamFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TeamFrameLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(TeamFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(playersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(matchnButton, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(trainButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(TeamFrameLayout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(teamLable)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        TeamFrameLayout.setVerticalGroup(
            TeamFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TeamFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(teamLable)
                .addGap(31, 31, 31)
                .addComponent(playersButton)
                .addGap(26, 26, 26)
                .addComponent(matchnButton)
                .addGap(32, 32, 32)
                .addComponent(trainButton)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        memberTextArea.setColumns(20);
        memberTextArea.setRows(5);
        jScrollPane1.setViewportView(memberTextArea);

        nameSearch.setText("search by name");
        nameSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameSearchActionPerformed(evt);
            }
        });

        positionSearch.setText("find players with certain positions");
        positionSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionSearchActionPerformed(evt);
            }
        });

        performanceSearch.setText("find players who have been late for more than 3 times");
        performanceSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performanceSearchActionPerformed(evt);
            }
        });

        memberSearchButton.setText("search");
        memberSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberSearchButtonActionPerformed(evt);
            }
        });

        rectifyButton.setText("AUTOMATIC RECTIFY");
        rectifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rectifyButtonActionPerformed(evt);
            }
        });

        memberAddButton.setText("ADD");
        memberAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberAddButtonActionPerformed(evt);
            }
        });

        memberRemoveButton.setText("REMOVE");
        memberRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberRemoveButtonActionPerformed(evt);
            }
        });

        memberApprLeaveButton.setText("Approve Leave");
        memberApprLeaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberApprLeaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout memberFrameLayout = new javax.swing.GroupLayout(memberFrame.getContentPane());
        memberFrame.getContentPane().setLayout(memberFrameLayout);
        memberFrameLayout.setHorizontalGroup(
            memberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberFrameLayout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(memberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(memberAddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(memberRemoveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rectifyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(memberApprLeaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
            .addGroup(memberFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(memberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameSearch)
                    .addComponent(memberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(positionSearch)
                    .addComponent(performanceSearch)
                    .addComponent(memberSearchButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        memberFrameLayout.setVerticalGroup(
            memberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberFrameLayout.createSequentialGroup()
                .addGroup(memberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memberFrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(memberAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memberRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rectifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memberApprLeaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(memberFrameLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(memberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(positionSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(performanceSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(memberSearchButton))
        );

        jLabel3.setText("Add a Member");

        jLabel4.setText("Name");

        jLabel5.setText("Height");

        jLabel6.setText("Weight");

        jLabel7.setText("Grade");

        jLabel8.setText("Gender");

        jLabel9.setText("Position");

        addMemberNameTextField.setText("Stannis");

        addMemberHeightTextField.setText("187");

        addMemberWeightTextField.setText("80");

        addMemberGradeTextField.setText("12");

        addMemberGenderTextField.setText("male");

        addMemberPositionTextField.setText("center defenser");

        soccerAddMemberButton.setText("Add");
        soccerAddMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soccerAddMemberButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addMemberFrameLayout = new javax.swing.GroupLayout(addMemberFrame.getContentPane());
        addMemberFrame.getContentPane().setLayout(addMemberFrameLayout);
        addMemberFrameLayout.setHorizontalGroup(
            addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMemberFrameLayout.createSequentialGroup()
                .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addMemberFrameLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(soccerAddMemberButton))
                    .addGroup(addMemberFrameLayout.createSequentialGroup()
                        .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addMemberFrameLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel7))
                            .addGroup(addMemberFrameLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))))
                        .addGap(41, 41, 41)
                        .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(addMemberNameTextField)
                            .addComponent(addMemberHeightTextField)
                            .addComponent(addMemberWeightTextField)
                            .addComponent(addMemberGradeTextField)
                            .addComponent(addMemberGenderTextField)
                            .addComponent(addMemberPositionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        addMemberFrameLayout.setVerticalGroup(
            addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMemberFrameLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(soccerAddMemberButton))
                .addGap(18, 18, 18)
                .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(addMemberNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addMemberHeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(addMemberWeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(addMemberGradeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(addMemberGenderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(addMemberFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(addMemberPositionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jLabel10.setText("Remove a Member");

        jLabel11.setText("Name");

        soccerMemberRemoveTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soccerMemberRemoveTextFieldActionPerformed(evt);
            }
        });

        soccerRemoveMemberButton.setText("remove");
        soccerRemoveMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soccerRemoveMemberButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout memberRemoveFrameLayout = new javax.swing.GroupLayout(memberRemoveFrame.getContentPane());
        memberRemoveFrame.getContentPane().setLayout(memberRemoveFrameLayout);
        memberRemoveFrameLayout.setHorizontalGroup(
            memberRemoveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberRemoveFrameLayout.createSequentialGroup()
                .addGroup(memberRemoveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(memberRemoveFrameLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel10))
                    .addGroup(memberRemoveFrameLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(soccerMemberRemoveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(memberRemoveFrameLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(soccerRemoveMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        memberRemoveFrameLayout.setVerticalGroup(
            memberRemoveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberRemoveFrameLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addGap(31, 31, 31)
                .addGroup(memberRemoveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soccerMemberRemoveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(soccerRemoveMemberButton)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jLabel12.setText("Approve a Member for Leave");

        jLabel13.setText("Name");

        soccerApproveLeaveButton.setText("approve");
        soccerApproveLeaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soccerApproveLeaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout approveLeaveFrameLayout = new javax.swing.GroupLayout(approveLeaveFrame.getContentPane());
        approveLeaveFrame.getContentPane().setLayout(approveLeaveFrameLayout);
        approveLeaveFrameLayout.setHorizontalGroup(
            approveLeaveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(approveLeaveFrameLayout.createSequentialGroup()
                .addGroup(approveLeaveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(approveLeaveFrameLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel12))
                    .addGroup(approveLeaveFrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addGap(42, 42, 42)
                        .addComponent(approveLeaveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(approveLeaveFrameLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(soccerApproveLeaveButton)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        approveLeaveFrameLayout.setVerticalGroup(
            approveLeaveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(approveLeaveFrameLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel12)
                .addGap(52, 52, 52)
                .addGroup(approveLeaveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(approveLeaveTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(soccerApproveLeaveButton)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        trainTextArea.setColumns(20);
        trainTextArea.setRows(5);
        jScrollPane2.setViewportView(trainTextArea);

        trainSearchButton.setText("search");
        trainSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainSearchButtonActionPerformed(evt);
            }
        });

        jLabel14.setText("Date");

        trainAddButton.setText("Add");
        trainAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainAddButtonActionPerformed(evt);
            }
        });

        trainReportButton.setText("Report");
        trainReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainReportButtonActionPerformed(evt);
            }
        });

        trainCancelButton.setText("Cancel");
        trainCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainCancelButtonActionPerformed(evt);
            }
        });

        jLabel22.setText("TRAIN");

        javax.swing.GroupLayout trainFrameLayout = new javax.swing.GroupLayout(trainFrame.getContentPane());
        trainFrame.getContentPane().setLayout(trainFrameLayout);
        trainFrameLayout.setHorizontalGroup(
            trainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainFrameLayout.createSequentialGroup()
                .addGroup(trainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trainFrameLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(trainSearchButton))
                    .addGroup(trainFrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addGap(11, 11, 11)
                        .addComponent(trainTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(trainFrameLayout.createSequentialGroup()
                .addGroup(trainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trainFrameLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(trainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(trainCancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(trainReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(trainAddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(trainFrameLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabel22)))
                .addGap(0, 77, Short.MAX_VALUE))
        );
        trainFrameLayout.setVerticalGroup(
            trainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainFrameLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addGroup(trainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trainFrameLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(trainAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(trainReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(trainCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(trainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trainTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(trainSearchButton)
                .addGap(43, 43, 43))
        );

        jLabel15.setText("Add a Train");

        jLabel16.setText("Date");

        jLabel17.setText("Time");

        addTrainDateTextField.setText("2023.2.1");

        addTrainTimeTextField.setText("3");

        addTrainButton.setText("Add");
        addTrainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTrainButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout trainAddFrameLayout = new javax.swing.GroupLayout(trainAddFrame.getContentPane());
        trainAddFrame.getContentPane().setLayout(trainAddFrameLayout);
        trainAddFrameLayout.setHorizontalGroup(
            trainAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainAddFrameLayout.createSequentialGroup()
                .addGroup(trainAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trainAddFrameLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel15))
                    .addGroup(trainAddFrameLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(trainAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(26, 26, 26)
                        .addGroup(trainAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addTrainDateTextField)
                            .addComponent(addTrainTimeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)))
                    .addGroup(trainAddFrameLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(addTrainButton)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        trainAddFrameLayout.setVerticalGroup(
            trainAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainAddFrameLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(trainAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(addTrainDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(trainAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trainAddFrameLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel17))
                    .addGroup(trainAddFrameLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(addTrainTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(addTrainButton)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jLabel18.setText("Report the names of members who arrive on training");

        jLabel19.setText("enter the date");

        reportTrainButton.setText("report");
        reportTrainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportTrainButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout trainReportFrameLayout = new javax.swing.GroupLayout(trainReportFrame.getContentPane());
        trainReportFrame.getContentPane().setLayout(trainReportFrameLayout);
        trainReportFrameLayout.setHorizontalGroup(
            trainReportFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainReportFrameLayout.createSequentialGroup()
                .addGroup(trainReportFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(trainReportMemberTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(trainReportFrameLayout.createSequentialGroup()
                        .addGroup(trainReportFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addGroup(trainReportFrameLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel19)
                                .addGap(48, 48, 48)
                                .addComponent(trainReportDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(trainReportFrameLayout.createSequentialGroup()
                                .addGap(254, 254, 254)
                                .addComponent(reportTrainButton)))
                        .addGap(0, 175, Short.MAX_VALUE)))
                .addContainerGap())
        );
        trainReportFrameLayout.setVerticalGroup(
            trainReportFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainReportFrameLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(trainReportFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(trainReportDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(trainReportMemberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reportTrainButton)
                .addGap(27, 27, 27))
        );

        jLabel20.setText("date of the train");

        cancelTrainButton.setText("Cancel");
        cancelTrainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelTrainButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout trainCancelFrameLayout = new javax.swing.GroupLayout(trainCancelFrame.getContentPane());
        trainCancelFrame.getContentPane().setLayout(trainCancelFrameLayout);
        trainCancelFrameLayout.setHorizontalGroup(
            trainCancelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainCancelFrameLayout.createSequentialGroup()
                .addGroup(trainCancelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trainCancelFrameLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel20)
                        .addGap(35, 35, 35)
                        .addComponent(trainCancelTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(trainCancelFrameLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(cancelTrainButton)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        trainCancelFrameLayout.setVerticalGroup(
            trainCancelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainCancelFrameLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(trainCancelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(trainCancelTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(cancelTrainButton)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jLabel21.setText("Match");

        matchTextArea.setColumns(20);
        matchTextArea.setRows(5);
        jScrollPane3.setViewportView(matchTextArea);

        jLabel23.setText("Date");

        matchAddButton.setText("ADD");
        matchAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchAddButtonActionPerformed(evt);
            }
        });

        matchRemoveButton.setText("REMOVE");
        matchRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchRemoveButtonActionPerformed(evt);
            }
        });

        matchSearchButton.setText("SEARCH");
        matchSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout matchFrameLayout = new javax.swing.GroupLayout(matchFrame.getContentPane());
        matchFrame.getContentPane().setLayout(matchFrameLayout);
        matchFrameLayout.setHorizontalGroup(
            matchFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(matchFrameLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(matchFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(matchFrameLayout.createSequentialGroup()
                        .addGroup(matchFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(matchFrameLayout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addComponent(jLabel21))
                            .addGroup(matchFrameLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(28, 28, 28)
                                .addGroup(matchFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(matchSearchButton)
                                    .addComponent(matchSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(matchFrameLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(matchFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(matchAddButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(matchRemoveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))))
        );
        matchFrameLayout.setVerticalGroup(
            matchFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(matchFrameLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel21)
                .addGap(29, 29, 29)
                .addGroup(matchFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(matchFrameLayout.createSequentialGroup()
                        .addComponent(matchAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(matchRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addGroup(matchFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matchSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(29, 29, 29)
                .addComponent(matchSearchButton)
                .addGap(65, 65, 65))
        );

        jLabel24.setText("Add a Match");

        jLabel25.setText("Date");

        jLabel26.setText("the score of opponent");

        jLabel27.setText("the Score of our team");

        jLabel28.setText("Win or Not?");

        jLabel29.setText("First Team");

        matchAddDateTextFiled.setText("2022.4.5");

        matchAddOppScoreTextFiled.setText("4");
        matchAddOppScoreTextFiled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchAddOppScoreTextFiledActionPerformed(evt);
            }
        });

        matchAddOurScrTextFiled.setText("2");

        matchAddWinTextFiled.setText("lose");

        matchAddPreTextFiled.setText("Jacob,Corry,Kevin");

        addMatchButton.setText("Add");
        addMatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMatchButtonActionPerformed(evt);
            }
        });

        jLabel30.setText("Team name of the opponent");

        matchOppNameTextFiled.setText("Wuhan Sixth High School");

        javax.swing.GroupLayout matchAddFrameLayout = new javax.swing.GroupLayout(matchAddFrame.getContentPane());
        matchAddFrame.getContentPane().setLayout(matchAddFrameLayout);
        matchAddFrameLayout.setHorizontalGroup(
            matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(matchAddFrameLayout.createSequentialGroup()
                .addGroup(matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(matchAddFrameLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel24))
                    .addGroup(matchAddFrameLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(matchAddDateTextFiled)
                            .addComponent(matchAddOppScoreTextFiled)
                            .addComponent(matchAddOurScrTextFiled)
                            .addComponent(matchAddWinTextFiled)
                            .addComponent(matchOppNameTextFiled, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
                    .addGroup(matchAddFrameLayout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(addMatchButton))
                    .addGroup(matchAddFrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(matchAddPreTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        matchAddFrameLayout.setVerticalGroup(
            matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(matchAddFrameLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addGroup(matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(matchAddDateTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(matchAddOppScoreTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(matchAddOurScrTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matchAddWinTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(matchOppNameTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(matchAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(matchAddPreTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(addMatchButton)
                .addContainerGap())
        );

        jLabel31.setText("Match Remove");

        jLabel32.setText("date");

        removeMatchButton.setText("remove");
        removeMatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMatchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout matchRemoveFrameLayout = new javax.swing.GroupLayout(matchRemoveFrame.getContentPane());
        matchRemoveFrame.getContentPane().setLayout(matchRemoveFrameLayout);
        matchRemoveFrameLayout.setHorizontalGroup(
            matchRemoveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(matchRemoveFrameLayout.createSequentialGroup()
                .addGroup(matchRemoveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(matchRemoveFrameLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel31))
                    .addGroup(matchRemoveFrameLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(matchRemoveTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(matchRemoveFrameLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(removeMatchButton)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        matchRemoveFrameLayout.setVerticalGroup(
            matchRemoveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(matchRemoveFrameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel31)
                .addGap(31, 31, 31)
                .addGroup(matchRemoveFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(matchRemoveTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(removeMatchButton)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("School Teams Management System");

        badmintonButton.setText("badminton");
        badmintonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                badmintonButtonActionPerformed(evt);
            }
        });

        baseballButton.setText("baseball");
        baseballButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseballButtonActionPerformed(evt);
            }
        });

        footballButton.setText("football");
        footballButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                footballButtonActionPerformed(evt);
            }
        });

        basketballButton.setText("basketball");
        basketballButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basketballButtonActionPerformed(evt);
            }
        });

        soccerButton.setText("soccer");
        soccerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soccerButtonActionPerformed(evt);
            }
        });

        volleyballButton.setText("volleyball");
        volleyballButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volleyballButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(badmintonButton, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(baseballButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(footballButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(basketballButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(soccerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(volleyballButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel1)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(basketballButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(badmintonButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baseballButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soccerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(footballButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volleyballButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void badmintonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_badmintonButtonActionPerformed
        temp=badminton;
        TeamFrame.setVisible(true);
        TeamFrame.pack();
        teamLable.setText("badminton");
    }//GEN-LAST:event_badmintonButtonActionPerformed

    private void baseballButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseballButtonActionPerformed
        temp=baseball;
        TeamFrame.setVisible(true);
        TeamFrame.pack();
        teamLable.setText("baseball");
    }//GEN-LAST:event_baseballButtonActionPerformed

    private void soccerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soccerButtonActionPerformed
        temp=soccer;
        TeamFrame.setVisible(true);
        TeamFrame.pack();
        teamLable.setText("soccer");
    }//GEN-LAST:event_soccerButtonActionPerformed

    private void playersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playersButtonActionPerformed
        memberFrame.setVisible(true);
        memberFrame.pack();
        memberTextArea.setText("");
        for(member mem: temp.getMembers()){
            memberTextArea.append(mem.toString()+"\n");
        }
    }//GEN-LAST:event_playersButtonActionPerformed

    private void memberAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberAddButtonActionPerformed
        addMemberFrame.setVisible(true);
        addMemberFrame.pack();
    }//GEN-LAST:event_memberAddButtonActionPerformed

    private void soccerAddMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soccerAddMemberButtonActionPerformed
        if(verifyAddMemberInput()){
            try{
                String name=addMemberNameTextField.getText();
                int height=Integer.parseInt(addMemberHeightTextField.getText());
                int weight=Integer.parseInt(addMemberWeightTextField.getText());
                int grade=Integer.parseInt(addMemberGradeTextField.getText());
                String gender=addMemberGenderTextField.getText();
                String position=addMemberPositionTextField.getText();
                member mem=temp.searchMem(name);
                if (mem == null) {
                    boolean add = false;
                    if (temp == soccer) {
                        add= ((soccerTeam) temp).addMem(grade,name,height,weight,gender,0,true,position);
                    } else if (temp == football) {
                        add= ((footballTeam) temp).addMem(grade,name,height,weight,gender,0,true,position);
                    } else if (temp == badminton) {
                        add= ((badmintonTeam) temp).addMem(grade,name,height,weight,gender,0,true,position);
                    } else if (temp == baseball) {
                        add= ((baseballTeam) temp).addMem(grade,name,height,weight,gender,0,true,position);
                    } else if (temp == volleyball) {
                        add= ((volleyballTeam) temp).addMem(grade,name,height,weight,gender,0,true,position);
                    } else if (temp == basketball) {
                        add= ((basketballTeam) temp).addMem(grade,name,height,weight,gender,0,true,position);
                    }
                    if(add){
                      JOptionPane.showMessageDialog(addMemberFrame, "The member has been successfully added");
                        memberTextArea.setText("");
                        for (member m : temp.getMembers()) {
                            memberTextArea.append(m.toString() + "\n");
                        }
                    }else{
                        JOptionPane.showMessageDialog(addMemberFrame, "check the spelling of the position please");
                    }
                }else{
                    JOptionPane.showMessageDialog(addMemberFrame, "Sorry. The member already exists");
                }
                saveData();
                for (Component c : addMemberFrame.getContentPane().getComponents()) {
                    if (c instanceof JTextField) {
                        JTextField inputTextField = (JTextField) c;
                        inputTextField.setText("");
                    }
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(addMemberFrame, "Note that height, weight, and grade must be integers");
            }
        }else{
            JOptionPane.showMessageDialog(addMemberFrame, "Please ensure all fields are not empty");
        }
    }//GEN-LAST:event_soccerAddMemberButtonActionPerformed

    private void memberRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberRemoveButtonActionPerformed
        memberRemoveFrame.setVisible(true);
        memberRemoveFrame.pack();
    }//GEN-LAST:event_memberRemoveButtonActionPerformed

    private void soccerRemoveMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soccerRemoveMemberButtonActionPerformed
        String name=soccerMemberRemoveTextField.getText().trim();
        boolean find=false;
        if (temp == soccer) {
            find= ((soccerTeam) temp).removeMem(name);
        } else if (temp == football) {
            find= ((footballTeam) temp).removeMem(name);
        } else if (temp == badminton) {
            find= ((badmintonTeam) temp).removeMem(name);
        } else if (temp == baseball) {
            find= ((baseballTeam) temp).removeMem(name);
        } else if (temp == volleyball) {
            find= ((volleyballTeam) temp).removeMem(name);
        } else if (temp == basketball) {
            find= ((basketballTeam) temp).removeMem(name);
        }
        if(!find){
            JOptionPane.showMessageDialog(memberRemoveFrame, "the player was not found");
        }else{
            JOptionPane.showMessageDialog(memberRemoveFrame," the player has been removed from the soccer team");
            saveData();
            memberTextArea.setText("");
            for (member mem : temp.getMembers()) {
                memberTextArea.append(mem.toString() + "\n");
            }
        }
        soccerMemberRemoveTextField.setText("");
    }//GEN-LAST:event_soccerRemoveMemberButtonActionPerformed

    private void soccerMemberRemoveTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soccerMemberRemoveTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soccerMemberRemoveTextFieldActionPerformed

    private void memberSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberSearchButtonActionPerformed
        String str = memberTextField.getText().trim();
        if((nameSearch.isSelected()||positionSearch.isSelected())&&str.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter something");
            return;
        }
        memberTextArea.setText("");
        if(nameSearch.isSelected()){
            member search=temp.searchMem(str);
            if(search!=null){        
                memberTextArea.append(search.toString());
            }else{
                JOptionPane.showMessageDialog(this, "Sorry. The person is not in the team");
            }
            return;
        }
        if(positionSearch.isSelected()){
            
            List<member> position =new ArrayList<>();
            if(temp==soccer){
                position=((soccerTeam)temp).findPosi(str);
            }else if(temp==football){
                position=((footballTeam)temp).findPosi(str);
            }else if(temp==badminton){
                position=((badmintonTeam)temp).findPosi(str);
            }else if(temp==baseball){
                position=((baseballTeam)temp).findPosi(str);
            }else if(temp==volleyball){
                position=((volleyballTeam)temp).findPosi(str);
            }else if(temp==basketball){
                position=((basketballTeam)temp).findPosi(str);
            }
            if(position==null){
                JOptionPane.showMessageDialog(this, "There is no such a position in the team");
                return;
            }
            if(position.size()==0){
                JOptionPane.showMessageDialog(this, "Sorry. So far there is no member of this position");
                return;
            }
            for(int i=0;i<position.size();i++){
                memberTextArea.append(position.get(i).toString()+"\n");
            }
            return;
        }
        if(performanceSearch.isSelected()){
            List<member> badPerformance=temp.checkPerformance();
            if(badPerformance.size()==0){
                JOptionPane.showMessageDialog(this, "Sorry. All the members in the team perform well");
                return;
            }
            for(int i=0;i<badPerformance.size();i++){
                memberTextArea.append(badPerformance.get(i).toString()+"\n");
            }
            return;
        }
        JOptionPane.showMessageDialog(this, "Remember to select a search standard");
    }//GEN-LAST:event_memberSearchButtonActionPerformed

    private void nameSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameSearchActionPerformed
     positionSearch.setSelected(false);
     performanceSearch.setSelected(false);
     nameSearch.setSelected(true);
    }//GEN-LAST:event_nameSearchActionPerformed

    private void positionSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positionSearchActionPerformed
     positionSearch.setSelected(true);
     performanceSearch.setSelected(false);
     nameSearch.setSelected(false);
    }//GEN-LAST:event_positionSearchActionPerformed

    private void performanceSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performanceSearchActionPerformed
     positionSearch.setSelected(false);
     performanceSearch.setSelected(true);
     nameSearch.setSelected(false);
    }//GEN-LAST:event_performanceSearchActionPerformed

    private void rectifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rectifyButtonActionPerformed
        if (temp == soccer) {
            ((soccerTeam) temp).rectify();
        } else if (temp == football) {
            ((footballTeam) temp).rectify();
        } else if (temp == badminton) {
            ((badmintonTeam) temp).rectify();
        } else if (temp == baseball) {
            ((baseballTeam) temp).rectify();
        } else if (temp == volleyball) {
            ((volleyballTeam) temp).rectify();
        } else if (temp == basketball) {
            ((basketballTeam) temp).rectify();
        }
        saveData();
        memberTextArea.setText("");
        for(member mem: temp.getMembers()){
            memberTextArea.append(mem.toString()+"\n");
        }
        JOptionPane.showMessageDialog(this, "You have removed members who have been late for more than three times");
    }//GEN-LAST:event_rectifyButtonActionPerformed

    private void memberApprLeaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberApprLeaveButtonActionPerformed
        approveLeaveFrame.setVisible(true);
        approveLeaveFrame.pack();
    }//GEN-LAST:event_memberApprLeaveButtonActionPerformed

    private void soccerApproveLeaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soccerApproveLeaveButtonActionPerformed
        String name=approveLeaveTextField.getText().trim();
        if(name.equals("")){
            JOptionPane.showMessageDialog(this, "Enter something in the text field please");
            return;
        }
        boolean find=temp.aprLeave(name);
        if(!find){
            JOptionPane.showMessageDialog(this, "There is no such a member in the team");
            return;
        }
        JOptionPane.showMessageDialog(this, "You have permitted a person for leave");
        approveLeaveTextField.setText("");
        saveData();
        memberTextArea.setText("");
        for (member mem : temp.getMembers()) {
            memberTextArea.append(mem.toString() + "\n");
        }
    }//GEN-LAST:event_soccerApproveLeaveButtonActionPerformed

    private void trainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainButtonActionPerformed
        trainFrame.setVisible(true);
        trainFrame.pack();
        trainTextArea.setText("");
        List<train> trains=temp.getTrains();
        for(int i=0;i<trains.size();i++){
            trainTextArea.append(trains.get(i).toString()+"\n");
        }
    }//GEN-LAST:event_trainButtonActionPerformed

    private void addTrainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTrainButtonActionPerformed
        if(verifyAddTrainInput()){
            try{
                String date=addTrainDateTextField.getText().trim();
                String Time=addTrainTimeTextField.getText().trim();
                int time=Integer.parseInt(Time);
                train search=temp.searchTrain(date);
                if(search==null){
                    train t=temp.addTrain(date,time);
                    List<String> due=t.getDue();
                    StringBuffer Due=new StringBuffer();
                    for(int i=0;i<due.size();i++){
                        Due.append(due.get(i)+",");
                    }
                    String res=Due.toString();
                    try{
                        if(temp==soccer){
                            BufferedWriter writer = new BufferedWriter(new FileWriter("trains/soccer/" + date));
                            writer.write(date + "\n");
                            writer.write(time + "\n");
                            writer.write("soccer" + "\n");
                            writer.write(res + "\n");
                            writer.close();
                        }else if(temp==baseball){
                            BufferedWriter writer = new BufferedWriter(new FileWriter("trains/baseball/" + date));
                            writer.write(date + "\n");
                            writer.write(time + "\n");
                            writer.write("baseball" + "\n");
                            writer.write(res + "\n");
                            writer.close();
                        }else if(temp==badminton){
                            BufferedWriter writer = new BufferedWriter(new FileWriter("trains/badminton/" + date));
                            writer.write(date + "\n");
                            writer.write(time + "\n");
                            writer.write("badminton" + "\n");
                            writer.write(res + "\n");
                            writer.close();
                        }else if(temp==basketball){
                            BufferedWriter writer = new BufferedWriter(new FileWriter("trains/basketball/" + date));
                            writer.write(date + "\n");
                            writer.write(time + "\n");
                            writer.write("basketball" + "\n");
                            writer.write(res + "\n");
                            writer.close();
                        }else if(temp==volleyball){
                            BufferedWriter writer = new BufferedWriter(new FileWriter("trains/volleyball/" + date));
                            writer.write(date + "\n");
                            writer.write(time + "\n");
                            writer.write("volleyball" + "\n");
                            writer.write(res + "\n");
                            writer.close();
                        }else if(temp==football){
                            BufferedWriter writer = new BufferedWriter(new FileWriter("trains/football/" + date));
                            writer.write(date + "\n");
                            writer.write(time + "\n");
                            writer.write("football" + "\n");
                            writer.write(res + "\n");
                            writer.close();
                        }
                     } catch (IOException ex) {
                       Logger.getLogger(mainFace.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     JOptionPane.showMessageDialog(this, "the train has been successfully added");
                     trainTextArea.setText("");
                    List<train> trains = temp.getTrains();
                    for (int i = 0; i < trains.size(); i++) {
                        trainTextArea.append(trains.get(i).toString() + "\n");
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "There has already been a train on this day.");
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Please enter number for the time");
            }
          }else{
            JOptionPane.showMessageDialog(this, "Please enter both the date and the time of the train");
        }
        for (Component c : trainAddFrame.getContentPane().getComponents()) {
            if (c instanceof JTextField) {
                JTextField inputTextField = (JTextField) c;
                inputTextField.setText("");
            }
        }
    }//GEN-LAST:event_addTrainButtonActionPerformed

    private void trainAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainAddButtonActionPerformed
       trainAddFrame.setVisible(true);
       trainAddFrame.pack();
    }//GEN-LAST:event_trainAddButtonActionPerformed

    private void trainSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainSearchButtonActionPerformed
        String date=trainTextField.getText().trim();
        if(date.equals("")){
            JOptionPane.showMessageDialog(this, "Pelase enter the date in the search field");
            return;
        }
        trainTextArea.setText("");
        train t=temp.searchTrain(date);
        if(t==null){
            JOptionPane.showMessageDialog(this, "There is no such a train so far");
        }else{
            trainTextArea.append(t.toString());
        }
    }//GEN-LAST:event_trainSearchButtonActionPerformed

    private void trainReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainReportButtonActionPerformed
        trainReportFrame.setVisible(true);
        trainReportFrame.pack();
    }//GEN-LAST:event_trainReportButtonActionPerformed

    private void reportTrainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportTrainButtonActionPerformed
        String date=trainReportDateTextField.getText().trim();
        String memStr=trainReportMemberTextField.getText().trim();
        trainReportDateTextField.setText("");
        trainReportMemberTextField.setText("");
        if(date.isEmpty()||memStr.isEmpty()){
            JOptionPane.showMessageDialog(this, "Don't leave the text fields empty");
            return;
        }
        if(temp.searchTrain(date)==null){
            JOptionPane.showMessageDialog(this, "There is no such a date of train so far");
            return;
        }
        if(temp.searchTrain(date).getLate().size()>0){
            JOptionPane.showMessageDialog(this, "Sorry. The train has already been reported. You cannot report again");
            return;
        }
        String[] names=memStr.split(separator);
        boolean flag=temp.reportTrain(date, names);
        if(flag){
            JOptionPane.showMessageDialog(this, "You have successfully reported");
            saveData();
            trainTextArea.setText("");
            List<train> trains = temp.getTrains();
            for (int i = 0; i < trains.size(); i++) {
                trainTextArea.append(trains.get(i).toString() + "\n");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Please check spellings of members");
        }
    }//GEN-LAST:event_reportTrainButtonActionPerformed

    private void cancelTrainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelTrainButtonActionPerformed
        String date=trainCancelTextArea.getText().trim();
        if(date.isEmpty()){
            JOptionPane.showMessageDialog(this, "Don't leave the text fields empty");
            return;
        }
        if(!temp.cancelTrain(date)){
            JOptionPane.showMessageDialog(this, "There is no such train. Please recheck the date");
            return;
        }
        File trainFiles=new File("trains/soccer");
        if(temp==basketball){
           trainFiles=new File("trains/basketball");
        }else if(temp==baseball){
            trainFiles=new File("trains/baseball");
        }else if(temp==badminton){
            trainFiles=new File("trains/badminton");
        }else if(temp==volleyball){
            trainFiles=new File("trains/volleyball");
        }else if(temp==football){
            trainFiles=new File("trains/football");
        }
        File[] trains=trainFiles.listFiles();
        for(File file:trains){
            if(file.getName().equals(date)){
                file.delete();
            }
        }
        JOptionPane.showMessageDialog(this, "TYou have successfully deleted the training");
        trainTextArea.setText("");
        List<train> ts = temp.getTrains();
        for (int i = 0; i < ts.size(); i++) {
            trainTextArea.append(ts.get(i).toString() + "\n");
        }
        trainCancelTextArea.setText("");
    }//GEN-LAST:event_cancelTrainButtonActionPerformed

    private void trainCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainCancelButtonActionPerformed
        trainCancelFrame.setVisible(true);
        trainCancelFrame.pack();
    }//GEN-LAST:event_trainCancelButtonActionPerformed

    private void matchAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchAddButtonActionPerformed
        matchAddFrame.setVisible(true);
        matchAddFrame.pack();
    }//GEN-LAST:event_matchAddButtonActionPerformed

    private void addMatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMatchButtonActionPerformed
        if(verifyAddMatchInput()){
            try{
                String date=matchAddDateTextFiled.getText().trim();
                int oppScore=Integer.parseInt(matchAddOppScoreTextFiled.getText().trim());
                int ourScore=Integer.parseInt(matchAddOurScrTextFiled.getText().trim());
                String res=matchAddWinTextFiled.getText().trim();
                String allMembers=matchAddPreTextFiled.getText().trim();
                String[] firstTeam=allMembers.split(separator);
                String oppName=matchOppNameTextFiled.getText().trim();
                boolean win=false;
                if(temp.searchMatch(date)!=null){
                    JOptionPane.showMessageDialog(this, "There has already been a match on such date");
                    return;
                }
                if(res.equals("win")){
                    win=true;
                }else if(res.equals("lose")){
                    win=false;
                }else{
                    JOptionPane.showMessageDialog(this, "Please either 'win' or'lose' in the corresponding field");
                    return;
                }
                List<String> present=new ArrayList<>();
                for(int i=0;i<firstTeam.length;i++){
                    present.add(firstTeam[i]);
                    if(!temp.containsMem(firstTeam[i])){
                        JOptionPane.showMessageDialog(this, "Please check the spelling of first team members' names");
                        return;
                    }
                }
                match mat=temp.addMatch(oppName,date,win,ourScore,oppScore);
                mat.setPre(present);
                try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter("matches/soccer/" + date));
                        if(temp==basketball){
                            writer = new BufferedWriter(new FileWriter("matches/basketball/" + date));
                        }else if(temp==baseball){
                            writer = new BufferedWriter(new FileWriter("matches/baseball/" + date));
                        }else if(temp==badminton){
                            writer = new BufferedWriter(new FileWriter("matches/badminton/" + date));
                        }else if(temp==volleyball){
                            writer = new BufferedWriter(new FileWriter("matches/volleyball/" + date));
                        }else if(temp==football){
                            writer = new BufferedWriter(new FileWriter("matches/football/" + date));
                        }
                        writer.write(mat.getName()+"\n");
                        writer.write(oppName+"\n");
                        writer.write(date+"\n");
                        writer.write(res+"\n");
                        writer.write(String.valueOf(ourScore)+'\n');
                        writer.write(String.valueOf(oppScore+"\n"));
                        writer.write(allMembers+"\n");
                        writer.close();
                     } catch (IOException ex) {
                       Logger.getLogger(mainFace.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Please enter the score int terms of numbers");
            }
            JOptionPane.showMessageDialog(this, "Succesfully added!");
            matchTextArea.setText("");
            List<match> matches = temp.getMatches();
            for (int i = 0; i < matches.size(); i++) {
                matchTextArea.append(matches.get(i).toString() + "\n");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Please do not leave any fields empty");
        }
    }//GEN-LAST:event_addMatchButtonActionPerformed

    private void matchnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchnButtonActionPerformed
        matchFrame.setVisible(true);
        matchFrame.pack();
        matchTextArea.setText("");
        List<match> matches=temp.getMatches();
        for(int i=0;i<matches.size();i++){
            matchTextArea.append(matches.get(i).toString()+"\n");
        }
    }//GEN-LAST:event_matchnButtonActionPerformed

    private void matchSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchSearchButtonActionPerformed
        String date=matchSearchTextField.getText().trim();
        if(date.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please type in the date of match that you are searching for");
            return;
        }
        match mat=temp.searchMatch(date);
        if(mat==null){
            JOptionPane.showMessageDialog(this, "No such match found");
            return;
        }
        matchTextArea.setText("");
        matchTextArea.append(mat.toString());
    }//GEN-LAST:event_matchSearchButtonActionPerformed

    private void removeMatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMatchButtonActionPerformed
        String date = matchRemoveTextArea.getText().trim();
        if (date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Don't leave the text field empty");
            return;
        }
        if (!temp.removeMatch(date)) {
            JOptionPane.showMessageDialog(this, "There is no such train. Please recheck the date");
            return;
        }
        File matchFiles = new File("matches/soccer");
        if(temp==basketball){
            matchFiles = new File("matches/basketball");
        }else if(temp==badminton){
            matchFiles = new File("matches/badminton");
        }else if(temp==baseball){
            matchFiles = new File("matches/baseball");
        }else if(temp==volleyball){
            matchFiles = new File("matches/volleyball");
        }else if(temp==football){
            matchFiles = new File("matches/football");
        }
        File[] m = matchFiles.listFiles();
        for (File file : m) {
            if (file.getName().equals(date)) {
                file.delete();
            }
        }
        matchTextArea.setText("");
        List<match> matches=temp.getMatches();
        for(int i=0;i<matches.size();i++){
            matchTextArea.append(matches.get(i).toString()+"\n");
        }
        JOptionPane.showMessageDialog(this, "TYou have successfully deleted the training");
        trainCancelTextArea.setText("");    
    }//GEN-LAST:event_removeMatchButtonActionPerformed

    private void matchRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchRemoveButtonActionPerformed
        matchRemoveFrame.setVisible(true);
        matchRemoveFrame.pack();
    }//GEN-LAST:event_matchRemoveButtonActionPerformed

    private void basketballButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basketballButtonActionPerformed
        temp=basketball;
        TeamFrame.setVisible(true);
        TeamFrame.pack();
        teamLable.setText("basketball");
    }//GEN-LAST:event_basketballButtonActionPerformed

    private void footballButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_footballButtonActionPerformed
        temp=football;
        TeamFrame.setVisible(true);
        TeamFrame.pack();
        teamLable.setText("football");
    }//GEN-LAST:event_footballButtonActionPerformed

    private void volleyballButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volleyballButtonActionPerformed
        temp=volleyball;
        TeamFrame.setVisible(true);
        TeamFrame.pack();
        teamLable.setText("volleyball");
    }//GEN-LAST:event_volleyballButtonActionPerformed

    private void matchAddOppScoreTextFiledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchAddOppScoreTextFiledActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matchAddOppScoreTextFiledActionPerformed

    private boolean verifyAddMemberInput() {
        for (Component c : addMemberFrame.getContentPane().getComponents()) {
            if (c instanceof JTextField) {
                JTextField inputTextField = (JTextField) c;
                if (inputTextField.getText().trim().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean verifyAddMatchInput() {
        for (Component c : matchAddFrame.getContentPane().getComponents()) {
            if (c instanceof JTextField) {
                JTextField inputTextField = (JTextField) c;
                if (inputTextField.getText().trim().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean verifyAddTrainInput(){
        for (Component c : trainAddFrame.getContentPane().getComponents()) {
            if (c instanceof JTextField) {
                JTextField inputTextField = (JTextField) c;
                if (inputTextField.getText().trim().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void soccerClearCheckBoxes() {
        for (Component c : memberFrame.getComponents()) {
            if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }
    }
    
    private void saveData(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Members.csv")));
            for(String psn: soccer.findAllMembers().keySet()){
                List<member> members=soccer.findAllMembers().get(psn);
                for(member mem:members){
                    //System.out.println(mem);
                    writer.write("soccer,"+mem.toCSV()+psn+"\n");
                    writer.flush();
                }
            }
            for(String psn: badminton.findAllMembers().keySet()){
                List<member> members=badminton.findAllMembers().get(psn);
                for(member mem:members){
                    writer.write("badminton,"+mem.toCSV()+psn+"\n");
                    writer.flush();
                }
            }
            for(String psn: basketball.findAllMembers().keySet()){
                List<member> members=basketball.findAllMembers().get(psn);
                for(member mem:members){
                    writer.write("basketball,"+mem.toCSV()+psn+"\n");
                    writer.flush();
                }
            }
            for(String psn: baseball.findAllMembers().keySet()){
                List<member> members=baseball.findAllMembers().get(psn);
                for(member mem:members){
                    writer.write("baseball,"+mem.toCSV()+psn+"\n");
                    writer.flush();
                }
            }
            for(String psn: football.findAllMembers().keySet()){
                List<member> members=football.findAllMembers().get(psn);
                for(member mem:members){
                    writer.write("football,"+mem.toCSV()+psn+"\n");
                    writer.flush();
                }
            }
            for(String psn: volleyball.findAllMembers().keySet()){
                List<member> members=volleyball.findAllMembers().get(psn);
                for(member mem:members){
                    writer.write("volleyball,"+mem.toCSV()+psn+"\n");
                    writer.flush();
                }
            }
            writer.close();
        }catch(IOException ex){
            Logger.getLogger(mainFace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            File soccerFolder=new File("trains/soccer");
            File[] soccerTrainFiles=soccerFolder.listFiles();
            for(File file :soccerTrainFiles){
                train t=soccer.searchTrain(file.getName());
                if (t != null) {
                    //System.out.println(file.getName());
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(t.getDate());
                    writer.newLine();
                    writer.write(String.valueOf(t.getTime()));
                    writer.newLine();
                    writer.write(t.getName());
                    writer.newLine();
                    writer.write(t.dueToCSV());
                    writer.newLine();
                    if (t.getLate().size() > 0) {
                        writer.write(t.lateToCSV());
                    }
                    writer.close();
                }
            }
            File baseballFolder=new File("trains/baseball");
            File[] baseballTrainFiles=baseballFolder.listFiles();
            for(File file :baseballTrainFiles){
                train t=baseball.searchTrain(file.getName());
                if (t != null) {
                    //System.out.println(file.getName());
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(t.getDate());
                    writer.newLine();
                    writer.write(String.valueOf(t.getTime()));
                    writer.newLine();
                    writer.write(t.getName());
                    writer.newLine();
                    writer.write(t.dueToCSV());
                    writer.newLine();
                    if (t.getLate().size() > 0) {
                        writer.write(t.lateToCSV());
                    }
                    writer.close();
                }
            }
            File basketballFolder=new File("trains/basketball");
            File[] basketballTrainFiles=basketballFolder.listFiles();
            for(File file :basketballTrainFiles){
                train t=basketball.searchTrain(file.getName());
                if (t != null) {
                    //System.out.println(file.getName());
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(t.getDate());
                    writer.newLine();
                    writer.write(String.valueOf(t.getTime()));
                    writer.newLine();
                    writer.write(t.getName());
                    writer.newLine();
                    writer.write(t.dueToCSV());
                    writer.newLine();
                    if (t.getLate().size() > 0) {
                        writer.write(t.lateToCSV());
                    }
                    writer.close();
                }
            }
            File badmintonFolder=new File("trains/badminton");
            File[] badmintonTrainFiles=badmintonFolder.listFiles();
            for(File file :badmintonTrainFiles){
                train t=badminton.searchTrain(file.getName());
                if (t != null) {
                    //System.out.println(file.getName());
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(t.getDate());
                    writer.newLine();
                    writer.write(String.valueOf(t.getTime()));
                    writer.newLine();
                    writer.write(t.getName());
                    writer.newLine();
                    writer.write(t.dueToCSV());
                    writer.newLine();
                    if (t.getLate().size() > 0) {
                        writer.write(t.lateToCSV());
                    }
                    writer.close();
                }
            }
            File footballFolder=new File("trains/football");
            File[] footballTrainFiles=footballFolder.listFiles();
            for(File file :footballTrainFiles){
                train t=football.searchTrain(file.getName());
                if (t != null) {
                    //System.out.println(file.getName());
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(t.getDate());
                    writer.newLine();
                    writer.write(String.valueOf(t.getTime()));
                    writer.newLine();
                    writer.write(t.getName());
                    writer.newLine();
                    writer.write(t.dueToCSV());
                    writer.newLine();
                    if (t.getLate().size() > 0) {
                        writer.write(t.lateToCSV());
                    }
                    writer.close();
                }
            }
            File volleyballFolder=new File("trains/volleyball");
            File[] volleyballTrainFiles=volleyballFolder.listFiles();
            for(File file :volleyballTrainFiles){
                train t=volleyball.searchTrain(file.getName());
                if (t != null) {
                    //System.out.println(file.getName());
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(t.getDate());
                    writer.newLine();
                    writer.write(String.valueOf(t.getTime()));
                    writer.newLine();
                    writer.write(t.getName());
                    writer.newLine();
                    writer.write(t.dueToCSV());
                    writer.newLine();
                    if (t.getLate().size() > 0) {
                        writer.write(t.lateToCSV());
                    }
                    writer.close();
                }
            }
        }catch (IOException ex) {
            Logger.getLogger(mainFace.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFace().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame TeamFrame;
    private javax.swing.JButton addMatchButton;
    private javax.swing.JFrame addMemberFrame;
    private javax.swing.JTextField addMemberGenderTextField;
    private javax.swing.JTextField addMemberGradeTextField;
    private javax.swing.JTextField addMemberHeightTextField;
    private javax.swing.JTextField addMemberNameTextField;
    private javax.swing.JTextField addMemberPositionTextField;
    private javax.swing.JTextField addMemberWeightTextField;
    private javax.swing.JButton addTrainButton;
    private javax.swing.JTextField addTrainDateTextField;
    private javax.swing.JTextField addTrainTimeTextField;
    private javax.swing.JFrame approveLeaveFrame;
    private javax.swing.JTextField approveLeaveTextField;
    private javax.swing.JButton badmintonButton;
    private javax.swing.JButton baseballButton;
    private javax.swing.JButton basketballButton;
    private javax.swing.JButton cancelTrainButton;
    private javax.swing.JButton footballButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton matchAddButton;
    private javax.swing.JTextField matchAddDateTextFiled;
    private javax.swing.JFrame matchAddFrame;
    private javax.swing.JTextField matchAddOppScoreTextFiled;
    private javax.swing.JTextField matchAddOurScrTextFiled;
    private javax.swing.JTextField matchAddPreTextFiled;
    private javax.swing.JTextField matchAddWinTextFiled;
    private javax.swing.JFrame matchFrame;
    private javax.swing.JTextField matchOppNameTextFiled;
    private javax.swing.JButton matchRemoveButton;
    private javax.swing.JFrame matchRemoveFrame;
    private javax.swing.JTextField matchRemoveTextArea;
    private javax.swing.JButton matchSearchButton;
    private javax.swing.JTextField matchSearchTextField;
    private javax.swing.JTextArea matchTextArea;
    private javax.swing.JButton matchnButton;
    private javax.swing.JButton memberAddButton;
    private javax.swing.JButton memberApprLeaveButton;
    private javax.swing.JFrame memberFrame;
    private javax.swing.JButton memberRemoveButton;
    private javax.swing.JFrame memberRemoveFrame;
    private javax.swing.JButton memberSearchButton;
    private javax.swing.JTextArea memberTextArea;
    private javax.swing.JTextField memberTextField;
    private javax.swing.JCheckBox nameSearch;
    private javax.swing.JCheckBox performanceSearch;
    private javax.swing.JButton playersButton;
    private javax.swing.JCheckBox positionSearch;
    private javax.swing.JButton rectifyButton;
    private javax.swing.JButton removeMatchButton;
    private javax.swing.JButton reportTrainButton;
    private javax.swing.JButton soccerAddMemberButton;
    private javax.swing.JButton soccerApproveLeaveButton;
    private javax.swing.JButton soccerButton;
    private javax.swing.JTextField soccerMemberRemoveTextField;
    private javax.swing.JButton soccerRemoveMemberButton;
    private javax.swing.JLabel teamLable;
    private javax.swing.JButton trainAddButton;
    private javax.swing.JFrame trainAddFrame;
    private javax.swing.JButton trainButton;
    private javax.swing.JButton trainCancelButton;
    private javax.swing.JFrame trainCancelFrame;
    private javax.swing.JTextField trainCancelTextArea;
    private javax.swing.JFrame trainFrame;
    private javax.swing.JButton trainReportButton;
    private javax.swing.JTextField trainReportDateTextField;
    private javax.swing.JFrame trainReportFrame;
    private javax.swing.JTextField trainReportMemberTextField;
    private javax.swing.JButton trainSearchButton;
    private javax.swing.JTextArea trainTextArea;
    private javax.swing.JTextField trainTextField;
    private javax.swing.JButton volleyballButton;
    // End of variables declaration//GEN-END:variables
}
