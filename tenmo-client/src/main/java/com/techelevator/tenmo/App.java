package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TenmoService;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    public static final int EXIT_APP = 0;
    public static final int VIEW_CURRENT_BALANCE = 1;
    public static final int VIEW_PAST_TRANSFERS = 2;
    public static final int VIEW_PENDING_REQUESTS = 3;
    public static final int SEND_TE_BUCKS = 4;
    public static final int REQUEST_TE_BUCKS = 5;
    public static final int REGISTER_USER = 1;
    public static final int LOGIN_USER = 2;


    TenmoService tenmoService = new TenmoService();
    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);


    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            // TODO: Instantiate services that require the current user to exist here

            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != EXIT_APP && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == REGISTER_USER) {
                handleRegister();
            } else if (menuSelection == LOGIN_USER) {
                handleLogin();
            } else if (menuSelection != EXIT_APP) {
                consoleService.printMessage("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        consoleService.printMessage("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            consoleService.printMessage("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        tenmoService.setAuthToken(currentUser.getToken());
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != EXIT_APP) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == VIEW_CURRENT_BALANCE) {
                viewCurrentBalance();
            } else if (menuSelection == VIEW_PAST_TRANSFERS) {
                viewTransferHistory();
            } else if (menuSelection == VIEW_PENDING_REQUESTS) {
                viewPendingRequests();
            } else if (menuSelection == SEND_TE_BUCKS) {
                sendBucks();
            } else if (menuSelection == REQUEST_TE_BUCKS) {
                requestBucks();
            } else if (menuSelection == EXIT_APP) {
                continue;
            } else {
                consoleService.printMessage("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {

       double balance = tenmoService.getBalance();
        consoleService.printBalance(balance);

	}

	private void viewTransferHistory() {
		// TODO Auto-generated method stub

        Transfer[] transferList = tenmoService.getTransferHistory();
        consoleService.printTransferHistory(transferList);


        Transfer transfer = tenmoService.getTransferHistoryById(consoleService.askForTransferId());

        consoleService.printHistoryId(transfer);

	}

	private void viewPendingRequests() {
		// TODO make method and then continue on client side
		
	}

	private void sendBucks() {
		// TODO Auto-generated method stub
        Transfer makeTransfer = new Transfer();
       User[] usArray = tenmoService.getListOfUsers();
        consoleService.printUsers(usArray);
        makeTransfer.setUserTo( consoleService.AskForUserId());

        makeTransfer.setAmount(consoleService.sendMoney());
        makeTransfer.setTransferStatusId(2);
        makeTransfer.setTransferTypeId(2);
        Transfer moneyTransfer = tenmoService.makeTransfer(makeTransfer);

	}

	private void requestBucks() {
		// TODO Auto-generated method stub
        Transfer makeTransfer = new Transfer();
        User[] usArray = tenmoService.getListOfUsers();
        consoleService.printUsers(usArray);
        makeTransfer.setUserFrom(consoleService.AskForUserId());


        makeTransfer.setAmount(consoleService.requestMoney());
        makeTransfer.setTransferStatusId(1);
        makeTransfer.setTransferTypeId(1);
        Transfer moneyTransfer = tenmoService.makeTransfer(makeTransfer);

	}

}
