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
    public Transfer transferMoney( Transfer transfer) {

        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
                "VALUES (?, ?, ?, ?, ?)RETURNING transfer_id;";

        int newTransferId = jdbcTemplate.queryForObject(sql, int.class , transfer.getTransferTypeId(), transfer.getTransferStatusId(),transfer.getAccountFrom(),transfer.getAccountTo(),transfer.getAmount());
        String sql2 = "SELECT * FROM transfer WHERE transfer_id = ? ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql2, newTransferId);
        transfer = mapToRowTransfer(results);
        return transfer;


    }

    @Override
    public double withdrawMoney( int accountFrom) {

        String sql = "UPDATE account\n" +
                "SET balance = balance - (SELECT amount\n" +
                "\t\t\t  FROM transfer)\n" +
                "WHERE account_id = (SELECT account_from\n" +
                "\t\t\t\t   FROM transfer);";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,accountFrom);
        if (results.next()){
            Transfer transfer = mapToRowTransfer(results);
            return transfer.getAmount();
        }


        return 0;
    }

    @Override
    public double deposit(double depositAmount) {

        String sql = "UPDATE account\n" +
                "SET balance = balance + (SELECT amount\n" +
                "\t\t\t  FROM transfer)\n" +
                "WHERE account_id = (SELECT account_to\n" +
                "\t\t\t\t   FROM transfer);\n";
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
