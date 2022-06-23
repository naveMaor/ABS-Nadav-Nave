package util;

import com.google.gson.Gson;

public class Constants {

    // fxml locations
    public final static String MAIN_PAGE_FXML_RESOURCE_LOCATION = "/chat/client/component/main/chat-app-main.fxml";
    public final static String LOGIN_PAGE_FXML_RESOURCE_LOCATION = "/chat/client/component/login/login.fxml";
    public final static String CHAT_ROOM_FXML_RESOURCE_LOCATION = "/chat/client/component/chatroom/chat-room-main.fxml";

    // Server resources locations
    public final static String BASE_DOMAIN = "localhost";
    private final static String BASE_URL = "http://" + BASE_DOMAIN + ":8080";
    private final static String CONTEXT_PATH = "/absWebApp";
    private final static String FULL_SERVER_PATH = BASE_URL + CONTEXT_PATH;

    public final static String LOGIN_PAGE = FULL_SERVER_PATH + "/loginShortResponse";
    public final static String LOANS_AS_LENDER = FULL_SERVER_PATH + "/LoansAsLender";
    public final static String LOANS_AS_BORROW = FULL_SERVER_PATH + "/LoansAsBorrow";
    public final static String CREATE_TRANSACTION = FULL_SERVER_PATH + "/CreateTransaction";
    public final static String GET_TRANSACTION_LIST = FULL_SERVER_PATH + "/TransactionList";
    public final static String NEW_LOAN_FROM_FILE = FULL_SERVER_PATH + "/CreateNewLoan";
    public final static String SCRAMBLE_LOANS = FULL_SERVER_PATH + "/ScrambleLoans";
    public final static String GET_CLIENT = FULL_SERVER_PATH + "/Client";
    public final static String GET_ALL_LOAN_LIST = FULL_SERVER_PATH + "/AllLoanList";

    ///client scramble
    public final static String RELEVANT_LOANS = FULL_SERVER_PATH + "/relevantLoans";

    public final static String USERS_LIST = FULL_SERVER_PATH + "/userslist";
    public final static String LOGOUT = FULL_SERVER_PATH + "/chat/logout";
    public final static String SEND_CHAT_LINE = FULL_SERVER_PATH + "/pages/chatroom/sendChat";
    public final static String CHAT_LINES_LIST = FULL_SERVER_PATH + "/chat";

    // GSON instance
    public final static Gson GSON_INSTANCE = new Gson();
}
