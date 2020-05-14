/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.urbanplanning3;
import java.awt.Color;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author user
 */
public class TableCell implements TableCellRenderer {
    private final TableCellRenderer REND=new DefaultTableCellRenderer();
    String symbol[];
    int color1[];
    int color2[];
    int color3[];
    String input1;
    int counter=0;
    String[]testcolor;
    public TableCell(){
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
        testcolor=new String[counter];
        color1=new int[counter];
        color2=new int[counter];
        color3=new int [counter];
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
        
        for(int u=0;u<counter;){
            Boolean checki=true;
            Random rand=new Random();
            
            //JOptionPane.showMessageDialog(this,color1[u], "Short Note:", JOptionPane.PLAIN_MESSAGE,null);
            //JOptionPane.showMessageDialog(this,color1[u]+" "+rand.nextInt(256), "Short Note:", JOptionPane.PLAIN_MESSAGE,null);
            color1[u]=rand.nextInt(256-70)+70;
            color2[u]=rand.nextInt(256-70)+70;
            color3[u]=rand.nextInt(256-70)+70;
            testcolor[u]=Integer.toString(color1[u])+" "+Integer.toString(color2[u])+" "+Integer.toString(color3[u]);
            for(int s=0;s<u;s++){
                if(testcolor[u].equals(testcolor[s])||testcolor[u].equals("255 255 255")||testcolor[u].equals("255 0 0")||testcolor[u].equals("211 211 211"))
                    checki=false;
            }
            if(checki)
                u++;
        }
        
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c=REND.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        //Random rad=new Random();
        
        Object result=table.getModel().getValueAt(row,column);
            for(int t=0;t<counter;t++){
                
                if(result.toString().equals(symbol[t])){
                    //JOptionPane.showMessageDialog(this,color1[t]+" "+color2[t]+" "+color3[t], "Short Note:", JOptionPane.PLAIN_MESSAGE,null);
                    Color col=new Color(color1[t],color2[t],color3[t]);
                    c.setBackground(col);
                }
                else if(result.toString().isBlank()||result.toString().isEmpty())
                    c.setBackground(Color.white);
                else if(result.toString().equals("R")){
                    c.setBackground(Color.RED);
                }
                else if(result.toString().contains(">")||result.toString().contains("<")||result.toString().contains("^")||result.toString().contains("v"))
                    c.setBackground(Color.LIGHT_GRAY);
        }
        
         
        return c;
    }
    
}
