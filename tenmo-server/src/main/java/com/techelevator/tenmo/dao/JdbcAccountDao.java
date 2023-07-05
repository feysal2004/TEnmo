package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int getUserId(String username) {

        String sql = "SELECT user_id FROM account\n" +
                "JOIN tenmo_user ON tenmo_user.user_id = account.user_id\n" +
                "WHERE username = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if (results.next()){
            Account account = mapRowToAccount(results);
            return account.getUserId();
        }
        return 0;
    }

    @Override
    public int getAccountId(int userId) {

        String sql = "SELECT account_id FROM account\n" +
                "WHERE user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()){
            Account account = mapRowToAccount(results);
            return account.getAccountId();
        }




        return 0;
    }

    @Override
    public double getBalance(int userId) {

        String sql = "SELECT balance FROM account\n" +
                "WHERE user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return results.getDouble("balance");
        } else {
            return 0.00;
        }

    }



    private Account mapRowToAccount(SqlRowSet sqlRowSet) {
        Account account = new Account();
        account.setAccountId(sqlRowSet.getInt("account_id"));
        account.setUserId(sqlRowSet.getInt("user_id"));
        account.setBalanceDecimal(sqlRowSet.getDouble("balance"));
        return account;
    }


}
