package com.ytowka;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Signature {
    private static String testSignature = "ytowka";
    //wmic diskdrive get model,name,serialnumber
    public static void setTestSignature(String s){
        testSignature = s;
    }

    public static String MotherBoardSignature(){
        String signature;
        try {
            Process r = Runtime.getRuntime().exec("wmic baseboard get product");
            InputStream consoleIS = r.getInputStream();
            Scanner in = new Scanner(consoleIS);
            signature = "";
            while (in.hasNext()){
                signature += in.nextLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            signature = "0";
        }
        return signature+":type=0";
    }
    public static String testSignature(){
        return testSignature;
    }
    public static String HardDriveSignature(){
        String signature;
        try {
            Process r = Runtime.getRuntime().exec("wmic diskdrive get model,name,serialnumber");
            InputStream consoleIS = r.getInputStream();
            Scanner in = new Scanner(consoleIS);
            signature = "";
            while (in.hasNext()){
                signature += "\n"+ in.nextLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            signature = "0";
        }
        return signature+":type=1";
    }
}
