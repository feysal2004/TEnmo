package com.techelevator.tenmo.model;

public class Account {

    private int accountId;
    private int userId;
    private double balanceDecimal;

    public Account (int accountId, int userId, double balanceDecimal ){
        this.accountId = accountId;
        this.userId = userId;
        this.balanceDecimal = balanceDecimal;

    }

    public Account(){

    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalanceDecimal() {
        return balanceDecimal;
    }

    public void setBalanceDecimal(double balanceDecimal) {
        this.balanceDecimal = balanceDecimal;
    }
}
