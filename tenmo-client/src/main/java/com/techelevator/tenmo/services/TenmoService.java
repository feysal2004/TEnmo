package com.techelevator.tenmo.services;

import com.techelevator.tenmo.App;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import com.techelevator.util.BasicLoggerException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class TenmoService {

    private static final String API_BASE_URL = "http://localhost:8080";

//    private String apiBaseUrl;
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




}
