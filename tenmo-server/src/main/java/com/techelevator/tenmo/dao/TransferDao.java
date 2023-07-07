package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    Transfer transferMoney(Transfer transfer);

    void withdrawMoney(Transfer transfer);

    void deposit(Transfer transfer);

    Transfer transferHistory(int id);

    List<Transfer> listTransferHistory();

//    Transfer username(int id);

}
