package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDao userDao;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Transfer transferMoney( Transfer transfer) {

        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, (SELECT account_id FROM account WHERE user_id = ?), (SELECT account_id FROM account WHERE user_id = ?), ?) RETURNING transfer_id; ";

        int newTransferId = jdbcTemplate.queryForObject(sql, int.class , 2, 2, transfer.getUserFrom(), transfer.getUserTo(),transfer.getAmount());

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
        String sql = "SELECT t.transfer_id, tt.transfer_type_desc, ts.transfer_status_desc, t.amount, " +
                "aFrom.account_id as fromAcct, aFrom.user_id as fromUser, aFrom.balance as fromBal, " +
                "aTo.account_id as toAcct, aTo.user_id as toUser, aTo.balance as toBal " +
                "FROM transfer t " +
                "INNER JOIN transfer_type tt ON t.transfer_type_id = tt.transfer_type_id " +
                "INNER JOIN transfer_status ts ON t.transfer_status_id = ts.transfer_status_id " +
                "INNER JOIN account aFrom on account_from = aFrom.account_id " +
                "INNER JOIN account aTo on account_to = aTo.account_id " +
                "WHERE t.transfer_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()){


            Transfer transfer = mapToRowTransfer(results);
            return transfer;
        }

        return null;
    }

    @Override
    public List<Transfer> listTransferHistory() {
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount, tenmo_user.username " +
                "FROM transfer " +
                "JOIN account ON transfer.account_from = account.account_id " +
                "JOIN tenmo_user ON account.user_id = tenmo_user.user_id " ;


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        List<Transfer> transferHistory = new ArrayList<>();

        while (results.next()){
            Transfer transfer = mapToRowTransfer(results);

            transferHistory.add(transfer);
        }

        return transferHistory;
    }



    private Transfer mapToRowTransfer(SqlRowSet sqlRowSet){
        Transfer transfer = new Transfer();

        User userFrom= userDao.getUserById(sqlRowSet.getInt("fromuser"));
        User userTo = userDao.getUserById(sqlRowSet.getInt("touser"));
        transfer.setUserFromName(userFrom.getUsername());
        transfer.setUserToName(userTo.getUsername());
        transfer.setAmount(sqlRowSet.getDouble("amount"));
        transfer.setTransferId(sqlRowSet.getInt("transfer_id"));
        //chnage to strings
        transfer.setTransferStatusId(sqlRowSet.getInt("transfer_status_id"));
        transfer.setTransferTypeId(sqlRowSet.getInt("transfer_type_id"));
        return transfer;
    }

}
