package com.techelevator.tenmo.dao;

public interface TransferDao {

    double transferMoney( int account);

    double withdrawMoney( int accountFrom);

    double deposit(double depositAmount);



}
