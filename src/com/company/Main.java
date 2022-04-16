package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Play desk = new Play();
        desk.initDesk();
        while(true){
            desk.goAI();
            desk.draw();
            desk.checkWin("0");
            desk.getHop();
            desk.draw();
            desk.checkWin("x");
        }

    }
}

class Play {

    private static final int max_desk = 3;

    private final String[][] desk = new String[max_desk][max_desk];
//    private final int[][] brain = new int[max_desk][max_desk];

    public void initDesk(){
        for(int x = 0; x<max_desk; x++){
            for(int y = 0; y<max_desk; y++) {
                desk[x][y] = " ";
            }
        }

//        brain[0][0] = 1;
//        brain[0][2] = 1;
//        brain[2][0] = 1;
//        brain[2][2] = 1;
//        brain[1][1] = 2;
    }

    private static int toInt(String str){
        int a = -1;
        try {
            a = (Integer.parseInt(str) - 1);
        } catch (Exception e) {
            System.out.println("Unable to parse string to int: " + e.getMessage());
        }
        if ((a < max_desk) & (a >= 0)) return a;
        else return -1;
    }

    public void getHop() {
        int x,y;
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Input (y x): ");
            String hop = in.nextLine();
            hop += " 4 4";  // fix if enter one or none
            String[] hod = hop.split(" ");
            x = toInt(hod[0]);
            y = toInt(hod[1]);
            if ((x != -1) & (y != -1)) {
                if (!desk[x][y].equals(" ")){
                    System.out.println("error, alrady busy");
                } else break;
            } else {
                System.out.println("error, type [1..3] [1..3]");
            }
        }
        desk[x][y] = "x";
    }

    public void goAI(){
        int max=2;
        int min=0;
        int x,y;
        do {
            x = (int) (Math.random() * ((max - min) + 1)) + min;
            y = (int) (Math.random() * ((max - min) + 1)) + min;
        } while (!desk[x][y].equals(" "));
        desk[x][y] = "0";
    }

    private void driveLine(){
        for(int i=0; i<max_desk;i++){
            System.out.print("+-");
        }
        System.out.println("+");
    }

    public void draw(){
        for (int x=0; x < max_desk; x++ ){
            //System.out.println("+-+-+-+");
            driveLine();
            for (int y=0; y < max_desk; y++ ){
                System.out.print("|");
                System.out.print(desk[x][y]);
            }
            System.out.println("|");
        }
        //System.out.println("+-+-+-+");
        driveLine();
    }

    public void checkWin(String simvol){
        int x,y;
        // on horizontal
        for(x=0;x<max_desk;x++){
            boolean flag=true;
            for(y=0;y<max_desk;y++){
               if(!desk[x][y].equals(simvol)){
                   flag = false;
               }
            }
            if (flag){
                System.out.println(simvol + " - Win! (hor)");
                System.exit(0);
            }
        }
        // on vertikal
        for(y=0;y<max_desk;y++){
            boolean flag=true;
            for(x=0;x<max_desk;x++){
                if(!desk[x][y].equals(simvol)){
                    flag = false;
                }
            }
            if (flag){
                System.out.println(simvol + " - Win! (ver)");
                System.exit(0);
            }
        }
        // on diagonal x=y
        boolean flag=true;
        for(y=0;y<max_desk;y++){
            if(!desk[y][y].equals(simvol)){
                flag = false;
            }
        }
        if (flag){
            System.out.println(simvol + " - Win! (diag x=y)");
            System.exit(0);
        }
        // on diagonal x=max-y
        flag=true;
        for(y=0;y<max_desk;y++){
            if(!desk[max_desk - y - 1][y].equals(simvol)){
                flag = false;
            }
        }
        if (flag){
            System.out.println(simvol + " - Win! (diag x=max-y)");
            System.exit(0);
        }
    }
}
