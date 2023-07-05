package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransferDao implements TransferDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public double transferMoney( int account) {

        String sql = "SELECT amount FROM transfer\n" +
                "WHERE account_to = ?";
        //insert

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, account);
        if (results.next()) {
            Transfer transfer = mapToRowTransfer(results);
            return  transfer.getAccountTo();
        } else {
            return 0.00;
        }

    }

    @Override
    public double withdrawMoney( int accountFrom) {

        String sql = "SELECT amount FROM transfer\n" +
                "WHERE account_from = ?";
        //update

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,accountFrom);
        if (results.next()){
            Transfer transfer = mapToRowTransfer(results);
            return transfer.getAmount();
        }


        return 0;
    }

    @Override
    public double deposit(double depositAmount) {

        String sql = "SELECT amount FROM transfer\n" +
                "WHERE account_from = ?";
        //updates

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, depositAmount);
        if (results.next()){
            Transfer transfer = mapToRowTransfer(results);
            return transfer.getAmount();
        }


        return 0;
    }

    private Transfer mapToRowTransfer(SqlRowSet sqlRowSet){
        Transfer transfer = new Transfer();
        transfer.setAccountFrom(sqlRowSet.getInt("account_from"));
        transfer.setAccountTo(sqlRowSet.getInt("account_to"));
        transfer.setAmount(sqlRowSet.getDouble("amount"));
        transfer.setTransferId(sqlRowSet.getInt("transfer_id"));
        transfer.setTransferStatusId(sqlRowSet.getInt("transfer_status_id"));
        transfer.setTransferTypeId(sqlRowSet.getInt("transfer_type_id"));
        return transfer;
    }

}
