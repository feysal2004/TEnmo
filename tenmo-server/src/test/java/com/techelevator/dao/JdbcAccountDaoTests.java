package com.techelevator.dao;

import ch.qos.logback.core.net.AbstractSSLSocketAppender;
import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcAccountDaoTests extends BaseDaoTests {

    protected static final Account ACCOUNT_1 = new Account(2001, 1001,888.00);
    protected static final Account ACCOUNT_2 = new Account(2002, 1002,1112.00);

    private JdbcAccountDao test;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        test = new JdbcAccountDao(jdbcTemplate);

    }


    @Test
    public void get_balance(){

        double account = test.getBalance(1001);
        Assert.assertEquals(ACCOUNT_1, account);

    }





}
