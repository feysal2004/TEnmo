package com.techelevator.tenmo.model;

public class Transfer {

    private int transferId;
    private int transferStatusId;
    private int transferTypeId;
    private int accountFrom;
    private int accountTo;
    private double amount;

    public Transfer(){

    }

    public Transfer(int transferId, int transferStatusId, int transferTypeId, int accountFrom, int accountTo, int amount){

        this.transferId = transferId;
        this.transferStatusId = transferStatusId;
        this.transferTypeId = transferTypeId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
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

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
