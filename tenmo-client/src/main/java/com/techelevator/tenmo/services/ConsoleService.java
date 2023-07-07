package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printBalance(double balance){
        System.out.println(balance);
    }

    public double sendMoney(){
        double enterMoney;
        System.out.println("Please enter an amount to send: ");
         enterMoney = scanner.nextDouble();
          scanner.nextLine();
          return enterMoney;
    }

    public double requestMoney(){
        double requestMoney;
        System.out.println("Please enter an amount to request: ");
        requestMoney = scanner.nextDouble();
        scanner.nextLine();
        return requestMoney;
    }

    public int AskForUserId(){
        System.out.println("Enter user id: ");
        int askForId;
        askForId = scanner.nextInt();
        scanner.nextLine();
        return askForId;
    }

    public int askForTransferId(){
        int askForTransferId;
        System.out.println("Enter Transfer id: ");
        askForTransferId = scanner.nextInt();
        scanner.nextLine();
        return askForTransferId;
    }

    public void printUsers(User[] users){
        for(User user : users){
            System.out.println(user.getId() + " " + " " + user.getUsername());
        }
    }

    public void printTransferHistory(Transfer[] transfers){
        for(Transfer transfer : transfers){
            System.out.println("TRANSFER ID: "+transfer.getTransferId() + "  FROM: "+ transfer.getUserFrom() + " TO: " + transfer.getUserTo() + " " +
                    "  AMOUNT: " + transfer.getAmount());
        }

    }

    public void printHistoryId(Transfer transfer){

        if (transfer.getTransferId() != 0){
            System.out.println("TRANSFER ID: "+transfer.getTransferId() + "  FROM: "+ transfer.getUserFrom() + "  TO: " + transfer.getUserTo() + " TYPE: " +
                    transfer.getTransferTypeId() + " STATUS: " + transfer.getTransferStatusId() + "  AMOUNT: " + transfer.getAmount());
        }


    }








}
