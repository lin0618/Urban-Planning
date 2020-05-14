package com.mycompany.urbanplanning3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class subway5  {

    public static void main(String[] args) {}
        subway5(){
         int width=0,length=0,counter=0;
        try{
             //Scanner sc = new Scanner(new FileInputStream("mapdisplay100x100.txt"));
             //Scanner sc = new Scanner(new FileInputStream("mapdisplay20x20.txt"));
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
            //Scanner scan = new Scanner(new FileInputStream("mapdisplay100x100.txt"));
            //Scanner scan = new Scanner(new FileInputStream("mapdisplay20x20.txt"));
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
                Scanner sc = new Scanner(new FileInputStream("numberforsubway.txt"));
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
         //scan central  
        int centralx=0,centraly=0,central_area=0,centralArea; //to calculate the position of the two  
        for(int i=0;i<building.length;i++){
            for(int j=0;j<building[i].length;j++){
                if(building[i][j].equals("R")){ //R is the central station in map1
                    centralx+=i;
                    centraly+=j;
                    central_area++;
                }
            }
        }
        centralx/=central_area;
        centraly/=central_area;
        System.out.println("central x : "+centralx+" central y : "+centraly);
//***********************************************************************************************************         
        //scan building to build railway station
        int numrow,numcolumn,rows=0,columns=0,countC1=0,countR1=0,countC2=0,countR2=0,tempi=0,tempj=0,countcolumn=0;
        boolean fbuilding=false;
        //int[] checkbuilding=new int[Integer.MAX_VALUE];
        int[] checkbuilding=new int[10000];
        int countbuilding=1;
        boolean gtbuilding=false;
        if(row<=50 || column<=50){
            rows=5;
            columns=5; 
            numcolumn=(int)Math.ceil(column/columns); 
            numrow=(int)Math.ceil(row/rows);
            countC1+=columns;
            countR1+=rows;
            
            while((countR1<=row) && (countC1<=column)){
                fbuilding=false;
                gtbuilding=false;
                countcolumn++;
                //diff area at upper n lower left n right corner 
                if(countR1<=centerx){
                    if(countC1<=centery){ //upper left corner
                        for(int i=countR2;i<countR1;i++){
                            for(int j=countC2;j<countC1;j++){
                                gtbuilding=false;
                               if((!building[i][j].equals(" "))&&(!building[i][j].equals("R"))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){ 
                                    if(i==0 || j==0)
                                        continue;
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
                                        fbuilding=true;
                                        tempi=i;
                                        tempj=j;
                                        checkbuilding[countbuilding]=nummaps[i][j];
                                        countbuilding++;
                                    }
                                    //fbuilding=true;
                                    //tempi=i;
                                    //tempj=j;
                                }
                                if(fbuilding==true)
                                    break;
                            }
                            if(fbuilding==true)
                                break;
                        }
                        if(fbuilding==true){
                            railway[tempi-1][tempj-1]="R";
                            fbuilding=false;
                        }
                    }
                     else{ //countC1>centery //upper right corner
                        for(int i=countR2;i<countR1;i++){
                            for(int j=countC1-1;j>=countC2;j--){
                                gtbuilding=false;
                                if((!building[i][j].equals(" "))&&(!building[i][j].equals("R"))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){    
                                    if(i==0 || j==column-1)
                                        continue;
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
                                        fbuilding=true;
                                        tempi=i;
                                        tempj=j;
                                        checkbuilding[countbuilding]=nummaps[i][j];
                                        countbuilding++;
                                    }
                                }
                                if(fbuilding==true)
                                    break;
                            }
                            if(fbuilding==true)
                                break;
                        }
                        if(fbuilding==true){
                            railway[tempi-1][tempj+1]="R";
                            fbuilding=false;
                        }
                    }
                }
                else{ //countR1>centerx
                    if(countC1<=centery){ //lower left corner
                        for(int i=countR1-1;i>=countR2;i--){
                            for(int j=countC2;j<countC1;j++){
                                gtbuilding=false;
                               if((!building[i][j].equals(" "))&&(!building[i][j].equals("R"))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){ 
                                    if(i==row-1 || j==0)
                                        continue;
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
                                        fbuilding=true;
                                        tempi=i;
                                        tempj=j;
                                        checkbuilding[countbuilding]=nummaps[i][j];
                                        countbuilding++;
                                    }
                                }
                                if(fbuilding==true)
                                    break;
                            }
                            if(fbuilding==true)
                                break;
                        }
                        if(fbuilding==true){
                            railway[tempi+1][tempj-1]="R";
                            fbuilding=false;
                        }
                    }
                     else{ //countC1>centery //lower right corner
                        for(int i=countR1-1;i>=countR2;i--){
                            for(int j=countC1-1;j>=countC2;j--){
                                gtbuilding=false;
                                if((!building[i][j].equals(" "))&&(!building[i][j].equals("R"))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){    
                                    if(i==row-1 || j==column-1)
                                        continue;
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
                                        fbuilding=true;
                                        tempi=i;
                                        tempj=j;
                                        checkbuilding[countbuilding]=nummaps[i][j];
                                        countbuilding++;
                                    }
                                }
                                if(fbuilding==true)
                                    break;
                            }
                            if(fbuilding==true)
                                break;
                        }
                        if(fbuilding==true){
                            railway[tempi+1][tempj+1]="R";
                            fbuilding=false;
                        }
                    }
                }
                countC2=countC1;
                if(countcolumn%numcolumn==0){
                    countR2=countR1;
                    countR1+=5;
                    countC2=0;
                    countC1=5;
                }
                else{
                    countC1+=5;
                }
            }
        }
        else{ //row>50 ||n column>50
            rows=10;
            columns=10; 
            numcolumn=(int)Math.ceil(column/columns); 
            numrow=(int)Math.ceil(row/rows);
            countC1+=columns;
            countR1+=rows;
            
            while((countR1<=row) && (countC1<=column)){
                fbuilding=false;
                countcolumn++;
                gtbuilding=false;
                
                //diff area at upper n lower left n right corner 
                if(countR1<=centerx){
                    if(countC1<=centery){ //upper left corner
                        for(int i=countR2;i<countR1;i++){
                            for(int j=countC2;j<countC1;j++){
                                gtbuilding=false;
                               if((!building[i][j].equals(" "))&&(!building[i][j].equals("R"))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){ 
                                    if(i==0 || j==0)
                                        continue;
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
                                        fbuilding=true;
                                        tempi=i;
                                        tempj=j;
                                        checkbuilding[countbuilding]=nummaps[i][j];
                                        countbuilding++;
                                    }
                                }
                                if(fbuilding==true)
                                    break;
                            }
                            if(fbuilding==true)
                                break;
                        }
                        if(fbuilding==true){
                            railway[tempi-1][tempj-1]="R";
                            fbuilding=false;
                        }
                    }
                     else{ //countC1>centery //upper right corner
                        for(int i=countR2;i<countR1;i++){
                            for(int j=countC1-1;j>=countC2;j--){
                                gtbuilding=false;
                                if((!building[i][j].equals(" "))&&(!building[i][j].equals("R"))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){    
                                    if(i==0 || j==column-1)
                                        continue;
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
                                        fbuilding=true;
                                        tempi=i;
                                        tempj=j;
                                        checkbuilding[countbuilding]=nummaps[i][j];
                                        countbuilding++;
                                    }
                                }
                                if(fbuilding==true)
                                    break;
                            }
                            if(fbuilding==true)
                                break;
                        }
                        if(fbuilding==true){
                            railway[tempi-1][tempj+1]="R";
                            fbuilding=false;
                        }
                    }
                }
                else{ //countR1>centerx
                    if(countC1<=centery){ //lower left corner
                        for(int i=countR1-1;i>=countR2;i--){
                            for(int j=countC2;j<countC1;j++){
                                gtbuilding=false;
                               if((!building[i][j].equals(" "))&&(!building[i][j].equals("R"))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){ 
                                    if(i==row-1 || j==0)
                                        continue;
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
                                        fbuilding=true;
                                        tempi=i;
                                        tempj=j;
                                        checkbuilding[countbuilding]=nummaps[i][j];
                                        countbuilding++;
                                    }
                                }
                                if(fbuilding==true)
                                    break;
                            }
                            if(fbuilding==true)
                                break;
                        }
                        if(fbuilding==true){
                            railway[tempi+1][tempj-1]="R";
                            fbuilding=false;
                        }
                    }
                     else{ //countC1>centery //lower right corner
                        for(int i=countR1-1;i>=countR2;i--){
                            for(int j=countC1-1;j>=countC2;j--){
                                gtbuilding=false;
                                if((!building[i][j].equals(" "))&&(!building[i][j].equals("R"))&&(!building[i][j].equals("^")) &&(!building[i][j].equals("v"))&&(!building[i][j].equals("<"))&&(!building[i][j].equals(">"))&&(!building[i][j].equals(">v"))&&(!building[i][j].equals("<v"))&&(!building[i][j].equals("<^"))&&(!building[i][j].equals(">^"))){    
                                    if(i==row-1 || j==column-1)
                                        continue;
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
                                        fbuilding=true;
                                        tempi=i;
                                        tempj=j;
                                        checkbuilding[countbuilding]=nummaps[i][j];
                                        countbuilding++;
                                    }
                                }
                                if(fbuilding==true)
                                    break;
                            }
                            if(fbuilding==true)
                                break;
                        }
                        if(fbuilding==true){
                            railway[tempi+1][tempj+1]="R";
                            fbuilding=false;
                        }
                    }
                }
                countC2=countC1;
                if(countcolumn%numcolumn==0){
                    countR2=countR1;
                    countR1+=10;
                    countC2=0;
                    countC1=10;
                }
                else{
                    countC1+=10;
                }  
            }
    
        }
//*****************************************************************************************
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //to build road for subway 
        int tx1=0,ty1=0,tx2=0,ty2=0;
        boolean station=false;
        
         if(row>=50 || column>=50){
            for(int i=0;i<=centralx;i++){ //upper corner left
                for(int j=0;j<=centraly/2;j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<=centralx;c++){
                            for(int d=0;d<=centraly/2;d++){
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
                                for(int e=tx2,f=ty2;e>=tx1 || f>=ty1;e--,f--){
                                    if(e<tx1)
                                        e=tx1;
                                    if(f<=ty1){
                                        f=ty1;
                                        subway[e][f]="a";
                                        station=false;
                                        continue;

                                    }
                                    subway[e][f]="a";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx2,f=ty2;e>=tx1 || f<=ty1;e--,f++){
                                    if(e<tx1)
                                        e=tx1;
                                    if(f>=ty1){
                                        f=ty1;
                                        subway[e][f]="a";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="a";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }

            for(int i=0;i<=centralx;i++){ //upper center left
                for(int j=centraly/2+1;j<=centraly;j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<=centralx;c++){
                            for(int d=centraly/2+1;d<=centraly;d++){
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
                                for(int e=tx2,f=ty2;e>=tx1 || f>=ty1;e--,f--){
                                    if(e<tx1)
                                        e=tx1;
                                    if(f<=ty1){
                                        f=ty1;
                                        subway[e][f]="b";
                                        station=false;
                                        continue;
                                    }
                                    subway[e][f]="b";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx2,f=ty2;e>=tx1 || f<=ty1;e--,f++){
                                    if(e<tx1)
                                        e=tx1;
                                    if(f>=ty1){
                                        f=ty1;
                                        subway[e][f]="b";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="b";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }

            for(int i=0;i<=centralx;i++){ //upper center right
                for(int j=centraly+1;j<(int)(centraly*1.5);j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<=centralx;c++){
                            for(int d=centraly+1;d<(int)(centraly*1.5);d++){
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
                                for(int e=tx1,f=ty1;e<=tx2 || f<=ty2;e++,f++){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f>=ty2){
                                        f=ty2;
                                        subway[e][f]="d";
                                        station=false;
                                        continue;
                                    }
                                    subway[e][f]="d";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx1,f=ty1;e<=tx2 || f>=ty2;e++,f--){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f<=ty2){
                                        f=ty2;
                                        subway[e][f]="d";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="d";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }

            for(int i=0;i<=centralx;i++){ //upper corner right
                for(int j=(int)(centraly*1.5+1);j<railway[i].length;j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<=centralx;c++){
                            for(int d=(int)(centraly*1.5+1);d<railway[c].length;d++){
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
                                for(int e=tx1,f=ty1;e<=tx2 || f<=ty2;e++,f++){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f>=ty2){
                                        f=ty2;
                                        subway[e][f]="e";
                                        station=false;
                                        continue;
                                    }
                                    subway[e][f]="e";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx1,f=ty1;e<=tx2 || f>=ty2;e++,f--){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f<=ty2){
                                        f=ty2;
                                        subway[e][f]="e";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="e";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }

            for(int i=centerx+1;i<railway.length;i++){ //lower corner left
                for(int j=0;j<=centraly/2;j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<railway.length;c++){
                            for(int d=0;d<=centraly/2;d++){
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
                                for(int e=tx1,f=ty1;e<=tx2 || f<=ty2;e++,f++){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f>=ty2){
                                        f=ty2;
                                        subway[e][f]="d";
                                        station=false;
                                        continue;
                                    }
                                    subway[e][f]="d";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx1,f=ty1;e<=tx2 || f>=ty2;e++,f--){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f<=ty2){
                                        f=ty2;
                                        subway[e][f]="d";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="d";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }

            for(int i=centerx+1;i<railway.length;i++){ //lower center left
                for(int j=centraly/2+1;j<=centraly;j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<railway.length;c++){
                            for(int d=centraly/2+1;d<=centraly;d++){
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
                                for(int e=tx1,f=ty1;e<=tx2 || f<=ty2;e++,f++){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f>=ty2){
                                        f=ty2;
                                        subway[e][f]="e";
                                        station=false;
                                        continue;
                                    }
                                    subway[e][f]="e";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx1,f=ty1;e<=tx2 || f>=ty2;e++,f--){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f<=ty2){
                                        f=ty2;
                                        subway[e][f]="e";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="e";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }

            for(int i=centralx+1;i<railway.length;i++){ //lower center right
                for(int j=centraly+1;j<=(int)(centraly*1.5);j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<railway.length;c++){
                            for(int d=centraly+1;d<=(int)(centraly*1.5);d++){
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
                                for(int e=tx1,f=ty1;e<=tx2 || f<=ty2;e++,f++){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f>=ty2){
                                        f=ty2;
                                        subway[e][f]="a";
                                        station=false;
                                        continue;
                                    }
                                    subway[e][f]="a";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx1,f=ty1;e<=tx2 || f>=ty2;e++,f--){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f<=ty2){
                                        f=ty2;
                                        subway[e][f]="a";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="a";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }

            for(int i=centralx+1;i<railway.length;i++){ //lower corner right
                for(int j=(int)(centraly*1.5+1);j<railway[i].length;j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<railway.length;c++){
                            for(int d=(int)(centraly*1.5+1);d<railway[c].length;d++){
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
                                for(int e=tx1,f=ty1;e<=tx2 || f<=ty2;e++,f++){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f>=ty2){
                                        f=ty2;
                                        subway[e][f]="b";
                                        station=false;
                                        continue;
                                    }
                                    subway[e][f]="b";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx1,f=ty1;e<=tx2 || f>=ty2;e++,f--){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f<=ty2){
                                        f=ty2;
                                        subway[e][f]="b";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="b";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }
         }
         else{ //(row<50 || column<50)
            for(int i=0;i<=centralx;i++){ //upper left
                for(int j=0;j<=centraly;j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<=centralx;c++){
                            for(int d=0;d<=centraly;d++){
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
                                for(int e=tx2,f=ty2;e>=tx1 || f>=ty1;e--,f--){
                                    if(e<tx1)
                                        e=tx1;
                                    if(f<=ty1){
                                        f=ty1;
                                        subway[e][f]="a";
                                        station=false;
                                        continue;

                                    }
                                    subway[e][f]="a";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx2,f=ty2;e>=tx1 || f<=ty1;e--,f++){
                                    if(e<tx1)
                                        e=tx1;
                                    if(f>=ty1){
                                        f=ty1;
                                        subway[e][f]="a";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="a";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }

            for(int i=0;i<=centralx;i++){ //upper right
                for(int j=centraly+1;j<railway[i].length;j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<=centralx;c++){
                            for(int d=centraly+1;d<railway[c].length;d++){
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
                                for(int e=tx1,f=ty1;e<=tx2 || f<=ty2;e++,f++){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f>=ty2){
                                        f=ty2;
                                        subway[e][f]="b";
                                        station=false;
                                        continue;
                                    }
                                    subway[e][f]="b";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx1,f=ty1;e<=tx2 || f>=ty2;e++,f--){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f<=ty2){
                                        f=ty2;
                                        subway[e][f]="b";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="b";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }


            for(int i=centerx+1;i<railway.length;i++){ //lower left
                for(int j=0;j<=centraly;j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<railway.length;c++){
                            for(int d=0;d<=centraly;d++){
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
                                for(int e=tx1,f=ty1;e<=tx2 || f<=ty2;e++,f++){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f>=ty2){
                                        f=ty2;
                                        subway[e][f]="b";
                                        station=false;
                                        continue;
                                    }
                                    subway[e][f]="b";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx1,f=ty1;e<=tx2 || f>=ty2;e++,f--){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f<=ty2){
                                        f=ty2;
                                        subway[e][f]="b";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="b";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            }

            for(int i=centralx+1;i<railway.length;i++){ //lower right
                for(int j=centraly+1;j<railway[i].length;j++){
                    if(railway[i][j].equals("R")){
                        for(int c=i;c<railway.length;c++){
                            for(int d=centraly+1;d<railway[c].length;d++){
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
                                for(int e=tx1,f=ty1;e<=tx2 || f<=ty2;e++,f++){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f>=ty2){
                                        f=ty2;
                                        subway[e][f]="a";
                                        station=false;
                                        continue;
                                    }
                                    subway[e][f]="a";
                                    station=false;
                                }
                            }
                            else{
                                for(int e=tx1,f=ty1;e<=tx2 || f>=ty2;e++,f--){
                                    if(e>tx2)
                                        e=tx2;
                                    if(f<=ty2){
                                        f=ty2;
                                        subway[e][f]="a";
                                        station=false;
                                        continue;
                                    } 
                                    subway[e][f]="a";
                                    station=false;
                                }
                            }
                        }
                    }
                }
            } 
         }
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

//############################################################
        //build subway connect railway to center
         boolean center=false;
        int cx=0,cy=0; //from center station to nearest railway station (x,y)//from center station to nearest railway station (x,y)
       
        if(row>=50 || column>=50){
            for(int i=centralx;i>=0;i--){ //upper corner left 
                for(int j=0;j<=centraly/2;j++){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){
                for(int k=centralx,l=centraly;k>=cx || l>=cy;k--,l--){
                    if(k<cx)
                        k=cx;
                    if(l<=cy){
                        l=cy;
                        subway[k][l]="a";
                        continue;

                    }
                    subway[k][l]="a";
                    center=false;
                }
            }

            for(int i=centralx;i>=0;i--){ //upper center left 
                //for(int j=centraly;j>=centraly/2+1;j--){
                for(int j=centraly/2+1;j<=centraly;j++){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){
                for(int k=centralx,l=centraly;k>=cx || l>=cy;k--,l--){
                    if(k<cx)
                        k=cx;
                    if(l<=cy){
                        l=cy;
                        subway[k][l]="b";
                        continue;

                    }
                    subway[k][l]="b";
                    center=false;
                }
            }

            for(int i=centralx;i>=0;i--){  //upper center right 
                for(int j=(int)(centraly*1.5);j>centraly;j--){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){
                for(int k=centralx,l=centraly;k>=cx || l<=cy;k--,l++){
                    if(k<cx)
                        k=cx;
                    if(l>=cy){
                        l=cy;
                        subway[k][l]="d";
                        continue;

                    }
                    subway[k][l]="d";
                    center=false;
                }
            }

            for(int i=centralx;i>=0;i--){  //upper corner right 
                for(int j=railway[i].length-1;j>(int)(centraly*1.5+1);j--){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){
                for(int k=centralx,l=centraly;k>=cx || l<=cy;k--,l++){
                    if(k<cx)
                        k=cx;
                    if(l>=cy){
                        l=cy;
                        subway[k][l]="e";
                        continue;

                    }
                    subway[k][l]="e";
                    center=false;
                }
            }

            for(int i=centralx;i<railway.length;i++){  //lower corner left  
                for(int j=0;j<=centraly/2;j++){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){ 
                for(int k=centralx,l=centraly;k<=cx || l>=cy;k++,l--){
                    if(k>cx)
                        k=cx;
                    if(l<=cy){
                        l=cy;
                        subway[k][l]="d";
                        continue;

                    }
                    subway[k][l]="d";
                    center=false;
                }
            }

            for(int i=centralx;i<railway.length;i++){  //lower center left  
                for(int j=centraly/2+1;j<=centraly;j++){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){ 
                for(int k=centralx,l=centraly;k<=cx || l>=cy;k++,l--){
                    if(k>cx)
                        k=cx;
                    if(l<=cy){
                        l=cy;
                        subway[k][l]="e";
                        continue;

                    }
                    subway[k][l]="e";
                    center=false;
                }
            }

            for(int i=centralx;i<railway.length;i++){ //lower center right 
                for(int j=(int)(centraly*1.5);j>=centraly+1;j--){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){
                for(int k=centralx,l=centraly;k<=cx || l<=cy;k++,l++){
                    if(k>cx)
                        k=cx;
                    if(l>=cy){
                        l=cy;
                        subway[k][l]="a";
                        continue;

                    }
                    subway[k][l]="a";
                    center=false;
                }
            }


            for(int i=centralx;i<railway.length;i++){ //lower corner right 
                for(int j=railway[i].length-1;j>=(int)(centraly*1.5);j--){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){
                for(int k=centralx,l=centraly;k<=cx || l<=cy;k++,l++){
                    if(k>cx)
                        k=cx;
                    if(l>=cy){
                        l=cy;
                        subway[k][l]="b";
                        continue;

                    }
                    subway[k][l]="b";
                    center=false;
                }
            }
        }
        else{ //(column<50 || row<50)
            for(int i=centralx;i>=0;i--){ //upper left 
                for(int j=centraly;j>=0;j--){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){
                for(int k=centralx,l=centraly;k>=cx || l>=cy;k--,l--){
                    if(k<cx)
                        k=cx;
                    if(l<=cy){
                        l=cy;
                        subway[k][l]="a";
                        continue;

                    }
                    subway[k][l]="a";
                    center=false;
                }
            }

            for(int i=centralx;i>=0;i--){ //upper right 
                for(int j=railway.length-1;j>centraly;j--){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){
                for(int k=centralx,l=centraly;k>=cx || l<=cy;k--,l++){
                    if(k<cx)
                        k=cx;
                    if(l>=cy){
                        l=cy;
                        subway[k][l]="b";
                        continue;

                    }
                    subway[k][l]="b";
                    center=false;
                }
            }

            for(int i=centralx;i<railway.length;i++){ //lower left 
                for(int j=centraly;j>=0;j--){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){ 
                for(int k=centralx,l=centraly;k<=cx || l>=cy;k++,l--){
                    if(k>cx)
                        k=cx;
                    if(l<=cy){
                        l=cy;
                        subway[k][l]="b";
                        continue;

                    }
                    subway[k][l]="b";
                    center=false;
                }
            }

            for(int i=centralx;i<railway.length;i++){ //lower right
                for(int j=centraly;j<railway.length;j++){
                    if(railway[i][j].equals("R")){
                        cx=i;
                        cy=j;
                        center=true;
                    }
                    if(center==true)
                        break;
                }
                if(center==true)
                    break;
            }
            while(center==true){
                for(int k=centralx,l=centraly;k<=cx || l<=cy;k++,l++){
                    if(k>cx)
                        k=cx;
                    if(l>=cy){
                        l=cy;
                        subway[k][l]="a";
                        continue;

                    }
                    subway[k][l]="a";
                    center=false;
                }
            }
        }
   
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^   

        //to print out the railway station "R"
        for(int i=0;i<railway.length;i++){
            for(int j=0;j<railway[i].length;j++){
                if(railway[i][j].equals("R"))
                    subway[i][j]="R";
            }
        }
        //set the central station????
        for(int i=0;i<building.length;i++){
            for(int j=0;j<building[i].length;j++){
                if(building[i][j].equals("R"))
                    subway[i][j]="C";
            }
        }
        
         //print map for subway n railway
        System.out.println("\nSubway map");
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
        String [][]map1=new String [row][column];
        try{
            Scanner sc=new Scanner(new FileInputStream("mapdisplay.txt"));
            //*******jinghui de  Scanner sc=new Scanner(new FileInputStream("newmaps.txt"));
            for(int r=0;r<row;r++){
                    String k[]=sc.nextLine().split("\\|");
                    for(int c=0;c<column;c++){
                        map1[r][c]=k[c];
                    }
                }sc.close();
            }
        catch(FileNotFoundException e){
            
        }
        try{
                PrintWriter pw1 = new PrintWriter(new FileOutputStream("subway.txt"));
                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < width; j++) {
                        System.out.println(map1[i][j]+"A");
                        if(map1[i][j].isBlank()||map1[i][j].isEmpty()||map1[i][j].toString().contains("<")||map1[i][j].toString().contains(">")||map1[i][j].toString().contains("^")||map1[i][j].toString().contains("v"))
                            pw1.print(subway[i][j]);
                        else
                            pw1.print(map1[i][j]);
                        pw1.print("|");
                        
                    }
                    pw1.println();
                    System.out.println("");
                }
                pw1.close();
            }catch (IOException e){
                System.out.println("Error with file output");
            }
    }
}