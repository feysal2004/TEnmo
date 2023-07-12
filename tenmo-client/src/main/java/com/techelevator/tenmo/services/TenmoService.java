package com.techelevator.tenmo.services;

import com.techelevator.tenmo.App;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import com.techelevator.util.BasicLoggerException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TenmoService {

    private static final String API_BASE_URL = "http://localhost:8080";

    private RestTemplate restTemplate = new RestTemplate();

    private String authToken = null; //ask about this

    public void setAuthToken(String authToken) { //ask about this too
        this.authToken = authToken;
    }

    public double getBalance(){
        double doubleBalance = 0.0;
        try{

            ResponseEntity<Double> response = restTemplate.exchange(API_BASE_URL + "/balance", HttpMethod.GET, makeAuthEntity(), Double.class);
            doubleBalance = response.getBody();
        }catch (BasicLoggerException e){
            BasicLogger.log("Balance Unavailable");
        }
        return doubleBalance;
    }

    @ResponseStatus(HttpStatus.CREATED)
    public Transfer makeTransfer(Transfer makeTransfer){

        try{

            ResponseEntity<Transfer> response = restTemplate.exchange(API_BASE_URL + "/transfer", HttpMethod.POST, transferHttpEntity(makeTransfer), Transfer.class);
            response.getBody();
           BasicLogger.log("Transfer Successful.");
        }catch (BasicLoggerException e){
            BasicLogger.log("Transfer unsuccessful");
        }
        return makeTransfer;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transfer[] getTransferHistory(){
        Transfer[] transfersHistory = null;
        Transfer historyOfTransfer = new Transfer();

        try{

            ResponseEntity<Transfer[]> response = restTemplate.exchange(API_BASE_URL + "/transferHistory", HttpMethod.GET, transferHttpEntity(historyOfTransfer), Transfer[].class);
            transfersHistory = response.getBody();
            BasicLogger.log("Here is history");
        } catch (BasicLoggerException e){
            BasicLogger.log("No history available. Try again.");
        }
        return transfersHistory;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transfer getTransferHistoryById(int id){
       Transfer transfer =null;

        try {
            ResponseEntity<Transfer> response = restTemplate.exchange(API_BASE_URL + "/transferHistory/" + id, HttpMethod.GET, makeAuthEntity(), Transfer.class);
            transfer = response.getBody();
            BasicLogger.log("here you go");
        } catch (BasicLoggerException e){
            BasicLogger.log("Invalid id.");
        }
        return transfer;
    }



    public User[] getListOfUsers(){
        User[] users = null;

        try{
            ResponseEntity<User[]> response = restTemplate.exchange(API_BASE_URL + "/users", HttpMethod.GET, makeAuthEntity(), User[].class);
            users = response.getBody();
        } catch (BasicLoggerException e){
            BasicLogger.log("No users found");
        }
        return users;
    }



    private HttpEntity<User> makeUserEntity(User user){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(user, headers);
    }


    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

    private HttpEntity<Transfer>transferHttpEntity(Transfer makeTransfer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(makeTransfer, headers);
    }




}
