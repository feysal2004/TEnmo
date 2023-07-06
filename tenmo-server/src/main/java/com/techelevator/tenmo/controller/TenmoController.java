package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.UserNotActivatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TenmoController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransferDao transferDao;
    private Transfer transfer;

    private User user;

    public TenmoController(UserDao _userDao) {
        this.userDao = _userDao;

    }

    @RequestMapping (path = "/balance", method = RequestMethod.GET)
    public double getBalance(Principal principal){
       int user = userDao.findIdByUsername(principal.getName());
        if (user == 0) {
            throw new UsernameNotFoundException("Username Not Found");
        } else {
            return accountDao.getBalance(user);
        }

    }


    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public Transfer transferMoney (@RequestBody Transfer transfer, Principal principal){

        int userId = userDao.findIdByUsername(principal.getName());
        if (userId == 0) {

            throw new UsernameNotFoundException("Username Not Found");
        } else {
            Transfer transfer1 = transferDao.transferMoney(transfer);
           transferDao.withdrawMoney(transfer.getAccountFrom());
           transferDao.deposit(transfer.getAccountTo());
            // call upon withdraw and deposit
            return transfer;
        }

    }

//    @RequestMapping(path = "/withdraw", method = RequestMethod.POST)
//    public double withdrawMoney (Principal principal){
//        int userId = userDao.findIdByUsername(principal.getName());
//        if (userId == 0){
//            throw new UsernameNotFoundException("Username Not Found");
//        } else {
//            return transferDao.withdrawMoney(userId);
//        }
//    }
//
//    @RequestMapping(path = "/deposit", method = RequestMethod.POST)
//    public double deposit (Principal principal){
//        int userId = userDao.findIdByUsername(principal.getName());
//        if (userId == 0){
//            throw new UsernameNotFoundException("Username Not Found");
//        } else {
//            return transferDao.deposit(userId);
//        }
//    }


    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User>userList(){
        List<User>users = new ArrayList<>();
         users = userDao.findAll();
         return users;
    }


//    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
//    public User findUserById(@PathVariable int id){
//        user = userDao.getUserById(id);
//        return user;
//    }
//
//    @RequestMapping(path = "/users/findByUsername", method = RequestMethod.GET)
//    public User findByUsername (@RequestParam String username){
//        user = userDao.findByUsername(username);
//        return user;
//    }











}
