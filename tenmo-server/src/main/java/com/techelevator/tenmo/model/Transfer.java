package com.techelevator.tenmo.model;

public class Transfer {

    private int transferId;
    private int transferStatusId;
    private int transferTypeId;
    private int userFrom;
    private int userTo;
    private double amount;

    public Transfer(){

    }

    public Transfer(int transferId, int transferStatusId, int transferTypeId, int userFrom, int userTo, int amount){

        this.transferId = transferId;
        this.transferStatusId = transferStatusId;
        this.transferTypeId = transferTypeId;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;

    }



    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(int userFrom) {
        this.userFrom = userFrom;
    }

    public int getUserTo() {
        return userTo;
    }

    public void setUserTo(int userTo) {
        this.userTo = userTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
