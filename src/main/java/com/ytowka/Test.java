package com.ytowka;

import com.ytowka.responses.LicenceCheckResponse;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true){
            String[] command = in.nextLine().split(" ");
            if(command.length < 2){
                System.out.println("invalid command parameter");
            }else if(command[0].equals("/check")){
                LicenceChecker lc = new LicenceChecker(command[1]);
                System.out.println("status: "+lc.getLicenseStatus());
            }else if(command[0].equals("/reg")){
                LicenceChecker lc = new LicenceChecker(command[1]);
                System.out.println("status: "+lc.registerLicenceStatus());
            }else if(command[0].equals("/setsign")){
                Signature.setTestSignature(command[1]);
            } else{
                System.out.println("unknown command");
            }

        }
    }
}
