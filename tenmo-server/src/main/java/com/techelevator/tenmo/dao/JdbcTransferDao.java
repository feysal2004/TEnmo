package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Transfer transferMoney( Transfer transfer) {

        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, (SELECT account_id FROM account WHERE user_id = ?), (SELECT account_id FROM account WHERE user_id = ?), ?) RETURNING transfer_id;";

        int newTransferId = jdbcTemplate.queryForObject(sql, int.class , transfer.getTransferTypeId(), transfer.getTransferStatusId(),transfer.getUserFrom(), transfer.getUserTo(),transfer.getAmount());

        transfer.setTransferId(newTransferId);

        return transfer;

    }

    @Override
    public void withdrawMoney(Transfer transfer) {

        String sql = "UPDATE account " +
                "SET balance = balance - (SELECT amount " +
                "FROM transfer " +
                "WHERE transfer_id = ?) " +
                "WHERE user_id = ?;";

        jdbcTemplate.update(sql, transfer.getTransferId(), transfer.getUserFrom());

    }

    @Override
    public void deposit(Transfer transfer) {
        String sql = "UPDATE account " +
                "SET balance = balance + (SELECT amount " +
                "FROM transfer " +
                "WHERE transfer_id = ?) " +
                "WHERE user_id = ?; ";
        //updates
            jdbcTemplate.update(sql, transfer.getTransferId(), transfer.getUserTo());
    }

    @Override
    public Transfer transferHistory(int id) {
        String sql = "select * from transfer where transfer_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()){
            Transfer transfer = mapToRowTransfer(results);
            return transfer;
        }

        return null;
    }

    @Override
    public List<Transfer> listTransferHistory() {
        String sql = "select * from transfer";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        List<Transfer> transferHistory = new ArrayList<>();

        if (results.next()){
            Transfer transfer = mapToRowTransfer(results);

            transferHistory.add(transfer);

            return transferHistory;
        }

        return null;
    }


    private Transfer mapToRowTransfer(SqlRowSet sqlRowSet){
        Transfer transfer = new Transfer();

        transfer.setUserFrom(sqlRowSet.getInt("account_from"));
        transfer.setUserTo(sqlRowSet.getInt("account_to"));
        transfer.setAmount(sqlRowSet.getDouble("amount"));
        transfer.setTransferId(sqlRowSet.getInt("transfer_id"));
        transfer.setTransferStatusId(sqlRowSet.getInt("transfer_status_id"));
        transfer.setTransferTypeId(sqlRowSet.getInt("transfer_type_id"));
        return transfer;
    }

}
