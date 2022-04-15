package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Play desk = new Play();
        desk.initDesk();
        while(true){
            desk.draw();
            desk.getHop();
        }

    }
}

class Play {

    private static final int max_desk = 3;

    private String[][] desk = new String[max_desk][max_desk];
    private int[][] brain = new int[max_desk][max_desk];

    public void initDesk(){
        for(int x = 0; x<max_desk; x++){
            for(int y = 0; y<max_desk; y++) {
                desk[x][y] = " ";
            }
        }

        brain[0][0] = 1;
        brain[0][2] = 1;
        brain[2][0] = 1;
        brain[2][2] = 1;
        brain[1][1] = 2;
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
        boolean flag = true;
        int x,y;
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Input (x y): ");
            String hop = in.nextLine();
            hop += " 4 4";  // fix if enter one or none
            String[] hod = hop.split(" ");
            x = toInt(hod[0]);
            y = toInt(hod[1]);
            if ((x != -1) & (y != -1)) {
                if (desk[x][y] != " 2"){
                    System.out.println("error, alrady busy");
                } else break;
            } else {
                System.out.println("error, type [1..3] [1..3]");
            }
        }
        desk[x][y] = "x";
    }

    public void draw(){
        for (int x=0; x < max_desk; x++ ){
            System.out.println("+-+-+-+");
            for (int y=0; y < max_desk; y++ ){
                System.out.print("|");
                System.out.print(desk[x][y]);
            }
            System.out.println("|");
        }
        System.out.println("+-+-+-+");
    }
}
