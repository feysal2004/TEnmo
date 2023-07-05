package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDao {

    Transfer transferMoney(Transfer transfer);

    double withdrawMoney( int accountFrom);

    double deposit(double depositAmount);



}
