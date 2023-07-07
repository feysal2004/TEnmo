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

    protected static final Transfer TRANSFER_1 = new Transfer(3009, 2, 2, 1009, 1013, -80, "jojo", "momo");
    protected static final Transfer TRANSFER_2 = new Transfer(3033, 2, 2, 1109, 1513, 120, "wendy", "cc");
    protected static final Transfer TRANSFER_3 = new Transfer(3933, 2, 2, 1909, 1133, 0, "frogMan", "catLover");
    protected static final Transfer TRANSFER_4 = new Transfer(3300, 2, 2, 1119, 1913, 60, "usernomnom", "unknown");



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
        Transfer transfer = test.transferHistory(3009);

        Assert.assertEquals(TRANSFER_1, transfer);
    }

    @Test
    public void transferHistoryById_given_invalid_Id(){
        test.transferHistory(0);
    }


}
