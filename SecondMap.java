package com.mycompany.urbanplanning3;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author user
 */
public class SecondMap extends javax.swing.JFrame {
    String input444="\nLegend:\n\n<>^v-Road\nR-Railway Station\n";
    int row,column,counter;
    String symbol[];
    int color1[],color2[],color3[];
    @SuppressWarnings("empty-statement")
    public SecondMap() {
        
        try{
            Scanner sc=new Scanner(new FileInputStream("rnc.txt"));
            String input=sc.nextLine();
            String input12[]=input.split(" ");
            row=Integer.parseInt(input12[0]);
            column=Integer.parseInt(input12[1]);
            sc.close();
        }catch(FileNotFoundException e){
            
        }     
        String input1;
        counter=0;
        try{
            Scanner sc1=new Scanner(new FileInputStream("userinput.txt"));
            while(sc1.hasNextLine()){
                String input3=sc1.nextLine();
                if(!input3.isEmpty()&&!input3.isBlank())
                    counter++;
                else 
                    break;
            }
            sc1.close();
        }catch(FileNotFoundException e){
            
        }
        symbol=new String[counter];
        String[]build=new String[counter];
        int y=0;
        try{
            Scanner sc=new Scanner(new FileInputStream("userinput.txt"));
            while(sc.hasNextLine()){
 
                    input1=sc.nextLine();
                    if(!input1.isEmpty()&&!input1.isBlank()){
                    input1=input1.replaceAll("\\s", "");
                    String line[]=input1.split(",");
                    line[1]=line[1].replaceAll("\\D","");
                    line[2]=line[2].replaceAll("\\D","");
                    symbol[y]=line[0];
                    build[y]=line[3];
                    y++;
        }
            }sc.close();
        }catch(FileNotFoundException e){
                
            }   
        
        for(int r=0;r<counter;r++){
            //JOptionPane.showMessageDialog(this,input444, "Short Note:", JOptionPane.PLAIN_MESSAGE,null);
            input444=input444+symbol[r]+"-"+build[r]+"\n";
            
        }
        JTextArea jt=new JTextArea();
        jt.setText(input444);
        jt.setBounds(880,50,200,300);
        jt.setEditable(false);
        add(jt);
        
       // JScrollPane scroll1=new JScrollPane(jScrollPane2);
        
        //setTextF();
        //legend1.setText(input444);
        //JScrollPane JScrollpane=new JScrollPane();
        initComponents();
        //this.add(new JScrollpane(),BorderLayout.CENTER);
                
        try{
            Scanner sc=new Scanner(new FileInputStream("rnc.txt"));
            String input=sc.nextLine();
            String input12[]=input.split(" ");
            row=Integer.parseInt(input12[0]);
            column=Integer.parseInt(input12[1]);
            sc.close();
        }catch(FileNotFoundException e){
            
        }        
        String [][]map1=new String [row][column];
        //JOptionPane.showMessageDialog(this,row+" "+column, "Short Note:", JOptionPane.PLAIN_MESSAGE,null);
        
        try{
            Scanner sc=new Scanner(new FileInputStream("subway.txt"));
            //*******jinghui de  Scanner sc=new Scanner(new FileInputStream("newmaps.txt"));
            System.out.println(row+" "+column);
            for(int r=0;r<row;r++){
                    String k[]=sc.nextLine().split("\\|");
                    for(int c=0;c<column;c++){
                        map1[r][c]=k[c];
                        System.out.print(map1[r][c]+"|");
                    }
                    System.out.println();
                }sc.close();
            }
        catch(FileNotFoundException e){
            
        }
        String []col ;
    /*    
        for(int r=0;r<column;r++){
     /*       col[r]="----";
        }
          */
          String[]coli1=null;
        if(column==20){
            String []coli={"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==22){
            String []coli={"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }else if(column==23){
            String []coli={"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==24){
            String []coli={"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==25){
            String []coli={"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==26){
            String []coli={"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==27){
            String []coli={"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==29){
            String []coli={"A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==30){
            String []coli={"A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
         else if(column==31){
            String []coli={"A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
          else if(column==32){
            String []coli={"a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
           else if(column==33){
            String []coli={"A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
           else if(column==34){
            String []coli={"a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
           else if(column==35){
            String []coli={"A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
           else if(column==36){
            String []coli={"a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
           else if(column==37){
            String []coli={"a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==38){
            String []coli={"A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==40){
            String []coli={"a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==41){
            String []coli={"a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==42){
            String []coli={"a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }else if(column==44){
            String []coli={"a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==45){
            String []coli={"a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==46){
            String []coli={"A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==48){
            String []coli={"A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==49){
            String []coli={"A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==50){
            String []coli={"A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==51){
            String []coli={"a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==58){
            String []coli={"A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }else if(column==61){
            String []coli={"A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==62){
            String []coli={"a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==63){
            String []coli={"a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==64){
            String []coli={"A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==68){
            String []coli={"A","A","a","a","A","A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==72){
            String []coli={"A","a","A","a","A","A","a","a","A","A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==73){
            String []coli={"a","a","a","A","a","A","A","a","a","A","A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==74){
            String []coli={"a","a","a","a","a","A","a","A","A","a","a","A","A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==71){
            String []coli={"a","A","a","A","A","a","a","A","A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==70){
            String []coli={"A","a","A","A","a","a","A","A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==69){
            String []coli={"a","A","A","a","a","A","A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==65){
            String []coli={"A","A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==66){
            String []coli={"a","A","A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==68){
            String []coli={"A","a","a","A","A","a","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==67){
            String []coli={"a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==68){
            String []coli={"a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        
       
        else if(column==83){
            String []coli={"a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==85){
            String []coli={"A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==86){
            String []coli={"a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==94){
            String []coli={"A","A","a","A","A","A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==95){
            String []coli={"A","A","A","a","A","A","A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==96){
            String []coli={"A","A","A","A","a","A","A","A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==97){
            String []coli={"A","A","A","A","A","a","A","A","A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==98){
            String []coli={"A","A","A","A","A","A","a","A","A","A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
         else if(column==99){
            String []coli={"a","A","A","A","A","A","A","a","A","A","A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==93){
            String []coli={"A","a","A","A","A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==92){
            String []coli={"a","A","A","A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==91){
            String []coli={"A","A","A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==90){
            String []coli={"A","A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==89){
            String []coli={"A","a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==88){
            String []coli={"a","A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==87){
            String []coli={"A","a","A","A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==84){
            String []coli={"A","a","a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==82){
            String []coli={"a","A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==81){
            String []coli={"A","A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==80){
            String []coli={"A","a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==79){
            String []coli={"a","A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==74){
            String []coli={"A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==78){
            String []coli={"A","a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==77){
            String []coli={"a","a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==76){
            String []coli={"a","A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==75){
            String []coli={"A","A","A","a","a","A","a","a","a","a","A","A","a","A","A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==60){
            String []coli={"A","A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==59){
            String []coli={"A","A","A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==57){
            String []coli={"A","a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==56){
            String []coli={"a","A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==55){
            String []coli={"A","a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }else if(column==54){
            String []coli={"a","a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==53){
            String []coli={"a","a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==52){
            String []coli={"a","a","A","A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==47){
            String []coli={"A","A","A","a","a","a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==43){
            String []coli={"a","a","a","a","a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==39){
            String []coli={"a","A","a","a","A","a","A","a","A","A","A","A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==28){
            String []coli={"A","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==21){
            String []coli={"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        else if(column==100){
            String []coli={"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
            coli1=coli;
        }
        JTableHeader Theader=map_two.getTableHeader();
        Theader.setVisible(false);
        //DefaultTableModel model=(DefaultTableModel)map_one.getModel();
        DefaultTableModel model=new DefaultTableModel(null,coli1);
        map_two.setModel(model);
        map_two.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //////////////
        //DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        //TableColumnModel column2 = map_one.getColumnModel();
        for (int r=0;r<column;r++) {
            map_two.getColumnModel().getColumn(r).setPreferredWidth(27);
            //map_one.getColumnModel().getColumn(r).setCellRenderer(cellRenderer);
            //column2.getColumn(r).setWidth(200);
        }
        ///////////
        //JScrollPane spane=new JScrollPane(map_one);
        //getContentPane().add(spane);
        /*
        String line1[]=new String [column];
        for(int y=0;y<column;y++){
        line1[y]=map1[0][y];
        }
         */
        //DefaultTableModel model=DefaultTableModel (column,row);
        // model.setColumnCount(column);
        //map_one.setModel(new DefaultTableMode(0,column));
        for (String[] map11 : map1) {
            String[] row1 = new String[map11.length];
            for (int j = 0; j < map11.length; j++) {
                if (map11[j].contains("a")) {
                    row1[j]="L1";
                } else if (map11[j].contains("b")) {
                    row1[j]="L2";
                } else if (map11[j].contains("d")) {
                    row1[j]="L3";
                } else if (map11[j].contains("e")) {
                    row1[j]="L4";
                } else {
                    row1[j] = map11[j];
                }
            }
            model.addRow(row1);
        }
        int co=1;
        String[]testcolor=new String[counter];
        color1=new int [counter];
        color2=new int[counter];
        color3=new int[counter];
        for(int u=0;u<counter;){
            Boolean checki=true;
            Random rand=new Random();
            
            //JOptionPane.showMessageDialog(this,color1[u], "Short Note:", JOptionPane.PLAIN_MESSAGE,null);
            //JOptionPane.showMessageDialog(this,color1[u]+" "+rand.nextInt(256), "Short Note:", JOptionPane.PLAIN_MESSAGE,null);
            color1[u]=rand.nextInt(256);
            color2[u]=rand.nextInt(256);
            color3[u]=rand.nextInt(256);
            testcolor[u]=Integer.toString(color1[u])+" "+Integer.toString(color2[u])+" "+Integer.toString(color3[u]);
            for(int s=0;s<u;s++){
                if(testcolor[u].equals(testcolor[s]))
                    checki=false;
            }
            if(checki)
                u++;
        }
        //map_one.setDefaultRenderer(Object.class, rend);
        TableCell1 tc=new TableCell1();
        map_two.setDefaultRenderer(Object.class,tc);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        map_two = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Second Map");

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("Map 1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Restart");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Quit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        map_two.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane2.setViewportView(map_two);

        jScrollPane3.setViewportView(jScrollPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(94, 94, 94))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(59, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FirstMap m1=new FirstMap();
                        m1.setVisible(true);
                        m1.pack();
                        m1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        m1.setLocationRelativeTo(null);
                        //this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Input m1=new Input();
                        m1.setVisible(true);
                        m1.pack();
                        m1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        m1.setLocationRelativeTo(null);
                        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(SecondMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SecondMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SecondMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SecondMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SecondMap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable map_two;
    // End of variables declaration//GEN-END:variables
}
