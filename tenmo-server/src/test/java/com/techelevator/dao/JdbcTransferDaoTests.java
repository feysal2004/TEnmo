package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class JdbcTransferDaoTests extends BaseDaoTests{

    protected static final Transfer TRANSFER_1 = new Transfer(3001, 2, 2, 1001, 1002, 100.50, "user1", "user2");
    protected static final Transfer TRANSFER_2 = new Transfer(3002, 2, 2, 1003, 1001, 1000.00, "user3", "user1");
    protected static final Transfer TRANSFER_3 = new Transfer(3003, 2, 2, 1002, 1001, 99.99, "user2", "user1");
    protected static final Transfer TRANSFER_4 = new Transfer(3004, 2, 2, 1001, 1002, 50.00, "user1", "user2");



    private JdbcTransferDao test;


    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        test = new JdbcTransferDao(jdbcTemplate);
    }

    @Test
    public void sendMoney(){
        Transfer testTransfer = test.transferMoney(TRANSFER_4);
        Assert.assertEquals(TRANSFER_4, testTransfer);
    }

    @Test
    public void sendMoney_given_invalid_amount_throws_exception(){
        test.transferMoney(TRANSFER_3);

        test.transferMoney(TRANSFER_1);
    }

    @Test
    public void transferHistory(){

        List<Transfer> transfer = test.listTransferHistory();

        Assert.assertNotNull(transfer);
        Assert.assertEquals(2, transfer.size());
        Assert.assertEquals(TRANSFER_2, transfer.get(0));
        Assert.assertEquals(TRANSFER_4, transfer.get(1));

    }

    @Test
    public void transferHistoryById(){
        Transfer transfer = test.transferHistory(TRANSFER_4.getTransferId());

        Assert.assertEquals(TRANSFER_4, transfer);
    }

    @Test
    public void transferHistoryById_given_invalid_Id(){
        test.transferHistory(011);
    }


}
