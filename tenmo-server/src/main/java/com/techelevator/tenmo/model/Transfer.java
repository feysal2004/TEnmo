package com.techelevator.tenmo.model;

public class Transfer {

    private int transferId;
    private int transferStatusId;
    private int transferTypeId;
    private int userFrom;
    private int userTo;
    private double amount;
    private String userFromName;
    private String userToName;
    private String transferTypeDescription;
    private String transferStatusDescription;
    public Transfer(){

    }

    public Transfer(int transferId, int transferStatusId, int transferTypeId, int userFrom, int userTo, double amount, String userFromName, String userToName){

        this.transferId = transferId;
        this.transferStatusId = transferStatusId;
        this.transferTypeId = transferTypeId;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;
        this.userToName = userToName;
        this.userFromName = userFromName;

    }

    public String getTransferTypeDescription() {
        return transferTypeDescription;
    }

    public void setTransferTypeDescription(String transferTyperDescription) {
        this.transferTypeDescription = transferTyperDescription;
    }

    public String getTransferStatusDescription() {
        return transferStatusDescription;
    }

    public void setTransferStatusDescription(String transferStatusDescription) {
        this.transferStatusDescription = transferStatusDescription;
    }

    public String getUserFromName() {
        return userFromName;
    }

    public void setUserFromName(String userFromName) {
        this.userFromName = userFromName;
    }

    public String getUserToName() {
        return userToName;
    }

    public void setUserToName(String userToName) {
        this.userToName = userToName;
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
