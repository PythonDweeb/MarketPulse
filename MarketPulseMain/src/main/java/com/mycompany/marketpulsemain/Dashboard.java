/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.marketpulsemain;

/**
 *
 * @author kraj200
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    private Data data;
    private User user;
    
    public Dashboard() {
        initComponents();
        data = Data.getInstance();
        user = User.getInstance();
//        if (data.getVisited() == 0) {
        setup();
//        data.setVisitedTrue();
//        }
    }
    
    private String formatContributionValue(double current, double difference) {
        if (current >= 0) {
            return String.format("%.2f%% (+$%.2f)", current*100, Math.abs(difference));
        } else {
            return String.format("%.2f%% (-$%.2f)", current*100, (0-difference));
        }
    }
    
    private String formatInvestmentValue(double change, double old, double curr) {
//        System.out.printf("%.2f, %.2f, %.2f%n", change, old, curr);
        if (change >= 0) {
            return String.format("$%.2f (+%.2f%%)", curr, Math.abs((100*change)/old));
        } else {
            return String.format("$%.2f (-%.2f%%)", curr, (0-(change/old)*100));
        }
    }
    
    private String formatHoldingsValue(double change, double old) {
//        System.out.printf("%.2f, %.2f, %.2f%n", change, old, curr);
        if (change >= 0) {
            return String.format("$%.2f (+$%.2f)", old+user.getTotalCash(), Math.abs(change));
        } else {
            return String.format("$%.2f (-$%.2f)", old+user.getTotalCash(), (0-(change)));
        }
    }
    
    private String getVolumeMagnitude(long vol, long vollist[]) {
        long avg = 0;
        long low = 0;
        long high = 0;
        for (int i = 0; i < 23; i++) {
            if (i == 0) {
                low = vollist[i];
                high = vollist[i];
            } else {
                if (vollist[i] < low) {
                    low = vollist[i];
                }
                if (vollist[i] > high) {
                    high = vollist[i];
                }
            }
            
            avg += vollist[i];
        }
        avg /= 23;
        if (low <= vol && vol <= avg && (vol-low <= avg - vol)) {
            return String.format("Low");
        } else if (high >= vol && vol >= avg && (high-vol <= vol-avg)) {
            return String.format("High");
        } else {
            return String.format("Medium");
        }
    }
    
    private void setup() {
        totalcash.setText(String.format("$%.2f",user.getTotalCash()));
        double appleval = user.getSharesInApple() * data.getAppleClose();
        double amazonval = user.getSharesInAmazon() * data.getAmazonClose(); 
        double broadcomval = user.getSharesInBroadcom() * data.getBroadcomClose();
        double googleval = user.getSharesInGoogle() * data.getGoogleClose();
        double metaval = user.getSharesInMeta() * data.getMetaClose();
        double microsoftval = user.getSharesInMicrosoft() * data.getMicrosoftClose();
        double qualcommval = user.getSharesInQualcomm() * data.getQualcommClose();
        double applechange = 0;
        double amazonchange = 0;
        double broadcomchange = 0;
        double googlechange = 0;
        double metachange = 0;
        double microsoftchange = 0;
        double qualcommchange = 0;
        double totalchange = 0;
        applevolume.setText(getVolumeMagnitude(data.getAppleVolume(),data.getAppleVolumeArray()));
        amazonvolume.setText(getVolumeMagnitude(data.getAmazonVolume(),data.getAmazonVolumeArray()));
        broadcomvolume.setText(getVolumeMagnitude(data.getBroadcomVolume(),data.getBroadcomVolumeArray()));
        googlevolume.setText(getVolumeMagnitude(data.getGoogleVolume(),data.getGoogleVolumeArray()));
        metavolume.setText(getVolumeMagnitude(data.getMetaVolume(),data.getMetaVolumeArray()));
        microsoftvolume.setText(getVolumeMagnitude(data.getMicrosoftVolume(),data.getMicrosoftVolumeArray()));
        qualcommvolume.setText(getVolumeMagnitude(data.getQualcommVolume(),data.getQualcommVolumeArray()));
        investmentvalue.setText(formatInvestmentValue(totalchange,appleval+amazonval+broadcomval+googleval+metaval+microsoftval+qualcommval,appleval+amazonval+broadcomval+googleval+metaval+microsoftval+qualcommval));
        amazoncontribution.setText(formatContributionValue(0, amazonchange));
        applecontribution.setText(formatContributionValue(0, applechange));
        broadcomcontribution.setText(formatContributionValue(0, broadcomchange));
        googlecontribution.setText(formatContributionValue(0, googlechange));
        metacontribution.setText(formatContributionValue(0, metachange));
        microsoftcontribution.setText(formatContributionValue(0, microsoftchange));
        qualcommcontribution.setText(formatContributionValue(0, qualcommchange));
        netholdings.setText(formatHoldingsValue(0,appleval+amazonval+broadcomval+googleval+metaval+microsoftval+qualcommval));
        if (data.getDay() > 0) {
            applechange = user.getSharesInApple() * (data.getAppleClose() - data.getAppleCloseYesterday());
            amazonchange = user.getSharesInAmazon() * (data.getAmazonClose() - data.getAmazonCloseYesterday()); 
            broadcomchange = user.getSharesInBroadcom() * (data.getBroadcomClose()-data.getBroadcomCloseYesterday());
            googlechange = user.getSharesInGoogle() * (data.getGoogleClose()-data.getGoogleCloseYesterday());
            metachange = user.getSharesInMeta() * (data.getMetaClose()-data.getMetaCloseYesterday());
            microsoftchange = user.getSharesInMicrosoft() * (data.getMicrosoftClose()-data.getMicrosoftCloseYesterday());
            qualcommchange = user.getSharesInQualcomm() * (data.getQualcommClose()-data.getQualcommCloseYesterday());
            totalchange = applechange + amazonchange + broadcomchange + googlechange + metachange + microsoftchange + qualcommchange;
            investmentvalue.setText(formatInvestmentValue(totalchange,user.getSharesInApple() * data.getAppleCloseYesterday() + user.getSharesInAmazon() * data.getAmazonCloseYesterday()
        +user.getSharesInBroadcom() * data.getBroadcomCloseYesterday() + user.getSharesInGoogle() * data.getGoogleCloseYesterday() + user.getSharesInMicrosoft() * data.getMicrosoftCloseYesterday()
        +user.getSharesInMeta() * data.getMetaCloseYesterday() + user.getSharesInQualcomm() * data.getQualcommCloseYesterday(),appleval+amazonval+broadcomval+googleval+metaval+microsoftval+qualcommval));
            amazoncontribution.setText(formatContributionValue(((data.getAmazonClose() - data.getAmazonCloseYesterday())/data.getAmazonCloseYesterday()), amazonchange));
            applecontribution.setText(formatContributionValue(((data.getAppleClose() - data.getAppleCloseYesterday())/data.getAppleCloseYesterday()), applechange));
            broadcomcontribution.setText(formatContributionValue(((data.getBroadcomClose() - data.getBroadcomCloseYesterday())/data.getBroadcomCloseYesterday()), broadcomchange));
            googlecontribution.setText(formatContributionValue(((data.getGoogleClose() - data.getGoogleCloseYesterday())/data.getGoogleCloseYesterday()), googlechange));
            metacontribution.setText(formatContributionValue(((data.getMetaClose() - data.getMetaCloseYesterday())/data.getMetaCloseYesterday()), metachange));
            microsoftcontribution.setText(formatContributionValue(((data.getMicrosoftClose() - data.getMicrosoftCloseYesterday())/data.getMicrosoftCloseYesterday()), microsoftchange));
            qualcommcontribution.setText(formatContributionValue(((data.getQualcommClose() - data.getQualcommCloseYesterday())/data.getQualcommCloseYesterday()), qualcommchange));
            netholdings.setText(formatHoldingsValue(totalchange,appleval+amazonval+broadcomval+googleval+metaval+microsoftval+qualcommval));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        totalcash = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        investmentvalue = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        netholdings = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        applevolume = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        amazonvolume = new javax.swing.JLabel();
        broadcomvolume = new javax.swing.JLabel();
        googlevolume = new javax.swing.JLabel();
        metavolume = new javax.swing.JLabel();
        microsoftvolume = new javax.swing.JLabel();
        qualcommvolume = new javax.swing.JLabel();
        applecontribution = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        amazoncontribution = new javax.swing.JLabel();
        broadcomcontribution = new javax.swing.JLabel();
        googlecontribution = new javax.swing.JLabel();
        metacontribution = new javax.swing.JLabel();
        microsoftcontribution = new javax.swing.JLabel();
        qualcommcontribution = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Simulate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Dashboard");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Graphs");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Buy/Sell");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Hints");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        Title.setText("Dashboard");

        jLabel1.setText("Cash (Able to invest):");

        totalcash.setText("$xxxx.xx");

        jLabel2.setText("Invested:");

        investmentvalue.setText("$xxxx.xx");

        jLabel3.setText("Net Holdings:");

        netholdings.setText("$xxxx.xx");

        jLabel4.setText("Amazon");

        jLabel5.setText("      Apple");

        jLabel6.setText("Broadcom");

        jLabel7.setText("Google");

        jLabel8.setText("Meta");

        jLabel9.setText("Microsoft");

        jLabel10.setText("Qualcomm");

        applevolume.setText("High");

        jLabel12.setText("Company");

        jLabel13.setText("Volume");

        amazonvolume.setText("High");

        broadcomvolume.setText("High");

        googlevolume.setText("High");

        metavolume.setText("High");

        microsoftvolume.setText("High");

        qualcommvolume.setText("High");

        applecontribution.setText("...");

        jLabel14.setText("Contribution");

        amazoncontribution.setText("...");

        broadcomcontribution.setText("...");

        googlecontribution.setText("...");

        metacontribution.setText("...");

        microsoftcontribution.setText("...");

        qualcommcontribution.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(Title)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(netholdings)
                                    .addComponent(totalcash)
                                    .addComponent(investmentvalue))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel7)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel10))
                                                .addGap(19, 19, 19)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(amazonvolume)
                                                    .addComponent(applevolume)
                                                    .addComponent(broadcomvolume)
                                                    .addComponent(googlevolume)
                                                    .addComponent(metavolume)
                                                    .addComponent(microsoftvolume)
                                                    .addComponent(qualcommvolume)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel12)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(amazoncontribution)
                                            .addComponent(applecontribution)
                                            .addComponent(broadcomcontribution)
                                            .addComponent(googlecontribution)
                                            .addComponent(metacontribution)
                                            .addComponent(microsoftcontribution)
                                            .addComponent(qualcommcontribution))))
                                .addGap(97, 97, 97))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(26, 26, 26)
                        .addComponent(investmentvalue)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(netholdings)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(totalcash)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(238, 238, 238))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(applevolume)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(amazonvolume))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(broadcomvolume))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(googlevolume))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(metavolume)
                                                .addGap(4, 4, 4)
                                                .addComponent(microsoftvolume)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(qualcommvolume))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel10))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(8, 8, 8)
                                        .addComponent(applecontribution)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(amazoncontribution)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(broadcomcontribution)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(googlecontribution)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(metacontribution)
                                        .addGap(4, 4, 4)
                                        .addComponent(microsoftcontribution)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(qualcommcontribution)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SimulateDay simday = new SimulateDay();
        simday.show();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Dashboard dash = new Dashboard();
        dash.show();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Graphs gra = new Graphs();
        gra.show();
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        BuySell byse = new BuySell();
        byse.show();
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Hints newan = new Hints();
        newan.show();
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JLabel amazoncontribution;
    private javax.swing.JLabel amazonvolume;
    private javax.swing.JLabel applecontribution;
    private javax.swing.JLabel applevolume;
    private javax.swing.JLabel broadcomcontribution;
    private javax.swing.JLabel broadcomvolume;
    private javax.swing.JLabel googlecontribution;
    private javax.swing.JLabel googlevolume;
    private javax.swing.JLabel investmentvalue;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JLabel metacontribution;
    private javax.swing.JLabel metavolume;
    private javax.swing.JLabel microsoftcontribution;
    private javax.swing.JLabel microsoftvolume;
    private javax.swing.JLabel netholdings;
    private javax.swing.JLabel qualcommcontribution;
    private javax.swing.JLabel qualcommvolume;
    private javax.swing.JLabel totalcash;
    // End of variables declaration//GEN-END:variables
}
