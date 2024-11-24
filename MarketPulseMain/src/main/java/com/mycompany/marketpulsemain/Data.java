/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.marketpulsemain;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author vihaan G08244778
 */

public class Data {

    // static for singleton
    private static Data instance = null;

    private static final int DATA_SIZE = 23; //num of data lines

    private String[] dates = new String[DATA_SIZE];
    private boolean datesLoaded = false;

    private int day = 0;

    private double[] amazonClose = new double[DATA_SIZE];
    private long[] amazonVolume = new long[DATA_SIZE];

    private double[] appleClose = new double[DATA_SIZE];
    private long[] appleVolume = new long[DATA_SIZE];

    private double[] broadcomClose = new double[DATA_SIZE];
    private long[] broadcomVolume = new long[DATA_SIZE];

    private double[] googleClose = new double[DATA_SIZE];
    private long[] googleVolume = new long[DATA_SIZE];

    private double[] metaClose = new double[DATA_SIZE];
    private long[] metaVolume = new long[DATA_SIZE];

    private double[] microsoftClose = new double[DATA_SIZE];
    private long[] microsoftVolume = new long[DATA_SIZE];

    private double[] qualcommClose = new double[DATA_SIZE];
    private long[] qualcommVolume = new long[DATA_SIZE];

//stops instantiation
    private Data() {
        loadData();
    }

//singleton instance shared across all files
    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    private void loadData() {
        String[] companies = {
            "Amazon", "Apple", "Broadcom", "Google", "Meta",
            "Microsoft", "Qualcomm"
        };

        for (String company : companies) {
            // Using relative paths to access resources
            String resourcePath = "/resources/" + company + "MonthData.csv";
            readCsvData(resourcePath, company);
        }
    }
    
    private void readCsvData(String resourcePath, String company) {
        try {
            InputStream is = getClass().getResourceAsStream(resourcePath);

//           for debugging maven resource directory location:
//            if (is == null) {
//                System.err.println("Resource not found: " + resourcePath);
//                return;
//            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Skip header line
            String line = br.readLine();

            for (int i = 0; i < DATA_SIZE; i++) {
                int index = DATA_SIZE - i - 1;
                line = br.readLine();

                if (line == null) {
                    System.err.println("Unexpected end of file for " + company + " at index " + index);
                    break;
                }

                // Split CSV line, accounting for commas inside quotes
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // Extract Date, Close, and Volume, cleaning up quotation marks and commas
                String date = values[0];
                String closeStr = values[4].replace("\"", "");
                String volumeStr = values[5].replace("\"", "").replace(",", "");

                // Add date to dates array if not already added
                if (!datesLoaded) {
                    dates[index] = date;
                }

                // Parse Close and Volume values
                double close = Double.parseDouble(closeStr);
                long volume = Long.parseLong(volumeStr);

                // Store data based on the company
                switch (company) {
                    case "Amazon":
                        amazonClose[index] = close;
                        amazonVolume[index] = volume;
                        break;
                    case "Apple":
                        appleClose[index] = close;
                        appleVolume[index] = volume;
                        break;
                    case "Broadcom":
                        broadcomClose[index] = close;
                        broadcomVolume[index] = volume;
                        break;
                    case "Google":
                        googleClose[index] = close;
                        googleVolume[index] = volume;
                        break;
                    case "Meta":
                        metaClose[index] = close;
                        metaVolume[index] = volume;
                        break;
                    case "Microsoft":
                        microsoftClose[index] = close;
                        microsoftVolume[index] = volume;
                        break;
                    case "Qualcomm":
                        qualcommClose[index] = close;
                        qualcommVolume[index] = volume;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown company: " + company);
                }
            }

            // Close the BufferedReader
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // After the first company's data is loaded, mark dates as loaded
        if (!datesLoaded) {
            datesLoaded = true;
        }
    }

    // Method to increment the day
    public void incrementDay() {
        if (day < DATA_SIZE - 1) {
            day++;
        } else {
            System.out.println("Reached the end of data.");
        }
    }

    // Getter for the current day
    public int getDay() {
        return day;
    }

    // Method to get the current date
    public String getCurrentDate() {
        return dates[day];
    }

    // Getter methods for the current day's data
    public double getAmazonClose() {
        return amazonClose[day];
    }

    public long getAmazonVolume() {
        return amazonVolume[day];
    }

    public double getAppleClose() {
        return appleClose[day];
    }

    public long getAppleVolume() {
        return appleVolume[day];
    }

    public double getBroadcomClose() {
        return broadcomClose[day];
    }

    public long getBroadcomVolume() {
        return broadcomVolume[day];
    }

    public double getGoogleClose() {
        return googleClose[day];
    }

    public long getGoogleVolume() {
        return googleVolume[day];
    }

    public double getMetaClose() {
        return metaClose[day];
    }

    public long getMetaVolume() {
        return metaVolume[day];
    }

    public double getMicrosoftClose() {
        return microsoftClose[day];
    }

    public long getMicrosoftVolume() {
        return microsoftVolume[day];
    }

    public double getQualcommClose() {
        return qualcommClose[day];
    }

    public long getQualcommVolume() {
        return qualcommVolume[day];
    }
    
    // add getters for other arrays if full are needed in future

    public String[] getDates() {
        return dates;
    }

    public double[] getAmazonCloseArray() {
        return amazonClose;
    }

    public long[] getAmazonVolumeArray() {
        return amazonVolume;
    }

}
