package com.itheima;

public class Account {
    private String cardID;
    private String userName;
    private String password;
    private double money;
    private double quotaMoney;

    public Account() {
    }

    public Account(String cardID, String userName, String password, double quotaMoney) {
        this.cardID = cardID;
        this.userName = userName;
        this.password = password;
        //this.money = money;
        this.quotaMoney = quotaMoney;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getQuotaMoney() {
        return quotaMoney;
    }

    public void setQuotaMoney(double quotaMoney) {
        this.quotaMoney = quotaMoney;
    }
}
