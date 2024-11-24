/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.marketpulsemain;

/**
 *
 * @author vihaan G08244778
 */

public class User {

    // Singleton instance
    private static User instance = null;

    // User attributes
    private double totalCash;
    private double investmentInAmazon;
    private double investmentInApple;
    private double investmentInBroadcom;
    private double investmentInGoogle;
    private double investmentInMeta;
    private double investmentInMicrosoft;
    private double investmentInQualcomm;
    private double investmentInSP500;

    // Private constructor to prevent instantiation
    private User() {
        // Initialize attributes
        totalCash = 10000.0; // Example initial cash amount
        investmentInAmazon = 0.0;
        investmentInApple = 0.0;
        investmentInBroadcom = 0.0;
        investmentInGoogle = 0.0;
        investmentInMeta = 0.0;
        investmentInMicrosoft = 0.0;
        investmentInQualcomm = 0.0;
        investmentInSP500 = 0.0;
    }

    // Method to get the singleton instance
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    // Getter and Setter methods

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public double getInvestmentInAmazon() {
        return investmentInAmazon;
    }

    public void setInvestmentInAmazon(double investmentInAmazon) {
        this.investmentInAmazon = investmentInAmazon;
    }

    public double getInvestmentInApple() {
        return investmentInApple;
    }

    public void setInvestmentInApple(double investmentInApple) {
        this.investmentInApple = investmentInApple;
    }

    public double getInvestmentInBroadcom() {
        return investmentInBroadcom;
    }

    public void setInvestmentInBroadcom(double investmentInBroadcom) {
        this.investmentInBroadcom = investmentInBroadcom;
    }

    public double getInvestmentInGoogle() {
        return investmentInGoogle;
    }

    public void setInvestmentInGoogle(double investmentInGoogle) {
        this.investmentInGoogle = investmentInGoogle;
    }

    public double getInvestmentInMeta() {
        return investmentInMeta;
    }

    public void setInvestmentInMeta(double investmentInMeta) {
        this.investmentInMeta = investmentInMeta;
    }

    public double getInvestmentInMicrosoft() {
        return investmentInMicrosoft;
    }

    public void setInvestmentInMicrosoft(double investmentInMicrosoft) {
        this.investmentInMicrosoft = investmentInMicrosoft;
    }

    public double getInvestmentInQualcomm() {
        return investmentInQualcomm;
    }

    public void setInvestmentInQualcomm(double investmentInQualcomm) {
        this.investmentInQualcomm = investmentInQualcomm;
    }

    public double getInvestmentInSP500() {
        return investmentInSP500;
    }

    public void setInvestmentInSP500(double investmentInSP500) {
        this.investmentInSP500 = investmentInSP500;
    }
}
