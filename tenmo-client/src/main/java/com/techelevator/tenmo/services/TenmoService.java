package com.techelevator.tenmo.services;

import com.techelevator.tenmo.App;
import com.techelevator.tenmo.model.User;
import org.springframework.web.client.RestTemplate;

public class TenmoService {

    public static final String API_BASE_URL = "http://localhost:8080/tenmo/";

    private RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

  //  public App App (){
  //  }

    //public App app( API_BASE_URL, String currentUser){
      //  this
    //}





    public User viewAccountBalance (double amount, int userId){

        User user = new User();







        return null;

    }



}
