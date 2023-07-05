package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.UserNotActivatedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/tenmo")
@PreAuthorize("isAuthenticated()")
public class TenmoController {

    private UserDao userDao;

    public TenmoController(UserDao _userDao) {
        this.userDao = _userDao;

    }

    @RequestMapping (path = "/{username}", method = RequestMethod.GET)

    public double getBalance(Principal principal){
       int user = userDao.findIdByUsername(principal.getName());



        if (user == 0) {
            throw new UsernameNotFoundException("Username Not Found");
        } else {
            return user;
        }

    }

    @RequestMapping(path = "/transfer", method = RequestMethod.POST)

    public User transferMoney (Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());

        return null;
    }


}
