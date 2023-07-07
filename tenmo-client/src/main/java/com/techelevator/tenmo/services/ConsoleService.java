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
        System.out.println("Your current account balance is: $" + balance);
    }

    public double sendMoney(){
        double enterMoney;
        System.out.println("--------------");
        System.out.println("Please enter an amount to send: ");
        enterMoney = scanner.nextDouble();
        scanner.nextLine();
        return enterMoney;
    }

    public double requestMoney(){
        double requestMoney;
        System.out.println("--------------");
        System.out.println("Please enter an amount to request: ");
        requestMoney = scanner.nextDouble();
        scanner.nextLine();
        return requestMoney;
    }

    public int AskForUserId(){
        System.out.println("--------------");
        System.out.println("Enter user id: ");
        int askForId;
        askForId = scanner.nextInt();
        scanner.nextLine();
        return askForId;
    }

    public int askForTransferId(){
        int askForTransferId;
        System.out.println("--------------");
        System.out.println("Enter Transfer id: ");
        askForTransferId = scanner.nextInt();
        scanner.nextLine();
        return askForTransferId;
    }

    public void printUsers(User[] users){
        for(User user : users){
            System.out.println("--------------------------------------------");
            System.out.format("%7s %10s", "UserID", "Name\n");

            System.out.println("--------------------------------------------");
            System.out.format("%7s %10s", user.getId(), user.getUsername() + "\n");

            System.out.println("--------------------------------------------");

        }
    }

    public void printTransferHistory(Transfer[] transfers){
        System.out.println("--------------------------------------------");
        System.out.format("%7s %15s %16s","Transfers ID",  "From/To", "Amount\n");
        System.out.println("--------------------------------------------");
        for(Transfer transfer : transfers){
            System.out.format("%7s %15s %16s ",transfer.getTransferId(), "FROM: "+ transfer.getUserFromName(), "$" + transfer.getAmount() + "\n");

            System.out.format("%7s %15s %16s ",transfer.getTransferId(), "TO: "+ transfer.getUserToName(), "$" + transfer.getAmount() + "\n\n");
        }


    }

    public void printHistoryId(Transfer transfer){

        System.out.println("--------------------------------------------");
        System.out.format("Transfer Details\n");
        System.out.println("--------------------------------------------");

        if (transfer.getTransferId() != 0){

            System.out.println("Id: " + transfer.getTransferId());
            System.out.println("From: " + transfer.getUserFromName());
            System.out.println("To: " + transfer.getUserToName());
            System.out.println("Type: " + transfer.getTransferTypeDescription());
            System.out.println("Status: " + transfer.getTransferStatusDescription());
            System.out.println("Amount: " + transfer.getAmount());
        }


    }








}
