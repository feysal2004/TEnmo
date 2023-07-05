package com.techelevator.tenmo.dao;

public interface AccountDao {

    double getBalance(int id);

    int getUserId(String username);
    int getAccountId(int userId);






}
