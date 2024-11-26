/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.marketpulsemain;

/**
 *
 * @author vihaan G08244778
 */

//added user class
public class User {

    // Singleton instance
    private static User instance = null;

    private double totalCash;
    private int sharesInAmazon;
    private int sharesInApple;
    private int sharesInBroadcom;
    private int sharesInGoogle;
    private int sharesInMeta;
    private int sharesInMicrosoft;
    private int sharesInQualcomm;

    private User() {
        totalCash = 10000.0;
        sharesInAmazon = 0;
        sharesInApple = 0;
        sharesInBroadcom = 0;
        sharesInGoogle = 0;
        sharesInMeta = 0;
        sharesInMicrosoft = 0;
        sharesInQualcomm = 0;
    }

    // Method to get the singleton instance
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }


    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public int getSharesInAmazon() {
        return sharesInAmazon;
    }

    public void setSharesInAmazon(int sharesInAmazon) {
        this.sharesInAmazon = sharesInAmazon;
    }

    public int getSharesInApple() {
        return sharesInApple;
    }

    public void setSharesInApple(int sharesInApple) {
        this.sharesInApple = sharesInApple;
    }

    public int getSharesInBroadcom() {
        return sharesInBroadcom;
    }

    public void setSharesInBroadcom(int sharesInBroadcom) {
        this.sharesInBroadcom = sharesInBroadcom;
    }

    public int getSharesInGoogle() {
        return sharesInGoogle;
    }

    public void setSharesInGoogle(int sharesInGoogle) {
        this.sharesInGoogle = sharesInGoogle;
    }
    
    public int getSharesInMeta() {
        return sharesInMeta;
    }

    public void setSharesInMeta(int sharesInMeta) {
        this.sharesInMeta = sharesInMeta;
    }
    
    public int getSharesInMicrosoft() {
        return sharesInMicrosoft;
    }

    public void setSharesInMicrosoft(int sharesInMicrosoft) {
        this.sharesInMicrosoft = sharesInMicrosoft;
    }

    public int getSharesInQualcomm() {
        return sharesInQualcomm;
    }

    public void setSharesInQualcomm(int sharesInQualcomm) {
        this.sharesInQualcomm = sharesInQualcomm;
    }
}
