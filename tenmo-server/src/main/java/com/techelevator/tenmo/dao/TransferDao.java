package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    Transfer transferMoney(Transfer transfer);

    void withdrawMoney(Transfer transfer);

    void deposit(Transfer transfer);

    Transfer transferHistory(Transfer transfer);

    List<Transfer> listTransferHistory();

}
