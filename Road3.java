package com.mycompany.urbanplanning3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Road3 {

    public static void main(String[] args) {}
    Road3(){
         int width=0,length=0,counter=0;
        try{
             Scanner sc = new Scanner(new FileInputStream("mapdisplay.txt"));
             while(sc.hasNextLine()){
                 String s= sc.nextLine();
                 String[] parameter=s.split("\\|");
                 width=parameter.length;
                 counter++;
                }
           }catch(FileNotFoundException e){
                System.out.println("problem with file input.");
            }
           length=counter;
           String[][] building= new String[length][width];
                
        try{
            Scanner scan = new Scanner(new FileInputStream("mapdisplay.txt"));
            int k=0;
            while(scan.hasNextLine()){
                String s1= scan.nextLine();
                String[] parameter=s1.split("\\|");
                for(int j=0;j<parameter.length;j++){
                    building[k][j]=parameter[j];
                }
                 k++;
             }
        }catch(FileNotFoundException e){
            System.out.println("problem with file input.");
        }
        int row=length;
        int column=width;
        int[][] nummaps= new int[row][column];  
        try{
                Scanner sc = new Scanner(new FileInputStream("numberforroad.txt"));
                //numberforroad.txt
                while(sc.hasNextLine()){
                    for(int i=0;i<row;i++){
                        String s= sc.nextLine();
                        String[] parameters=s.split(" ");
                        for(int j=0;j<column;j++){
                            nummaps[i][j]=Integer.parseInt(parameters[j]);
                        }
                    }
                }
            }catch(FileNotFoundException e){
            System.out.println("problem with file input.");
        }
        String[][] railway=new String[row][column];//railway station
        String[][] subway=new String[row][column];//subway road
        int centerx=row/2;
        int centery=column/2;
        //initialize all railway to space
         for(int i=0;i<railway.length;i++){ //row
            for(int j=0;j<railway[i].length;j++){ //column
                railway[i][j]=" ";
            }
         }
         //initialize all subway to space
         for(int i=0;i<subway.length;i++){ //row
            for(int j=0;j<subway[i].length;j++){ //column
                subway[i][j]=" ";
            }
        }
//********************************************************************************
        //scan building to build railway station
        
        int[] checkbuilding=new int[10000];
        int countbuilding=1;
        checkbuilding[countbuilding]=1;
        countbuilding=2;
        boolean gtbuilding=false;
        //upper left
        for(int i=0;i<=centerx;i++){
            for(int j=0;j<=centery;j++){
                 gtbuilding=false;
                     if((!building[i][j].equals(" "))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){ 
                          for(int count1=0;count1<countbuilding;count1++){
                              if(nummaps[i][j]==checkbuilding[count1] ){
                                  gtbuilding=true;
                              }
                              if(gtbuilding)
                                  break;
                          }
                          if(gtbuilding){
                              continue;
                          }else{
                              railway[i][j]="R";
                              checkbuilding[countbuilding]=nummaps[i][j];
                              countbuilding++;
                          }
                      }
            }
        }
        //upper right
        for(int i=0;i<=centerx;i++){
            for(int j=building[i].length-1;j>centery;j--){
                 gtbuilding=false;
                     if((!building[i][j].equals(" "))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){ 
                         for(int count1=0;count1<countbuilding;count1++){
                              if(nummaps[i][j]==checkbuilding[count1] ){
                                  gtbuilding=true;
                              }
                              if(gtbuilding)
                                  break;
                          }
                          if(gtbuilding){
                              continue;
                          }else{
                              railway[i][j]="R";
                              checkbuilding[countbuilding]=nummaps[i][j];
                              countbuilding++;
                          }
                      }
            }
        }
        //lower left
        for(int i=building.length-1;i>centerx;i--){
            for(int j=0;j<=centery;j++){
                 gtbuilding=false;
                     if((!building[i][j].equals(" "))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){ 
                          for(int count1=0;count1<countbuilding;count1++){
                              if(nummaps[i][j]==checkbuilding[count1] ){
                                  gtbuilding=true;
                              }
                              if(gtbuilding)
                                  break;
                          }
                          if(gtbuilding){
                              continue;
                          }else{
                              railway[i][j]="R";
                              checkbuilding[countbuilding]=nummaps[i][j];
                              countbuilding++;
                          }
                      }
            }
        }
        //lower right
        for(int i=building.length-1;i>centerx;i--){
            for(int j=building[i].length-1;j>centery;j--){
                 gtbuilding=false;
                     if((!building[i][j].equals(" "))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){ 
                         for(int count1=0;count1<countbuilding;count1++){
                              if(nummaps[i][j]==checkbuilding[count1] ){
                                  gtbuilding=true;
                              }
                              if(gtbuilding)
                                  break;
                          }
                          if(gtbuilding){
                              continue;
                          }else{
                              railway[i][j]="R";
                              checkbuilding[countbuilding]=nummaps[i][j];
                              countbuilding++;
                          }
                      }
            }
        }
//********************************************************************************
//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        //to build road for subway 
        int tx1=0,ty1=0,tx2=0,ty2=0;
        boolean station=false;
        
        for(int i=0;i<=centerx;i++){ //upper left
                for(int j=0;j<=centery;j++){
                    station=false;
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<=centerx;c++){
                            for(int d=0;d<=centery;d++){
                                if(railway[c][d].equals("R")){
                                    if(c==i && d<=j) //dw scan back same station 
                                        continue;
                                    tx1=i;
                                    ty1=j;
                                    tx2=c;
                                    ty2=d;
                                    station=true;
                                }
                                if(station)
                                    break;
                            }
                            if(station)
                                break;
                        }
                        if(station){
                            if(ty1<=ty2){
                                for(int e=tx1;e<=tx2;e++){
                                    subway[e][ty1]="v^";
                                }
                                for(int f=ty1;f<=ty2;f++){
                                    subway[tx2][f]="<>";
                                }
                            }
                            else{
                                for(int e=tx1;e<=tx2;e++){
                                    subway[e][ty1]="v^";
                                }
                                for(int f=ty1;f>=ty2;f--){
                                    subway[tx2][f]="<>";
                                }
                                
                            }
                        }
                    }
                }
            }
        
        for(int i=0;i<=centerx;i++){ //upper right
                for(int j=centery+1;j<railway.length;j++){
                    station=false;
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<=centerx;c++){
                            for(int d=centery+1;d<railway[c].length;d++){
                                if(railway[c][d].equals("R")){
                                    if(c==i && d<=j) //dw scan back same station 
                                        continue;
                                    tx1=i;
                                    ty1=j;
                                    tx2=c;
                                    ty2=d;
                                    station=true;
                                    //System.out.println(tx1+" "+ty1+" "+tx2+" "+ty2);
                                }
                                if(station)
                                    break;
                            }
                            if(station)
                                break;
                        }
                        if(station){
                            if(ty1<=ty2){
                                for(int e=tx1;e<=tx2;e++){
                                    subway[e][ty1]="v^";
                                }
                                for(int f=ty1;f<=ty2;f++){
                                    subway[tx2][f]="<>";
                                }
                            }
                            else{
                                for(int e=tx1;e<=tx2;e++){
                                    subway[e][ty1]="^v";
                                }
                                for(int f=ty1;f>=ty2;f--){
                                    subway[tx2][f]="<>";
                                }
                                
                            }
                        }
                    }
                }
            }
        
        for(int i=centerx+1;i<railway.length;i++){ //lower left
                for(int j=0;j<=centery;j++){
                    station=false;
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<railway.length;c++){
                            for(int d=0;d<=centery;d++){
                                if(railway[c][d].equals("R")){
                                    if(c==i && d<=j) //dw scan back same station 
                                        continue;
                                    tx1=i;
                                    ty1=j;
                                    tx2=c;
                                    ty2=d;
                                    station=true;
                                }
                                if(station)
                                    break;
                            }
                            if(station)
                                break;
                        }
                        if(station){
                            if(ty1<=ty2){
                                for(int e=tx1;e<=tx2;e++){
                                    subway[e][ty2]="^v";
                                }
                                for(int f=ty1;f<=ty2;f++){
                                    subway[tx1][f]="<>";
                                }
                            }
                            else{
                                for(int e=tx1;e<=tx2;e++){
                                    subway[e][ty2]="^v";
                                }
                                for(int f=ty1;f>=ty2;f--){
                                    subway[tx1][f]="<>";
                                }
                                
                            }
                        }
                    }
                }
            }

        for(int i=centerx+1;i<railway.length;i++){ //lower right
                for(int j=centery+1;j<railway[i].length;j++){
                    station=false;
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<railway.length;c++){
                            for(int d=centery+1;d<railway[i].length;d++){
                                if(railway[c][d].equals("R")){
                                    if(c==i && d<=j) //dw scan back same station 
                                        continue;
                                    tx1=i;
                                    ty1=j;
                                    tx2=c;
                                    ty2=d;
                                    station=true;
                                }
                                if(station)
                                    break;
                            }
                            if(station)
                                break;
                        }
                        if(station){
                            if(ty1<=ty2){
                                for(int e=tx1;e<=tx2;e++){
                                    subway[e][ty2]="^v";
                                }
                                for(int f=ty1;f<=ty2;f++){
                                    subway[tx1][f]="<>";
                                }
                            }
                            else{
                                for(int e=tx1;e<=tx2;e++){
                                    subway[e][ty2]="^v";
                                }
                                for(int f=ty1;f>=ty2;f--){
                                    subway[tx1][f]="<>";
                                }
                                
                            }
                        }
                    }
                }
            }
//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //print from center to station
        int tempi,tempj;
        int[] checkbuilding1=new int[10000];
        int countbuilding1=1;
        checkbuilding1[countbuilding1]=1;
        countbuilding1=2;
        boolean gtbuilding1=false;
        boolean fbuilding=false; //fillbuilding
        //upper left
        for(int i=0;i<=centerx;i++){
            for(int j=0;j<=centery;j++){
                if(railway[i][j].equals("R")){
                    for(int c=j;c<=centery;c++){
                        subway[i][c]="<>";
                    }
                }
            }
        }
        
        //upper right
        for(int i=0;i<=centerx;i++){
            for(int j=centery+1;j<building[i].length;j++){
                 if(railway[i][j].equals("R")){
                     for(int c=j;c>=centery;c--){
                        subway[i][c]="<>";
                    }
                 }
            }
        }
        
        //lower left
        for(int i=centerx+1;i<building.length;i++){
            for(int j=0;j<=centery;j++){
                 if(railway[i][j].equals("R")){
                     for(int c=j;c<=centery;c++){
                        subway[i][c]="<>";
                    }
                 }
            }
        }
        
        //upper right
        for(int i=centerx+1;i<building.length;i++){
            for(int j=centery+1;j<building[i].length;j++){
                 if(railway[i][j].equals("R")){
                     for(int c=j;c>=centery;c--){
                        subway[i][c]="<>";
                    }
                 }
            }
        }
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        //print the building back
        for(int i=0;i<building.length;i++){
            for(int j=0;j<building[i].length;j++){
                if(!building[i][j].equals(" "))
                    subway[i][j]=building[i][j];
            }
        } 
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        //print map for  railway
        System.out.println("\nrailway  map");
         //print num of column
        for(int i=0;i<column;i++){
            System.out.printf("%7d",i);
        }
        System.out.println("");
        //print subway in table
        for(int i=0;i<railway.length;i++){
            System.out.printf("%2d",i);
            for(int j=0;j<railway[i].length;j++){
                System.out.printf("| %3s  ",railway[i][j]);
            }
            System.out.println("|");
        }
        //print map for subway n railway
        System.out.println("\nsubway  map");
         //print num of column
        for(int i=0;i<column;i++){
            System.out.printf("%7d",i);
        }
        System.out.println("");
        //print subway in table
        for(int i=0;i<subway.length;i++){
            System.out.printf("%2d",i);
            for(int j=0;j<subway[i].length;j++){
                System.out.printf("| %3s  ",subway[i][j]);
            }
            System.out.println("|");
            
        }
        try{ //print string block array into text file
            PrintWriter p= new PrintWriter(new FileOutputStream("newmaps.txt"));
            for(int i=0;i<length;i++){
                for(int j=0;j<width;j++){
                    p.print(subway[i][j]);
                    p.print("|");
                }p.println();
            }
            p.close();
        }catch(IOException e){
            System.out.println("Problem with output");
        }
    }
    
}