/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.urbanplanning3;

import java.util.Random;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 *
 * @author acer
 */
public class UrbanPlanning {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {}
   
    UrbanPlanning(){
         try{
                    PrintWriter p123=new PrintWriter(new FileOutputStream("fail.txt"));
                    p123.print("0");
                    p123.close();
                }catch(IOException e){
                    
                }
         try{
        // TODO code application logic here
        String s = "";
        String minimum = "";
        String maximum = "";
        String areaSmall = "";
        String numberBuild = "";
        String row1 = "";
        String column1 = "";
        String checkHouse = "";
        try {
            Scanner sc = new Scanner(new FileInputStream("allinputs.txt"));
            s = sc.nextLine();
            minimum = sc.nextLine();
            maximum = sc.nextLine();
            areaSmall = sc.nextLine();
            checkHouse = sc.nextLine();
            numberBuild = sc.nextLine();
            row1 = sc.nextLine();
            column1 = sc.nextLine();
            
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        String[] sym = s.split(" ");
        String[] min = minimum.split(" ");
        String[] max = maximum.split(" ");
        String[] num = numberBuild.split(" ");
        String[] area = areaSmall.split(" ");
        int length = Integer.parseInt(row1);
        int width = Integer.parseInt(column1);
        String[] checkCondo = checkHouse.split(" ");

        //boolean for regenerate whole map
        boolean regenerate = true;

        //declare boolean array for checking block exist
        boolean[][] checkBlock = new boolean[length][width];
        String[][] fillBlock = new String[length][width];
        int[][] forRoad = new int[length][width];
        int[][] forSubway = new int[length][width];
        ///***************************************************************************************************
        int fail = 0;
        while (regenerate == true) { //while loop in case to regenerate whole map) { //a do while loop if building cant find place to generate so whole map regenerate
            fail++;
            if (fail > 100) {
                
                try{
                    PrintWriter p123=new PrintWriter(new FileOutputStream("fail.txt"));
                    p123.print("1");
                    p123.close();
                }catch(IOException e){
                    
                }
                break;
            }
            
            regenerate = false;
            for (boolean[] row : checkBlock) { //fill boolean false by row
                Arrays.fill(row, Boolean.FALSE);
            }

            for (String[] row : fillBlock) { //fill String blank by row
                Arrays.fill(row, " ");
            }

            for (int[] row : forRoad) { //fill char blank by row
                Arrays.fill(row, 0);
            }

            for (int[] row : forSubway) {
                Arrays.fill(row, 0);
            }

            //print highway
            int horizontal = length / 2;
            int vertical = width / 2;
            for (int x = 0; x < length; x++) {
                fillBlock[x][vertical - 1] = "v";
                checkBlock[x][vertical - 1] = true; //fill boolean array to true when generate highway, prevent overwrite of building generate (refer method)
                forRoad[x][vertical - 1] = 1;
                forSubway[x][vertical - 1] = 1;
            }
            for (int x = 0; x < length; x++) {
                fillBlock[x][vertical] = "^";
                checkBlock[x][vertical] = true;
                forRoad[x][vertical] = 1;
                forSubway[x][vertical] = 1;
            }
            for (int y = 0; y < width; y++) {
                fillBlock[horizontal - 1][y] = ">";
                checkBlock[horizontal - 1][y] = true;
                forRoad[horizontal - 1][y] = 1;
                forSubway[horizontal - 1][y] = 1;
            }
            for (int y = 0; y < width; y++) {
                fillBlock[horizontal][y] = "<";
                checkBlock[horizontal][y] = true;
                forRoad[horizontal][y] = 1;
                forSubway[horizontal][y] = 1;
            }
            fillBlock[horizontal - 1][vertical - 1] = ">v";
            fillBlock[horizontal - 1][vertical] = ">^";
            fillBlock[horizontal][vertical - 1] = "<v";
            fillBlock[horizontal][vertical] = "<^";

            //separate the map to various large area
            int lengthPerArea = length;
            int widthPerArea = width;
            int ratioLength = 0;
            int ratioWidth = 0;
            int ratio = 0;

            if (length >= 100 || width >= 100) { //if either side > 100, area seperate to 9
                ratio = 3;
                lengthPerArea = length / 3;
                widthPerArea = width / 3;
            } else if (length >= 40 || width >= 40) { //if either side > 40 , area (seperate to 4
                ratio = 2;
                lengthPerArea = length / 2;
                widthPerArea = width / 2;
            } else {
                ratio = 1;
                lengthPerArea = length;
                widthPerArea = width;
            }

            //declare variable for large area seperation
            int XminArea = 0; //minimum x coordinate for area
            int XmaxArea = 0; //maximum x coordinate for area
            int YminArea = 0; //minimum y coordinate for area
            int YmaxArea = 0; //maximum y coordinate for area
            int numArea = ratio * ratio; //get total number of area seperate

            //declare variable for small area seperation (inside every large area)
            int Xmin = 0; //min x for such small area
            int Xmax = 0; //max x for such small area
            int Ymin = 0; //min y for such small area
            int Ymax = 0; //max y for such small area

            //print center railway station
            station(length, width, checkBlock, fillBlock, forRoad, 2, forSubway, 2);

            test:
            {
                int road = 3;
                int subway = 3;
                //start get input and fill the symbol
                for (int i = 0; i < min.length; i++) {
                    int minBlock = Integer.parseInt(min[i]); //minimum block of building
                    int maxBlock = Integer.parseInt(max[i]); //maximum block of building
                    String symbol = sym[i]; //symbol of building
                    int numBlock = Integer.parseInt(num[i]); //number of each building need to generate
                    int areaBlock = Integer.parseInt(area[i]); //area of each building generate on small area (seperation)
                    int checkCH = Integer.parseInt(checkCondo[i]);

                    int numPerArea = 0; //number of building for each large area
                    int numExtra = 0; //number of building extra (if cannot equally divide to each area)
                    int stop = 0; //counter variable
                    if (numBlock >= numArea) { //number of building >= number of large area(after seperation of large area)
                        numPerArea = numBlock / numArea; //get number of building per area
                        numExtra = numBlock - (numPerArea * numArea); //get number of building extra
                        stop = 0; //let the loop run to both stage
                    } else { //number of building < number of area
                        numExtra = numBlock; //let number of building equal to number extra
                        stop = 1; //loop only run second stage
                    }
                    for (int a = stop; a < 2; a++) { //loop generate
                        XminArea = 0;
                        XmaxArea = 0; //renew coordinate X
                        if (a == 0) { //for numBlock >= numArea, so generate number of block consecutive in each area
                            int temp = subway;
                            if (checkCH == 1) {
                                subway = 0;
                            }
                            for (int x = 0; x < ratio; x++) { //(same to print highway method), to change coordinate of X and Y
                                XminArea = lengthPerArea * x;
                                XmaxArea = lengthPerArea * (x + 1);
                                YminArea = 0;
                                YmaxArea = 0; //renew coordinate Y
                                for (int y = 0; y < ratio; y++) {
                                    YminArea = widthPerArea * y;
                                    YmaxArea = widthPerArea * (y + 1);
                                    //call method choose small area
                                    regenerate = areaBlock(symbol, minBlock, maxBlock, numPerArea, areaBlock, length, width, checkBlock, fillBlock, lengthPerArea, widthPerArea, XminArea, XmaxArea, YminArea, YmaxArea, forRoad, road, forSubway, subway);
                                    if (regenerate == true) {
                                        break test;
                                    }
                                }
                            }
                            road = road + (numPerArea * numArea);
                            subway = temp;
                            subway = subway + (numPerArea * numArea);

                        }
                        if (a == 1) { //for numBlock < numArea or numExtra
                            if (numExtra != 0) { //use condition if numBlock >= numArea but numExtra = 0
                                int[] repeat = new int[numExtra]; //array to store integer for switch case below
                                Arrays.fill(repeat, 0); //let array be new one for second building
                                XminArea = 0;
                                XmaxArea = 0;
                                YminArea = 0;
                                YmaxArea = 0; //refresh the coordinate if case a = 0 ady run

                                Random r = new Random();
                                repeat[0] = 1 + r.nextInt(numArea); //random generate integer for large area choosing to generate building
                                boolean checkRepeat = true; //boolean to check repeat integer
                                int count = 1;
                                while (count < repeat.length) { //while loop use for checking repeat integer
                                    int areaLarge = 1 + r.nextInt(numArea);
                                    for (int count1 = 0; count1 < count; count1++) { //check integer ady generate in array either duplicate or not
                                        if (areaLarge == repeat[count1]) {
                                            checkRepeat = false;
                                        }
                                    }
                                    if (checkRepeat == true) { //if no duplicate, add integer to array
                                        repeat[count] = areaLarge;
                                        count++;
                                    }
                                    checkRepeat = true;
                                }
                                int temp = subway;
                                if (checkCH == 1) {
                                    subway = 0;
                                }
                                for (int counter = 0; counter < repeat.length; counter++) { //for every integer in array
                                    int chooseArea = repeat[counter];
                                    switch (chooseArea) { //by using integer random generate in array, choose which large area want to generate building
                                        case 1: //top left corner (case 1 can use for all size)
                                            XminArea = lengthPerArea * 0;
                                            YminArea = widthPerArea * 0;
                                            XmaxArea = lengthPerArea * (0 + 1);
                                            YmaxArea = widthPerArea * (0 + 1);
                                            break;
                                        case 2: //row 1, column 2 (case 2 to 4 can use for size above 40 x 40)
                                            XminArea = lengthPerArea * 0;
                                            YminArea = widthPerArea * 1;
                                            XmaxArea = lengthPerArea * (0 + 1);
                                            YmaxArea = widthPerArea * (1 + 1);
                                            break;
                                        case 3: //row 2, column 1
                                            XminArea = lengthPerArea * 1;
                                            YminArea = widthPerArea * 0;
                                            XmaxArea = lengthPerArea * (1 + 1);
                                            YmaxArea = widthPerArea * (0 + 1);
                                            break;
                                        case 4: //row 2, column 2
                                            XminArea = lengthPerArea * 1;
                                            YminArea = widthPerArea * 1;
                                            XmaxArea = lengthPerArea * (1 + 1);
                                            YmaxArea = widthPerArea * (1 + 1);
                                            break;
                                        case 5: //row 1, column 3 (case 5 to 9 can use for size above 100 x 100)
                                            XminArea = lengthPerArea * 0;
                                            YminArea = widthPerArea * 2;
                                            XmaxArea = lengthPerArea * (0 + 1);
                                            YmaxArea = widthPerArea * (2 + 1);
                                            break;
                                        case 6: //row 2, column 3
                                            XminArea = lengthPerArea * 1;
                                            YminArea = widthPerArea * 2;
                                            XmaxArea = lengthPerArea * (1 + 1);
                                            YmaxArea = widthPerArea * (2 + 1);
                                            break;
                                        case 7: //row 3, column 1
                                            XminArea = lengthPerArea * 2;
                                            YminArea = widthPerArea * 0;
                                            XmaxArea = lengthPerArea * (2 + 1);
                                            YmaxArea = widthPerArea * (0 + 1);
                                            break;
                                        case 8: //row 3, column 2
                                            XminArea = lengthPerArea * 2;
                                            YminArea = widthPerArea * 1;
                                            XmaxArea = lengthPerArea * (2 + 1);
                                            YmaxArea = widthPerArea * (1 + 1);
                                            break;
                                        case 9: //row 3, column 3
                                            XminArea = lengthPerArea * 2;
                                            YminArea = widthPerArea * 2;
                                            XmaxArea = lengthPerArea * (2 + 1);
                                            YmaxArea = widthPerArea * (2 + 1);
                                            break;
                                    }
                                    //call areaBlock method to choose small area
                                    regenerate = areaBlock(symbol, minBlock, maxBlock, 1, areaBlock, length, width, checkBlock, fillBlock, lengthPerArea, widthPerArea, XminArea, XmaxArea, YminArea, YmaxArea, forRoad, road, forSubway, subway);
                                    road++;
                                    if (checkCH != 1){
                                      subway++;  
                                    }
                                    if (regenerate == true) {
                                        break test;
                                    }
                                }
                                subway = temp + numExtra;
                            }
                        }
                    }
                }
            }
        }

        display:
        {
            if (fail > 100) {
                break display;
            }
            // fill building or road to third array
            for (int p = 0; p < length; p++) {
                for (int q = 0; q < width; q++) {
                    if (!(fillBlock[p][q].equals(" ")) && forRoad[p][q] == ' ') {
                        forRoad[p][q] = 'B';
                    }
                }
            }

            //display map
            for (int k = 0; k < length; k++) {
                System.out.print(" -----");
            }
            System.out.println();
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.print("| ");
                    System.out.printf("%4s", fillBlock[i][j]);
                }
                System.out.print("|");
                System.out.println();
                for (int k = 0; k < length; k++) {
                    System.out.print(" -----");
                }
                System.out.println();
            }

            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream("numberforroad.txt"));
                //print vertical line for column in map
                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < width; j++) {
                        pw.print(forRoad[i][j] + " ");
                    }
                    pw.println();
                }
                pw.close();
            } catch (IOException e) {
                System.out.println("Error with file output");
            }

            try {
                PrintWriter pw1 = new PrintWriter(new FileOutputStream("numberforsubway.txt"));
                //print vertical line for column in map
                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < width; j++) {
                        pw1.print(forSubway[i][j] + " ");
                    }
                    pw1.println();
                }
                pw1.close();
            } catch (IOException e) {
                System.out.println("Error with file output");
            }

            try {
                PrintWriter pw2 = new PrintWriter(new FileOutputStream("mapdisplay.txt"));
                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < width; j++) {
                        pw2.print(fillBlock[i][j]);
                        pw2.print("|");
                    }
                    pw2.println();
                }
                pw2.close();
            } catch (IOException e) {
                System.out.println("Error with file output");
            }

        }
    }catch(ArrayIndexOutOfBoundsException e1){
         try{
                    PrintWriter p123=new PrintWriter(new FileOutputStream("fail.txt"));
                    p123.print("1");
                    p123.close();
                }catch(IOException e){
                    
                }
}catch(NumberFormatException e3){
    try{
                    PrintWriter p123=new PrintWriter(new FileOutputStream("fail.txt"));
                    p123.print("1");
                    p123.close();
                }catch(IOException e){
                    
                }
}
    }
    //method place center railway station
    public static void station(int length, int width, boolean[][] checkBlock, String[][] fillBlock, int[][] forRoad, int road, int[][] forSubway, int subway) {
        //restrict the area for railway station generate at center of map
        int Xmin = length / 2 - 5;
        int Ymin = width / 2 - 5;
        int Xmax = length / 2 + 5;
        int Ymax = width / 2 + 5;
        String symbol = "R";
        int minBlock = 4;
        int maxBlock = 8;
        String name = "Railway Station";
        int num = 1;
        //call fixBlock method
        fixBlock(symbol, minBlock, maxBlock, num, length, width, checkBlock, fillBlock, Xmin, Xmax, Ymin, Ymax, length, width, 0, length, 0, width, forRoad, road, forSubway, subway);
    }

    //method fix area block generate
    public static boolean areaBlock(String symbol, int minBlock, int maxBlock, int num, int areaBlock, int length, int width, boolean[][] checkBlock, String[][] fillBlock, int lengthPerArea, int widthPerArea, int XminArea, int XmaxArea, int YminArea, int YmaxArea, int[][] forRoad, int road, int[][] forSubway, int subway) {
        boolean regenerate; //boolean for checking if need to regenerate whole map
        //declare variable for small area seperation
        int Xmin = 0;
        int Xmax = 0;
        int Ymin = 0;
        int Ymax = 0;
        //separate to small area (rectangle or square) from large area
        switch (areaBlock) {
            case 1: //top left
                Xmin = XminArea;
                Ymin = YminArea;
                Xmax = (XmaxArea - XminArea) / 2 + XminArea;
                Ymax = (YmaxArea - YminArea) / 2 + YminArea;
                break;
            case 2: //top right
                Xmin = XminArea;
                Ymin = (YmaxArea - YminArea) / 2 + YminArea;
                Xmax = (XmaxArea - XminArea) / 2 + XminArea;
                Ymax = YmaxArea - 1;
                break;
            case 3: //bottom left
                Xmin = (XmaxArea - XminArea) / 2 + XminArea;
                Ymin = YminArea;
                Xmax = XmaxArea - 1;
                Ymax = (YmaxArea - YminArea) / 2 + YminArea;
                break;
            case 4: //bottom right
                Xmin = (XmaxArea - XminArea) / 2 + XminArea;
                Ymin = (YmaxArea - YminArea) / 2 + YminArea;
                Xmax = XmaxArea - 1;
                Ymax = YmaxArea - 1;
                break;
            case 5: //random generate
                Xmin = XminArea;
                Ymin = YminArea;
                Xmax = XmaxArea - 1;
                Ymax = YmaxArea - 1;
                break;
        }
        regenerate = fixBlock(symbol, minBlock, maxBlock, num, length, width, checkBlock, fillBlock, Xmin, Xmax, Ymin, Ymax, lengthPerArea, widthPerArea, XminArea, XmaxArea, YminArea, YmaxArea, forRoad, road, forSubway, subway);
        return regenerate;
    }

    //method place building to array
    public static boolean fixBlock(String symbol, int minBlock, int maxBlock, int num, int length, int width, boolean[][] check, String[][] block, int Xmin, int Xmax, int Ymin, int Ymax, int lengthPerArea, int widthPerArea, int XminArea, int XmaxArea, int YminArea, int YmaxArea, int[][] forRoad, int road, int[][] forSubway, int subway) {
        boolean regenerate = false;
        int counter = 0; //counter for building cant find place
        Random r = new Random();
        int numberBlock = 0;
        //fix first block of building generate in map     
        for (int count = 0; count < num;) { //condition for building more than 1 area
            numberBlock = minBlock + r.nextInt(maxBlock - minBlock + 1); //random fix number of block generate for a building in such range   
            int x = 0;
            int y = 0;
            x = Xmin + r.nextInt(Xmax - Xmin + 1); //fix building in certain small area
            y = Ymin + r.nextInt(Ymax - Ymin + 1); //fix first block of symbol generate
            counter++;
            if (counter > 50) { //building can random generate in whole large area if no place can generate in such small area
                x = XminArea + r.nextInt(XmaxArea - XminArea + 1); //fix building in certain small area
                y = YminArea + r.nextInt(YmaxArea - YminArea + 1); //fix first block of symbol generate
                Xmin = XminArea;
                Ymin = YminArea;
                Xmax = XmaxArea;
                Ymax = YmaxArea;
            }
            if (counter > 100) { //building can random generate in whole map if no place can generate in such large area
                x = r.nextInt(length - 1);
                y = r.nextInt(width - 1);
                Xmin = 0;
                Ymin = 0;
                Xmax = length - 1;
                Ymax = width - 1;
            }
            if (checkBlock(numberBlock, x, y, check, block, symbol, length, width, Xmin, Xmax, Ymin, Ymax, lengthPerArea, widthPerArea, forRoad, road, forSubway, subway) == true) { //when true, means buildiing generate in map successful
                count++; //so count add for first, if more than 1 area input, loop continue
                counter = 0;
                road++;
                if (subway != 0) {
                    subway++;
                }
            }
            //checking for building cant find place
            if (counter > length * width) {
                regenerate = true;
                break;
            }
        }
        return regenerate;
    }

    //check the block ady generate block or not
    public static boolean checkBlock(int num, int x, int y, boolean checkBlock[][], String[][] fillBlock, String symbol, int length, int width, int Xmin, int Xmax, int Ymin, int Ymax, int lengthPerArea, int widthPerArea, int[][] forRoad, int road, int[][] forSubway, int subway) {
        //fix width and length of the building
        Random r = new Random();
        int i = 0; //width
        int j = 0; //length
        int blockExtra = 0; //extra block eg. prime number
        if (num > 4) {
            int factor = 2 + r.nextInt((num - 2) / 2); //random generate for factor to divide width and length of building
            i = factor;
            j = num / factor;
            blockExtra = num - (i * j);
        } else if (num == 2 || num == 4) { //small area block
            i = 2;
            j = num / 2;
        } else if (num == 3) { //small area block
            i = 3;
            j = 1;
        }
        //random generate either the length and width will be horizontal or vertical
        int f = r.nextInt(2);
        if (f == 1) { //if random generate number is 1, interchange the width and length
            int temp = i;
            i = j;
            j = temp;
        }

        //detect array out of bound
        boolean exception = false;
        if (x + i >= Xmax - 1 || y + j >= Ymax - 1 || x < Xmin || y < Ymin) { //condition building will generate is out of bound
            exception = true;
        }

        if (x + i > length - 1 || y + j > width || x < 0 || y < 0) {
            exception = true;
        }

        boolean check = true;
        test:
        {
            //array out of bound, return to previous method, regenerate new coordinate and building area
            if (exception == true) {
                check = false;
                break test;
            }

            //check the whole area fix by method above is ady fill with block or not
            check = true;
            for (int a = 0; a < i; a++) {
                for (int b = 0; b < j; b++) {
                    if (checkBlock[x + a][y + b] == true) { //if this method true, means somewhere ady fill with other building
                        check = false; //fail to generate building, return check to fixBlock so will generate other block for checking and generate
                    }
                }
            }
            int side = -1;
            int path = 0;
            int direction = 0;

            //random generate a side for filling block extra
            //fix side 0 for top, 1 for right, 2, for bottom, 3 for left
            if (blockExtra > 0) {
                if (x == 0 && y == 0) { //top left, so block extra can generate at right or bottom(same concept for below funtion)
                    side = 1 + r.nextInt(2);
                } else if (x == 0 && y + j - 1 == width - 1) { //top right
                    side = 2 + r.nextInt(2);
                } else if (x == 0) { //top (not left and right)
                    side = 1 + r.nextInt(3);
                } else if (x + i - 1 == length - 1 && y == 0) { //bottom left
                    side = r.nextInt(2);
                } else if (x + i - 1 == length - 1 && y + j - 1 == width - 1) { //bottom right
                    do {
                        side = r.nextInt(4);
                    } while (side == 1 || side == 2);
                } else if (x + i - 1 == length - 1) { //bottom (not left and right)
                    do {
                        side = r.nextInt(4);
                    } while (side == 2);
                } else if (y == 0) { //left (not up and down)
                    side = r.nextInt(3);
                } else if (y + j - 1 == width - 1) { //right (not up and down)
                    do {
                        side = r.nextInt(4);
                    } while (side == 1);
                } else { //center (no touching any side)
                    side = r.nextInt(4);
                }

                if (blockExtra > i) { //prevent block extra generate longer than length 
                    if (x == 0 && y == 0) { //top left, so block extra can generate at right or bottom(same concept for below funtion)
                        side = 0;
                    } else if (x == 0 && y + j - 1 == width - 1) { //top right
                        side = 2;
                    } else if (x == 0) { //top (not left and right)
                        side = 2;
                    } else if (x + i - 1 == length - 1 && y == 0) { //bottom left
                        side = 0;
                    } else if (x + i - 1 == length - 1 && y + j - 1 == width - 1) { //bottom right
                        side = 0;
                    } else if (x + i - 1 == length - 1) { //bottom (not left and right)
                        side = 0;
                    } else if (y == 0) { //left (not up and down)
                        do {
                            side = r.nextInt(3);
                        } while (side == 1);
                    } else if (y + j - 1 == width - 1) { //right (not up and down)
                        do {
                            side = r.nextInt(3);
                        } while (side == 1);
                    } else { //center (no touching any side)
                        do {
                            side = r.nextInt(3);
                        } while (side == 1);
                    }
                }

                if (blockExtra > j) { //prevent block generate longer than width so cannot generate at top and bottom
                    if (x == 0 && y == 0) { //top left, so block extra can generate at right or bottom(same concept for below funtion)
                        side = 1;
                    } else if (x == 0 && y + j - 1 == width - 1) { //top right
                        side = 3;
                    } else if (x == 0) { //top (not left and right)
                        do {
                            side = 1 + r.nextInt(3);
                        } while (side == 2);
                    } else if (x + i - 1 == length - 1 && y == 0) { //bottom left
                        side = 1;
                    } else if (x + i - 1 == length - 1 && y + j - 1 == width - 1) { //bottom right
                        side = 3;
                    } else if (x + i - 1 == length - 1) { //bottom (not left and right)
                        do {
                            side = 1 + r.nextInt(3);
                        } while (side == 2);
                    } else if (y == 0) { //left (not up and down)
                        side = 1;
                    } else if (y + j - 1 == width - 1) { //right (not up and down)
                        side = 3;
                    } else { //center (no touching any side)
                        do {
                            side = 1 + r.nextInt(3);
                        } while (side == 2);
                    }
                }

                //checking boolean array for ady have building or not
                switch (side) {
                    case 0: //generate block extra at top
                        direction = r.nextInt(2);
                        if (direction == 0) { //left
                            for (int a = 0, b = 0; a < blockExtra; a++, b++) {
                                if (checkBlock[x - 1][y + b] == true) {
                                    check = false;
                                }
                            }
                            path = 1;
                        } else if (direction == 1) { //right
                            for (int a = 0, b = - 1; a < blockExtra; a++, b--) {
                                if (checkBlock[x - 1][y + j + b] == true) {
                                    check = false;
                                }
                            }
                            path = 2;
                        }
                        break;

                    case 1: //generate block extra at right
                        direction = r.nextInt(2);
                        if (direction == 0) { //up
                            for (int a = 0, b = 0; a < blockExtra; a++, b++) {
                                if (checkBlock[x + b][y + j] == true) {
                                    check = false;
                                }
                            }
                            path = 3;
                        } else if (direction == 1) { //down
                            for (int a = 0, b = -1; a < blockExtra; a++, b--) {
                                if (checkBlock[x + i + b][y + j] == true) {
                                    check = false;
                                }
                            }
                            path = 4;
                        }
                        break;

                    case 2: //generate block extra at bottom
                        direction = r.nextInt(2);
                        if (direction == 0) { //left
                            for (int a = 0, b = 0; a < blockExtra; a++, b++) {
                                if (checkBlock[x + i][y + b] == true) {
                                    check = false;
                                }
                            }
                            path = 5;
                        } else if (direction == 1) { //right
                            for (int a = 0, b = - 1; a < blockExtra; a++, b--) {
                                if (checkBlock[x + i][y + j + b] == true) {
                                    check = false;
                                }
                            }
                            path = 6;
                        }
                        break;

                    case 3: //generate block extra at left
                        direction = r.nextInt(2);
                        if (direction == 0) { //up
                            for (int a = 0, b = 0; a < blockExtra; a++, b++) {
                                if (checkBlock[x + b][y - 1] == true) {
                                    check = false;
                                }
                            }
                            path = 7;
                        } else if (direction == 1) { //down
                            for (int a = 0, b = - 1; a < blockExtra; a++, b--) {
                                if (checkBlock[x + i + b][y - 1] == true) {
                                    check = false;
                                }
                            }
                            path = 8;
                        }
                        break;
                }
            }
            if (check == true) { //no building exist so continue to generate building
                generateBlock(i, j, fillBlock, symbol, checkBlock, x, y, blockExtra, path, length, width, forRoad, road, forSubway, subway); //go to generateBlock method
            }
        }
        return check; //return to fixBlock method
    }
    //generate symbol in array

    public static void generateBlock(int i, int j, String[][] block, String s, boolean[][] check, int x, int y, int blockLeft, int path, int length, int width, int[][] forRoad, int road, int[][] forSubway, int subway) {
        //generate the symbol in map according the area fix in above method
        for (int row = 0; row < i; row++) {
            for (int column = 0; column < j; column++) {
                block[x + row][y + column] = s;
                forRoad[x + row][y + column] = road;
                forSubway[x + row][y + column] = subway;
            }
        }

        //fill boolean array to true
        fillBoolean(x, y, i, j, check, length, width);

        //generate symbol for block extra
        switch (path) {
            case 1: //top left
                for (int a = 0, b = 0; a < blockLeft; a++, b++) {
                    block[x - 1][y + b] = s;
                    forRoad[x - 1][y + b] = road;
                    forSubway[x - 1][y + b] = subway;
                }
                break;

            case 2: //top right
                for (int a = 0, b = - 1; a < blockLeft; a++, b--) {
                    block[x - 1][y + j + b] = s;
                    forRoad[x - 1][y + j + b] = road;
                    forSubway[x - 1][y + j + b] = subway;
                }
                break;

            case 3: //right up
                for (int a = 0, b = 0; a < blockLeft; a++, b++) {
                    block[x + b][y + j] = s;
                    forRoad[x + b][y + j] = road;
                    forSubway[x + b][y + j] = subway;
                }
                break;

            case 4: //right down
                for (int a = 0, b = - 1; a < blockLeft; a++, b--) {
                    block[x + i + b][y + j] = s;
                    forRoad[x + i + b][y + j] = road;
                    forSubway[x + i + b][y + j] = subway;
                }
                break;

            case 5:
                //bottom left
                for (int a = 0, b = 0; a < blockLeft; a++, b++) {
                    block[x + i][y + b] = s;
                    forRoad[x + i][y + b] = road;
                    forSubway[x + i][y + b] = subway;
                }
                break;

            case 6: // bottom right
                for (int a = 0, b = - 1; a < blockLeft; a++, b--) {
                    block[x + i][y + j + b] = s;
                    forRoad[x + i][y + j + b] = road;
                    forSubway[x + i][y + j + b] = subway;
                }
                break;

            case 7: //left up
                for (int a = 0, b = 0; a < blockLeft; a++, b++) {
                    block[x + b][y - 1] = s;
                    forRoad[x + b][y - 1] = road;
                    forSubway[x + b][y - 1] = subway;
                }
                break;

            case 8: //left down
                for (int a = 0, b = - 1; a < blockLeft; a++, b--) {
                    block[x + i + b][y - 1] = s;
                    forRoad[x + i + b][y - 1] = road;
                    forSubway[x + i + b][y - 1] = subway;
                }
                break;
            case 0:
                break;
        }

        //fill boolean array for block extra
        int p = 0, q = 0;
        switch (path) {
            case 1:
                //top left
                p = x - 1;
                q = y;
                fillBoolean(p, q, 1, blockLeft, check, length, width);
                break;
            case 2:
                //top right
                p = x - 1;
                q = y + j - blockLeft;
                fillBoolean(p, q, 1, blockLeft, check, length, width);
                break;
            case 3:
                //right up
                p = x;
                q = y + j;
                fillBoolean(p, q, blockLeft, 1, check, length, width);
                break;
            case 4:
                //right down
                p = x + i - blockLeft;
                q = y + j;
                fillBoolean(p, q, blockLeft, 1, check, length, width);
                break;
            case 5:
                //bottom left
                p = x + i;
                q = y;
                fillBoolean(p, q, 1, blockLeft, check, length, width);
                break;
            case 6:
                //bottom right
                p = x + i;
                q = y + j - blockLeft;
                fillBoolean(p, q, 1, blockLeft, check, length, width);
                break;
            case 7:
                //left up
                p = x;
                q = y - 1;
                fillBoolean(p, q, blockLeft, 1, check, length, width);
                break;
            case 8:
                //left down
                p = x + i - blockLeft;
                q = y - 1;
                fillBoolean(p, q, blockLeft, 1, check, length, width);
                break;
            case 0:
                break;
        }
    }

    //start from here is to fill boolean array to true if building generate in such block, and prevent building from combine
    //do condition to prevent out of range of array
    public static void fillBoolean(int x, int y, int i, int j, boolean[][] checkBlock, int length, int width) {
        if (x == 0 && y == 0) { //top left
            for (int a = 0; a <= i; a++) {
                for (int b = 0; b <= j; b++) {
                    checkBlock[x + a][y + b] = true;
                }
            }
        } else if (x == 0 && y + j - 1 == width - 1) { //top right
            for (int a = -1; a < i; a++) {
                for (int b = 0; b <= j; b++) {
                    checkBlock[x + a][y + b] = true;
                }
            }
        } else if (x + i - 1 == length - 1 && y == 0) { //bottom left
            for (int a = -1; a < i; a++) {
                for (int b = 0; b <= j; b++) {
                    checkBlock[x + a][y + b] = true;
                }
            }
        } else if (x + i - 1 == length - 1 && y + j - 1 == width - 1) { //bottom right
            for (int a = -1; a < i; a++) {
                for (int b = -1; b < j; b++) {
                    checkBlock[x + a][y + b] = true;
                }
            }
        } else if (x == 0) { //top
            for (int a = 0; a <= i; a++) {
                for (int b = -1; b <= j; b++) {
                    checkBlock[x + a][y + b] = true;
                }
            }
        } else if (y == 0) { //left
            for (int a = -1; a <= i; a++) {
                for (int b = 0; b <= j; b++) {
                    checkBlock[x + a][y + b] = true;
                }
            }
        } else if (y + j - 1 == width - 1) { //right
            for (int a = -1; a <= i; a++) {
                for (int b = -1; b < j; b++) {
                    checkBlock[x + a][y + b] = true;
                }
            }
        } else if (x + i - 1 == length - 1) { //bottom
            for (int a = -1; a < i; a++) {
                for (int b = -1; b <= j; b++) {
                    checkBlock[x + a][y + b] = true;
                }
            }
        } else { //center
            for (int a = -1; a <= i; a++) {
                for (int b = -1; b <= j; b++) {
                    checkBlock[x + a][y + b] = true;
                }
            }
        }
    }
}
